package com.corporatebanking.gateway;

import com.corporatebanking.nodejsservice.grpc.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

@Path("/api/v1/greeting")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingGatewayResource {

    private static final Logger LOG = Logger.getLogger(GreetingGatewayResource.class);

    @GrpcClient("nodejs-service")
    GreetingService greetingService;

    @GET
    @Path("/hello")
    public Response sayHello(@QueryParam("name") String name) {
        try {
            LOG.infof("Greeting request via gateway with name: %s", name);
            
            HelloRequest request = HelloRequest.newBuilder()
                    .setName(name != null ? name : "World")
                    .build();

            HelloResponse response = greetingService.sayHello(request).await().indefinitely();
            
            HelloResponseDTO dto = new HelloResponseDTO();
            dto.message = response.getMessage();
            dto.timestamp = response.getTimestamp();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Error calling greeting service");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @GET
    @Path("/info")
    public Response getGreetingInfo() {
        try {
            LOG.info("Getting greeting info via gateway");
            
            GreetingInfoRequest request = GreetingInfoRequest.newBuilder().build();
            GreetingInfoResponse response = greetingService.getGreetingInfo(request).await().indefinitely();
            
            GreetingInfoResponseDTO dto = new GreetingInfoResponseDTO();
            dto.serviceName = response.getServiceName();
            dto.language = response.getLanguage();
            dto.version = response.getVersion();
            dto.message = response.getMessage();
            
            return Response.ok(dto).build();
        } catch (Exception e) {
            LOG.errorf(e, "Error getting greeting info");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    public static class HelloResponseDTO {
        public String message;
        public String timestamp;
    }

    public static class GreetingInfoResponseDTO {
        public String serviceName;
        public String language;
        public String version;
        public String message;
    }

    public static class ErrorResponse {
        public String message;
        public boolean success = false;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }
}

