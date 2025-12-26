package com.corporatebanking.gateway;

import com.corporatebanking.authorizationservice.grpc.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.stream.Collectors;

@Path("/api/v1/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthGatewayResource {

    private static final Logger LOG = Logger.getLogger(AuthGatewayResource.class);

    @GrpcClient("authorization-service")
    AuthorizationService authorizationService;

    @POST
    @Path("/signup")
    public Response signup(SignupRequestDTO request) {
        try {
            LOG.infof("Signup attempt for email: %s", request.email);
            
            SignupRequest grpcRequest = SignupRequest.newBuilder()
                    .setEmail(request.email)
                    .setPassword(request.password)
                    .setFirstName(request.firstName)
                    .setLastName(request.lastName)
                    .setPhoneNumber(request.phoneNumber != null ? request.phoneNumber : "")
                    .setRole(request.role != null ? request.role : "USER")
                    .build();

            TokenResponse response = authorizationService.signup(grpcRequest).await().indefinitely();
            
            TokenResponseDTO dto = new TokenResponseDTO();
            dto.accessToken = response.getAccessToken();
            dto.refreshToken = response.getRefreshToken();
            dto.tokenType = response.getTokenType();
            dto.expiresIn = response.getExpiresIn();
            dto.scope = response.getScope();
            
            return Response.ok(dto).status(Response.Status.CREATED).build();
        } catch (Exception e) {
            LOG.errorf(e, "Signup failed for email: %s", request.email);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse("Signup failed: " + e.getMessage())).build();
        }
    }

    @POST
    @Path("/login")
    public Response login(LoginRequestDTO request) {
        try {
            LOG.infof("Login attempt for user: %s", request.username);
            
            AuthenticateRequest grpcRequest = AuthenticateRequest.newBuilder()
                    .setUsername(request.username)
                    .setPassword(request.password)
                    .setClientId(request.clientId != null ? request.clientId : "")
                    .setClientSecret(request.clientSecret != null ? request.clientSecret : "")
                    .build();

            TokenResponse response = authorizationService.authenticate(grpcRequest).await().indefinitely();
            
            TokenResponseDTO dto = new TokenResponseDTO();
            dto.accessToken = response.getAccessToken();
            dto.refreshToken = response.getRefreshToken();
            dto.tokenType = response.getTokenType();
            dto.expiresIn = response.getExpiresIn();
            dto.scope = response.getScope();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Login failed for user: %s", request.username);
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Authentication failed: " + e.getMessage())).build();
        }
    }

    @POST
    @Path("/validate")
    public Response validateToken(@HeaderParam("Authorization") String authHeader) {
        try {
            String token = extractToken(authHeader);
            if (token == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Missing or invalid Authorization header")).build();
            }
            
            ValidateTokenRequest request = ValidateTokenRequest.newBuilder()
                    .setToken(token)
                    .build();

            TokenValidationResponse response = authorizationService.validateToken(request).await().indefinitely();
            
            if (!response.getValid()) {
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(new ErrorResponse(response.getError())).build();
            }
            
            TokenValidationResponseDTO dto = new TokenValidationResponseDTO();
            dto.valid = response.getValid();
            dto.userId = response.getUserId();
            dto.username = response.getUsername();
            dto.email = response.getEmail();
            dto.roles = response.getRolesList();
            dto.expiresAt = response.getExpiresAt();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Token validation failed");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Token validation failed: " + e.getMessage())).build();
        }
    }

    @POST
    @Path("/refresh")
    public Response refreshToken(RefreshTokenRequestDTO request) {
        try {
            LOG.infof("Refreshing token");
            
            RefreshTokenRequest grpcRequest = RefreshTokenRequest.newBuilder()
                    .setRefreshToken(request.refreshToken)
                    .setClientId(request.clientId != null ? request.clientId : "")
                    .setClientSecret(request.clientSecret != null ? request.clientSecret : "")
                    .build();

            TokenResponse response = authorizationService.refreshToken(grpcRequest).await().indefinitely();
            
            TokenResponseDTO dto = new TokenResponseDTO();
            dto.accessToken = response.getAccessToken();
            dto.refreshToken = response.getRefreshToken();
            dto.tokenType = response.getTokenType();
            dto.expiresIn = response.getExpiresIn();
            dto.scope = response.getScope();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Token refresh failed");
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ErrorResponse("Token refresh failed: " + e.getMessage())).build();
        }
    }

    @GET
    @Path("/userinfo")
    public Response getUserInfo(@HeaderParam("Authorization") String authHeader) {
        try {
            String token = extractToken(authHeader);
            if (token == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Missing or invalid Authorization header")).build();
            }
            
            GetUserInfoRequest request = GetUserInfoRequest.newBuilder()
                    .setToken(token)
                    .build();

            UserInfoResponse response = authorizationService.getUserInfo(request).await().indefinitely();
            
            UserInfoResponseDTO dto = new UserInfoResponseDTO();
            dto.userId = response.getUserId();
            dto.username = response.getUsername();
            dto.email = response.getEmail();
            dto.firstName = response.getFirstName();
            dto.lastName = response.getLastName();
            dto.roles = response.getRolesList();
            dto.attributes = response.getAttributesMap();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Failed to get user info");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Failed to get user info: " + e.getMessage())).build();
        }
    }

    @POST
    @Path("/logout")
    public Response logout(@HeaderParam("Authorization") String authHeader, LogoutRequestDTO request) {
        try {
            String token = extractToken(authHeader);
            if (token == null && request.refreshToken == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Missing token or refresh token")).build();
            }
            
            LogoutRequest grpcRequest = LogoutRequest.newBuilder()
                    .setToken(token != null ? token : "")
                    .setRefreshToken(request.refreshToken != null ? request.refreshToken : "")
                    .build();

            LogoutResponse response = authorizationService.logout(grpcRequest).await().indefinitely();
            
            if (response.getSuccess()) {
                return Response.ok(new SuccessResponse(response.getMessage())).build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(new ErrorResponse(response.getMessage())).build();
            }
        } catch (Exception e) {
            LOG.errorf(e, "Logout failed");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Logout failed: " + e.getMessage())).build();
        }
    }

    @POST
    @Path("/check-permission")
    public Response checkPermission(@HeaderParam("Authorization") String authHeader, CheckPermissionRequestDTO request) {
        try {
            String token = extractToken(authHeader);
            if (token == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(new ErrorResponse("Missing or invalid Authorization header")).build();
            }
            
            CheckPermissionRequest grpcRequest = CheckPermissionRequest.newBuilder()
                    .setToken(token)
                    .setResource(request.resource)
                    .setScope(request.scope)
                    .build();

            PermissionResponse response = authorizationService.checkPermission(grpcRequest).await().indefinitely();
            
            PermissionResponseDTO dto = new PermissionResponseDTO();
            dto.granted = response.getGranted();
            dto.message = response.getMessage();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Permission check failed");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse("Permission check failed: " + e.getMessage())).build();
        }
    }

    private String extractToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }
        return authHeader.substring(7);
    }

    public static class SignupRequestDTO {
        public String email;
        public String password;
        public String firstName;
        public String lastName;
        public String phoneNumber;
        public String role;
    }

    public static class LoginRequestDTO {
        public String username;
        public String password;
        public String clientId;
        public String clientSecret;
    }

    public static class RefreshTokenRequestDTO {
        public String refreshToken;
        public String clientId;
        public String clientSecret;
    }

    public static class LogoutRequestDTO {
        public String refreshToken;
    }

    public static class CheckPermissionRequestDTO {
        public String resource;
        public String scope;
    }

    public static class TokenResponseDTO {
        public String accessToken;
        public String refreshToken;
        public String tokenType;
        public int expiresIn;
        public String scope;
    }

    public static class TokenValidationResponseDTO {
        public boolean valid;
        public String userId;
        public String username;
        public String email;
        public List<String> roles;
        public long expiresAt;
    }

    public static class UserInfoResponseDTO {
        public String userId;
        public String username;
        public String email;
        public String firstName;
        public String lastName;
        public List<String> roles;
        public java.util.Map<String, String> attributes;
    }

    public static class PermissionResponseDTO {
        public boolean granted;
        public String message;
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

