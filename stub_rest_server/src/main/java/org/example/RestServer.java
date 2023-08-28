package org.example;


import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class RestServer {

    private static final String HOST = "localhost";
    private static final int PORT = 9955;


    public static void main(String[] args) throws IOException{
        URI base_URI = URI.create("http://"+HOST+":"+PORT+"/");
        ResourceConfig serverResources = new ResourceConfig(RestResources.class);
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(base_URI,serverResources);
        server.start();

        System.out.println("Server running!");
        System.out.println("Server started on: http://"+HOST+":"+PORT); System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.shutdownNow();
        System.out.println("Server stopped");



    }
}
