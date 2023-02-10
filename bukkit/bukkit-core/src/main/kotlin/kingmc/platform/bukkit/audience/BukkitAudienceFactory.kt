package kingmc.platform.bukkit.audience

import kingmc.platform.audience.AudienceFactory
import kingmc.platform.audience.CommandSender
import kingmc.platform.audience.Player

/**
 * Represent a audience factory use
 * in bukkit servers. This interface is
 * not implement default, current using:
 *  + [Adventure api implement][kingmc.platform.bukkit.compat.adventure.BukkitAudiences]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see AudienceFactory
 */
interface BukkitAudienceFactory : AudienceFactory {
    /**
     * Convert a bukkit command sender into audience type
     *
     * @since 0.0.3
     * @see OriginalBukkitCommandSender
     * @see CommandSender
     */
    fun commandSender(commandSender: OriginalBukkitCommandSender): CommandSender

    /**
     * Gets a player from original bukkit
     * player
     *
     * @since 0.0.3
     * @see Player
     * @see OriginalBukkitCommandSender
     */
    fun player(bukkitPlayer: OriginalBukkitPlayer): Player

    /**
     * Gets a audience from original bukkit block
     *
     * @since 0.0.5
     * @see OriginalBukkitBlockCommandSender
     */
    fun block(bukkitBlock: OriginalBukkitBlockCommandSender): CommandSender
}