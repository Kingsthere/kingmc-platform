dependencies {
    api(project(":${rootProject.name}-velocity:api"))
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Velocity Impl")
                artifactId = "${rootProject.name}-velocity-impl"
            }
        }
    }
}