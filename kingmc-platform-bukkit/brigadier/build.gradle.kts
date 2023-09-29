dependencies {
    api(project(":${rootProject.name}-bukkit:api"))
    api(project(":${rootProject.name}-bukkit:impl"))
    api(Libs.BRIGADIER)
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit Brigadier")
                artifactId = "${rootProject.name}-bukkit-brigadier"
            }
        }
    }
}