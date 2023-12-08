plugins {
    id("twomartens.spring-boot")
    id("twomartens.kotlin")
    kotlin("kapt")
}

dependencies {
    implementation(libs.spring.boot.shell)
}
