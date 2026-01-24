package com.corporatebanking.userservice.grpc;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: user_service.proto")
public interface UserService extends MutinyService {

    io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> createUser(com.corporatebanking.userservice.grpc.CreateUserRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> getUser(com.corporatebanking.userservice.grpc.GetUserRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> updateUser(com.corporatebanking.userservice.grpc.UpdateUserRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.DeleteUserResponse> deleteUser(com.corporatebanking.userservice.grpc.DeleteUserRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.ListUsersResponse> listUsers(com.corporatebanking.userservice.grpc.ListUsersRequest request);
}
