package kingmc.platform.audience.sound

import kingmc.util.key.Key

/**
 * A sound and/or a sound source, used for stopping in-game sounds that
 * are being played on a game client matching the given sound and/or sound source
 *
 * @author kingsthere
 * @since 0.0.5
 */
interface SoundStop {
    /**
     * The type of sounds to stop
     */
    val type: Key?

    /**
     * The source of sounds to stop
     */
    val source: Sound.Source?

    companion object {
        val ALL = SoundStopImpl(null, null)
    }
}