plugins {
    kotlin("jvm") version libs.versions.kotlin.get()

    // Quality Gate
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter)
}

group = "com.hellocuriosity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
