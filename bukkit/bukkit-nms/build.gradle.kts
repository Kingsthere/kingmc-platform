val kingmc_version: String by project

group = "com.kingmc.platform"
version = kingmc_version

plugins {
    id("java")
    `maven-publish`
}

java {
    withSourcesJar()
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
    // Configure repositories to publish
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("bukkit-nms") {
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