package kingmc.platform.bukkit.audience.adventure

import kingmc.platform.Location
import kingmc.platform.audience.AdventureAudience
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.text.Mark
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.audience.BukkitConsole
import kingmc.util.InternalAPI
import java.util.*

class AdventureBukkitConsole(private val _adventureConsoleAudience: AdventureAudience) :
    BukkitConsole() {
    /**
     * The displaying name of this receiver
     *
     * @since 0.0.3
     * @see Text
     */
    override var displayName: Text =
        Text.text("Console")

    /**
     * The name of this receiver
     *
     * @since 0.0.3
     */
    override val name: String
        get() = "Console"

    /**
     * The player list that is displaying
     * to this receiver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override var playerlist: PlayerList
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * The uuid of this identity
     *
     * @since 0.0.3
     * @see UUID
     */
    override val uuid: UUID
        get() = throw UnsupportedOperationException()

    /**
     * Send a actionbar to this audience
     *
     * @see Text
     * @since 0.0.3
     */
    override fun actionBar(text: Text) {
        _adventureConsoleAudience.sendActionBar(text)
    }

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        displayName

    /**
     * Show a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun bossBar(bossBar: BossBar) {
        TODO("Not yet implemented")
    }

    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override fun chat(line: String) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), line)
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

    @InternalAPI
    override fun close() = Unit

    /**
     * Hide a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun hideBossBar(bossBar: BossBar) {
        TODO("Not yet implemented")
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

    override fun sound(sound: Sound) {
        TODO("Not yet implemented")
    }

    override fun sound(sound: Sound, location: Location) {
        TODO("Not yet implemented")
    }

    override fun stopSound(soundStop: SoundStop) {
        TODO("Not yet implemented")
    }

    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see Text
     */
    override fun text(text: Text) {
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
    override fun text(text: Text, vararg marks: Mark) {
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
    override fun title(title: Title) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }
}