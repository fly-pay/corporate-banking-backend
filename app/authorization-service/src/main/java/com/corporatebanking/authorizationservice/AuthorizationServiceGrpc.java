package com.corporatebanking.authorizationservice;

import com.corporatebanking.authorizationservice.grpc.*;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.inject.Singleton;
import org.jboss.logging.Logger;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.*;
import java.util.stream.Collectors;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import com.corporatebanking.authorizationservice.entity.UserEntity;
import com.corporatebanking.authorizationservice.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import io.quarkus.arc.Arc;
import io.quarkus.arc.ManagedContext;
import org.mindrot.jbcrypt.BCrypt;
import java.util.UUID;
import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@GrpcService
@Singleton
public class AuthorizationServiceGrpc implements AuthorizationService {

    private static final Logger LOG = Logger.getLogger(AuthorizationServiceGrpc.class);
    
    @Inject
    UserRepository userRepository;
    
    private Keycloak keycloakAdmin;
    private final String keycloakServerUrl;
    private final String realm;
    private final String clientId;
    private final String clientSecret;
    private final Client httpClient;
    private final ObjectMapper objectMapper;
    private final SecretKey jwtSecretKey;

    public AuthorizationServiceGrpc() {
        this.keycloakServerUrl = System.getenv().getOrDefault("KEYCLOAK_SERVER_URL", "http://keycloak:8080");
        this.realm = System.getenv().getOrDefault("KEYCLOAK_REALM", "corporate-banking");
        this.clientId = System.getenv().getOrDefault("KEYCLOAK_CLIENT_ID", "corporate-banking-client");
        this.clientSecret = System.getenv().getOrDefault("KEYCLOAK_CLIENT_SECRET", "corporate-banking-secret");
        
        this.httpClient = ClientBuilder.newClient();
        this.objectMapper = new ObjectMapper();
        String secret = System.getenv().getOrDefault("JWT_SECRET", "corporate-banking-secret-key-for-jwt-token-generation-min-32-chars");
        this.jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        
        LOG.infof("Authorization Service initialized with Keycloak at: %s, realm: %s", keycloakServerUrl, realm);
    }
    
    private Keycloak getKeycloakAdmin() {
        if (keycloakAdmin == null) {
            synchronized (this) {
                if (keycloakAdmin == null) {
                    keycloakAdmin = KeycloakBuilder.builder()
                            .serverUrl(keycloakServerUrl)
                            .realm(realm)
                            .clientId("admin-cli")
                            .username("admin")
                            .password("admin")
                            .build();
                }
            }
        }
        return keycloakAdmin;
    }

    @Override
    public Uni<TokenResponse> signup(SignupRequest request) {
        return Uni.createFrom().item(() -> {
            ManagedContext requestContext = Arc.container().requestContext();
            if (!requestContext.isActive()) {
                requestContext.activate();
            }
            try {
                return performSignup(request);
            } finally {
                if (requestContext.isActive()) {
                    requestContext.terminate();
                }
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
    
    @Transactional
    TokenResponse performSignup(SignupRequest request) {
        try {
            LOG.infof("Signing up user with email: %s", request.getEmail());
            
            UserEntity existingUser = userRepository.findByEmail(request.getEmail());
            if (existingUser != null) {
                throw new RuntimeException("User with email already exists: " + request.getEmail());
            }
            
            String userId = "user-" + UUID.randomUUID().toString();
            String passwordHash = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
            
            UserEntity userEntity = new UserEntity();
            userEntity.userId = userId;
            userEntity.email = request.getEmail();
            userEntity.passwordHash = passwordHash;
            userEntity.firstName = request.getFirstName();
            userEntity.lastName = request.getLastName();
            userEntity.phoneNumber = request.getPhoneNumber();
            userEntity.role = request.getRole().isEmpty() ? "USER" : request.getRole();
            
            userRepository.persist(userEntity);
            
            String accessToken = generateJwtToken(userEntity);
            String refreshToken = generateRefreshToken(userEntity);
            
            TokenResponse tokenResponse = TokenResponse.newBuilder()
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setTokenType("Bearer")
                    .setExpiresIn(3600)
                    .setScope("read write")
                    .build();
            
            LOG.infof("User signed up successfully: %s", request.getEmail());
            return tokenResponse;
        } catch (Exception e) {
            LOG.errorf(e, "Signup failed for user: %s", request.getEmail());
            throw new RuntimeException("Signup failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Uni<TokenResponse> authenticate(AuthenticateRequest request) {
        return Uni.createFrom().item(() -> {
            ManagedContext requestContext = Arc.container().requestContext();
            if (!requestContext.isActive()) {
                requestContext.activate();
            }
            try {
                return performAuthenticate(request);
            } finally {
                if (requestContext.isActive()) {
                    requestContext.terminate();
                }
            }
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
    
    @Transactional
    TokenResponse performAuthenticate(AuthenticateRequest request) {
        try {
            LOG.infof("Authenticating user: %s", request.getUsername());
            
            UserEntity userEntity = userRepository.findByEmail(request.getUsername());
            if (userEntity == null) {
                throw new RuntimeException("Invalid credentials");
            }
            
            if (userEntity.passwordHash == null || !BCrypt.checkpw(request.getPassword(), userEntity.passwordHash)) {
                throw new RuntimeException("Invalid credentials");
            }
            
            String accessToken = generateJwtToken(userEntity);
            String refreshToken = generateRefreshToken(userEntity);
            
            TokenResponse tokenResponse = TokenResponse.newBuilder()
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setTokenType("Bearer")
                    .setExpiresIn(3600)
                    .setScope("read write")
                    .build();
            
            LOG.infof("User authenticated successfully: %s", request.getUsername());
            return tokenResponse;
        } catch (Exception e) {
            LOG.errorf(e, "Authentication failed for user: %s", request.getUsername());
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }
    
    private String generateJwtToken(UserEntity user) {
        Instant now = Instant.now();
        Instant expiry = now.plus(1, ChronoUnit.HOURS);
        
        return Jwts.builder()
                .subject(user.userId)
                .claim("email", user.email)
                .claim("preferred_username", user.email)
                .claim("role", user.role != null ? user.role : "USER")
                .claim("firstName", user.firstName != null ? user.firstName : "")
                .claim("lastName", user.lastName != null ? user.lastName : "")
                .issuedAt(java.util.Date.from(now))
                .expiration(java.util.Date.from(expiry))
                .signWith(jwtSecretKey)
                .compact();
    }
    
    private String generateRefreshToken(UserEntity user) {
        Instant now = Instant.now();
        Instant expiry = now.plus(7, ChronoUnit.DAYS);
        
        return Jwts.builder()
                .subject(user.userId)
                .claim("type", "refresh")
                .issuedAt(java.util.Date.from(now))
                .expiration(java.util.Date.from(expiry))
                .signWith(jwtSecretKey)
                .compact();
    }

    @Override
    public Uni<TokenValidationResponse> validateToken(ValidateTokenRequest request) {
        return Uni.createFrom().item(() -> {
            try {
                LOG.infof("Validating token");
                
                try {
                    String token = request.getToken().trim();
                    
                    Claims claims = Jwts.parser()
                            .verifyWith(jwtSecretKey)
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
                    
                    String userId = claims.getSubject();
                    String email = claims.get("email", String.class);
                    String username = claims.get("preferred_username", String.class);
                    String role = claims.get("role", String.class);
                    long exp = claims.getExpiration().getTime();
                    
                    List<String> roles = new ArrayList<>();
                    if (role != null) {
                        roles.add(role);
                    }
                    
                    TokenValidationResponse validationResponse = TokenValidationResponse.newBuilder()
                            .setValid(true)
                            .setUserId(userId)
                            .setUsername(username != null ? username : email)
                            .setEmail(email != null ? email : "")
                            .addAllRoles(roles)
                            .setExpiresAt(exp)
                            .build();
                    
                    LOG.infof("Token validated successfully for user: %s", username);
                    return validationResponse;
                    
                } catch (Exception e) {
                    LOG.warnf("Failed to validate JWT token: %s", e.getMessage());
                    return TokenValidationResponse.newBuilder()
                            .setValid(false)
                            .setError("Token validation failed: " + e.getMessage())
                            .build();
                }
                
            } catch (Exception e) {
                LOG.warnf("Token validation failed: %s", e.getMessage());
                return TokenValidationResponse.newBuilder()
                        .setValid(false)
                        .setError("Token validation failed: " + e.getMessage())
                        .build();
            }
        });
    }
    
    private TokenValidationResponse validateTokenViaIntrospection(String token) {
        try {
            String introspectionUrl = keycloakServerUrl + "/realms/" + realm + "/protocol/openid-connect/token/introspect";
            
            String formData = "token=" + URLEncoder.encode(token, StandardCharsets.UTF_8) +
                    "&token_type_hint=access_token" +
                    "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8) +
                    "&client_secret=" + URLEncoder.encode(clientSecret, StandardCharsets.UTF_8);
            
            try (Response response = httpClient.target(introspectionUrl)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(formData, MediaType.APPLICATION_FORM_URLENCODED))) {
                
                if (response.getStatus() != 200) {
                    String errorBody = response.readEntity(String.class);
                    LOG.warnf("Token introspection failed with status: %d, body: %s", response.getStatus(), errorBody);
                    return TokenValidationResponse.newBuilder()
                            .setValid(false)
                            .setError("Token validation failed")
                            .build();
                }
                
                String responseBody = response.readEntity(String.class);
                JsonNode jsonNode = objectMapper.readTree(responseBody);
            
                boolean active = jsonNode.get("active").asBoolean(false);
                
                if (!active) {
                    LOG.warnf("Token introspection returned active=false. Response: %s", responseBody);
                    return TokenValidationResponse.newBuilder()
                            .setValid(false)
                            .setError("Token is not active")
                            .build();
                }
                
                String userId = jsonNode.has("sub") ? jsonNode.get("sub").asText() : "";
                String username = jsonNode.has("preferred_username") ? jsonNode.get("preferred_username").asText() : "";
                String email = jsonNode.has("email") ? jsonNode.get("email").asText() : "";
                long exp = jsonNode.has("exp") ? jsonNode.get("exp").asLong() * 1000L : 0L;
                
                List<String> roles = new ArrayList<>();
                if (jsonNode.has("realm_access") && jsonNode.get("realm_access").has("roles")) {
                    jsonNode.get("realm_access").get("roles").forEach(role -> roles.add(role.asText()));
                }
                
                TokenValidationResponse validationResponse = TokenValidationResponse.newBuilder()
                        .setValid(true)
                        .setUserId(userId)
                        .setUsername(username)
                        .setEmail(email)
                        .addAllRoles(roles)
                        .setExpiresAt(exp)
                        .build();
                
                LOG.infof("Token validated via introspection for user: %s", username);
                return validationResponse;
            }
        } catch (Exception e) {
            LOG.errorf(e, "Token introspection failed");
            return TokenValidationResponse.newBuilder()
                    .setValid(false)
                    .setError("Token validation failed: " + e.getMessage())
                    .build();
        }
    }

    @Override
    public Uni<TokenResponse> refreshToken(RefreshTokenRequest request) {
        return Uni.createFrom().item(() -> {
            return performRefreshToken(request);
        }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
    }
    
    @Transactional
    TokenResponse performRefreshToken(RefreshTokenRequest request) {
        try {
            LOG.infof("Refreshing token");
            
            Claims claims = Jwts.parser()
                    .verifyWith(jwtSecretKey)
                    .build()
                    .parseSignedClaims(request.getRefreshToken())
                    .getPayload();
            
            String userId = claims.getSubject();
            String tokenType = claims.get("type", String.class);
            
            if (!"refresh".equals(tokenType)) {
                throw new RuntimeException("Invalid refresh token");
            }
            
            UserEntity userEntity = userRepository.findByUserId(userId);
            if (userEntity == null) {
                throw new RuntimeException("User not found");
            }
            
            String accessToken = generateJwtToken(userEntity);
            String refreshToken = generateRefreshToken(userEntity);
            
            TokenResponse tokenResponse = TokenResponse.newBuilder()
                    .setAccessToken(accessToken)
                    .setRefreshToken(refreshToken)
                    .setTokenType("Bearer")
                    .setExpiresIn(3600)
                    .setScope("read write")
                    .build();
            
            LOG.infof("Token refreshed successfully");
            return tokenResponse;
            
        } catch (Exception e) {
            LOG.errorf(e, "Token refresh failed");
            throw new RuntimeException("Token refresh failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Uni<UserInfoResponse> getUserInfo(GetUserInfoRequest request) {
        LOG.infof("Getting user info from token");
        
        return validateToken(ValidateTokenRequest.newBuilder()
                .setToken(request.getToken())
                .build())
            .flatMap(validation -> {
                if (!validation.getValid()) {
                    return Uni.createFrom().failure(
                        new RuntimeException("Invalid token")
                    );
                }
                
                return Uni.createFrom().item(() -> {
                    UserEntity userEntity = userRepository.findByUserId(validation.getUserId());
                    if (userEntity == null) {
                        throw new RuntimeException("User not found");
                    }
                    
                    UserInfoResponse.Builder responseBuilder = UserInfoResponse.newBuilder()
                            .setUserId(userEntity.userId)
                            .setUsername(userEntity.email)
                            .setEmail(userEntity.email)
                            .setFirstName(userEntity.firstName != null ? userEntity.firstName : "")
                            .setLastName(userEntity.lastName != null ? userEntity.lastName : "");
                    
                    if (userEntity.role != null) {
                        responseBuilder.addRoles(userEntity.role);
                    }
                    
                    LOG.infof("User info retrieved for: %s", userEntity.email);
                    return responseBuilder.build();
                }).runSubscriptionOn(Infrastructure.getDefaultWorkerPool());
            })
            .onFailure().recoverWithUni(e -> {
                LOG.errorf(e, "Failed to get user info");
                return Uni.createFrom().failure(
                    new RuntimeException("Failed to get user info: " + e.getMessage())
                );
            });
    }

    @Override
    public Uni<LogoutResponse> logout(LogoutRequest request) {
        try {
            LOG.infof("Logging out user");
            
            LogoutResponse response = LogoutResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Logout successful")
                    .build();
            
            LOG.infof("User logged out successfully");
            return Uni.createFrom().item(response);
            
        } catch (Exception e) {
            LOG.errorf(e, "Logout failed");
            return Uni.createFrom().item(LogoutResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Logout failed: " + e.getMessage())
                    .build());
        }
    }

    @Override
    public Uni<PermissionResponse> checkPermission(CheckPermissionRequest request) {
        LOG.infof("Checking permission for resource: %s, scope: %s", request.getResource(), request.getScope());
        
        return validateToken(ValidateTokenRequest.newBuilder()
                .setToken(request.getToken())
                .build())
            .map(validation -> {
                if (!validation.getValid()) {
                    return PermissionResponse.newBuilder()
                            .setGranted(false)
                            .setMessage("Invalid token")
                            .build();
                }
                
                boolean hasPermission = validation.getRolesList().stream()
                        .anyMatch(role -> role.equals("ADMIN") || role.equals("USER"));
                
                PermissionResponse response = PermissionResponse.newBuilder()
                        .setGranted(hasPermission)
                        .setMessage(hasPermission ? "Permission granted" : "Permission denied")
                        .build();
                
                LOG.infof("Permission check result: %s", hasPermission);
                return response;
            })
            .onFailure().recoverWithUni(e -> {
                LOG.errorf(e, "Permission check failed");
                return Uni.createFrom().item(PermissionResponse.newBuilder()
                        .setGranted(false)
                        .setMessage("Permission check failed: " + e.getMessage())
                        .build());
            });
    }
}

