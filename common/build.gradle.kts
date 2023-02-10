val kingmc_version: String by project
val kingmc_test_version: String by project
val nbtapi_version: String by project

group = "com.kingmc.platform"
version = kingmc_version

plugins {
    id("java")
    `maven-publish`
}

java {
    withSourcesJar()
}

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://repo.codemc.org/repository/maven-public/")
        name = "codemc-repo"
    }
}

dependencies {
    api("com.kingmc.common:common:$kingmc_version")
    api("com.kingmc.common:application:$kingmc_version")
    api("com.kingmc.common:context:$kingmc_version")
    api("com.kingmc.common:coroutine:$kingmc_version")
    api("com.kingmc.common:configure:$kingmc_version")
    api("com.kingmc.common:environment:$kingmc_version")
    api("com.kingmc.common:file:$kingmc_version")
    api("com.kingmc.common:logging:$kingmc_version")
    api("com.kingmc.common:structure:$kingmc_version")
    api("de.tr7zw:item-nbt-api:$nbtapi_version")
    api(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testApi("com.kingmc:common:$kingmc_test_version")
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin", "src/main/java")
    }
}

publishing {
    repositories {
        mavenLocal()
    }

    publications {
        create<MavenPublication>("common") {
            groupId = "com.kingmc.platform"
            artifactId = "common"
            version = version

            from(components.getByName("java"))
        }

    }
}