package org.wso2.carbon.test;

import org.wso2.carbon.apimgt.api.APIManagementException;
import org.wso2.carbon.apimgt.impl.token.ClaimsRetriever;
import org.wso2.carbon.apimgt.keymgt.service.TokenValidationContext;
import org.wso2.carbon.apimgt.keymgt.token.JWTGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;

public class CustomTokenGenerator extends JWTGenerator {
    public Map<String, String> populateStandardClaims(TokenValidationContext validationContext) throws APIManagementException {
        Map<String, String> claims = new LinkedHashMap(20);
        return claims;
    }

    public Map<String, String> populateCustomClaims(TokenValidationContext tokenValidationContext) throws APIManagementException {
        ClaimsRetriever claimsRetriever = this.getClaimsRetriever();
        String tenantAwareUserName = tokenValidationContext.getValidationInfoDTO().getEndUserName();
        SortedMap<String, String> map = claimsRetriever.getClaims(tenantAwareUserName);
        return map;
    }
}