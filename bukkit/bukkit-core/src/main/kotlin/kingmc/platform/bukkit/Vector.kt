package kingmc.platform.bukkit

import kingmc.common.application.Application
import kingmc.platform.Vector
import kingmc.platform.platform

typealias _BukkitVector = org.bukkit.util.Vector

fun Vector.asBukkit(): _BukkitVector =
    _BukkitVector(
        this.x,
        this.y,
        this.z
    )

// @WithApplication use parameter [application] instead
fun _BukkitVector.asKingMC(application: Application): Vector =
    application.platform.vectors.create(
        this.x,
        this.y,
        this.z
    )