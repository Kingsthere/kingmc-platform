val kingmc_version: String by project

group = "com.kingmc"
version = kingmc_version

plugins {
    `maven-publish`
    kotlin("jvm") version "1.7.20"
}

subprojects {
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

repositories {
    mavenLocal()
    mavenCentral()
}
