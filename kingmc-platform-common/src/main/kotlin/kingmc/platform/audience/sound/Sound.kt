package kingmc.platform.audience.sound

import com.google.errorprone.annotations.Immutable
import kingmc.util.builder.Buildable
import kingmc.util.key.Key

/**
 * Represents an in-game sound which can be played to the client
 *
 * @author kingsthere
 * @since 0.0.5
 */
@Immutable
interface Sound : Buildable<Sound, Sound.Builder> {
    /**
     * The type of this sound
     *
     * @since 0.0.5
     */
    val type: Key

    /**
     * The source of this sound
     *
     * @since 0.0.5
     */
    val source: Source

    /**
     * The volume of this sound
     *
     * @since 0.0.5
     */
    val volume: Float

    /**
     * The pitch of this sound, must between -1 - 1
     *
     * @since 0.0.5
     */
    val pitch: Float

    /**
     * The sound sources
     *
     * @since 0.0.5
     */
    enum class Source(val soundName: String) {
        MASTER("master"),
        MUSIC("music"),
        RECORD("record"),
        WEATHER("weather"),
        BLOCK("block"),
        HOSTILE("hostile"),
        NEUTRAL("neutral"),
        PLAYER("player"),
        AMBIENT("ambient"),
        VOICE("voice")
    }

    /**
     * A builder to build [Sound] instances
     *
     * @since 0.0.5
     */
    interface Builder : Buildable.Builder<Sound> {
        var type: Key
        var source: Source
        var volume: Float
        var pitch: Float

        /**
         * Set the type of the sound
         */
        fun type(type: Key): Builder

        /**
         * Set the source of the sound
         */
        fun source(source: Source): Builder

        /**
         * Set the volume of this sound
         */
        fun volume(volume: Float): Builder

        /**
         * Set the pitch of this sound
         */
        fun pitch(pitch: Float): Builder
    }
}