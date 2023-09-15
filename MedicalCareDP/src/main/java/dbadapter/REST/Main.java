package dbadapter.REST;

import dbadapter.PostgreeConnector;
import helpers.Server;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.sql.SQLException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    private static final String HOST = "localhost";
    private static final int PORT = 9956;


    public static void main(String[] args) throws IOException {
        Server adapter_server = new Server(HOST, PORT, new ResourceConfig(AdapterRestResources.class));

        try {
            PostgreeConnector.connect();
        } catch (SQLException e) {
            System.out.println("impossibile connettersi al DB");
            return;
        }
        adapter_server.startServer();
    }
}