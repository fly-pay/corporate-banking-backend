package com.corporatebanking.authorizationservice.grpc;

import static com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getServiceDescriptor;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: authorization_service.proto")
public final class MutinyAuthorizationServiceGrpc implements io.quarkus.grpc.MutinyGrpc {

    private MutinyAuthorizationServiceGrpc() {
    }

    public static MutinyAuthorizationServiceStub newMutinyStub(io.grpc.Channel channel) {
        return new MutinyAuthorizationServiceStub(channel);
    }

    /**
     * <pre>
     *  Authorization Service
     * </pre>
     */
    public static class MutinyAuthorizationServiceStub extends io.grpc.stub.AbstractStub<MutinyAuthorizationServiceStub> implements io.quarkus.grpc.MutinyStub {

        private AuthorizationServiceGrpc.AuthorizationServiceStub delegateStub;

        private MutinyAuthorizationServiceStub(io.grpc.Channel channel) {
            super(channel);
            delegateStub = AuthorizationServiceGrpc.newStub(channel);
        }

        private MutinyAuthorizationServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
            delegateStub = AuthorizationServiceGrpc.newStub(channel).build(channel, callOptions);
        }

        @Override
        protected MutinyAuthorizationServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new MutinyAuthorizationServiceStub(channel, callOptions);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::signup);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::authenticate);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::validateToken);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::refreshToken);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::getUserInfo);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.LogoutResponse> logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::logout);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.PermissionResponse> checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request) {
            return io.quarkus.grpc.stubs.ClientCalls.oneToOne(request, delegateStub::checkPermission);
        }
    }

    /**
     * <pre>
     *  Authorization Service
     * </pre>
     */
    public static abstract class AuthorizationServiceImplBase implements io.grpc.BindableService {

        private String compression;

        /**
         * Set whether the server will try to use a compressed response.
         *
         * @param compression the compression, e.g {@code gzip}
         */
        public AuthorizationServiceImplBase withCompression(String compression) {
            this.compression = compression;
            return this;
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.LogoutResponse> logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.PermissionResponse> checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getSignupMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.SignupRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>(this, METHODID_SIGNUP, compression))).addMethod(com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getAuthenticateMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.AuthenticateRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>(this, METHODID_AUTHENTICATE, compression))).addMethod(com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getValidateTokenMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenValidationResponse>(this, METHODID_VALIDATE_TOKEN, compression))).addMethod(com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getRefreshTokenMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>(this, METHODID_REFRESH_TOKEN, compression))).addMethod(com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getGetUserInfoMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest, com.corporatebanking.authorizationservice.grpc.UserInfoResponse>(this, METHODID_GET_USER_INFO, compression))).addMethod(com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getLogoutMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.LogoutRequest, com.corporatebanking.authorizationservice.grpc.LogoutResponse>(this, METHODID_LOGOUT, compression))).addMethod(com.corporatebanking.authorizationservice.grpc.AuthorizationServiceGrpc.getCheckPermissionMethod(), asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest, com.corporatebanking.authorizationservice.grpc.PermissionResponse>(this, METHODID_CHECK_PERMISSION, compression))).build();
        }
    }

    private static final int METHODID_SIGNUP = 0;

    private static final int METHODID_AUTHENTICATE = 1;

    private static final int METHODID_VALIDATE_TOKEN = 2;

    private static final int METHODID_REFRESH_TOKEN = 3;

    private static final int METHODID_GET_USER_INFO = 4;

    private static final int METHODID_LOGOUT = 5;

    private static final int METHODID_CHECK_PERMISSION = 6;

    private static final class MethodHandlers<Req, Resp> implements io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>, io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>, io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {

        private final AuthorizationServiceImplBase serviceImpl;

        private final int methodId;

        private final String compression;

        MethodHandlers(AuthorizationServiceImplBase serviceImpl, int methodId, String compression) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
            this.compression = compression;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_SIGNUP:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.authorizationservice.grpc.SignupRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse>) responseObserver, compression, serviceImpl::signup);
                    break;
                case METHODID_AUTHENTICATE:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.authorizationservice.grpc.AuthenticateRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse>) responseObserver, compression, serviceImpl::authenticate);
                    break;
                case METHODID_VALIDATE_TOKEN:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse>) responseObserver, compression, serviceImpl::validateToken);
                    break;
                case METHODID_REFRESH_TOKEN:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse>) responseObserver, compression, serviceImpl::refreshToken);
                    break;
                case METHODID_GET_USER_INFO:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.UserInfoResponse>) responseObserver, compression, serviceImpl::getUserInfo);
                    break;
                case METHODID_LOGOUT:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.authorizationservice.grpc.LogoutRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.LogoutResponse>) responseObserver, compression, serviceImpl::logout);
                    break;
                case METHODID_CHECK_PERMISSION:
                    io.quarkus.grpc.stubs.ServerCalls.oneToOne((com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.PermissionResponse>) responseObserver, compression, serviceImpl::checkPermission);
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
