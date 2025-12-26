const grpc = require('@grpc/grpc-js');
const protoLoader = require('@grpc/proto-loader');
const express = require('express');
const consul = require('consul');
const path = require('path');
require('dotenv').config();

const PROTO_PATH = path.join(__dirname, '../proto/greeting_service.proto');
const GRPC_PORT = process.env.GRPC_PORT || 9006;
const HTTP_PORT = process.env.HTTP_PORT || 9007;
const CONSUL_HOST = process.env.CONSUL_HOST || 'consul';
const CONSUL_PORT = process.env.CONSUL_PORT || 8500;
const SERVICE_NAME = 'nodejs-service';
const SERVICE_ID = `${SERVICE_NAME}-${Date.now()}`;

const packageDefinition = protoLoader.loadSync(PROTO_PATH, {
  keepCase: true,
  longs: String,
  enums: String,
  defaults: true,
  oneofs: true
});

const greetingProto = grpc.loadPackageDefinition(packageDefinition).com.corporatebanking.nodejsservice;

const sayHello = (call, callback) => {
  const name = call.request.name || 'World';
  const message = `Hello from Node.js, ${name}!`;
  const timestamp = new Date().toISOString();
  
  console.log(`[gRPC] SayHello called with name: ${name}`);
  
  callback(null, {
    message: message,
    timestamp: timestamp
  });
};

const getGreetingInfo = (call, callback) => {
  console.log('[gRPC] GetGreetingInfo called');
  
  callback(null, {
    service_name: 'Node.js Greeting Service',
    language: 'Node.js',
    version: '1.0.0',
    message: 'Hello from Node.js gRPC service!'
  });
};

const server = new grpc.Server();
server.addService(greetingProto.GreetingService.service, {
  SayHello: sayHello,
  GetGreetingInfo: getGreetingInfo
});

server.bindAsync(`0.0.0.0:${GRPC_PORT}`, grpc.ServerCredentials.createInsecure(), (error, port) => {
  if (error) {
    console.error('Failed to start gRPC server:', error);
    process.exit(1);
  }
  console.log(`gRPC server running on port ${port}`);
  server.start();
});

const app = express();
app.use(express.json());

app.get('/health', (req, res) => {
  res.json({ status: 'healthy', service: SERVICE_NAME });
});

app.get('/api/hello', (req, res) => {
  const name = req.query.name || 'World';
  res.json({
    message: `Hello from Node.js, ${name}!`,
    timestamp: new Date().toISOString(),
    service: SERVICE_NAME
  });
});

app.get('/api/greeting/info', (req, res) => {
  res.json({
    service_name: 'Node.js Greeting Service',
    language: 'Node.js',
    version: '1.0.0',
    message: 'Hello from Node.js REST API!'
  });
});

app.listen(HTTP_PORT, '0.0.0.0', () => {
  console.log(`HTTP server running on port ${HTTP_PORT}`);
});

const consulClient = consul({
  host: CONSUL_HOST,
  port: CONSUL_PORT,
  promisify: true
});

async function registerWithConsul() {
  try {
    const serviceDefinition = {
      name: SERVICE_NAME,
      id: SERVICE_ID,
      address: SERVICE_NAME,
      port: parseInt(GRPC_PORT),
      check: {
        http: `http://${SERVICE_NAME}:${HTTP_PORT}/health`,
        interval: '10s',
        timeout: '5s',
        deregistercriticalserviceafter: '30s'
      },
      tags: ['grpc', 'nodejs', 'greeting']
    };

    await consulClient.agent.service.register(serviceDefinition);
    console.log(`Registered ${SERVICE_NAME} with Consul`);
  } catch (error) {
    console.error('Failed to register with Consul:', error);
  }
}

setTimeout(() => {
  registerWithConsul();
}, 5000);

process.on('SIGTERM', async () => {
  console.log('SIGTERM received, shutting down gracefully...');
  
  try {
    await consulClient.agent.service.deregister({ id: SERVICE_ID });
    console.log('Deregistered from Consul');
  } catch (error) {
    console.error('Error deregistering from Consul:', error);
  }
  
  server.tryShutdown(() => {
    console.log('gRPC server shut down');
    process.exit(0);
  });
});

process.on('SIGINT', async () => {
  console.log('SIGINT received, shutting down gracefully...');
  
  try {
    await consulClient.agent.service.deregister({ id: SERVICE_ID });
    console.log('Deregistered from Consul');
  } catch (error) {
    console.error('Error deregistering from Consul:', error);
  }
  
  server.tryShutdown(() => {
    console.log('gRPC server shut down');
    process.exit(0);
  });
});

