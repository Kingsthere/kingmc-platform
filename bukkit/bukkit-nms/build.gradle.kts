val kingmc_platform_version: String by project

group = "net.kingmc.platform"
version = kingmc_platform_version

plugins {
    id("java")
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

subprojects {
    dependencies {
        implementation(project(":bukkit:bukkit-core"))
        implementation(project(":bukkit:bukkit-brigadier"))
    }
}

publishing {
    publications {
        create<MavenPublication>("bukkit-nms") {
//                groupId = "net.kingmc"
//                artifactId = "platform"
//                version = version.toString()

            pom {
                name.set("KingMC Platform Bukkit NMS")
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
    sign(publishing.publications["bukkit-nms"])
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}