dependencies {
    implementation(project(":${rootProject.name}-bukkit:api"))
    implementation(project(":${rootProject.name}-bukkit:adventure"))
    implementation(project(":${rootProject.name}-bukkit:brigadier"))
    implementation(project(":${rootProject.name}-bukkit:impl"))
    compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
}
