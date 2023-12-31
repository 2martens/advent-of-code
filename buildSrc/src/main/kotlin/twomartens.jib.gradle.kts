plugins {
    id("com.google.cloud.tools.jib")
    id("twomartens.java-base")
}

tasks.named("jib") {
    dependsOn("build")
}

tasks.named("jibDockerBuild") {
    dependsOn("build")
}

tasks.named("build") {
    dependsOn("cleanCache")
}

tasks.register("cleanCache") {
    delete("${layout.buildDirectory}/jib-cache")
    delete("${layout.buildDirectory}/libs")
}
