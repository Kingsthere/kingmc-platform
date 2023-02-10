val kingmc_version: String by project

group = "com.kingmc.platform"
version = kingmc_version

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    `maven-publish`
}

java {
    withSourcesJar()
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        name = "spigot-repo"
    }
    maven {
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
        name = "sonatype-oss-snapshots"
    }
    maven {
        url = uri("https://repo.codemc.org/repository/maven-public/")
        name = "codemc-repo"
    }
    maven {
        url = uri("https://repo.dmulloy2.net/repository/public/")
    }
    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

publishing {
    // Configure repositories to publish
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("bukkit-core") {
            groupId = group.toString()
            artifactId = project.name
            version = version

            from(components["java"])
        }

    }
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}