package kingmc.platform.bukkit.audience

import kingmc.platform.Location
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.text.Mark
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.text.serializer.serializeFromTextToLegacy
import kingmc.platform.audience.text.text
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.block.Block
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.block.BukkitBlock
import kingmc.util.InternalAPI
import java.util.*

class BukkitBlockCommandSenderImpl(private val _bukkitBlockCommandSender: OriginalBukkitBlockCommandSender) :
    BukkitBlockCommandSender(),
    Block by BukkitBlock(_bukkitBlockCommandSender.block) {
    /**
     * The displaying name of this receiver
     *
     * @since 0.0.3
     * @see Text
     */
    override var displayName: Text
        get() = text(_bukkitBlockCommandSender.name)
        set(_) {
            throw UnsupportedOperationException()
        }

    /**
     * The name of this receiver
     *
     * @since 0.0.3
     */
    override val name: String
        get() = _bukkitBlockCommandSender.name

    /**
     * The player list that is displaying
     * to this receiver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override var playerlist: PlayerList
        get() = throw UnsupportedOperationException()
        set(_) {
            throw UnsupportedOperationException()
        }

    /**
     * The uuid of this identity
     *
     * @since 0.0.3
     * @see UUID
     */
    override val uuid: UUID
        get() = throw UnsupportedOperationException()

    /**
     * Send an actionbar to this audience
     *
     * @see Text
     * @since 0.0.3
     */
    override fun actionBar(text: Text) {  }

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text {
        throw UnsupportedOperationException()
    }

    /**
     * Show a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun bossBar(bossBar: BossBar) {  }

    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override fun chat(line: String) {
        Bukkit.dispatchCommand(this._bukkitBlockCommandSender, line)
    }

    /**
     * Clears the title, if one is being displayed
     *
     * @see Title
     * @since 0.0.3
     */
    override fun clearTitle() {  }

    @InternalAPI
    override fun close() {  }

    /**
     * Hide a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun hideBossBar(bossBar: BossBar) {  }

    /**
     * Resets the title and timings back to their default
     *
     * @see Title
     * @since 0.0.3
     */
    override fun resetTitle() {  }

    override fun sound(sound: Sound) {  }

    override fun sound(sound: Sound, location: Location) {  }

    override fun stopSound(soundStop: SoundStop) {  }

    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see Text
     */
    override fun text(text: Text) {
        this._bukkitBlockCommandSender.sendMessage(text.serializeFromTextToLegacy())
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
        this._bukkitBlockCommandSender.sendMessage(text.serializeFromTextToLegacy())
    }

    /**
     * Send a title to this audience
     *
     * @param title the title to send to
     *              the audience
     * @since 0.0.3
     * @see Title
     */
    override fun title(title: Title) {  }

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
    override fun <T : Any> titlePart(titlePart: TitlePartType<T>, value: T) {  }
}