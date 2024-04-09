package org.wso2.carbon.test;

import org.osgi.service.component.annotations.Component;
import org.wso2.carbon.apimgt.common.gateway.dto.JWTInfoDto;
import org.wso2.carbon.apimgt.common.gateway.exception.JWTGeneratorException;
import org.wso2.carbon.apimgt.common.gateway.jwtgenerator.APIMgtGatewayJWTGeneratorImpl;
import org.wso2.carbon.apimgt.common.gateway.jwtgenerator.AbstractAPIMgtGatewayJWTGenerator;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

@Component(
        enabled = true,
        service = AbstractAPIMgtGatewayJWTGenerator.class,
        name = "customgatewayJWTGenerator"
)
public class CustomGatewayJWTGenerator extends APIMgtGatewayJWTGeneratorImpl {

    @Override
    public Map<String, Object> populateStandardClaims(JWTInfoDto jwtInfoDto) {
        //Map<String,Object> claims = super.populateStandardClaims(jwtInfoDto);
        //claims.putAll(jwtInfoDto.getJwtValidationInfo().getClaims());
        //claims.put("prova","value");
        //System.out.println(claims);

        System.out.println(super.jwtConfigurationDto.getTokenIssuerDtoMap().keySet().toString());
        debug_write("enter in popStandardClaims");

        return super.populateStandardClaims(jwtInfoDto);
    }

    @Override
    public Map<String, Object> populateCustomClaims(JWTInfoDto jwtInfoDto) {
        debug_write("enter in popCustomClaims");
        Map<String, Object> claims = super.populateCustomClaims(jwtInfoDto);
        claims = jwtInfoDto.getJwtValidationInfo().getClaims();
        claims.put("uuid", UUID.randomUUID().toString());
        debug_write(claims.keySet().toString());
        return super.populateCustomClaims(jwtInfoDto);
    }
    private void debug_write(String message) {
        try {
            System.out.println(message);
            FileWriter fileWriter = new FileWriter("C:\\Users\\DavideRaimondi\\Desktop\\MyLog.txt");
            BufferedWriter appenderWriter = new BufferedWriter(fileWriter);
            appenderWriter.append(message);
            appenderWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred." + e + message);
        }
    }
    private void debug_set(Set <String> set){
        for (String key: set
             ) {
            System.out.println("key :" + key);

        }
    }

}