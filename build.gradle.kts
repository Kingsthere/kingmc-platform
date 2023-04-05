import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kingmc_version: String by project
val ossrhUsername: String by project
val ossrhPassword: String by project
val kingmc_test_version: String by project
val nbtapi_version: String by project

group = "net.kingmc"
version = kingmc_version

plugins {
    `maven-publish`
    signing
    kotlin("jvm") version "1.8.10"
}

allprojects {
    apply(plugin = "org.gradle.signing")
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
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
        api("net.kingmc.common:common:$kingmc_version")
        api("net.kingmc.common:application:$kingmc_version")
        api("net.kingmc.common:context:$kingmc_version")
        api("net.kingmc.common:coroutine:$kingmc_version")
        api("net.kingmc.common:configure:$kingmc_version")
        api("net.kingmc.common:environment:$kingmc_version")
        api("net.kingmc.common:file:$kingmc_version")
        api("net.kingmc.common:logging:$kingmc_version")
        api("net.kingmc.common:structure:$kingmc_version")
        api("de.tr7zw:item-nbt-api:$nbtapi_version")
        api(kotlin("reflect"))
        testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
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

        publications {
            create<MavenPublication>("kingmc") {
//                groupId = "net.kingmc"
//                artifactId = "platform"
//                version = version.toString()

                pom {
                    name.set("KingMC Platform")
                    packaging = "jar"
                    description.set("A high performance minecraft plugin framework")
                    url.set("https://www.kingmc.net")

                    scm {
                        url.set("https://github.com/Kingsthere/kingmc-common.git")
                    }

                    licenses {
                        license {
                            name.set("The MIT License")
                            url.set("https://mit-license.org/")
                        }
                    }

                    developers {
                        developer {
                            id.set("kingsthere")
                            name.set("Kingsthere")
                            email.set("kingsthere0@hotmail.com")
                        }
                    }
                }

                from(components.getByName("java"))
            }

        }
    }

    java {
        withSourcesJar()
        withJavadocJar()
    }

    signing {
        sign(publishing.publications["kingmc"])
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
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