val kingmc_platform_version: String by project

group = "net.kingmc.platform"
version = kingmc_platform_version

plugins {
    id("java")
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin", "src/main/java")
    }
}

publishing {
    publications {
        create<MavenPublication>("common") {
//          groupId = "net.kingmc"
//          artifactId = "platform"
//            version = "0.0.81-SNAPSHOT"

            pom {
                name.set("KingMC Platform Common")
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
    sign(publishing.publications["common"])
}