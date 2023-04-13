package kingmc.platform.bukkit.adventure.impl.audience

import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.platform.Location
import kingmc.platform.audience._AdventureAudience
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.audience.BukkitConsole

class AdventureBukkitConsoleImpl(private val _adventureConsoleAudience: _AdventureAudience) :
    BukkitConsole() {
    /**
     * The player list that is displaying
     * to this receiver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override var playerlist: PlayerList
        get() = throw UnsupportedOperationException("A console could not receive this kind of media")
        set(_) {
            throw UnsupportedOperationException("A console could not receive this kind of media")
        }

    /**
     * Send an actionbar to this audience
     *
     * @see Text
     * @since 0.0.3
     */
    override fun sendActionBar(text: Text) {
        _adventureConsoleAudience.sendActionBar(text)
    }

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        Text("Console")

    /**
     * Show a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun showBossBar(bossBar: BossBar) {
        throw UnsupportedOperationException("A console could not receive this kind of media")
    }

    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override fun chat(message: String) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), message)
    }

    /**
     * Clears the title, if one is being displayed
     *
     * @see Title
     * @since 0.0.3
     */
    override fun clearTitle() {
        _adventureConsoleAudience.clearTitle()
    }

    /**
     * Hide a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun hideBossBar(bossBar: BossBar) {
        throw UnsupportedOperationException("A console could not receive this kind of media")
    }

    /**
     * Resets the title and timings back to their default
     *
     * @see Title
     * @since 0.0.3
     */
    override fun resetTitle() {
        _adventureConsoleAudience.resetTitle()
    }

    override fun playSound(sound: Sound) {
        throw UnsupportedOperationException("A console could not receive this kind of media")
    }

    override fun playSound(sound: Sound, location: Location) {
        throw UnsupportedOperationException("A console could not receive this kind of media")
    }

    override fun stopSound(soundStop: SoundStop) {
        throw UnsupportedOperationException("A console could not receive this kind of media")
    }

    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see Text
     */
    override fun sendText(text: Text) {
        _adventureConsoleAudience.sendMessage(text)
    }

    /**
     * Send a text to this receivable with
     * tags
     *
     * @since 0.0.3
     * @see Text
     * @see Mark
     */
    override fun sendText(text: Text, vararg marks: Mark) {
        _adventureConsoleAudience.sendMessage(text)
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
        throw UnsupportedOperationException("A console could not receive this kind of media")
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
        throw UnsupportedOperationException("A console could not receive this kind of media")
    }
}