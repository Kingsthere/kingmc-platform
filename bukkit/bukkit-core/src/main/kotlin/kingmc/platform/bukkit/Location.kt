package kingmc.platform.bukkit

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.Direction
import kingmc.platform.Location
import kingmc.platform.platform

typealias _BukkitLocation = org.bukkit.Location

@WithApplication
fun Location.asBukkit(): _BukkitLocation =
    _BukkitLocation(
        this.world?.asBukkit(),
        this.x,
        this.y,
        this.z,
        this.direction.yaw,
        this.direction.pitch
    )

@WithApplication
fun _BukkitLocation.asKingMC(): Location {
    this.world?.let {
        return currentApplication().platform.locations.createLocation(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch),
            it.asKingMC()
        )
    } ?: let {
        return currentApplication().platform.locations.createLocation(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch)
        )
    }
}