package kingmc.platform.bukkit

import kingmc.common.application.Application
import kingmc.platform.Direction
import kingmc.platform.Location
import kingmc.platform.Location3D
import kingmc.platform.bukkit.asBukkit
import kingmc.platform.bukkit.asKingMC

typealias _BukkitLocation = org.bukkit.Location

fun Location.asBukkit(): _BukkitLocation =
    _BukkitLocation(
        this.world?.asBukkit(),
        this.x,
        this.y,
        this.z,
        this.direction.yaw,
        this.direction.pitch
    )

fun Location3D.asBukkit(): _BukkitLocation =
    _BukkitLocation(
        null,
        this.x,
        this.y,
        this.z,
    )

// @WithApplication use parameter [application] instead
fun _BukkitLocation.asKingMC(application: Application): Location {
    this.world?.let {
        return Location(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch),
            it.asKingMC(application)
        )
    } ?: let {
        return Location(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch)
        )
    }
}

// @WithApplication use parameter [application] instead
fun _BukkitLocation.asKingMCLocation3D(): Location3D {
    return Location3D(
        this.x,
        this.y,
        this.z
    )
}