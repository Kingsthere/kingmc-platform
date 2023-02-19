val kingmc_version: String by project

group = "net.kingmc.platform"
version = kingmc_version

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

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}