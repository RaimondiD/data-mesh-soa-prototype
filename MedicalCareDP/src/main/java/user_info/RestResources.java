package user_info;

import com.google.gson.Gson;
import data_class.UserInfo;
import helpers.StringHelpersFunction;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Path("/info")
public class RestResources {
    @GET
    @Path("/user")
    @Produces("application/json")
    public Response user_info(@Context HttpHeaders requestHeader) throws IOException {
        String encoded_user_claims = requestHeader.getHeaderString("user_jwt");
        Gson json_converter = new Gson();
        String user_claims = StringHelpersFunction.decodeJWTpayload(encoded_user_claims);

        UserInfo user_info_object = json_converter.fromJson(user_claims, UserInfo.class);
        user_info_object.setAction("read");
        user_info_object.setFormat("sql");
        String user_cf = user_info_object.getCf();
        if(user_cf!=null)
            user_info_object.setCf(user_cf.toUpperCase());
        String json_info = "{ \"input\" : {\"user\" : " + json_converter.toJson(user_info_object) + "} }";

        return Response.status(Response.Status.OK).entity(Entity.json(json_info)).build();

    }

}
