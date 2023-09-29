package kingmc.platform.entity.player

import kingmc.common.text.Text
import kingmc.platform.audience.particle.ParticleRecipient
import kingmc.platform.entity.HumanEntity
import kingmc.platform.messaging.PluginMessageSink
import kingmc.platform.messaging.PluginMessageSource
import kingmc.platform.permission.Permissible
import kingmc.platform.util.InboundConnection

/**
 * Represent an online minecraft player
 *
 * @author kingsthere
 * @since 0.0.4
 * @see HumanEntity
 */
interface Player : HumanEntity, OfflinePlayer, ParticleRecipient, PluginMessageSink, PluginMessageSource, Permissible, InboundConnection {
    @Deprecated(
        """This value is controlled only by the client and is therefore unreliable
                            and vulnerable to spoofing and/or desync depending on the context/time 
                            which it is accessed"""
    )
    override val isOnGround: Boolean

    /**
     * The display name of this player, unlike [customName], it works for the player-list and
     * the scoreboard
     */
    var displayName: Text

    /**
     * `true` if this player is sneaking
     */
    var isSneaking: Boolean

    /**
     * `true` if this player is sprinting
     */
    var isSprinting: Boolean

    /**
     * The current player's ping (in milliseconds)
     */
    val ping: Long

    /**
     * The player's client brand
     */
    val clientBrand: String?

    /**
     * Disconnects the player by the given reason
     *
     * @param reason reason to disconnect the player
     */
    fun disconnect(reason: Text)
}