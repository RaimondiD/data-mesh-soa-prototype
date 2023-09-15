package requestGenerator;

import helpers.Server;
import org.glassfish.jersey.server.ResourceConfig;
import requestGenerator.REST.GeneratorRestResources;

import java.io.IOException;

public class Main{
        private static final String HOST = "localhost";
        private static final int PORT = 9957;


        public static void main(String[] args) throws IOException {
            Server adapter_server = new Server(HOST, PORT, new ResourceConfig(GeneratorRestResources.class));
            adapter_server.startServer();
        }
    }

