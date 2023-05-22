package kingmc.platform.bukkit

import kingmc.platform.Vector

typealias _BukkitVector = org.bukkit.util.Vector

fun Vector.asBukkit(): _BukkitVector =
    _BukkitVector(
        this.x,
        this.y,
        this.z
    )

// @WithApplication use parameter [application] instead
fun _BukkitVector.asKingMC(): Vector =
    Vector(
        this.x,
        this.y,
        this.z
    )