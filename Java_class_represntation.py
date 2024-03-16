from pathlib import Path

COMPILE_COMMAND = {"gradle" : "", "maven" : "mvn clean install"}
ARTIFACT_FOLDER = {"gradle" : "", "maven " : lambda build : Path("""C:\Users\DavideRaimondi\.m2\repository""") / build}
class Java_class_representation():
    def __init__(self, name, out_path, type) -> None:
        pass