package kingmc.platform.audience.capable

import kingmc.platform.Location
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.text.LiteralText
import kingmc.platform.audience.text.Mark
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import java.util.*

/**
 * Represent a receiver could receive [LiteralText]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@AudienceCapable
interface TextCapable {
    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see LiteralText
     */
    fun text(text: Text /* = net.kyori.adventure.text.Component */)

    /**
     * Send a text to this receivable with
     * tags
     *
     * @since 0.0.3
     * @see LiteralText
     * @see Mark
     */
    fun text(text: Text /* = net.kyori.adventure.text.Component */, vararg marks: Mark)
}

/**
 * Represent a receiver that could receive
 * [Title] and [TitlePartType]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Title
 * @see TitlePartType
 * @see AudienceCapable
 */
@AudienceCapable
interface TitleCapable {
    /**
     * Send a title to this audience
     *
     * @param title the title to send to
     *              the audience
     * @since 0.0.3
     * @see Title
     */
    fun title(title: Title)

    /**
     * Send/operate a title part to this
     * audience by the specify value
     *
     * @param titlePart the part of title to
     *                  send
     * @param value the value of the title part
     * @param T the type of value of the title part
     * @since 0.0.3
     */
    fun <T : Any> titlePart(titlePart: TitlePartType<T>, value: T)

    /**
     * Clears the title, if one is being displayed
     *
     * @see Title
     * @since 0.0.3
     */
    fun clearTitle()

    /**
     * Resets the title and timings back to their default
     *
     * @see Title
     * @since 0.0.3
     */
    fun resetTitle()

}

/**
 * Represent a receiver that could receive
 * action bar
 *
 * @since 0.0.3
 * @author kingsthere
 * @see AudienceCapable
 */
@AudienceCapable
interface ActionBarCapable {
    /**
     * Send a actionbar to this audience
     *
     * @see LiteralText
     * @since 0.0.3
     */
    fun actionBar(text: Text)
}

/**
 * Represent a receiver that could receive
 * boss-bars
 *
 * @since 0.0.3
 * @author kingsthere
 * @see BossBar
 */
@AudienceCapable
interface BossBarCapable {
    /**
     * Show a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    fun bossBar(bossBar: BossBar)

    /**
     * Hide a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    fun hideBossBar(bossBar: BossBar)
}

/**
 * Represent a receiver that has identifiers
 * in minecraft, including name, display name, and uuid
 *
 * @since 0.0.3
 * @author kingsthere
 * @see AudienceCapable
 */
@AudienceCapable
interface MinecraftIdentityCapable {
    /**
     * The name of this receiver
     *
     * @since 0.0.3
     */
    val name: String

    /**
     * The displaying name of this receiver
     *
     * @since 0.0.3
     * @see LiteralText
     */
    var displayName: Text

    /**
     * The uuid of this identity
     *
     * @since 0.0.3
     * @see UUID
     */
    val uuid: UUID
}

/**
 * Represent a receiver that could receive
 * player list footer & header
 *
 * @since 0.0.3
 * @author kingsthere
 */
@AudienceCapable
interface PlayerListCapable {
    /**
     * The player list that is displaying
     * to this receiver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    var playerlist: PlayerList
}

/**
 * Represent a receiver that could receive sounds from server and play
 *
 * @since 0.0.5
 * @author kingsthere
 * @see Sound
 */
@AudienceCapable
interface SoundCapable {
    /**
     * Play a sound to this audience
     *
     * @since 0.0.5
     */
    fun sound(sound: Sound)

    /**
     * Play a sound to this audience in specifies location
     *
     * @since 0.0.5
     */
    fun sound(sound: Sound, location: Location)

    /**
     * Stop a sound
     *
     * @since 0.0.5
     */
    fun stopSound(soundStop: SoundStop)
}

// TODO Boss-bar receivable
// TODO Inventory(GUI) receiver