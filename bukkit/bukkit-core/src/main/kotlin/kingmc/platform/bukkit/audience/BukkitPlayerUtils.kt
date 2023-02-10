package kingmc.platform.bukkit.audience

import kingmc.platform.audience.playerWith

/**
 * Convert a [OriginalBukkitPlayer] to a [BukkitPlayer]
 */
fun playerFromBukkit(player: OriginalBukkitPlayer) =
    playerWith { this fromBukkit player }!!