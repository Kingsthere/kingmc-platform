rootProject.name = "kingmc-platform"

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }
}

val prefix: String = rootProject.name

// Common platform implementation
include(":$prefix-common")

// Facet implementation
include(":$prefix-facet")

// Proxy-like platform implementation, such as bungeecord, velocity
include(":$prefix-proxy")

// Single platform implementation
include(
    ":$prefix-bukkit",
    ":$prefix-bukkit:adventure",
    ":$prefix-bukkit:api",
    ":$prefix-bukkit:brigadier",
    ":$prefix-bukkit:impl",
    ":$prefix-bukkit:nbtapi",
    ":$prefix-bukkit:nms",
    ":$prefix-bukkit:nms:common",
    ":$prefix-bukkit:nms:v1_8_8",
    ":$prefix-bukkit:nms:v1_19_2",
    ":$prefix-bukkit:nms:v1_20_1",
    ":$prefix-bukkit:protocollib",
    ":$prefix-bukkit:paperlib",
    ":$prefix-bukkit:viaversion",

    ":$prefix-velocity",
    ":$prefix-velocity:api",
    ":$prefix-velocity:impl"
)
