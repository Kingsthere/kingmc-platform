dependencies {
    api(Libs.VELOCITY_API)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Velocity API")
                artifactId = "${rootProject.name}-velocity-api"
            }
        }
    }
}