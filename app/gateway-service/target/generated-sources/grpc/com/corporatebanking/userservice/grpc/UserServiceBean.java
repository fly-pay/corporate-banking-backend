package com.corporatebanking.userservice.grpc;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: user_service.proto")
public class UserServiceBean extends MutinyUserServiceGrpc.UserServiceImplBase implements BindableService, MutinyBean {

    private final UserService delegate;

    UserServiceBean(@GrpcService UserService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> createUser(com.corporatebanking.userservice.grpc.CreateUserRequest request) {
        try {
            return delegate.createUser(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> getUser(com.corporatebanking.userservice.grpc.GetUserRequest request) {
        try {
            return delegate.getUser(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> updateUser(com.corporatebanking.userservice.grpc.UpdateUserRequest request) {
        try {
            return delegate.updateUser(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.DeleteUserResponse> deleteUser(com.corporatebanking.userservice.grpc.DeleteUserRequest request) {
        try {
            return delegate.deleteUser(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.ListUsersResponse> listUsers(com.corporatebanking.userservice.grpc.ListUsersRequest request) {
        try {
            return delegate.listUsers(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
