package org.example;

import com.google.gson.JsonObject;
import oracle.ucp.proxy.annotation.Post;
import org.example.data_class.SQLInsertQuery;
import org.example.data_class.SQLSelectQuery;
import org.glassfish.grizzly.http.HttpHeader;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
public class RestResources {
        @POST
        @Path("/insert")
        @Produces({"application/json", "application/xml"})
        public Response insertQuery(SQLInsertQuery insertInfo) {
            return Response.status(Response.Status.OK).build();

        }
        @GET
        @Path("/select")
        @Produces({"application/json", "application/xml"})
        public Response selectQuery(@Context SQLSelectQuery selectInfo) {
            JsonObject sqlJsonRepresentaiton =  new JsonObject();

        return Response.status(Response.Status.OK).build();
    }

}
