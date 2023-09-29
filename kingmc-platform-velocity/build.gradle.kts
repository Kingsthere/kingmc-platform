import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("kapt")
}

allprojects {
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
        api(project(":${rootProject.name}-common"))
        api(project(":${rootProject.name}-proxy"))
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
    runtimeOnly(project(":${rootProject.name}-velocity:api"))
    runtimeOnly(project(":${rootProject.name}-velocity:impl"))
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Velocity")
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