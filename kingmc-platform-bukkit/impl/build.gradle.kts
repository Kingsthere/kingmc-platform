dependencies {
    api(project(":${rootProject.name}-bukkit:api"))
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit Impl")
                artifactId = "${rootProject.name}-bukkit-impl"
            }
        }
    }
}