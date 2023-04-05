package kingmc.platform.entity.player.profile

import java.net.URL

/**
 * An interface provide access to a player's texture, includes
 *  + The cape of the player
 *  + The skin of the player
 *  + The skin model of the player
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface PlayerTextures {
    /**
     * The skin of this player, the [URL] that pointing to the skin
     */
    val skin: URL

    /**
     * The cape of this player, the [URL] that pointing to the cape
     */
    val cape: URL

    /**
     * The skin model of this player
     */
    val skinModel: SkinModel

    /**
     * The Minecraft skin models
     */
    enum class SkinModel {
        /**
         * The classic Minecraft skin model
         */
        CLASSIC,

        /**
         * The slim model has slimmer arms than the classic model
         */
        SLIM
    }
}