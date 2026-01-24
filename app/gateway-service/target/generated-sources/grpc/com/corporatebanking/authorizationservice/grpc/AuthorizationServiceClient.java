package com.corporatebanking.authorizationservice.grpc;

import java.util.function.BiFunction;
import io.quarkus.grpc.MutinyClient;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: authorization_service.proto")
public class AuthorizationServiceClient implements AuthorizationService, MutinyClient<MutinyAuthorizationServiceGrpc.MutinyAuthorizationServiceStub> {

    private final MutinyAuthorizationServiceGrpc.MutinyAuthorizationServiceStub stub;

    public AuthorizationServiceClient(String name, io.grpc.Channel channel, BiFunction<String, MutinyAuthorizationServiceGrpc.MutinyAuthorizationServiceStub, MutinyAuthorizationServiceGrpc.MutinyAuthorizationServiceStub> stubConfigurator) {
        this.stub = stubConfigurator.apply(name, MutinyAuthorizationServiceGrpc.newMutinyStub(channel));
    }

    private AuthorizationServiceClient(MutinyAuthorizationServiceGrpc.MutinyAuthorizationServiceStub stub) {
        this.stub = stub;
    }

    public AuthorizationServiceClient newInstanceWithStub(MutinyAuthorizationServiceGrpc.MutinyAuthorizationServiceStub stub) {
        return new AuthorizationServiceClient(stub);
    }

    @Override
    public MutinyAuthorizationServiceGrpc.MutinyAuthorizationServiceStub getStub() {
        return stub;
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request) {
        return stub.signup(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request) {
        return stub.authenticate(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request) {
        return stub.validateToken(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request) {
        return stub.refreshToken(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request) {
        return stub.getUserInfo(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.LogoutResponse> logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request) {
        return stub.logout(request);
    }

    @Override
    public io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.PermissionResponse> checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request) {
        return stub.checkPermission(request);
    }
}
