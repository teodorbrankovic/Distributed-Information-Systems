plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
// Moxy-JAXB (JSON)
// https://mvnrepository.com/artifact/org.glassfish.jersey.media/jersey-media-moxy
    //implementation("jakarta.xml.bind:jakarta.xml.bind-api:3.0.1")
    implementation("org.glassfish.jersey.media:jersey-media-moxy:3.1.5")
}

application {
// Define the main class for the application.
    mainClass.set("at.fhooe.sail.vis.jaxb.json.Main")
}