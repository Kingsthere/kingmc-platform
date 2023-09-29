package kingmc.platform.bukkit.impl.command

import kingmc.common.application.Application
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
import kingmc.platform.bukkit.BukkitServer
import kingmc.platform.bukkit.command.BukkitCommandSender
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.bukkit.impl.permission.BukkitPermissibleImpl
import kingmc.platform.permission.Permissible
import kingmc.platform.server

/**
 * A simple implementation for [BukkitCommandSender]
 *
 * @property _bukkitCommandSender bukkit command sender
 * @property _adventureAudience adventure audience refer to [_bukkitCommandSender]
 * @property _permissibleDelegate [Permissible] instance to delegate this bukkit command sender to
 * @constructor
 */
open class BukkitCommandSenderImpl(
    val _bukkitCommandSender: _BukkitCommandSender,
    val _adventureAudience: _AdventureAudience,
    val application: Application,
    val _permissibleDelegate: Permissible = BukkitPermissibleImpl(_bukkitCommandSender, application)
) : BukkitCommandSender, Permissible by _permissibleDelegate {
    private val _server = (application.server as BukkitServer).asBukkitServer()
    override fun toBukkitCommandSender(): _BukkitCommandSender = _bukkitCommandSender

    override fun command(command: String) {
        _server.dispatchCommand(_bukkitCommandSender, command)
    }
    override fun sendText(text: Text) {
        _adventureAudience.sendMessage(text)
    }

    override fun sendText(text: Text, vararg marks: Mark) {
        _adventureAudience.sendMessage(text)
    }

    override var playerlist: PlayerList
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun showBossBar(bossBar: BossBar) {
        TODO("Not yet implemented")
    }

    override fun hideBossBar(bossBar: BossBar) {
        TODO("Not yet implemented")
    }

    override fun sendTitle(title: Title) {
        TODO("Not yet implemented")
    }

    override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {
        TODO("Not yet implemented")
    }

    override fun clearTitle() {
        _adventureAudience.clearTitle()
    }

    override fun resetTitle() {
        _adventureAudience.resetTitle()
    }

    override fun sendActionBar(text: Text) {
        _adventureAudience.sendActionBar(text)
    }

    override fun playSound(sound: Sound) {
        TODO("Not yet implemented")
    }

    override fun playSound(sound: Sound, location: Location) {
        TODO("Not yet implemented")
    }

    override fun stopSound(soundStop: SoundStop) {
        TODO("Not yet implemented")
    }
}