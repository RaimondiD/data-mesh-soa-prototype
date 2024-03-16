import pathlib
import os
import shutil

APIM_HOME_DIR = pathlib.Path("C:/Users/DavideRaimondi/Desktop\WS02/wso2am-4.2.0")
CONF_PATH = pathlib.Path("configuration/deployment.toml")
JAVA_PROGRAMS = ["/customJWTgenerator","/CustomGatewayJWTGenerator","/stub_rest_server"]

def update_WSO2_conf():
    relative_path = pathlib.Path("repository/conf/deployment.toml")
    apim_conf_path = APIM_HOME_DIR / relative_path
    os.remove(apim_conf_path)
    shutil.copy(CONF_PATH, apim_conf_path)
    
def create_artifacts():
    JAVA_PROGRAMS
update_WSO2_conf()