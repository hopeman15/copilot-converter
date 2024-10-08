plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    kotlin("plugin.serialization") version libs.versions.kotlin.get()

    // Quality Gate
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.kover)
}

group = "com.hellocuriosity"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.serialization)
    testImplementation(kotlin("test"))
    testImplementation(libs.forge)
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}
