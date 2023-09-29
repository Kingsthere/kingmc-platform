dependencies {
    implementation(project(":${rootProject.name}-common"))
}

publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Facet")
            }
        }
    }
}