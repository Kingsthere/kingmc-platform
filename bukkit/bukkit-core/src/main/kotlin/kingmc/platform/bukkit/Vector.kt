package kingmc.platform.bukkit

import kingmc.platform.Vector

typealias OriginalBukkitVector = org.bukkit.util.Vector

fun Vector.toBukkit(): OriginalBukkitVector =
    OriginalBukkitVector(
        this.x,
        this.y,
        this.z
    )

fun OriginalBukkitVector.fromBukkit(): Vector =
    Vector(
        this.x,
        this.y,
        this.z
    )