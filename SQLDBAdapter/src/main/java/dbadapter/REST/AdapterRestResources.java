package dbadapter.REST;

import dbadapter.REST.data_class.SQLInsertQuery;
import dbadapter.PostgreeConnector;
import dbadapter.REST.data_class.SQLSelectQuery;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/DB")
public class RestResources {
        @POST
        @Path("/insert")
        @Consumes({"application/json", "application/xml"})
        @Produces({"application/json", "application/xml"})
        public Response insertQuery(SQLInsertQuery insertInfo) {
            System.out.println("here" + insertInfo);
            String resultQuery = PostgreeConnector.ExecuteUpdate(insertInfo.generateInsertQuery());
            return Response.status(Response.Status.OK).entity(resultQuery).build();

        }
        @POST
        @Path("/select")
        @Consumes({"application/json", "application/xml"})
        public Response selectQuery(SQLSelectQuery selectInfo) {
            String resultQuery = PostgreeConnector.ExecuteQuery(selectInfo.generateSelectQuery());
            System.out.println(resultQuery);
            return Response.status(Response.Status.OK).entity(resultQuery).build();

        }
        @POST
        @Path("/query")
        @Produces({"applicatoin/json","application/xml"})
        public Response genericQuery(String query){
            System.out.println("generic-query");
            return Response.status(Response.Status.OK).build();
        }

}
