package kingmc.platform.bukkit.audience

import kingmc.common.context.annotation.Component
import kingmc.platform.audience.Audience
import kingmc.platform.audience.AudienceFactory
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.command.CommandSender
import kingmc.platform.entity.player.Player

/**
 * Represent an audience factory use
 * in bukkit servers. This interface is
 * not implement default, such as:
 *  + [Adventure api implement][kingmc.platform.bukkit.audience.adventure.AdventureAudienceFactory]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see AudienceFactory
 */
@Component
interface BukkitAudienceFactory : AudienceFactory {
    /**
     * Convert a bukkit command sender into audience type
     *
     * @since 0.0.3
     * @see _BukkitCommandSender
     * @see CommandSender
     */
    @Deprecated("CommandSenders are no longer supplied by the AudienceFactory, use World.getPlayer(uuid) instead")
    fun commandSender(commandSender: _BukkitCommandSender): CommandSender

    /**
     * Gets a player from original bukkit
     * player
     *
     * @since 0.0.3
     * @see Player
     * @see _BukkitCommandSender
     */
    @Deprecated("Entities such as player are no longer supplied by the AudienceFactory, use World.getPlayer(uuid) instead")
    fun player(bukkitPlayer: _BukkitPlayer): Audience
}