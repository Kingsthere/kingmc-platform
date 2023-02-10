import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kingmc_version: String by project

group = "com.kingmc.platform"
version = kingmc_version

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.typecraft.gradlesource.spigot") version "1.0.0"
    `maven-publish`
}

java {
    withSourcesJar()
}

repositories {
    maven {
        url = uri("https://repo.codemc.io/repository/nms/")
    }
}

dependencies {
    // api("com.kingmc.platform:bukkit-nms-1_19_2:0.0.4:obfuscated")
    api(project(":bukkit:bukkit-nms:bukkit-nms-1_19_2"))
    api(project(":bukkit:bukkit-nbtapi"))
    api(project(":bukkit:bukkit-brigadier"))
    api(project(":bukkit:bukkit-core"))
    api(project(":common"))
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven {
            url = uri("https://libraries.minecraft.net")
        }
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
            url = uri("https://repo.papermc.io/repository/maven-public/")
        }
    }

    dependencies {
        implementation(group = "com.comphenix.protocol", name = "ProtocolLib", version = "4.8.0")
        api("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
        implementation("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
        implementation("net.kyori:adventure-platform-bukkit:4.2.0")
        implementation("com.mojang:brigadier:1.0.18")
        implementation("com.kingmc.platform:common:0.0.4")
        implementation("io.papermc:paperlib:1.0.7")
        testImplementation("org.junit.jupiter:junit-jupiter:5.9.2")
    }
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

spigotRemap {
    spigotVersion.set("1.19.2")
    sourceJarTask.set(tasks.shadowJar)
}

publishing {
    // Configure repositories to publish
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("bukkit") {
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

tasks {
    withType<ShadowJar> {
        archiveBaseName.set("kingmc-bukkit")
        dependencies {
            include(dependency("com.kingmc.common:common:$kingmc_version"))
            include(dependency("com.kingmc.common:application:$kingmc_version"))
            include(dependency("com.kingmc.common:boot:$kingmc_version"))
            include(dependency("com.kingmc.common:context:$kingmc_version"))
            include(dependency("com.kingmc.common:coroutine:$kingmc_version"))
            include(dependency("com.kingmc.common:configure:$kingmc_version"))
            include(dependency("com.kingmc.common:environment:$kingmc_version"))
            include(dependency("com.kingmc.common:file:$kingmc_version"))
            include(dependency("com.kingmc.common:logging:$kingmc_version"))
            include(dependency("com.kingmc.common:structure:$kingmc_version"))
            include(dependency("com.kingmc.common:script:$kingmc_version"))
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
        relocate("de.tr7zw.changeme.nbtapi", "kingmc.platform.nbt")
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