import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kingmc_common_version: String by project
val kingmc_platform_version: String by project

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    kotlin("kapt")
    `maven-publish`
}

group = "net.kingmc.platform"
version = kingmc_platform_version

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        url = uri("https://repo.codemc.org/repository/maven-public/")
        name = "codemc-repo"
    }
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    api(project(":common"))
    api("com.velocitypowered:velocity-api:3.1.1")
    kapt("com.velocitypowered:velocity-api:3.1.1")
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
        create<MavenPublication>("velocity") {
            groupId = group.toString()
            artifactId = project.name
            version = version

            from(components["java"])
        }

    }
}

tasks {
    withType<ShadowJar> {
        archiveBaseName.set("kingmc-bukkit")
        dependencies {
            val kingmc_version = kingmc_common_version
            include(dependency("net.kingmc.common:common:$kingmc_version"))
            include(dependency("net.kingmc.common:application:$kingmc_version"))
            include(dependency("net.kingmc.common:boot:$kingmc_version"))
            include(dependency("net.kingmc.common:context:$kingmc_version"))
            include(dependency("net.kingmc.common:coroutine:$kingmc_version"))
            include(dependency("net.kingmc.common:configure:$kingmc_version"))
            include(dependency("net.kingmc.common:environment:$kingmc_version"))
            include(dependency("net.kingmc.common:file:$kingmc_version"))
            include(dependency("net.kingmc.common:logging:$kingmc_version"))
            include(dependency("net.kingmc.common:structure:$kingmc_version"))
            include(dependency("net.kingmc.common:script:$kingmc_version"))
            include(project(":common"))
            include(dependency("me.lucko:jar-relocator:1.5"))
            val ktil = "0.1"
            include(dependency("com.kingsthere.ktil:common:$ktil"))
            include(dependency("com.kingsthere.ktil:annotation:$ktil"))
            include(dependency("org.ow2.asm:asm:9.3"))
            include(dependency("org.ow2.asm:asm-util:9.3"))
            include(dependency("org.ow2.asm:asm-commons:9.3"))
            include(project(":bukkit:bukkit-nms:bukkit-nms-1_19_2"))
            include(project(":bukkit:bukkit-brigadier"))
            include(project(":bukkit:bukkit-nbtapi"))
            include(project(":bukkit:bukkit-core"))
        }
        relocate("me.lucko", "kingmc.library")
        relocate("de.tr7zw.changeme", "kingmc.platform.bukkit")
        relocate("dev.jorel", "kingmc.platform.bukkit.compatible")
        from(sourceSets.main.get().output)
    }
    build {
        dependsOn("shadowJar")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}