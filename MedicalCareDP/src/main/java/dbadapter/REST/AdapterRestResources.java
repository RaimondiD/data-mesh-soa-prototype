package dbadapter.REST;

import data_class.SQLInsertQuery;
import dbadapter.PostgreeConnector;
import data_class.SQLSelectQuery;

import javax.ws.rs.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@Path("/DB")
public class AdapterRestResources {
        @POST
        @Path("/insert")
        @Consumes({"application/json", "application/xml"})
        @Produces({"application/json", "application/xml"})
        public Response insertQuery(SQLInsertQuery insertInfo) {
            String resultQuery = PostgreeConnector.ExecuteUpdate(insertInfo.generateInsertQuery());
            return Response.status(Response.Status.OK).entity(resultQuery).build();

        }
        @POST
        @Path("/select")
        @Consumes({"application/json", "application/xml"})
        public Response selectQuery(SQLSelectQuery selectInfo) {
            selectInfo.queryConditionPreProcess();
            System.out.println(selectInfo.getColumns());
            System.out.println(selectInfo.getConditions());
            String resultQuery = PostgreeConnector.ExecuteQuery(selectInfo.generateSelectQuery());
            System.out.println(resultQuery);
            return Response.status(Response.Status.OK).entity(resultQuery).build();

        }
        @POST
        @Path("/query")
        @Produces({"applicatoin/json","application/xml"})
        public Response genericQuery(String query){
            return Response.status(Response.Status.OK).build();
        }

}
