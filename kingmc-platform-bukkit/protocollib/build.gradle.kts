dependencies {
    api(Libs.PROTOCOLLIB)
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit NMS Implementation")
                artifactId = "${rootProject.name}-bukkit-protocollib"
            }
        }
    }
}