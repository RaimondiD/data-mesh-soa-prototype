package orchestrator;

import org.jooq.JSON;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.swing.*;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileNotFoundException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class ApiDescriptor {
    String url;
    String consumerKey;
    String consumerSecret;

    public ApiDescriptor(String confFilePath){
        File myFile = new File(confFilePath);
        Scanner myReader = null;
        try {
            myReader = new Scanner(myFile);
        } catch (FileNotFoundException e) {
            System.out.println("error in path of file " + confFilePath);
            throw new RuntimeException(e);
        }
        url=  myReader.nextLine();
        consumerKey = myReader.nextLine();
        consumerSecret = myReader.nextLine();
        myReader.close();

    }

    public Client getSslClient() {

        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e + "TLS");
        }
        try {
            sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new java.security.SecureRandom());
        } catch (KeyManagementException e) {
            System.out.println("error with key management (ApiDescriptor 52)");
            throw new RuntimeException(e);
        }

        return ClientBuilder.newBuilder()
                .sslContext(sslcontext)
                .hostnameVerifier((s1, s2) -> true)
                .build();
    }
    public String getAccessToken(){
        Client client = getSslClient();
        MultivaluedMap<String,String> request_data = new MultivaluedHashMap<>();
        request_data.add("grant_type","password");
        request_data.add("username",DPuserInfo.getInstance().dpUser);
        request_data.add("password",DPuserInfo.getInstance().dpPassword);
        Response serverResponse = client.target("https://localhost:9443/oauth2/token").
                request().header("Authorization", "Basic " + getSecretEncoded()).
                post(Entity.form(request_data));
        Map<String,String> responseBody = serverResponse.readEntity(Map.class);
        return responseBody.getOrDefault("access_token",null);
    }
    public String getSecretEncoded(){
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encoded_secret = encoder.encode((consumerKey + ":" + consumerSecret).getBytes());
        return new String(encoded_secret);
    }

    public Response get(String get_path){
        MultivaluedMap<String,Object> defaultMap = new MultivaluedHashMap<>();
        return get(get_path,defaultMap);
    }
    public Response get(String get_path,  MultivaluedMap<String,Object> headers){
        headers.add("Authorization","Bearer " + getAccessToken());
        return getSslClient().target(url + get_path).request().headers(headers).get();
    }
    public Response post(String post_path, Entity<String> body){
        MultivaluedMap<String,Object> defaultMap = new MultivaluedHashMap<>();
        return post(post_path,body,defaultMap);
    }
    public Response post(String post_path, Entity<String> body,   MultivaluedMap<String,Object> headers){
        headers.add("Authorization","Bearer " + getAccessToken());
        return getSslClient().target(url+post_path).request().headers(headers).post(body);
    }
}
