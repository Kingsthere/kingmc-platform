val kingmc_version: String by project

group = "com.kingmc.platform"
version = kingmc_version

val spigot_version: String by project

plugins {
    id("java")
    `maven-publish`
}

repositories {
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        name = "spigot-repo"
    }
    maven {
        url = uri("https://repo.codemc.io/repository/nms/")
    }
    mavenCentral()
}

java {
    withSourcesJar()
}

dependencies {
    compileOnly("org.spigotmc:spigot:$spigot_version:remapped-mojang")
    // Junit test
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
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
        create<MavenPublication>("bukkit-nms-1_19_2") {
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