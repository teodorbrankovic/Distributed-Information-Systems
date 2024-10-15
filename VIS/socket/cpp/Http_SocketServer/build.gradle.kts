import org.apache.tools.ant.taskdefs.condition.Os

plugins {
   id("cpp-application")
}

val port: String = "4949"
// task to run cpp server
tasks.register("run", Exec::class) {
    dependsOn("build")         // make sure project has been built
    group = "application"       // set task group
    standardInput = System.`in`    // enable commandline input
    val exeDir:  String = "${buildDir}/exe/main/debug/"
    var exeFile: String? = null
    when {
        Os.isFamily(Os.FAMILY_WINDOWS) -> {
            exeFile = "Http_SocketServer.exe"
			commandLine("cmd", "/k", exeDir+exeFile) // start command in cmd shell
        }
        Os.isFamily(Os.FAMILY_MAC) -> {
            exeFile = "Http_SocketServer"
			commandLine(exeDir+exeFile, port) // start command in cmd shell
        }
        Os.isFamily(Os.FAMILY_UNIX) -> {
            exeFile = "Http_SocketServer"
			commandLine("bash", "-c", exeDir+exeFile) // start command in cmd shell
        }
        else -> { throw GradleException(":cpp:Http_SocketServer run-target -> unknown OS family encountered")}
    }
}

// task to kill cpp server
tasks.register("kill", Exec::class) {
    group = "application"                // set task group
    when {
        Os.isFamily(Os.FAMILY_WINDOWS) -> {
            commandLine("cmd", "/k", "for /f \"tokens=5\" %a in ('netstat -aon ^| findstr ${port}') do taskkill /F /PID %a")
        }
        Os.isFamily(Os.FAMILY_MAC) -> {
            commandLine("bash", "-c", "lsof -nti:${port} | xargs kill -9")
        }
        Os.isFamily(Os.FAMILY_UNIX) -> {
            commandLine("bash", "-c", "lsof -nti:${port} | xargs kill -9")
            // commandLine("sh", "-c", "kill \"\$(lsof -t -i:$port)\"") // possible alternative
        }
        else -> { throw GradleException(":cpp:Http_SocketServer kill-target -> unknown OS family encountered")}
    }
}

tasks["run"].dependsOn("kill") // kill any running instances before starting new one
tasks["build"].dependsOn("kill") // kill any running instances before starting new one
