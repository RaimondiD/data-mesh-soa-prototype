package org.example;
import org.glassfish.grizzly.http.HttpHeader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/info")
public class RestResources {
    @GET
    @Path("/request")
    public Response info_request(@Context HttpHeaders requestHeader){
        System.out.println("prova");
        System.out.print("morte " + requestHeader.getRequestHeaders());
        return Response.status(Response.Status.OK).entity("prova" + requestHeader.getRequestHeaders()).build();
    }
}
