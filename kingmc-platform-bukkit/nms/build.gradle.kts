subprojects {
    dependencies {
        implementation(project(":${rootProject.name}-bukkit:api"))
        implementation(project(":${rootProject.name}-bukkit:impl"))
        implementation(project(":${rootProject.name}-bukkit:brigadier"))
    }
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Bukkit NMS Implementation")
                artifactId = "${rootProject.name}-bukkit-nms"
            }
        }
    }
}