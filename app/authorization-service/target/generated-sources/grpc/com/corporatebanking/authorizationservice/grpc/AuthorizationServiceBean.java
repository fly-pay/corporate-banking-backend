package com.corporatebanking.authorizationservice.grpc;

import io.grpc.BindableService;
import io.quarkus.grpc.GrpcService;
import io.quarkus.grpc.MutinyBean;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: authorization_service.proto")
public class AuthorizationServiceBean extends MutinyAuthorizationServiceGrpc.AuthorizationServiceImplBase implements BindableService, MutinyBean {

    private final AuthorizationService delegate;

    AuthorizationServiceBean(@GrpcService AuthorizationService delegate) {
        this.delegate = delegate;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request) {
        try {
            return delegate.signup(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request) {
        try {
            return delegate.authenticate(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request) {
        try {
            return delegate.validateToken(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request) {
        try {
            return delegate.refreshToken(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request) {
        try {
            return delegate.getUserInfo(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.LogoutResponse> logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request) {
        try {
            return delegate.logout(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.PermissionResponse> checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request) {
        try {
            return delegate.checkPermission(request);
        } catch (UnsupportedOperationException e) {
            throw new io.grpc.StatusRuntimeException(io.grpc.Status.UNIMPLEMENTED);
        }
    }
}
