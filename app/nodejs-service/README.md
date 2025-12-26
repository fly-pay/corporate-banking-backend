# Node.js Service

A Node.js microservice that provides gRPC and REST APIs for greeting functionality.

## Features

- gRPC server on port 9006
- REST API on port 9007
- Consul service registration
- Health check endpoint

## API Endpoints

### REST API

- `GET /health` - Health check endpoint
- `GET /api/hello?name=World` - Simple hello endpoint
- `GET /api/greeting/info` - Get greeting service information

### gRPC API

- `SayHello(HelloRequest)` - Returns a greeting message
- `GetGreetingInfo(GreetingInfoRequest)` - Returns service information

## Running Locally

```bash
npm install
npm start
```

## Environment Variables

- `GRPC_PORT` - gRPC server port (default: 9006)
- `HTTP_PORT` - HTTP server port (default: 9007)
- `CONSUL_HOST` - Consul host (default: consul)
- `CONSUL_PORT` - Consul port (default: 8500)

## Docker

```bash
docker build -t nodejs-service .
docker run -p 9006:9006 -p 9007:9007 nodejs-service
```

