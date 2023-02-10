package kingmc.platform.bukkit.audience

import kingmc.platform.audience.text.Mark
import kingmc.platform.audience.text.Text

/**
 * Represent a bukkit player is currently connected to this server
 *
 * @since 0.0.3
 * @author kingsthere
 * @param player the player in original bukkit
 * @see BukkitTunnel
 * @see BukkitPlayer
 * @see OriginalBukkitPlayer
 */
abstract class OnlineBukkitPlayer(val player: OriginalBukkitPlayer) : BukkitPlayer() {
    override fun text(text: Text, vararg marks: Mark) {
        this.text(text)
    }
}