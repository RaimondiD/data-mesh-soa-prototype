package org.example.REST;

import com.google.gson.JsonObject;
import org.example.REST.data_class.SQLInsertQuery;
import org.example.REST.data_class.SQLSelectQuery;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
