package com.corporatebanking.userservice.grpc;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: user_service.proto")
public class UserServiceClient implements UserService, MutinyClient<MutinyUserServiceGrpc.MutinyUserServiceStub> {

    private final MutinyUserServiceGrpc.MutinyUserServiceStub stub;

    public UserServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyUserServiceGrpc.MutinyUserServiceStub, MutinyUserServiceGrpc.MutinyUserServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyUserServiceGrpc.newMutinyStub(channel));
    }

    private UserServiceClient(MutinyUserServiceGrpc.MutinyUserServiceStub stub) {
        this.stub = stub;
    }

    public UserServiceClient newInstanceWithStub(MutinyUserServiceGrpc.MutinyUserServiceStub stub) {
        return new UserServiceClient(stub);
    }

    @Override
    public MutinyUserServiceGrpc.MutinyUserServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> createUser(com.corporatebanking.userservice.grpc.CreateUserRequest request) {
        return stub.createUser(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> getUser(com.corporatebanking.userservice.grpc.GetUserRequest request) {
        return stub.getUser(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> updateUser(com.corporatebanking.userservice.grpc.UpdateUserRequest request) {
        return stub.updateUser(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.DeleteUserResponse> deleteUser(com.corporatebanking.userservice.grpc.DeleteUserRequest request) {
        return stub.deleteUser(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.ListUsersResponse> listUsers(com.corporatebanking.userservice.grpc.ListUsersRequest request) {
        return stub.listUsers(request);
    }
}
