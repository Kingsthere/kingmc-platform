import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

dependencies {
    implementation(project(":${rootProject.name}-bukkit:api"))
    implementation(project(":${rootProject.name}-bukkit:adventure"))
    implementation(project(":${rootProject.name}-bukkit:brigadier"))
    implementation(project(":${rootProject.name}-bukkit:impl"))
    implementation("org.spigotmc:spigot:1.20.1-R0.1-SNAPSHOT")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}