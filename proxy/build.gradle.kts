val kingmc_platform_version: String by project

group = "net.kingmc.platform"
version = kingmc_platform_version

plugins {
    id("java")
}

dependencies {
    implementation(project(":common"))
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
        create<MavenPublication>("proxy") {
            groupId = group.toString()
            artifactId = project.name
            version = version

            from(components["java"])
        }

    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}