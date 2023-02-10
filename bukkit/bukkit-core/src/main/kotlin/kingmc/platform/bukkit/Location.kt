package kingmc.platform.bukkit

import kingmc.platform.Direction
import kingmc.platform.Location

typealias OriginalBukkitLocation = org.bukkit.Location

fun Location.toBukkit(): OriginalBukkitLocation =
    OriginalBukkitLocation(
        this.world?.toBukkit(),
        this.x,
        this.y,
        this.z,
        this.direction.yaw,
        this.direction.pitch
    )

fun OriginalBukkitLocation.fromBukkit(): Location {
    this.world?.let {
        return bukkitPlatform.locations.of(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch),
            it.fromBukkit()
        )
    } ?: let {
        return bukkitPlatform.locations.of(
            this.x,
            this.y,
            this.z,
            Direction(this.yaw, this.pitch)
        )
    }
}