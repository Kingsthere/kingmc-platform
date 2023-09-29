import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val ossrhUsername: String by project
val ossrhPassword: String by project

plugins {
    `maven-publish`
    signing
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("org.jetbrains.dokka") version "1.9.0"
    kotlin("jvm") version KOTLIN_VERSION
}

allprojects {
    group = "net.kingmc"
    version = KINGMC_VERSION

    apply(plugin = "java")
    apply(plugin = "org.gradle.signing")
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "org.jetbrains.dokka")

    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            name = "papermc"
            url = uri("https://papermc.io/repo/repository/maven-releases/")
        }
        maven {
            name = "minecraft"
            url = uri("https://libraries.minecraft.net")
        }
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8

        withSourcesJar()
        withJavadocJar()
    }

    dependencies {
        // KingMC Dependencies
        api("net.kingmc:kingmc-common:$KINGMC_VERSION")

        testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
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
                pom {
                    name.set("KingMC Platform")
                    packaging = "jar"
                    description.set("A high performance minecraft plugin framework")
                    url.set("https://www.kingmc.net")

                    scm {
                        url.set("https://github.com/Kingsthere/kingmc-platform.git")
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

    signing {
        sign(publishing.publications["kingmc"])
    }

    sourceSets {
        main {
            java.srcDirs("src/main/kotlin", "src/main/java")
        }
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                // useK2 = true
            }
        }

        withType<Javadoc> {
            options {
                encoding = "UTF-8"
                isFailOnError = false
                quiet()
            }
        }

        withType<Test> {
            useJUnitPlatform()
        }

        withType<ShadowJar> {
            archiveClassifier.set("plugin")
            relocate("me.lucko.jarrelocator", "kingmc.library.jarrelocator")
            relocate("de.tr7zw.changeme.nbtapi", "kingmc.library.nbtapi")
        }
    }

}

extra["kotlin.code.style"] = "official"