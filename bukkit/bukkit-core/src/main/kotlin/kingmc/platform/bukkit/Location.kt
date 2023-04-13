package kingmc.platform.bukkit

import kingmc.common.application.Application
import kingmc.platform.Direction
import kingmc.platform.Location
import kingmc.platform.platform

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

// @WithApplication use parameter [application] instead
fun _BukkitLocation.asKingMC(application: Application): Location {
    this.world?.let {
        return application.platform.locations.createLocation(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch),
            it.asKingMC(application)
        )
    } ?: let {
        return application.platform.locations.createLocation(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch)
        )
    }
}