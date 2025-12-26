# User Service

A Quarkus microservice providing user management functionality via gRPC.

## Features

- Create, Read, Update, Delete (CRUD) operations for users
- List users with pagination
- gRPC protocol for inter-service communication

## gRPC API

The service exposes the following gRPC methods:

- `CreateUser` - Create a new user
- `GetUser` - Get user by ID
- `UpdateUser` - Update user information
- `DeleteUser` - Delete a user
- `ListUsers` - List all users with pagination

## Running the Service

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
java -jar target/user-service-1.0.0-SNAPSHOT-runner.jar
```

## gRPC Port

The service runs on port **9001** by default.

## Testing

You can test the gRPC service using tools like:
- [grpcurl](https://github.com/fullstorydev/grpcurl)
- [BloomRPC](https://github.com/uw-labs/bloomrpc)
- [Postman](https://www.postman.com/) (with gRPC support)

