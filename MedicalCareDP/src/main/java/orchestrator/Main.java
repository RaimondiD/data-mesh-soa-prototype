package orchestrator;

import helpers.Server;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;

public class Main {
    private static final String HOST = "localhost";
    private static final int PORT = 9954;

    public static void main(String[] args) throws IOException {
        Server orchestratorServer = new Server(HOST,PORT,new ResourceConfig(ServiceAPI.class));
        DPuserInfo.getInstance();
        orchestratorServer.startServer();

    }
}
