package kingmc.platform.bukkit.entity.player

import kingmc.platform.bukkit.getPlayerForBukkit
import kingmc.platform.entity.player.Player

/**
 * Convert this [org.bukkit.entity.Player] to an [Player]
 *
 * @receiver the entity to convert to
 * @since 0.0.7
 * @author kingsthere
 */
fun _BukkitPlayer.asKingMC(): Player = getPlayerForBukkit(this)