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
    api(project(":proxy"))
    api(project(":facet"))
    api("com.velocitypowered:velocity-api:3.1.1")
    // implementation(files("resources/velocity-3.2.0-SNAPSHOT-258.jar")) velocity jar implementation
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
    sourcesJar {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
    withType<ShadowJar> {
        archiveBaseName.set("kingmc-velocity")
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
            include(project(":facet"))
            include(project(":proxy"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib:1.9.0"))
            include(dependency("me.lucko:jar-relocator:1.5"))
            include(dependency("net.kyori:adventure-text-logger-slf4j:4.12.0"))
            include(dependency("commons-io:commons-io:2.11.0"))
            val ktil = "0.1"
            include(dependency("com.kingsthere.ktil:common:$ktil"))
            include(dependency("com.kingsthere.ktil:annotation:$ktil"))
        }
        relocate("me.lucko", "kingmc.library")
        relocate("org.objectweb.asm", "kingmc.library.asm")
        from(sourceSets.main.get().output)
    }
    build {
        dependsOn("shadowJar")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}