# data mesh soa prototype
 
This repository work using WSO2 apim and wso2 micro-integrator. Refer to wso2 documentation to download and install wso2 apim 4.2.0
All the file relative to WSO2 apim are inserted in the configuration folder, the folder contains the API definition, the deployment option, and some Java classes used to customize the jwt sent from the identity server to Api (look here https://apim.docs.wso2.com/en/latest/design/api-security/openid-connect/obtaining-user-profile-information-with-openid-connect/ and here https://apim.docs.wso2.com/en/latest/design/api-security/oauth2/grant-types/password-grant/ for more info).

The folders starting with "OPA*" contain the files related to the Open Policy Agent services (.rego for policies and .json for data).

The folder table_schema contains the schema of the PostgreSQL DB

The folder MedicalCareDP contains all the java code and relative conf. file used to create the services.

To replicate the project is necessary to import the api definition in WSO2 APIM, run all the main classes contained in the MedicalCareDP folder, run two OPA services, one for each OPA folder, and check if the credential and the ip/port written in the MedicalCareDP conf.file and in the code are coherent. 

In the folder "scrittura tesi" are the .tex file that documents all the project, written in italian.