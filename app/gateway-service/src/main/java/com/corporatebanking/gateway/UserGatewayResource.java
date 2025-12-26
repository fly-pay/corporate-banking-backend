package com.corporatebanking.gateway;

import com.corporatebanking.userservice.grpc.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Path("/api/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserGatewayResource {

    private static final Logger LOG = Logger.getLogger(UserGatewayResource.class);

    @GrpcClient("user-service")
    UserService userService;

    @POST
    public Response createUser(CreateUserRequestDTO request) {
        try {
            LOG.infof("Creating user via gateway: %s", request.email);
            
            CreateUserRequest grpcRequest = CreateUserRequest.newBuilder()
                    .setEmail(request.email)
                    .setFirstName(request.firstName)
                    .setLastName(request.lastName)
                    .setPhoneNumber(request.phoneNumber)
                    .setRole(request.role)
                    .build();

            UserResponse response = userService.createUser(grpcRequest).await().indefinitely();
            return Response.ok(toDTO(response)).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            LOG.errorf(e, "Error creating user");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    @Path("/{userId}")
    public Response getUser(@PathParam("userId") String userId) {
        try {
            LOG.infof("Getting user via gateway: %s", userId);
            
            GetUserRequest request = GetUserRequest.newBuilder()
                    .setUserId(userId)
                    .build();

            UserResponse response = userService.getUser(request).await().indefinitely();
            return Response.ok(toDTO(response)).build();
        } catch (Exception e) {
            LOG.errorf(e, "Error getting user: %s", userId);
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @PUT
    @Path("/{userId}")
    public Response updateUser(@PathParam("userId") String userId, UpdateUserRequestDTO request) {
        try {
            LOG.infof("Updating user via gateway: %s", userId);
            
            UpdateUserRequest grpcRequest = UpdateUserRequest.newBuilder()
                    .setUserId(userId)
                    .setEmail(request.email != null ? request.email : "")
                    .setFirstName(request.firstName != null ? request.firstName : "")
                    .setLastName(request.lastName != null ? request.lastName : "")
                    .setPhoneNumber(request.phoneNumber != null ? request.phoneNumber : "")
                    .setRole(request.role != null ? request.role : "")
                    .build();

            UserResponse response = userService.updateUser(grpcRequest).await().indefinitely();
            return Response.ok(toDTO(response)).build();
        } catch (Exception e) {
            LOG.errorf(e, "Error updating user: %s", userId);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @DELETE
    @Path("/{userId}")
    public Response deleteUser(@PathParam("userId") String userId) {
        try {
            LOG.infof("Deleting user via gateway: %s", userId);
            
            DeleteUserRequest request = DeleteUserRequest.newBuilder()
                    .setUserId(userId)
                    .build();

            DeleteUserResponse response = userService.deleteUser(request).await().indefinitely();
            if (response.getSuccess()) {
                return Response.ok(new SuccessResponse(response.getMessage())).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse(response.getMessage())).build();
            }
        } catch (Exception e) {
            LOG.errorf(e, "Error deleting user: %s", userId);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    public Response listUsers(@QueryParam("page") @DefaultValue("1") int page,
                              @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        try {
            LOG.infof("Listing users via gateway - page: %d, pageSize: %d", page, pageSize);
            
            ListUsersRequest request = ListUsersRequest.newBuilder()
                    .setPage(page)
                    .setPageSize(pageSize)
                    .build();

            ListUsersResponse response = userService.listUsers(request).await().indefinitely();
            ListUsersResponseDTO dto = new ListUsersResponseDTO();
            dto.users = response.getUsersList().stream()
                    .map(this::toDTO)
                    .collect(Collectors.toList());
            dto.totalCount = response.getTotalCount();
            dto.page = response.getPage();
            dto.pageSize = response.getPageSize();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Error listing users");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    private UserResponseDTO toDTO(UserResponse grpc) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.userId = grpc.getUserId();
        dto.email = grpc.getEmail();
        dto.firstName = grpc.getFirstName();
        dto.lastName = grpc.getLastName();
        dto.phoneNumber = grpc.getPhoneNumber();
        dto.role = grpc.getRole();
        dto.createdAt = grpc.getCreatedAt();
        dto.updatedAt = grpc.getUpdatedAt();
        return dto;
    }

    public static class CreateUserRequestDTO {
        public String email;
        public String firstName;
        public String lastName;
        public String phoneNumber;
        public String role;
    }

    public static class UpdateUserRequestDTO {
        public String email;
        public String firstName;
        public String lastName;
        public String phoneNumber;
        public String role;
    }

    public static class UserResponseDTO {
        public String userId;
        public String email;
        public String firstName;
        public String lastName;
        public String phoneNumber;
        public String role;
        public String createdAt;
        public String updatedAt;
    }

    public static class ListUsersResponseDTO {
        public List<UserResponseDTO> users;
        public int totalCount;
        public int page;
        public int pageSize;
    }

    public static class SuccessResponse {
        public String message;
        public boolean success = true;

        public SuccessResponse(String message) {
            this.message = message;
        }
    }

    public static class ErrorResponse {
        public String message;
        public boolean success = false;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }
}

