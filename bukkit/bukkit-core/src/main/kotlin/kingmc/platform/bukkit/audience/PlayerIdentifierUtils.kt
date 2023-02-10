package kingmc.platform.bukkit.audience

import kingmc.platform.audience.PlayerIdentifier

/**
 * To specify a player by using bukkit player instance
 *
 * @since 0.0.3
 * @author kingsthere
 */
infix fun PlayerIdentifier.fromBukkit(player: OriginalBukkitPlayer) =
    BukkitPlayerIdentifier(this.name, this.uuid, player)
