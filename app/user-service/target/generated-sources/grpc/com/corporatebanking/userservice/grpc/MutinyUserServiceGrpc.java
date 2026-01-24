package com.corporatebanking.userservice.grpc;

import static com.corporatebanking.userservice.grpc.UserServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: user_service.proto")
public final class MutinyUserServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyUserServiceGrpc() {
    }

    public static MutinyUserServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyUserServiceStub(channel);
    }

    /**
     * <pre>
     *  User Service
     * </pre>
     */
    public static class MutinyUserServiceStub extends io.grpc.stub.AbstractStub<MutinyUserServiceStub> implements io.quarkus.grpc.MutinyStub {

        private UserServiceGrpc.UserServiceStub delegateStub;

        private MutinyUserServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = UserServiceGrpc.newStub(channel);
        }

        private MutinyUserServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = UserServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyUserServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyUserServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> createUser(com.corporatebanking.userservice.grpc.CreateUserRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::createUser);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> getUser(com.corporatebanking.userservice.grpc.GetUserRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getUser);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> updateUser(com.corporatebanking.userservice.grpc.UpdateUserRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::updateUser);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.DeleteUserResponse> deleteUser(com.corporatebanking.userservice.grpc.DeleteUserRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::deleteUser);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.ListUsersResponse> listUsers(com.corporatebanking.userservice.grpc.ListUsersRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::listUsers);
        }
    }

    /**
     * <pre>
     *  User Service
     * </pre>
     */
    public static abstract class UserServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public UserServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> createUser(com.corporatebanking.userservice.grpc.CreateUserRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> getUser(com.corporatebanking.userservice.grpc.GetUserRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.UserResponse> updateUser(com.corporatebanking.userservice.grpc.UpdateUserRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.DeleteUserResponse> deleteUser(com.corporatebanking.userservice.grpc.DeleteUserRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.userservice.grpc.ListUsersResponse> listUsers(com.corporatebanking.userservice.grpc.ListUsersRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(com.corporatebanking.userservice.grpc.UserServiceGrpc.getCreateUserMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.userservice.grpc.CreateUserRequest, com.corporatebanking.userservice.grpc.UserResponse>(this, METHODID_CREATE_USER, compression))).addMethod(com.corporatebanking.userservice.grpc.UserServiceGrpc.getGetUserMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.userservice.grpc.GetUserRequest, com.corporatebanking.userservice.grpc.UserResponse>(this, METHODID_GET_USER, compression))).addMethod(com.corporatebanking.userservice.grpc.UserServiceGrpc.getUpdateUserMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.userservice.grpc.UpdateUserRequest, com.corporatebanking.userservice.grpc.UserResponse>(this, METHODID_UPDATE_USER, compression))).addMethod(com.corporatebanking.userservice.grpc.UserServiceGrpc.getDeleteUserMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.userservice.grpc.DeleteUserRequest, com.corporatebanking.userservice.grpc.DeleteUserResponse>(this, METHODID_DELETE_USER, compression))).addMethod(com.corporatebanking.userservice.grpc.UserServiceGrpc.getListUsersMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.userservice.grpc.ListUsersRequest, com.corporatebanking.userservice.grpc.ListUsersResponse>(this, METHODID_LIST_USERS, compression))).build();
        }
    }

    private static final int METHODID_CREATE_USER = 0;

    private static final int METHODID_GET_USER = 1;

    private static final int METHODID_UPDATE_USER = 2;

    private static final int METHODID_DELETE_USER = 3;

    private static final int METHODID_LIST_USERS = 4;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final UserServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(UserServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_CREATE_USER:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.userservice.grpc.CreateUserRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.userservice.grpc.UserResponse>) responseObserver, compression, serviceImpl::createUser);
                    break;
                case METHODID_GET_USER:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.userservice.grpc.GetUserRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.userservice.grpc.UserResponse>) responseObserver, compression, serviceImpl::getUser);
                    break;
                case METHODID_UPDATE_USER:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.userservice.grpc.UpdateUserRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.userservice.grpc.UserResponse>) responseObserver, compression, serviceImpl::updateUser);
                    break;
                case METHODID_DELETE_USER:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.userservice.grpc.DeleteUserRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.userservice.grpc.DeleteUserResponse>) responseObserver, compression, serviceImpl::deleteUser);
                    break;
                case METHODID_LIST_USERS:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.userservice.grpc.ListUsersRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.userservice.grpc.ListUsersResponse>) responseObserver, compression, serviceImpl::listUsers);
                    break;
                default:
                    throw new java.lang.AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new java.lang.AssertionError();
            }
        }
    }
}
