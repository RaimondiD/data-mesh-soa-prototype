package orchestrator;

import com.google.gson.Gson;
import data_class.OpaDbRequest;
import data_class.OpaReadResult;
import data_class.SQLSelectQuery;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.util.Map;

public class OrchestratorMethods {

    public static String DPNAME = "medical_care";
    static String repositoryRootPath = "C:/Users/DavideRaimondi/Desktop/repo_git/data-mesh-soa-use-case/";

    static String userInfoSpecificPath = "/info/request";
    static String userInfoCredentialfPath = "MedicalCareDP/src/main/resources/restStub.txt";

    static String opaDpCredentialPath = "MedicalCareDP/src/main/resources/opaDP.txt";
    static String opaDpRequestPath = "/data/medical";

    static String requestGeneratorCredentialPath = "MedicalCareDP/src/main/resources/requestGenerator.txt";
    static String requestGeneratorReadPath = "/generator/read";

    static String opaDbCredentialPath = "MedicalCareDP/src/main/resources/opaDB.txt";
    static String opaDbRequestPath = "/data/db";

    static String sqlAdapterCredentialPath = "MedicalCareDP/src/main/resources/DBAdapter.txt";
    static String sqlAdapterRequestPath = "/DB/select";


    public static String get_opa_request(String cdc, HttpHeaders headerRequest){
        System.out.println(headerRequest.getHeaderString("X-JWT-Assertion")); //TODO check JWT header and solve jwt user problem
        ApiDescriptor userInfoApi = new ApiDescriptor(repositoryRootPath + userInfoCredentialfPath);
        Response response = userInfoApi.get( userInfoSpecificPath);
        return response.readEntity(Map.class).get("entity").toString();
    }

    public static String get_opa_response(String opa_request){
        ApiDescriptor opaDPApi = new ApiDescriptor(repositoryRootPath + opaDpCredentialPath);
        Response opaResponse = opaDPApi.post(opaDpRequestPath, Entity.json(opa_request));
        return opaResponse.readEntity(Map.class).get("result").toString();
    }

    public static OpaReadResult authorized_opa (String opa_response){
        Gson gson = new Gson();
        try{
            OpaReadResult result = gson.fromJson(opa_response, OpaReadResult.class);
            return result;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static String get_db_request(OpaReadResult opa_evaluation){
        Gson gson_converter = new Gson();
        ApiDescriptor generatorApi = new ApiDescriptor(repositoryRootPath + requestGeneratorCredentialPath);
        Response responseOfGenerator = generatorApi.post(requestGeneratorReadPath,Entity.json(gson_converter.toJson(opa_evaluation)));;
        return responseOfGenerator.readEntity(Map.class).get("entity").toString();
    }

    public static String contactDBOpa(String sql_generated_request){
        Gson gsonConverter = new Gson();
        OpaDbRequest query_object = gsonConverter.fromJson(sql_generated_request, OpaDbRequest.class);
        String json_of_query_object = gsonConverter.toJson(query_object);
        String formatted_sql_request = "{ \"input\" : " + json_of_query_object  +" }";

        ApiDescriptor  opaDBApi = new ApiDescriptor(repositoryRootPath + opaDbCredentialPath);
        Response opaDbRresponse = opaDBApi.post(opaDbRequestPath, Entity.json(formatted_sql_request));
        return opaDbRresponse.readEntity(Map.class).get("result").toString();
    }

    public static String getDbResponse(String opaResponse) {
        Gson gsonConverter = new Gson();
        OpaDbRequest objectOpaResponse = gsonConverter.fromJson(opaResponse, OpaDbRequest.class);
        SQLSelectQuery sqlQuery = objectOpaResponse.getQuery();
        if (sqlQuery != null) {
            ApiDescriptor sqlAdapterApi = new ApiDescriptor(repositoryRootPath +  sqlAdapterCredentialPath);
            String sqlQueryString = gsonConverter.toJson(sqlQuery);
            System.out.println(sqlQueryString);
            Response dbResponse = sqlAdapterApi.post(sqlAdapterRequestPath, Entity.json(sqlQueryString));
            System.out.println(dbResponse);
            if(dbResponse.getStatus() != 200)
                return "Error, in the db request";


            return dbResponse.readEntity(String.class);
        }
        return "Error, the data product doesn't have enough permission to access this resource in db";
    }

}
