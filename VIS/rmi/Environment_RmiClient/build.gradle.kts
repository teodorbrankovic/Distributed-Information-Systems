plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":general:EnvironmentI"))
    implementation("com.google.guava:guava:31.0.1-jre")
}

application {
    mainClass.set("at.fhooe.sail.vis.environmentrmiclient.Environment_RmiClient")
}

tasks.named<JavaExec>("run") {
    // Pass command line arguments to the application.
    standardInput = System.`in`
}