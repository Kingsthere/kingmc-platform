val kingmc_platform_version: String by project

group = "net.kingmc.platform"
version = kingmc_platform_version

val nbtapi_version: String by project

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

dependencies {
    implementation("de.tr7zw:item-nbt-api:$nbtapi_version")
}

publishing {
    publications {
        create<MavenPublication>("bukkit-core") {
//                groupId = "net.kingmc"
//                artifactId = "platform"
//                version = version.toString()

            pom {
                name.set("KingMC Platform Bukkit Core")
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
    sign(publishing.publications["bukkit-core"])
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}