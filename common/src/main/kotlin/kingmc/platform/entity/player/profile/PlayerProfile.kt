package kingmc.platform.entity.player.profile

import java.util.*

/**
 * An interface for the profile of a `Player`
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface PlayerProfile {
    /**
     * The unique id of this player profile
     */
    val uniqueId: UUID

    /**
     * The name of this player profile
     */
    val name: String

    /**
     * The texture of this player profile
     */
    val textures: PlayerTextures
}