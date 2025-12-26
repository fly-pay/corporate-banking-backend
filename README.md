# Corporate Banking Backend

A microservices-based corporate banking backend system built with Quarkus, gRPC, and Oracle Database.

## Architecture

This project consists of the following microservices:

- **Gateway Service** - REST API gateway (port 8080)
- **Authorization Service** - Authentication and authorization service (gRPC: 9004, HTTP: 9005)
- **User Service** - User management service (gRPC: 9001, HTTP: 9002)

## Prerequisites

- Docker and Docker Compose
- Java 17+ (for local development)
- Maven 3.8+ (for local development)

## Quick Start

To build and start all services in detached mode:

```bash
docker compose up --build -d
```

This command will:
- Build all microservices
- Start Oracle Database, Consul, Keycloak, and all application services
- Run in detached mode (background)

## Services and Ports

| Service | Port(s) | Description |
|---------|---------|-------------|
| Gateway Service | 8080 | REST API Gateway |
| Authorization Service | 9004 (gRPC), 9005 (HTTP) | Authentication service |
| User Service | 9001 (gRPC), 9002 (HTTP) | User management service |
| Oracle Database | 1521, 5500 | Database server |
| Keycloak | 8090 | Identity and access management |
| Consul | 8500, 8600 | Service discovery |
| Dozzle | 8081 | Docker log viewer |

## Useful Commands

### View logs
```bash
docker compose logs -f
```

### View logs for a specific service
```bash
docker compose logs -f gateway-service
```

### Stop all services
```bash
docker compose down
```

### Stop and remove volumes (clean slate)
```bash
docker compose down -v
```

### Restart a specific service
```bash
docker compose restart gateway-service
```

### Check service status
```bash
docker compose ps
```

## API Documentation

Once the services are running, you can access:

- **API Gateway**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/q/swagger-ui
- **Keycloak Admin Console**: http://localhost:8090 (admin/admin)
- **Consul UI**: http://localhost:8500
- **Dozzle (Logs)**: http://localhost:8081

## Health Checks

All services include health check endpoints:

- Gateway: http://localhost:8080/health
- Authorization Service: http://localhost:9005/health
- User Service: http://localhost:9002/health

## Postman Collection

A Postman collection is available in the `postman/` directory for testing the API endpoints.

## Database

The Oracle database is automatically initialized with:
- Database: `corporate_banking`
- User: `banking_user`
- Password: `banking_pass`
- Admin Password: `Oracle123`

The PDB state is automatically saved on container startup to persist across restarts.

## Development

For local development without Docker, see individual service READMEs in:
- `app/gateway-service/README.md`
- `app/authorization-service/README.md`
- `app/user-service/README.md`
