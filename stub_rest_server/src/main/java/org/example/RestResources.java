package org.example;
import org.glassfish.grizzly.http.HttpHeader;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/info")
public class RestResources {
    @GET
    @Path("/request")
    @Produces("application/json")
    public Response info_request(@Context HttpHeaders requestHeader) throws IOException {
        String sample_result = new String(Files.readAllBytes(Paths.get("C:\\Users\\DavideRaimondi\\Desktop\\repo_git\\data-mesh-soa-use-case\\stub_rest_server\\src\\main\\resources\\query.json")));
        return Response.status(Response.Status.OK).entity(Entity.json(sample_result)).build();

    }
}
