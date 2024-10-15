import org.apache.tools.ant.taskdefs.condition.Os

plugins {
   id("cpp-application")
}

val port: String = "4949"
// task to run cpp program
tasks.register("run", Exec::class) {
    dependsOn("build")         // make sure project has been built
    group = "application"                // set task group
    standardInput = System.`in`    // enable commandline input
    val exeDir:  String = "${buildDir}/exe/main/debug/"
    var exeFile: String? = null
    when {
        Os.isFamily(Os.FAMILY_WINDOWS) -> {
            exeFile = "SocketClientTest.exe"
			commandLine("cmd", "/k", exeDir+exeFile) // start command in cmd shell
        }
        Os.isFamily(Os.FAMILY_MAC) -> {
            exeFile = "SocketClientTest"
			commandLine(exeDir+exeFile, port) // start command in cmd shell
        }
        Os.isFamily(Os.FAMILY_UNIX) -> {
            exeFile = "SocketClientTest"
			commandLine("bash", "-c", exeDir+exeFile) // start command in cmd shell
        }
        else -> { throw GradleException(":cpp:SocketClientTest run-target -> unknown OS family encountered")}
    }
}


