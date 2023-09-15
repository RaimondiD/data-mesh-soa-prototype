package helpers;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;


public class Server{
    private final String  host;
    private final int port;
    private final ResourceConfig restResources;

    public Server(String host, int port, ResourceConfig restResources) {
        this.host = host;
        this.port = port;
        this.restResources = restResources;
    }
    public void startServer() throws IOException {
        URI base_URI = URI.create("http://"+host+":"+port+"/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(base_URI,restResources);
        server.start();

        System.out.println("Server started on: http://"+host+":"+port);
        while(true);
    }
}

