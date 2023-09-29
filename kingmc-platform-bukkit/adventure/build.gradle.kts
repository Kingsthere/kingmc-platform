dependencies {
    api(project(":${rootProject.name}-bukkit:api"))
    api(project(":${rootProject.name}-bukkit:impl"))
    api(Libs.ADVENTURE_PLATFORM_BUKKIT)
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit NMS Implementation")
                artifactId = "${rootProject.name}-bukkit-adventure"
            }
        }
    }
}