package kingmc.platform.audience.sound

import kingmc.platform.Location
import kingmc.platform.audience.capable.SoundCapable
import kingmc.util.key.Key

/**
 * Play a configured sound
 *
 * @since 0.0.5
 * @author kingsthere
 */
fun SoundCapable.sound(builder: Sound.Builder.() -> Unit) {
    this.sound(Sound(builder))
}

/**
 * Play a configured sound at specifies [location]
 *
 * @since 0.0.5
 * @author kingsthere
 */
fun SoundCapable.sound(location: Location, builder: Sound.Builder.() -> Unit) {
    this.sound(Sound(builder))
}
/**
 * Create a [Sound]
 *
 * @since 0.0.5
 * @author kingsthere
 */
fun Sound(type: Key, source: Sound.Source, volume: Float, pitch: Float): Sound {
    return SoundImpl(type, source, volume, pitch)
}

/**
 * Create a [Sound] using a builder
 *
 * @since 0.0.5
 * @author kingsthere
 */
fun Sound(builder: Sound.Builder.() -> Unit): Sound {
    return SoundImpl.BuilderImpl().apply(builder).build()
}

/**
 * Create a [SoundStop] that stops all sounds
 */
fun SoundStop(): SoundStop {
    return SoundStop.ALL
}

/**
 * Create a [SoundStop] that stops sounds with specifies [type]
 */
fun SoundStop(type: Key): SoundStop {
    return SoundStopImpl(type, null)
}

/**
 * Create a [SoundStop] that stops sounds with specifies [type] and [source]
 */
fun SoundStop(type: Key, source: Sound.Source): SoundStop {
    return SoundStopImpl(type, source)
}

/**
 * Create a [SoundStop] that stops sounds with specifies [source]
 */
fun SoundStop(source: Sound.Source): SoundStop {
    return SoundStopImpl(null, source)
}