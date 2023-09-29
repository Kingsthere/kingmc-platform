package kingmc.platform.audience

import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.platform.Location
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType

/**
 * A receiver that forward the messages to one or more receivers
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface ForwardingAudience : Audience {
    /**
     * Gets the audiences to forward to
     */
    fun audiences(): Iterable<Audience>

    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see text
     */
    override fun sendText(text: Text) {
        this.audiences().forEach { it.sendText(text) }
    }

    /**
     * Send a text to this receivable with
     * tags
     *
     * @since 0.0.3
     * @see text
     * @see Mark
     */
    override fun sendText(text: Text, vararg marks: Mark) {
        this.audiences().forEach { it.sendText(text, *marks) }
    }

    /**
     * Send a title to this audience
     *
     * @param title the title to send to
     *              the audience
     * @since 0.0.3
     * @see Title
     */
    override fun sendTitle(title: Title) {
        this.audiences().forEach { it.sendTitle(title) }
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
    override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {
        this.audiences().forEach { it.sendTitlePart(titlePart, value) }
    }

    /**
     * Play a sound to this audience
     *
     * @since 0.0.5
     */
    override fun playSound(sound: Sound) {
        this.audiences().forEach { it.playSound(sound) }
    }

    /**
     * Play a sound to this audience in specifies location
     *
     * @since 0.0.5
     */
    override fun playSound(sound: Sound, location: Location) {
        this.audiences().forEach { it.playSound(sound, location) }
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
     * Send an actionbar to this audience
     *
     * @see text
     * @since 0.0.3
     */
    override fun sendActionBar(text: Text) {
        this.audiences().forEach { it.sendActionBar(text) }
    }

    /**
     * Show a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun showBossBar(bossBar: BossBar) {
        this.audiences().forEach { it.showBossBar(bossBar) }
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