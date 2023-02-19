val kingmc_version: String by project

group = "net.kingmc.platform"
version = kingmc_version

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}