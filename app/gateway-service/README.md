# Gateway Service

A Quarkus API Gateway that provides REST endpoints and routes requests to backend gRPC microservices.

## Features

- RESTful API endpoints for all services
- gRPC client integration
- Token-based authentication
- Request/Response transformation (gRPC to JSON)

## REST API Endpoints

### Authentication Endpoints
- `POST /api/v1/auth/login` - Login and get access token
- `POST /api/v1/auth/validate` - Validate access token
- `POST /api/v1/auth/refresh` - Refresh access token
- `GET /api/v1/auth/userinfo` - Get user information
- `POST /api/v1/auth/logout` - Logout user
- `POST /api/v1/auth/check-permission` - Check user permission

### User Service Endpoints
- `POST /api/v1/users` - Create a new user
- `GET /api/v1/users/{userId}` - Get user by ID
- `PUT /api/v1/users/{userId}` - Update user
- `DELETE /api/v1/users/{userId}` - Delete user
- `GET /api/v1/users?page={page}&pageSize={pageSize}` - List users with pagination

## Running the Gateway

### Prerequisites
- Java 17+
- Maven 3.8+

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
java -jar target/gateway-service-1.0.0-SNAPSHOT-runner.jar
```

## Configuration

The gateway connects to:
- User Service on port 9001
- Authorization Service on port 9004

## Port

The gateway runs on port **8080** by default.

## Example Requests

### Login
```bash
curl -X POST http://localhost:8080/api/v1/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "testuser",
    "password": "testpassword"
  }'
```

### Create User (requires authentication)
```bash
curl -X POST http://localhost:8080/api/v1/users \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_ACCESS_TOKEN" \
  -d '{
    "email": "john.doe@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "phoneNumber": "+1234567890",
    "role": "USER"
  }'
```

