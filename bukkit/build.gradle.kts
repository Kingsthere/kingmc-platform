import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val kingmc_version: String by project

group = "net.kingmc.platform"
version = kingmc_version

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

dependencies {
    // api("net.kingmcplatform:bukkit-nms-1_19_2:0.0.4:obfuscated")
    api(project(":bukkit:bukkit-nms:bukkit-nms-1_19_2"))
    api(project(":bukkit:bukkit-nbtapi"))
    api(project(":bukkit:bukkit-brigadier"))
    api(project(":bukkit:bukkit-core"))
    api(project(":common"))
}

allprojects {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.codemc.io/repository/nms/")
        }
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
        api("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
        implementation("io.papermc.paper:paper-api:1.19.3-R0.1-SNAPSHOT")
        implementation("net.kyori:adventure-platform-bukkit:4.2.0")
        implementation("com.mojang:brigadier:1.0.18")
        implementation(project(":common"))
        implementation(project(":facet"))
        implementation("io.papermc:paperlib:1.0.7")
    }
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks {
    withType<ShadowJar> {
        archiveBaseName.set("kingmc-bukkit")
        dependencies {
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
            include(dependency("me.lucko:jar-relocator:1.5"))
            include(dependency("org.ow2.asm:asm:9.3"))
            include(dependency("org.ow2.asm:asm-util:9.3"))
            include(dependency("org.ow2.asm:asm-commons:9.3"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib:1.8.10"))
            include(project(":bukkit:bukkit-nms:bukkit-nms-1_19_2"))
            include(project(":bukkit:bukkit-brigadier"))
            include(project(":bukkit:bukkit-nbtapi"))
            include(project(":bukkit:bukkit-core"))
        }
        relocate("me.lucko", "kingmc.library")
        relocate("de.tr7zw.changeme.nbtapi", "kingmc.platform.bukkit.nbtapi")
        from(sourceSets.main.get().output)
    }
    build {
        dependsOn("shadowJar")
    }
}