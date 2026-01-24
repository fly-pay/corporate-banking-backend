package com.corporatebanking.authorizationservice.grpc;

import io.quarkus.grpc.MutinyService;

@jakarta.annotation.Generated(value = "by Mutiny Grpc generator", comments = "Source: authorization_service.proto")
public interface AuthorizationService extends MutinyService {

    io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> signup(com.corporatebanking.authorizationservice.grpc.SignupRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> authenticate(com.corporatebanking.authorizationservice.grpc.AuthenticateRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenValidationResponse> validateToken(com.corporatebanking.authorizationservice.grpc.ValidateTokenRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.TokenResponse> refreshToken(com.corporatebanking.authorizationservice.grpc.RefreshTokenRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.UserInfoResponse> getUserInfo(com.corporatebanking.authorizationservice.grpc.GetUserInfoRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.LogoutResponse> logout(com.corporatebanking.authorizationservice.grpc.LogoutRequest request);

    io.smallrye.mutiny.Uni<com.corporatebanking.authorizationservice.grpc.PermissionResponse> checkPermission(com.corporatebanking.authorizationservice.grpc.CheckPermissionRequest request);
}
