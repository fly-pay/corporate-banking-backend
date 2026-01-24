package com.corporatebanking.authorizationservice.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * Authorization Service
 * </pre>
 */
@io.quarkus.Generated(value = "by gRPC proto compiler (version 1.59.0)", comments = "Source: authorization_service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthorizationServiceGrpc {

    private AuthorizationServiceGrpc() {
    }

    public static final java.lang.String SERVICE_NAME = "com.corporatebanking.authorizationservice.AuthorizationService";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.SignupRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getSignupMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "Signup", requestType = com.corporatebanking.authorizationservice.grpc.SignupRequest.class, responseType = com.corporatebanking.authorizationservice.grpc.TokenResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.SignupRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getSignupMethod() {
        io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.SignupRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getSignupMethod;
        if ((getSignupMethod = AuthorizationServiceGrpc.getSignupMethod) == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                if ((getSignupMethod = AuthorizationServiceGrpc.getSignupMethod) == null) {
                    AuthorizationServiceGrpc.getSignupMethod = getSignupMethod = io.grpc.MethodDescriptor.<com.corporatebanking.authorizationservice.grpc.SignupRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "Signup")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.SignupRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.TokenResponse.getDefaultInstance())).setSchemaDescriptor(new AuthorizationServiceMethodDescriptorSupplier("Signup")).build();
                }
            }
        }
        return getSignupMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.AuthenticateRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getAuthenticateMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "Authenticate", requestType = com.corporatebanking.authorizationservice.grpc.AuthenticateRequest.class, responseType = com.corporatebanking.authorizationservice.grpc.TokenResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.AuthenticateRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getAuthenticateMethod() {
        io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.AuthenticateRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getAuthenticateMethod;
        if ((getAuthenticateMethod = AuthorizationServiceGrpc.getAuthenticateMethod) == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                if ((getAuthenticateMethod = AuthorizationServiceGrpc.getAuthenticateMethod) == null) {
                    AuthorizationServiceGrpc.getAuthenticateMethod = getAuthenticateMethod = io.grpc.MethodDescriptor.<com.corporatebanking.authorizationservice.grpc.AuthenticateRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "Authenticate")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.TokenResponse.getDefaultInstance())).setSchemaDescriptor(new AuthorizationServiceMethodDescriptorSupplier("Authenticate")).build();
                }
            }
        }
        return getAuthenticateMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> getValidateTokenMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "ValidateToken", requestType = com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest.class, responseType = com.corporatebanking.authorizationservice.grpc.TokenValidationResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> getValidateTokenMethod() {
        io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> getValidateTokenMethod;
        if ((getValidateTokenMethod = AuthorizationServiceGrpc.getValidateTokenMethod) == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                if ((getValidateTokenMethod = AuthorizationServiceGrpc.getValidateTokenMethod) == null) {
                    AuthorizationServiceGrpc.getValidateTokenMethod = getValidateTokenMethod = io.grpc.MethodDescriptor.<com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenValidationResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "ValidateToken")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.TokenValidationResponse.getDefaultInstance())).setSchemaDescriptor(new AuthorizationServiceMethodDescriptorSupplier("ValidateToken")).build();
                }
            }
        }
        return getValidateTokenMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getRefreshTokenMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "RefreshToken", requestType = com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest.class, responseType = com.corporatebanking.authorizationservice.grpc.TokenResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getRefreshTokenMethod() {
        io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse> getRefreshTokenMethod;
        if ((getRefreshTokenMethod = AuthorizationServiceGrpc.getRefreshTokenMethod) == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                if ((getRefreshTokenMethod = AuthorizationServiceGrpc.getRefreshTokenMethod) == null) {
                    AuthorizationServiceGrpc.getRefreshTokenMethod = getRefreshTokenMethod = io.grpc.MethodDescriptor.<com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "RefreshToken")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.TokenResponse.getDefaultInstance())).setSchemaDescriptor(new AuthorizationServiceMethodDescriptorSupplier("RefreshToken")).build();
                }
            }
        }
        return getRefreshTokenMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest, com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getGetUserInfoMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "GetUserInfo", requestType = com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest.class, responseType = com.corporatebanking.authorizationservice.grpc.UserInfoResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest, com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getGetUserInfoMethod() {
        io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest, com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getGetUserInfoMethod;
        if ((getGetUserInfoMethod = AuthorizationServiceGrpc.getGetUserInfoMethod) == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                if ((getGetUserInfoMethod = AuthorizationServiceGrpc.getGetUserInfoMethod) == null) {
                    AuthorizationServiceGrpc.getGetUserInfoMethod = getGetUserInfoMethod = io.grpc.MethodDescriptor.<com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest, com.corporatebanking.authorizationservice.grpc.UserInfoResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserInfo")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.UserInfoResponse.getDefaultInstance())).setSchemaDescriptor(new AuthorizationServiceMethodDescriptorSupplier("GetUserInfo")).build();
                }
            }
        }
        return getGetUserInfoMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.LogoutRequest, com.corporatebanking.authorizationservice.grpc.LogoutResponse> getLogoutMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "Logout", requestType = com.corporatebanking.authorizationservice.grpc.LogoutRequest.class, responseType = com.corporatebanking.authorizationservice.grpc.LogoutResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.LogoutRequest, com.corporatebanking.authorizationservice.grpc.LogoutResponse> getLogoutMethod() {
        io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.LogoutRequest, com.corporatebanking.authorizationservice.grpc.LogoutResponse> getLogoutMethod;
        if ((getLogoutMethod = AuthorizationServiceGrpc.getLogoutMethod) == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                if ((getLogoutMethod = AuthorizationServiceGrpc.getLogoutMethod) == null) {
                    AuthorizationServiceGrpc.getLogoutMethod = getLogoutMethod = io.grpc.MethodDescriptor.<com.corporatebanking.authorizationservice.grpc.LogoutRequest, com.corporatebanking.authorizationservice.grpc.LogoutResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "Logout")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.LogoutRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.LogoutResponse.getDefaultInstance())).setSchemaDescriptor(new AuthorizationServiceMethodDescriptorSupplier("Logout")).build();
                }
            }
        }
        return getLogoutMethod;
    }

    private static volatile io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest, com.corporatebanking.authorizationservice.grpc.PermissionResponse> getCheckPermissionMethod;

    @io.grpc.stub.annotations.RpcMethod(fullMethodName = SERVICE_NAME + '/' + "CheckPermission", requestType = com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest.class, responseType = com.corporatebanking.authorizationservice.grpc.PermissionResponse.class, methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest, com.corporatebanking.authorizationservice.grpc.PermissionResponse> getCheckPermissionMethod() {
        io.grpc.MethodDescriptor<com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest, com.corporatebanking.authorizationservice.grpc.PermissionResponse> getCheckPermissionMethod;
        if ((getCheckPermissionMethod = AuthorizationServiceGrpc.getCheckPermissionMethod) == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                if ((getCheckPermissionMethod = AuthorizationServiceGrpc.getCheckPermissionMethod) == null) {
                    AuthorizationServiceGrpc.getCheckPermissionMethod = getCheckPermissionMethod = io.grpc.MethodDescriptor.<com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest, com.corporatebanking.authorizationservice.grpc.PermissionResponse>newBuilder().setType(io.grpc.MethodDescriptor.MethodType.UNARY).setFullMethodName(generateFullMethodName(SERVICE_NAME, "CheckPermission")).setSampledToLocalTracing(true).setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest.getDefaultInstance())).setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(com.corporatebanking.authorizationservice.grpc.PermissionResponse.getDefaultInstance())).setSchemaDescriptor(new AuthorizationServiceMethodDescriptorSupplier("CheckPermission")).build();
                }
            }
        }
        return getCheckPermissionMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static AuthorizationServiceStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<AuthorizationServiceStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AuthorizationServiceStub>() {

            @java.lang.Override
            public AuthorizationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new AuthorizationServiceStub(channel, callOptions);
            }
        };
        return AuthorizationServiceStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static AuthorizationServiceBlockingStub newBlockingStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<AuthorizationServiceBlockingStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AuthorizationServiceBlockingStub>() {

            @java.lang.Override
            public AuthorizationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new AuthorizationServiceBlockingStub(channel, callOptions);
            }
        };
        return AuthorizationServiceBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static AuthorizationServiceFutureStub newFutureStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<AuthorizationServiceFutureStub> factory = new io.grpc.stub.AbstractStub.StubFactory<AuthorizationServiceFutureStub>() {

            @java.lang.Override
            public AuthorizationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                return new AuthorizationServiceFutureStub(channel, callOptions);
            }
        };
        return AuthorizationServiceFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * Authorization Service
     * </pre>
     */
    public interface AsyncService {

        /**
         */
        default void signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSignupMethod(), responseObserver);
        }

        /**
         */
        default void authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAuthenticateMethod(), responseObserver);
        }

        /**
         */
        default void validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getValidateTokenMethod(), responseObserver);
        }

        /**
         */
        default void refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRefreshTokenMethod(), responseObserver);
        }

        /**
         */
        default void getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetUserInfoMethod(), responseObserver);
        }

        /**
         */
        default void logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.LogoutResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
        }

        /**
         */
        default void checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.PermissionResponse> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCheckPermissionMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service AuthorizationService.
     * <pre>
     * Authorization Service
     * </pre>
     */
    public static abstract class AuthorizationServiceImplBase implements io.grpc.BindableService, AsyncService {

        @java.lang.Override
        public io.grpc.ServerServiceDefinition bindService() {
            return AuthorizationServiceGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service AuthorizationService.
     * <pre>
     * Authorization Service
     * </pre>
     */
    public static class AuthorizationServiceStub extends io.grpc.stub.AbstractAsyncStub<AuthorizationServiceStub> {

        private AuthorizationServiceStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected AuthorizationServiceStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new AuthorizationServiceStub(channel, callOptions);
        }

        /**
         */
        public void signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getSignupMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getAuthenticateMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getRefreshTokenMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.LogoutResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
        }

        /**
         */
        public void checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request, io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.PermissionResponse> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(getChannel().newCall(getCheckPermissionMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service AuthorizationService.
     * <pre>
     * Authorization Service
     * </pre>
     */
    public static class AuthorizationServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<AuthorizationServiceBlockingStub> {

        private AuthorizationServiceBlockingStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected AuthorizationServiceBlockingStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new AuthorizationServiceBlockingStub(channel, callOptions);
        }

        /**
         */
        public com.corporatebanking.authorizationservice.grpc.TokenResponse signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getSignupMethod(), getCallOptions(), request);
        }

        /**
         */
        public com.corporatebanking.authorizationservice.grpc.TokenResponse authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getAuthenticateMethod(), getCallOptions(), request);
        }

        /**
         */
        public com.corporatebanking.authorizationservice.grpc.TokenValidationResponse validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getValidateTokenMethod(), getCallOptions(), request);
        }

        /**
         */
        public com.corporatebanking.authorizationservice.grpc.TokenResponse refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getRefreshTokenMethod(), getCallOptions(), request);
        }

        /**
         */
        public com.corporatebanking.authorizationservice.grpc.UserInfoResponse getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getGetUserInfoMethod(), getCallOptions(), request);
        }

        /**
         */
        public com.corporatebanking.authorizationservice.grpc.LogoutResponse logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getLogoutMethod(), getCallOptions(), request);
        }

        /**
         */
        public com.corporatebanking.authorizationservice.grpc.PermissionResponse checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(getChannel(), getCheckPermissionMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service AuthorizationService.
     * <pre>
     * Authorization Service
     * </pre>
     */
    public static class AuthorizationServiceFutureStub extends io.grpc.stub.AbstractFutureStub<AuthorizationServiceFutureStub> {

        private AuthorizationServiceFutureStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected AuthorizationServiceFutureStub build(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new AuthorizationServiceFutureStub(channel, callOptions);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<com.corporatebanking.authorizationservice.grpc.TokenResponse> signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getSignupMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<com.corporatebanking.authorizationservice.grpc.TokenResponse> authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getAuthenticateMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getValidateTokenMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<com.corporatebanking.authorizationservice.grpc.TokenResponse> refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getRefreshTokenMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<com.corporatebanking.authorizationservice.grpc.LogoutResponse> logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
        }

        /**
         */
        public com.google.common.util.concurrent.ListenableFuture<com.corporatebanking.authorizationservice.grpc.PermissionResponse> checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(getChannel().newCall(getCheckPermissionMethod(), getCallOptions()), request);
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

        private final AsyncService serviceImpl;

        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                case METHODID_SIGNUP:
                    serviceImpl.signup((com.corporatebanking.authorizationservice.grpc.SignupRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse>) responseObserver);
                    break;
                case METHODID_AUTHENTICATE:
                    serviceImpl.authenticate((com.corporatebanking.authorizationservice.grpc.AuthenticateRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse>) responseObserver);
                    break;
                case METHODID_VALIDATE_TOKEN:
                    serviceImpl.validateToken((com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse>) responseObserver);
                    break;
                case METHODID_REFRESH_TOKEN:
                    serviceImpl.refreshToken((com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.TokenResponse>) responseObserver);
                    break;
                case METHODID_GET_USER_INFO:
                    serviceImpl.getUserInfo((com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.UserInfoResponse>) responseObserver);
                    break;
                case METHODID_LOGOUT:
                    serviceImpl.logout((com.corporatebanking.authorizationservice.grpc.LogoutRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.LogoutResponse>) responseObserver);
                    break;
                case METHODID_CHECK_PERMISSION:
                    serviceImpl.checkPermission((com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest) request, (io.grpc.stub.StreamObserver<com.corporatebanking.authorizationservice.grpc.PermissionResponse>) responseObserver);
                    break;
                default:
                    throw new AssertionError();
            }
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public io.grpc.stub.StreamObserver<Req> invoke(io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch(methodId) {
                default:
                    throw new AssertionError();
            }
        }
    }

    public static io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getSignupMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.SignupRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>(service, METHODID_SIGNUP))).addMethod(getAuthenticateMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.AuthenticateRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>(service, METHODID_AUTHENTICATE))).addMethod(getValidateTokenMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenValidationResponse>(service, METHODID_VALIDATE_TOKEN))).addMethod(getRefreshTokenMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest, com.corporatebanking.authorizationservice.grpc.TokenResponse>(service, METHODID_REFRESH_TOKEN))).addMethod(getGetUserInfoMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest, com.corporatebanking.authorizationservice.grpc.UserInfoResponse>(service, METHODID_GET_USER_INFO))).addMethod(getLogoutMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.LogoutRequest, com.corporatebanking.authorizationservice.grpc.LogoutResponse>(service, METHODID_LOGOUT))).addMethod(getCheckPermissionMethod(), io.grpc.stub.ServerCalls.asyncUnaryCall(new MethodHandlers<com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest, com.corporatebanking.authorizationservice.grpc.PermissionResponse>(service, METHODID_CHECK_PERMISSION))).build();
    }

    private static abstract class AuthorizationServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {

        AuthorizationServiceBaseDescriptorSupplier() {
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return com.corporatebanking.authorizationservice.grpc.AuthorizationServiceProto.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("AuthorizationService");
        }
    }

    private static final class AuthorizationServiceFileDescriptorSupplier extends AuthorizationServiceBaseDescriptorSupplier {

        AuthorizationServiceFileDescriptorSupplier() {
        }
    }

    private static final class AuthorizationServiceMethodDescriptorSupplier extends AuthorizationServiceBaseDescriptorSupplier implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {

        private final java.lang.String methodName;

        AuthorizationServiceMethodDescriptorSupplier(java.lang.String methodName) {
            this.methodName = methodName;
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
            return getServiceDescriptor().findMethodByName(methodName);
        }
    }

    private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

    public static io.grpc.ServiceDescriptor getServiceDescriptor() {
        io.grpc.ServiceDescriptor result = serviceDescriptor;
        if (result == null) {
            synchronized (AuthorizationServiceGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME).setSchemaDescriptor(new AuthorizationServiceFileDescriptorSupplier()).addMethod(getSignupMethod()).addMethod(getAuthenticateMethod()).addMethod(getValidateTokenMethod()).addMethod(getRefreshTokenMethod()).addMethod(getGetUserInfoMethod()).addMethod(getLogoutMethod()).addMethod(getCheckPermissionMethod()).build();
                }
            }
        }
        return result;
    }
}
