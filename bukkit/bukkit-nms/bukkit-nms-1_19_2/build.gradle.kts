val kingmc_version: String by project

group = "net.kingmc.platform"
version = kingmc_version

val spigot_version: String by project

plugins {
    `maven-publish`
}

dependencies {
    compileOnly("org.spigotmc:spigot:$spigot_version")
    // Junit test
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
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