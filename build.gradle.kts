import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kingmc_platform_version: String by project
val kingmc_common_version: String by project
val ossrhUsername: String by project
val ossrhPassword: String by project
val kingmc_test_version: String by project

group = "net.kingmc"
version = kingmc_platform_version

plugins {
    `maven-publish`
    signing
    kotlin("jvm") version "1.9.0"
}

allprojects {
    apply(plugin = "org.gradle.signing")
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

        withSourcesJar()
        withJavadocJar()
    }

    repositories {
        // Central repository
        mavenCentral()
        // Maven local for dev
        mavenLocal()
        // Snapshot repository
         maven {
             url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
             name = "sonatype-snapshot"
         }

        maven {
            url = uri("https://repo.codemc.org/repository/maven-public/")
            name = "codemc-repo"
        }
    }

    dependencies {
        // KingMC Dependencies
        api("net.kingmc.common:common:$kingmc_common_version")
        api("net.kingmc.common:application:$kingmc_common_version")
        api("net.kingmc.common:context:$kingmc_common_version")
        api("net.kingmc.common:coroutine:$kingmc_common_version")
        api("net.kingmc.common:configure:$kingmc_common_version")
        api("net.kingmc.common:environment:$kingmc_common_version")
        api("net.kingmc.common:file:$kingmc_common_version")
        api("net.kingmc.common:logging:$kingmc_common_version")
        api("net.kingmc.common:structure:$kingmc_common_version")
        implementation("io.github.classgraph:classgraph:4.8.158")
        api(kotlin("reflect"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
        testApi("net.kingmc.common:common:$kingmc_test_version")
    }

    publishing {
        repositories {
            mavenLocal()
            maven {
                name = "Snapshot"
                url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots/")
                credentials {
                    username = ossrhUsername
                    password = ossrhPassword
                }
            }
            maven {
                name = "Release"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = ossrhUsername
                    password = ossrhPassword
                }
            }
        }

    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
            // useK2 = true
        }
    }

    tasks.withType<Javadoc> {
        options {
            encoding = "UTF-8"
            isFailOnError = false
            quiet()
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

dependencies {
    api(project(":bukkit"))
    api(project(":common"))
}