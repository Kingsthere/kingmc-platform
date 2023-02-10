package kingmc.platform.audience

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
import kingmc.util.InternalAPI
import java.util.*

/**
 * A receiver that forward the messages to one or more receivers
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface ForwardingAudience : Audience {
    /**
     * Gets the audiences to forward to
     */
    fun audiences(): Iterable<Audience>

    @InternalAPI
    override fun close() {
        this.audiences().forEach { it.close() }
    }

    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see LiteralText
     */
    override fun text(text: Text) {
        this.audiences().forEach { it.text(text) }
    }

    /**
     * Send a text to this receivable with
     * tags
     *
     * @since 0.0.3
     * @see LiteralText
     * @see Mark
     */
    override fun text(text: Text, vararg marks: Mark) {
        this.audiences().forEach { it.text(text, *marks) }
    }

    /**
     * Send a title to this audience
     *
     * @param title the title to send to
     *              the audience
     * @since 0.0.3
     * @see Title
     */
    override fun title(title: Title) {
        this.audiences().forEach { it.title(title) }
    }

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
    override fun <T : Any> titlePart(titlePart: TitlePartType<T>, value: T) {
        this.audiences().forEach { it.titlePart(titlePart, value) }
    }

    /**
     * Play a sound to this audience
     *
     * @since 0.0.5
     */
    override fun sound(sound: Sound) {
        this.audiences().forEach { it.sound(sound) }
    }

    /**
     * Play a sound to this audience in specifies location
     *
     * @since 0.0.5
     */
    override fun sound(sound: Sound, location: Location) {
        this.audiences().forEach { it.sound(sound, location) }
    }

    /**
     * Stop a sound
     *
     * @since 0.0.5
     */
    override fun stopSound(soundStop: SoundStop) {
        this.audiences().forEach { it.stopSound(soundStop) }
    }

    /**
     * Clears the title, if one is being displayed
     *
     * @see Title
     * @since 0.0.3
     */
    override fun clearTitle() {
        this.audiences().forEach { it.clearTitle() }
    }

    /**
     * Resets the title and timings back to their default
     *
     * @see Title
     * @since 0.0.3
     */
    override fun resetTitle() {
        this.audiences().forEach { it.resetTitle() }
    }

    /**
     * Send a actionbar to this audience
     *
     * @see LiteralText
     * @since 0.0.3
     */
    override fun actionBar(text: Text) {
        this.audiences().forEach { it.actionBar(text) }
    }

    /**
     * Show a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun bossBar(bossBar: BossBar) {
        this.audiences().forEach { it.bossBar(bossBar) }
    }

    /**
     * Hide a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun hideBossBar(bossBar: BossBar) {
        this.audiences().forEach { it.hideBossBar(bossBar) }
    }

    /**
     * The name of this receiver
     *
     * @since 0.0.3
     */
    override val name: String
        get() = this.audiences().first().name

    /**
     * The displaying name of this receiver
     *
     * @since 0.0.3
     * @see LiteralText
     */
    override var displayName: Text
        get() = this.audiences().first().displayName
        set(value) {}

    /**
     * The uuid of this identity
     *
     * @since 0.0.3
     * @see UUID
     */
    override val uuid: UUID
        get() = this.audiences().first().uuid

    /**
     * The player list that is displaying
     * to this receiver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override var playerlist: PlayerList
        get() = this.audiences().first().playerlist
        set(value) {
            this.audiences().forEach { it.playerlist = value }
        }
}