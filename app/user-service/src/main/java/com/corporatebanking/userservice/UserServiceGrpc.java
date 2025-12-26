package com.corporatebanking.userservice;

import com.corporatebanking.userservice.grpc.UserService;
import com.corporatebanking.userservice.grpc.CreateUserRequest;
import com.corporatebanking.userservice.grpc.GetUserRequest;
import com.corporatebanking.userservice.grpc.UpdateUserRequest;
import com.corporatebanking.userservice.grpc.DeleteUserRequest;
import com.corporatebanking.userservice.grpc.ListUsersRequest;
import com.corporatebanking.userservice.grpc.UserResponse;
import com.corporatebanking.userservice.grpc.DeleteUserResponse;
import com.corporatebanking.userservice.grpc.ListUsersResponse;
import com.corporatebanking.userservice.entity.UserEntity;
import com.corporatebanking.userservice.repository.UserRepository;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GrpcService
@Singleton
public class UserServiceGrpc implements UserService {

    private static final Logger LOG = Logger.getLogger(UserServiceGrpc.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Inject
    UserRepository userRepository;

    @Override
    public Uni<UserResponse> createUser(CreateUserRequest request) {
        return Uni.createFrom().item(() -> {
            try {
                LOG.infof("Creating user with email: %s", request.getEmail());
                
                UserEntity existingUser = userRepository.findByEmail(request.getEmail());
                if (existingUser != null) {
                    throw new RuntimeException("User with email already exists: " + request.getEmail());
                }
                
                String userId = "user-" + UUID.randomUUID().toString();
                UserEntity userEntity = new UserEntity();
                userEntity.userId = userId;
                userEntity.email = request.getEmail();
                userEntity.firstName = request.getFirstName();
                userEntity.lastName = request.getLastName();
                userEntity.phoneNumber = request.getPhoneNumber();
                userEntity.role = request.getRole();
                
                userRepository.persist(userEntity);
                
                LOG.infof("User created successfully with ID: %s", userId);
                return toUserResponse(userEntity);
            } catch (Exception e) {
                LOG.errorf(e, "Error creating user");
                throw new RuntimeException("Failed to create user: " + e.getMessage(), e);
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @Override
    public Uni<UserResponse> getUser(GetUserRequest request) {
        return Uni.createFrom().item(() -> {
            try {
                LOG.infof("Getting user with ID: %s", request.getUserId());
                
                UserEntity userEntity = userRepository.findByUserId(request.getUserId());
                if (userEntity == null) {
                    LOG.warnf("User not found with ID: %s", request.getUserId());
                    throw new RuntimeException("User not found with ID: " + request.getUserId());
                }
                
                return toUserResponse(userEntity);
            } catch (Exception e) {
                LOG.errorf(e, "Error getting user");
                throw new RuntimeException("Failed to get user: " + e.getMessage(), e);
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @Override
    public Uni<UserResponse> updateUser(UpdateUserRequest request) {
        return Uni.createFrom().item(() -> {
            try {
                LOG.infof("Updating user with ID: %s", request.getUserId());
                
                UserEntity userEntity = userRepository.findByUserId(request.getUserId());
                if (userEntity == null) {
                    LOG.warnf("User not found with ID: %s", request.getUserId());
                    throw new RuntimeException("User not found with ID: " + request.getUserId());
                }
                
                if (!request.getEmail().isEmpty()) {
                    UserEntity existingEmail = userRepository.findByEmail(request.getEmail());
                    if (existingEmail != null && !existingEmail.userId.equals(request.getUserId())) {
                        throw new RuntimeException("Email already in use: " + request.getEmail());
                    }
                    userEntity.email = request.getEmail();
                }
                if (!request.getFirstName().isEmpty()) {
                    userEntity.firstName = request.getFirstName();
                }
                if (!request.getLastName().isEmpty()) {
                    userEntity.lastName = request.getLastName();
                }
                if (!request.getPhoneNumber().isEmpty()) {
                    userEntity.phoneNumber = request.getPhoneNumber();
                }
                if (!request.getRole().isEmpty()) {
                    userEntity.role = request.getRole();
                }
                
                userRepository.persist(userEntity);
                
                LOG.infof("User updated successfully with ID: %s", request.getUserId());
                return toUserResponse(userEntity);
            } catch (Exception e) {
                LOG.errorf(e, "Error updating user");
                throw new RuntimeException("Failed to update user: " + e.getMessage(), e);
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @Override
    public Uni<DeleteUserResponse> deleteUser(DeleteUserRequest request) {
        return Uni.createFrom().item(() -> {
            try {
                LOG.infof("Deleting user with ID: %s", request.getUserId());
                
                UserEntity userEntity = userRepository.findByUserId(request.getUserId());
                if (userEntity == null) {
                    LOG.warnf("User not found with ID: %s", request.getUserId());
                    return DeleteUserResponse.newBuilder()
                            .setSuccess(false)
                            .setMessage("User not found with ID: " + request.getUserId())
                            .build();
                }
                
                userRepository.delete(userEntity);
                
                LOG.infof("User deleted successfully with ID: %s", request.getUserId());
                return DeleteUserResponse.newBuilder()
                        .setSuccess(true)
                        .setMessage("User deleted successfully")
                        .build();
            } catch (Exception e) {
                LOG.errorf(e, "Error deleting user");
                return DeleteUserResponse.newBuilder()
                        .setSuccess(false)
                        .setMessage("Failed to delete user: " + e.getMessage())
                        .build();
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    @Override
    public Uni<ListUsersResponse> listUsers(ListUsersRequest request) {
        return Uni.createFrom().item(() -> {
            try {
                LOG.infof("Listing users - page: %d, pageSize: %d", request.getPage(), request.getPageSize());
                
                int page = request.getPage() > 0 ? request.getPage() : 1;
                int pageSize = request.getPageSize() > 0 ? request.getPageSize() : 10;
                
                long totalCount = userRepository.count();
                int offset = (page - 1) * pageSize;
                
                List<UserEntity> userEntities = userRepository.findAll()
                        .page(offset / pageSize, pageSize)
                        .list();
                
                List<UserResponse> userResponses = userEntities.stream()
                        .map(this::toUserResponse)
                        .collect(Collectors.toList());
                
                return ListUsersResponse.newBuilder()
                        .addAllUsers(userResponses)
                        .setTotalCount((int) totalCount)
                        .setPage(page)
                        .setPageSize(pageSize)
                        .build();
            } catch (Exception e) {
                LOG.errorf(e, "Error listing users");
                throw new RuntimeException("Failed to list users: " + e.getMessage(), e);
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }

    private UserResponse toUserResponse(UserEntity entity) {
        return UserResponse.newBuilder()
                .setUserId(entity.userId)
                .setEmail(entity.email)
                .setFirstName(entity.firstName != null ? entity.firstName : "")
                .setLastName(entity.lastName != null ? entity.lastName : "")
                .setPhoneNumber(entity.phoneNumber != null ? entity.phoneNumber : "")
                .setRole(entity.role != null ? entity.role : "")
                .setCreatedAt(entity.createdAt != null ? entity.createdAt.format(formatter) : "")
                .setUpdatedAt(entity.updatedAt != null ? entity.updatedAt.format(formatter) : "")
                .build();
    }
}
