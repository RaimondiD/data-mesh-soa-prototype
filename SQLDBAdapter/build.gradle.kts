plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("com.oracle.ojdbc:ojdbc8:19.3.0.0")
    implementation("org.glassfish.jersey.media:jersey-media-json-jackson:2.14")
    implementation("org.glassfish.jersey.containers:jersey-container-servlet:2.14")
    implementation("javax.xml.bind:jaxb-api:2.2.4")
    implementation( "com.google.code.gson:gson:2.8.9")
    implementation("org.glassfish.jersey.containers:jersey-container-grizzly2-http:2.13")
}

tasks.test {
    useJUnitPlatform()
}