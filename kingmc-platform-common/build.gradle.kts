publishing {
    publications {
        named("kingmc", MavenPublication::class) {
            pom {
                name.set("KingMC Platform Common")
            }
        }
    }
}

dependencies {
    implementation("com.google.guava:guava-io:r03")
}