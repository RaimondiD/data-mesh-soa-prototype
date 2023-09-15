package dbadapter.REST;

import com.google.gson.Gson;
import data_class.SQLInsertQuery;
import data_class.SQLSelectQuery;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;

import static orchestrator.OrchestratorMethods.DPNAME;

public class ClientForTesting {
    private static final String serverAddress = "http://localhost:9956";
    private static final Client client = ClientBuilder.newClient();
    private static Response clientResponse = null;

    public static void main(String args[]) {
        // POST EXAMPLE
        selectQuery();
        insertQuery();

    }

    private static void insertQuery() {
        String postPath = "/DB/insert";
        SQLInsertQuery insertQuery = CreateInsertSqlQuery();
        clientResponse = GetRequest(client, serverAddress + postPath, insertQuery);
        System.out.println(clientResponse.toString());
    }

    private static SQLInsertQuery CreateInsertSqlQuery() {
        System.out.println("test insert query... ");
        String tablename = "medicalPrescription";
        ArrayList<String> columnNames = new ArrayList<String>(Arrays.asList("prescription_code", "cf", "priority", "medic_cf", "description",
                "medical_service_code", "date_of_creation"));
        ArrayList<String> values = new ArrayList<String>(Arrays.asList("'8'","'RRRRRR333333333'","'U'","'ZZZZZZZZZZZZZZ'","'brain intervention'","'55555'","'2023/09/01'"));
        return new SQLInsertQuery(tablename,columnNames,values);
    }

    private static void selectQuery() {
        System.out.println("test select query...");
        String postPath = "/DB/select";

        SQLSelectQuery selectQuery = CreateSelectSqlQuery();
        clientResponse = GetRequest(client,serverAddress+postPath,selectQuery);
        System.out.println(clientResponse.readEntity(String.class));
    }

    public static Response GetRequest(Client client, String url, Object query){
        WebTarget webTarget = client.target(url);
        String input = new Gson().toJson(query);
        System.out.println(input);
        return webTarget.request().post(Entity.json(input));

    }
    public static SQLSelectQuery CreateSelectSqlQuery(){
        ArrayList<String> allColumn = new ArrayList<>();
        allColumn.add("*");
        ArrayList<String> noCcondition = new ArrayList<>();
        noCcondition.add("true");
        return new SQLSelectQuery(DPNAME,"medicalprescription", allColumn, noCcondition);
    }
}
