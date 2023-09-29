import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

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
        maven {
            url = uri("https://repo.dmulloy2.net/repository/public/")
        }
    }

    dependencies {
        api(project(":${rootProject.name}-common"))
        api(project(":${rootProject.name}-facet"))
    }
}

sourceSets.main {
    java.srcDirs("src/main/java", "src/main/kotlin")
}

tasks.withType<Jar> {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

dependencies {
    runtimeOnly(project(":${rootProject.name}-bukkit:adventure"))
    runtimeOnly(project(":${rootProject.name}-bukkit:api"))
    runtimeOnly(project(":${rootProject.name}-bukkit:brigadier"))
    runtimeOnly(project(":${rootProject.name}-bukkit:impl"))
    runtimeOnly(project(":${rootProject.name}-bukkit:nbtapi"))
    runtimeOnly(project(":${rootProject.name}-bukkit:paperlib"))
    runtimeOnly(project(":${rootProject.name}-bukkit:protocollib"))
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit")
            }
        }
    }
}

tasks {
    withType<ShadowJar> {
        dependencies {
            include(project(":${rootProject.name}-bukkit:adventure"))
            include(project(":${rootProject.name}-bukkit:api"))
            include(project(":${rootProject.name}-bukkit:brigadier"))
            include(project(":${rootProject.name}-bukkit:impl"))
            include(project(":${rootProject.name}-bukkit:nbtapi"))
            include(project(":${rootProject.name}-bukkit:paperlib"))
            include(project(":${rootProject.name}-bukkit:protocollib"))
            include(dependency("net.kingmc:kingmc-common-api:$KINGMC_VERSION"))
            include(dependency("net.kingmc:kingmc-common-application:$KINGMC_VERSION"))
            include(dependency("net.kingmc:kingmc-common-context:$KINGMC_VERSION"))
            include(dependency("net.kingmc:kingmc-common-coroutine:$KINGMC_VERSION"))
            include(dependency("net.kingmc:kingmc-common-configure:$KINGMC_VERSION"))
            include(dependency("net.kingmc:kingmc-common-environment:$KINGMC_VERSION"))
            include(dependency("net.kingmc:kingmc-common-file:$KINGMC_VERSION"))
            include(dependency("net.kingmc:kingmc-common-logging:$KINGMC_VERSION"))
            include(project(":${rootProject.name}-common"))
            include(project(":${rootProject.name}-facet"))
            include(dependency("me.lucko:jar-relocator:1.5"))
            include(dependency("org.jetbrains.kotlin:kotlin-stdlib:$KOTLIN_VERSION"))
        }
        from(sourceSets.main.get().output)
        // with(jar)
    }
    build {
        dependsOn("shadowJar")
    }
}