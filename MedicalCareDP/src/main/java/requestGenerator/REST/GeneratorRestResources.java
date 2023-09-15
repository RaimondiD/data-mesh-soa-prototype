package requestGenerator.REST;

import com.google.gson.Gson;
import data_class.OpaDbRequest;
import data_class.OpaReadResult;
import data_class.SQLSelectQuery;
import orchestrator.OrchestratorMethods;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Path("/generator")
public class GeneratorRestResources {
    @POST
    @Path("/read")
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml"})
    public Response readQuery(OpaReadResult readInfo) {
        System.out.println("here");
        //readInfo.queryConditionPreProcess();

        OpaDbRequest result = new OpaDbRequest(OrchestratorMethods.DPNAME,"SELECT",readInfo.generateSQLSelectQuery());

        return Response.status(Response.Status.OK).entity(Entity.json(result)).build();
    }
    @GET
    @Path("/test")
    public Response prova(){
        System.out.println("eccoci");
        return Response.status(Response.Status.OK).build();
    }
}