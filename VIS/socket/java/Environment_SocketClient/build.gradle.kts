plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation(project(":general:EnvironmentI"))
}

application {
    // Define the main class for the application.
    mainClass.set("at.fhooe.sail.vis.environmentsocketclient.Environment_SocketClient")
}

tasks.named<JavaExec>("run") {
    // Pass command line arguments to the application.
    standardInput = System.`in`
}

