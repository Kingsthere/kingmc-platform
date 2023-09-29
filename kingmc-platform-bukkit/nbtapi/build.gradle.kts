dependencies {
    implementation(project(":${rootProject.name}-bukkit:api"))
    implementation(project(":${rootProject.name}-bukkit:impl"))
    implementation(Libs.NBTAPI)
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit NBT-API")
                artifactId = "${rootProject.name}-bukkit-nbtapi"
            }
        }
    }
}