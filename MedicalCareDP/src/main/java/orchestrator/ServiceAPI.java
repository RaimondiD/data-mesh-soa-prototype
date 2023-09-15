package orchestrator;

import com.google.gson.Gson;
import data_class.OpaDbRequest;
import data_class.OpaReadResult;
import data_class.SQLSelectQuery;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/medicalprescription")
public class ServiceAPI {
        @Path("/get-data")
        @GET
        @Produces({"application/json", "application/xml"})
        public Response get_data(@DefaultValue("false") @QueryParam("cdc") String cdc, @Context HttpHeaders requestHeader) {
            String opa_request = OrchestratorMethods.get_opa_request(cdc, requestHeader);
            System.out.println(opa_request);
            String opa_response_string = OrchestratorMethods.get_opa_response(opa_request);
            OpaReadResult opa_response_object = OrchestratorMethods.authorized_opa(opa_response_string);
            if(!opa_response_object.isAllow()){
                return Response.status(Response.Status.OK).entity(Entity.text("the user dosen't have permission to " +
                        "get this resource")).build();
            }
            String sql_request = OrchestratorMethods.get_db_request(opa_response_object);
            String opa_db_result = OrchestratorMethods.contactDBOpa(sql_request);

            //create_query();
            //String db_response = readFromDb();
            //return Response.status(Response.Status.OK).entity(Entity.json(db_response)).build();
            String db_response = OrchestratorMethods.getDbResponse(opa_db_result);
            System.out.println("here");
            System.out.println(db_response);

            return Response.status(Response.Status.OK).entity(Entity.json(db_response)).build();
        }
    }
