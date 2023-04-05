package kingmc.platform.bukkit.audience

import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.command.CommandSender

/**
 * Extended `CommandSender` exposed an interface to convert this into a `org.bukkit.command.CommandSender`
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface BukkitCommandSender : CommandSender {
    /**
     * Convert this bukkit command sender to a `org.bukkit.command.CommandSender`
     */
    fun toBukkitCommandSender(): _BukkitCommandSender
}