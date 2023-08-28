package org.wso2.carbon.test;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.impl.token.ClaimsRetriever;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class CustomClaimRetriever implements ClaimsRetriever {

    public void init() throws APIManagementException {
        //  Todo : initialize any variable for Claim retriever.
    }

    public SortedMap<String, String> getClaims(String endUserName) throws APIManagementException {
        SortedMap<String, String> claimsMap = this.getClaims(endUserName);
        System.out.println("call to claims retriever");
        return claimsMap;
    }

    public String getDialectURI(String s) throws APIManagementException {
        System.out.println(s + "call to getDialect");
        return "http://wso2.org/claims";
    }
}