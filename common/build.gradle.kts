val kingmc_version: String by project

group = "net.kingmc.platform"
version = kingmc_version

plugins {
    id("java")
}

sourceSets {
    main {
        java.srcDirs("src/main/kotlin", "src/main/java")
    }
}