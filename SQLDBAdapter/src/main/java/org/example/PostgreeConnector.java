package org.example;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import org.jooq.impl.DSL;

public class PostgreeConnector {
    private static final String url = "jdbc:postgresql://localhost/postgres";
    private static String user;
    private static String password;
    private static Connection connection = null;



    public static void connect() throws SQLException {
        if (connection != null){
            return;
        }
        try_connection();
    }

    private static void try_connection() {
        while(true){
            System.out.println("inserire utente postgree DB:");
            Scanner reader = new Scanner(System.in);
            user = reader.nextLine();
            System.out.println("inserire password utente");
            password = reader.nextLine();

            try {
                connection = DriverManager.getConnection(url, user, password);                break;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                System.out.println("error, re-insert username and password ");

            }
        }


    }
    public static String ExecuteQuery(String query){
        try {
            connect();
            ResultSet queryResult =  connection.createStatement().executeQuery(query);
            return DSL.using(connection).fetch(queryResult).formatJSON();

        } catch (SQLException e) {
            System.out.println("exception in query execution: "+e);
            throw new RuntimeException(e);
        }
    }

    public static String ExecuteUpdate(String query){
        try { 
            connect();
            connection.createStatement().executeUpdate(query);
            return "success in insert";

        } catch (SQLException e) {
            System.out.println("error in query: " + e);
            return e.toString();
        //TODO change return type in (status, Entity)
        }
    }
}
