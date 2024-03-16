package user_info;

import helpers.Server;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;

public class Main {

    private static final String HOST = "localhost";
    private static final int PORT = 9955;


    public static void main(String[] args) throws IOException {
        Server adapter_server = new Server(HOST, PORT, new ResourceConfig(RestResources.class));
        adapter_server.startServer();
    }
}
