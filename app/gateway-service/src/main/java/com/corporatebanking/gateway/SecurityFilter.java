package com.corporatebanking.gateway;

import com.corporatebanking.authorizationservice.grpc.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.inject.Singleton;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

import java.util.Set;

@Provider
@Singleton
public class SecurityFilter implements ContainerRequestFilter {

    private static final Logger LOG = Logger.getLogger(SecurityFilter.class);
    
    private static final Set<String> PUBLIC_PATHS = Set.of(
            "/api/v1/auth/signup",
            "/api/v1/auth/login",
            "/api/v1/auth/refresh",
            "/health",
            "/q/health"
    );

    @GrpcClient("authorization-service")
    AuthorizationService authorizationService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        String path = requestContext.getUriInfo().getPath();
        
        if (PUBLIC_PATHS.contains(path) || path.startsWith("/q/")) {
            return;
        }

        String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            LOG.warnf("Unauthorized access attempt to: %s", path);
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                    .entity("{\"error\":\"Missing or invalid Authorization header\"}")
                    .build()
            );
            return;
        }

        String token = authHeader.substring(7).trim();
        
        LOG.debugf("Validating token, length: %d, starts with: %s", 
                token.length(), token.length() > 50 ? token.substring(0, 50) + "..." : token);
        
        try {
            ValidateTokenRequest request = ValidateTokenRequest.newBuilder()
                    .setToken(token)
                    .build();

            TokenValidationResponse validation = authorizationService.validateToken(request).await().indefinitely();
            
            if (!validation.getValid()) {
                LOG.warnf("Invalid token for path: %s", path);
                requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"error\":\"" + validation.getError() + "\"}")
                        .build()
                );
                return;
            }
            
            requestContext.setProperty("userId", validation.getUserId());
            requestContext.setProperty("username", validation.getUsername());
            requestContext.setProperty("roles", validation.getRolesList());
            
            LOG.debugf("Token validated successfully for user: %s accessing: %s", validation.getUsername(), path);
            
        } catch (Exception e) {
            LOG.errorf(e, "Token validation error for path: %s", path);
            requestContext.abortWith(
                Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\":\"Token validation failed: " + e.getMessage() + "\"}")
                    .build()
            );
        }
    }
}

