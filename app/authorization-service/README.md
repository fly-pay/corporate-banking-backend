# Authorization Service

A Quarkus microservice providing authentication and authorization functionality via gRPC, integrated with Keycloak.

## Features

- User authentication with Keycloak
- Token generation and validation
- Token refresh
- User information retrieval
- Permission checking
- Logout functionality

## gRPC API

The service exposes the following gRPC methods:

- `Authenticate` - Authenticate user and get access/refresh tokens
- `ValidateToken` - Validate an access token
- `RefreshToken` - Refresh an access token using refresh token
- `GetUserInfo` - Get user information from token
- `Logout` - Logout user
- `CheckPermission` - Check if user has permission for a resource

## Keycloak Configuration

The service requires Keycloak to be running and configured with:
- Realm: `corporate-banking` (default)
- Client: `corporate-banking-client` (default)
- Client Secret: `corporate-banking-secret` (default)

## Running the Service

### Prerequisites
- Java 17+
- Maven 3.8+
- Keycloak running (via Docker Compose)

### Development Mode
```bash
./mvnw quarkus:dev
```

### Build
```bash
./mvnw clean package
```

### Run
```bash
java -jar target/authorization-service-1.0.0-SNAPSHOT-runner.jar
```

## gRPC Port

The service runs on port **9004** by default.

## Environment Variables

- `KEYCLOAK_SERVER_URL` - Keycloak server URL (default: http://keycloak:8080)
- `KEYCLOAK_REALM` - Keycloak realm name (default: corporate-banking)
- `KEYCLOAK_CLIENT_ID` - Keycloak client ID (default: corporate-banking-client)
- `KEYCLOAK_CLIENT_SECRET` - Keycloak client secret (default: corporate-banking-secret)

