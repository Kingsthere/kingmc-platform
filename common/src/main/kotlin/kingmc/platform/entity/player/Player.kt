package kingmc.platform.entity.player

import kingmc.common.text.Text
import kingmc.platform.audience.HumanAudience
import kingmc.platform.audience.particle.ParticleRecipient
import kingmc.platform.entity.Entity
import kingmc.platform.messaging.PluginMessageRecipient

/**
 * Represent a online minecraft player
 *
 * @since 0.0.3
 * @author kingsthere
 * @see HumanAudience
 */
interface Player : Entity, OfflinePlayer, ParticleRecipient, HumanAudience, PluginMessageRecipient {
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
    val displayName: Text

    /**
     * `true` if this player is sneaking
     */
    var isSneaking: Boolean

    /**
     * `true` if this player is sprinting
     */
    var isSprinting: Boolean
}