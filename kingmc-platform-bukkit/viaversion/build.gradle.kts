dependencies {
    api(project(":${rootProject.name}-bukkit:impl"))
    runtimeOnly(Libs.ADVENTURE_PLATFORM_VIAVERSION)
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit ViaVersion")
                artifactId = "${rootProject.name}-bukkit-viaversion"
            }
        }
    }
}