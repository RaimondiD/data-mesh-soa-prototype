package helpers;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ConnectionMethods {

    public static WebTarget get_client_from_url(String url){
        Client generic_client= ClientBuilder.newClient();
        return generic_client.target(url);

    }


}
