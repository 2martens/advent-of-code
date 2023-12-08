import java.nio.file.Files

plugins {
    id("twomartens.base")
}

apply(plugin="com.netflix.nebula.release")

tasks.register("writeVersionProperties") {
    group = "version"
    mustRunAfter("release")
    outputs.file(layout.buildDirectory.file("version.properties"))
    val directory = layout.buildDirectory.asFile.get()
    doLast {
        Files.createDirectories(directory.toPath())
        File("${directory.toPath()}/version.properties").writeText("VERSION=${project.version}\n")
    }
}