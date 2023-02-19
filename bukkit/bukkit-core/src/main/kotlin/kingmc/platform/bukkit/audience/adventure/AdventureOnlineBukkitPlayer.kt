package kingmc.platform.bukkit.audience.adventure

import kingmc.platform.Locatable
import kingmc.platform.Location
import kingmc.platform.Vector
import kingmc.platform.audience.*
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.particle.*
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.text.serializer.deserializeFromLegacyToText
import kingmc.platform.audience.text.serializer.serializeFromTextToLegacy
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.audience.BukkitParticleAnimationTask
import kingmc.platform.bukkit.audience.OnlineBukkitPlayer
import kingmc.platform.bukkit.audience.OriginalBukkitPlayer
import kingmc.platform.bukkit.bukkitPluginInstance
import kingmc.platform.bukkit.fromBukkit
import kingmc.platform.bukkit.nms.PlayerNMS
import kingmc.platform.bukkit.toBukkit
import kingmc.platform.messaging.OutputMessage
import kingmc.util.InternalAPI
import net.kyori.adventure.key.Key
import java.util.*
import java.util.stream.Collectors

/**
 * Implement of [OnlineBukkitPlayer] with kyori
 * adventure api
 */
open class AdventureOnlineBukkitPlayer(
    protected var _playerNMS: PlayerNMS<Any>,
    protected var _bukkitPlayer: OriginalBukkitPlayer,
    protected var _adventureAudience: AdventureAudience
) : OnlineBukkitPlayer(_bukkitPlayer) {
    private val _handle by lazy { _playerNMS.getHandle(this@AdventureOnlineBukkitPlayer) }

    override fun text(text: Text) {
        this.refresh()
        _adventureAudience.sendMessage(text)
    }

    override var displayName: Text = _bukkitPlayer.displayName.deserializeFromLegacyToText()
        get() {
            this.refresh()
            return _bukkitPlayer.displayName.deserializeFromLegacyToText()
        }
        set(value) {
            this.refresh()
            _bukkitPlayer.setDisplayName(value.serializeFromTextToLegacy())
            field = value
        }

    /**
     * Gets the first date and time that this player was witnessed on this
     * server
     *
     *
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970,
     * UTC
     *
     * @return Date of first log-in for this player, or 0
     */
    override val firstPlayed: Long
        get() = _bukkitPlayer.firstPlayed

    /**
     * Checks if this player is currently online
     */
    override val isOnline: Boolean
        get() = _bukkitPlayer.isOnline

    /**
     * Gets the last date and time that this player was witnessed on this
     * server
     *
     *
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970,
     * UTC
     *
     * @return Date of last log-in for this player, or 0
     */
    override val lastPlayed: Long
        get() = _bukkitPlayer.lastPlayed

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on.
     *
     * @return Set containing all the channels that this client may accept.
     */
    override val listeningPluginChannels: Set<String>
        get() = _bukkitPlayer.listeningPluginChannels


    /**
     * Cast this player to the player
     * in the original platform api copy
     *
     * @since 0.0.3
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T> cast(): T {
        this.refresh()
        return _bukkitPlayer as T
    }

    override var vector: Vector
        get() = _bukkitPlayer.velocity.fromBukkit()
        set(value) {
            _bukkitPlayer.velocity = value.toBukkit()
        }

    private fun refresh() {
        val bukkitPlayer = Bukkit.getPlayer(uuid)
        if (bukkitPlayer != null) {
            if (this._bukkitPlayer != bukkitPlayer) {
                this._bukkitPlayer = bukkitPlayer
                _adventureAudience = bukkitAudiences.player(bukkitPlayer)
            }
        } else {
            throw IllegalStateException("This player is not online")
        }
    }

    override fun actionBar(text: Text) {
        this.refresh()
        _adventureAudience.sendActionBar(text)
    }

    override fun bossBar(bossBar: BossBar) {
        this.refresh()
        _adventureAudience.showBossBar(/* bar = */ AdventureBossBar.bossBar(
            bossBar.name,
            bossBar.progress,
            net.kyori.adventure.bossbar.BossBar.Color.valueOf(bossBar.color.name),
            net.kyori.adventure.bossbar.BossBar.Overlay.valueOf(bossBar.overlay.name),
            bossBar.flags.stream()
                .map { flag -> net.kyori.adventure.bossbar.BossBar.Flag.valueOf(flag.name) }
                .collect(Collectors.toSet())
        ))
    }

    override var location: Location
        get() {
            this.refresh()
            return _bukkitPlayer.location.fromBukkit()
        }
        set(value) {
            this.refresh()
            _bukkitPlayer.teleport(value.toBukkit())
        }

    /**
     * Teleport this locatable to another [location]
     *
     * @since 0.0.3
     * @param location the location this locatable to teleport to
     * @see Location
     */
    override fun teleport(location: Location) {
        this.refresh()
        _bukkitPlayer.teleport(location.toBukkit())
    }

    /**
     * Teleport [locatable] to another locatable location
     *
     * @since 0.0.3
     * @param locatable the locatable this locatable to teleport to
     * @see Location
     */
    override fun teleport(locatable: Locatable) {
        this.refresh()
        _bukkitPlayer.teleport(locatable.location.toBukkit())
    }

    override val name: String
        get() {
            this.refresh()
            return _bukkitPlayer.name
        }

    override val uuid: UUID
        get() {
            return _bukkitPlayer.uniqueId
        }

    /**
     * The player list that is displaying
     * to this receiver
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override var playerlist: PlayerList
        get() { TODO() }
        set(_) {}

    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override fun chat(line: String) {
        this.refresh()
        this._bukkitPlayer.chat(line)
    }

    @InternalAPI
    override fun close() {  }

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        this.displayName

    override fun clearTitle() {
        this.refresh()
        this._adventureAudience.clearTitle()
    }

    override fun hideBossBar(bossBar: BossBar) {
        this.refresh()
        this._adventureAudience.hideBossBar(/* bar = */ AdventureBossBar.bossBar(
            bossBar.name,
            bossBar.progress,
            net.kyori.adventure.bossbar.BossBar.Color.valueOf(bossBar.color.name),
            net.kyori.adventure.bossbar.BossBar.Overlay.valueOf(bossBar.overlay.name),
            bossBar.flags.stream()
                .map { flag -> net.kyori.adventure.bossbar.BossBar.Flag.valueOf(flag.name) }
                .collect(Collectors.toSet())
        ))
    }

    override fun particle(particle: Particle<*>) {
        this.refresh()
        this._playerNMS.sendParticle(this._handle, particle)
    }

    override fun particle(particleAnimation: ParticleAnimation): ParticleAnimationTask =
        BukkitParticleAnimationTask(particleAnimation, this)

    override fun particle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask =
        BukkitParticleAnimationTask(particleAnimation, this, speed)

    override fun particle(particleGroup: ParticleGroup) {
        particleGroup.particles.forEach { particle -> this.particle(particle) }
    }

    override fun resetTitle() {
        this.refresh()
        this._adventureAudience.resetTitle()
    }

    /**
     * Sends this recipient a Plugin Message on the specified outgoing
     * channel.
     *
     *
     * The message may not be larger than 32766
     * bytes, and the plugin must be registered to send messages on the
     * specified channel.
     *
     * @param channel The channel to send this message on.
     * @param message The raw message to send.
     */
    override fun sendPluginMessage(channel: String, message: OutputMessage) {
        this.refresh()
        this._bukkitPlayer.sendPluginMessage(bukkitPluginInstance, channel, message.toByteArray())
    }

    override fun sound(sound: Sound) {
        this.refresh()
        this._adventureAudience.playSound(AdventureSound.sound(
            Key.key(sound.type.namespace(), sound.type.value()),
            AdventureSoundSource.valueOf(sound.source.name),
            sound.pitch,
            sound.volume
        ))
    }

    override fun sound(sound: Sound, location: Location) {
        this.refresh()
        this._adventureAudience.playSound(AdventureSound.sound(
            Key.key(sound.type.namespace(), sound.type.value()),
            AdventureSoundSource.valueOf(sound.source.name),
            sound.pitch,
            sound.volume
        ), location.x, location.y, location.z)
    }

    override fun title(title: Title) {
        this.refresh()
        this._adventureAudience.showTitle(AdventureTitle.title(
            title.title,
            title.subtitle,
            AdventureTitleTimes.times(
                title.times.fadeIn,
                title.times.stay,
                title.times.fadeOut
            )
        ))
    }

    override fun stopSound(soundStop: SoundStop) {
        this.refresh()
        if (soundStop.type != null) {
            if (soundStop.source != null) {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.namedOnSource(
                    Key.key(soundStop.type!!.namespace(), soundStop.type!!.value()),
                    AdventureSoundSource.valueOf(soundStop.source!!.name),
                ))
            } else {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.named(
                    Key.key(soundStop.type!!.namespace(), soundStop.type!!.value()),
                ))
            }
        } else {
            if (soundStop.source != null) {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.source(
                    AdventureSoundSource.valueOf(soundStop.source!!.name),
                ))
            } else {
                this._adventureAudience.stopSound(net.kyori.adventure.sound.SoundStop.all())
            }
        }
    }

    override fun <T : Any> titlePart(titlePart: TitlePartType<T>, value: T) {
        this.refresh()
        when (titlePart) {
            TitlePartType.TITLE -> {
                _adventureAudience.sendTitlePart(AdventureTitlePart.TITLE, value as Text)
            }
            TitlePartType.SUBTITLE -> {
                _adventureAudience.sendTitlePart(AdventureTitlePart.SUBTITLE, value as Text)
            }
            TitlePartType.TIMES -> {
                _adventureAudience.sendTitlePart(AdventureTitlePart.TIMES, AdventureTitleTimes.times(
                    (value as Title.Times).fadeIn,
                    (value as Title.Times).stay,
                    (value as Title.Times).fadeOut
                ))
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdventureOnlineBukkitPlayer

        if (name != other.name) return false
        if (uuid != other.uuid) return false

        return true
    }

    /**
     * Checks if this player has played on this server before
     *
     * @return True if the player has played before, otherwise false
     */
    override fun hasPlayedBefore(): Boolean =
        _bukkitPlayer.hasPlayedBefore()


    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + uuid.hashCode()
        return result
    }
}