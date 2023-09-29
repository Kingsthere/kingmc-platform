package kingmc.platform.audience.sound

import kingmc.platform.Locatable
import kingmc.platform.Location
import kingmc.platform.audience.kind.SoundCapable
import kingmc.util.key.Key

/**
 * Play a configured sound
 *
 * @author kingsthere
 * @since 0.0.5
 */
fun SoundCapable.playSound(builder: Sound.Builder.() -> Unit) {
    this.playSound(Sound(builder))
}

/**
 * Play a configured sound at specifies [location]
 *
 * @author kingsthere
 * @since 0.0.5
 */
fun SoundCapable.playSound(location: Location, builder: Sound.Builder.() -> Unit) {
    this.playSound(Sound(builder), location)
}

/**
 * Play a configured sound at specifies [Locatable]
 *
 * @author kingsthere
 * @since 0.0.5
 */
fun SoundCapable.playSound(locatable: Locatable, builder: Sound.Builder.() -> Unit) {
    this.playSound(Sound(builder), locatable.location)
}

/**
 * Play a configured sound at specifies [Locatable]
 *
 * @author kingsthere
 * @since 0.0.5
 */
fun SoundCapable.playSound(sound: Sound, locatable: Locatable) {
    this.playSound(sound, locatable.location)
}

/**
 * Create a [Sound]
 *
 * @author kingsthere
 * @since 0.0.5
 */
fun Sound(type: Key, source: Sound.Source, volume: Float, pitch: Float): Sound {
    return SoundImpl(type, source, volume, pitch)
}

/**
 * Create a [Sound] using a builder
 *
 * @author kingsthere
 * @since 0.0.5
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