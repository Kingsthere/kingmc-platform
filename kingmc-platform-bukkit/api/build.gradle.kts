dependencies {
    api(Libs.SPIGOT_API)
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit API")
                artifactId = "${rootProject.name}-bukkit-api"
            }
        }
    }
}