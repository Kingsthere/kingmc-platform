package kingmc.platform.velocity.impl.audience

import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.platform.Location
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.permission.Permissible
import kingmc.platform.velocity.VelocityImplementation
import kingmc.platform.velocity._VelocityProxyServer
import kingmc.platform.velocity.audience.VelocityConsole

@VelocityImplementation
class VelocityConsoleImpl(private val _velocityProxyServer: _VelocityProxyServer) :
    VelocityConsole(),
    Permissible by Permissible.ALWAYS {
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
        _velocityProxyServer.sendActionBar(text)
    }

    /**
     * Show a bossbar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun showBossBar(bossBar: BossBar) {
        throw UnsupportedOperationException("A console could not receive this kind of media")
    }

    override fun command(command: String) {
        _velocityProxyServer.commandManager.executeAsync(_velocityProxyServer.consoleCommandSource, command)
    }

    /**
     * Clears the title, if one is being displayed
     *
     * @see Title
     * @since 0.0.3
     */
    override fun clearTitle() {
        _velocityProxyServer.clearTitle()
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
        _velocityProxyServer.resetTitle()
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
        _velocityProxyServer.sendMessage(text)
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
        _velocityProxyServer.sendMessage(text)
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