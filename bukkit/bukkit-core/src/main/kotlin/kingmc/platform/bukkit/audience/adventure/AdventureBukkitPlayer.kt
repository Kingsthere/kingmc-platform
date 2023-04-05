package kingmc.platform.bukkit.audience.adventure

import kingmc.common.application.Application
import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.common.text.serializer.deserializeFromLegacyToText
import kingmc.common.text.serializer.serializeFromTextToLegacy
import kingmc.platform.*
import kingmc.platform.Vector
import kingmc.platform.audience.*
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.exception.AudienceOfflineException
import kingmc.platform.audience.particle.*
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.asBukkit
import kingmc.platform.bukkit.asKingMC
import kingmc.platform.bukkit.audience.BukkitParticleAnimationTask
import kingmc.platform.bukkit.audience.particle.bukkit
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.bukkit.entity.impl.BukkitEntityImpl
import kingmc.platform.bukkit.entity.player.BukkitPlayer
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.messaging.OutputMessage
import net.kyori.adventure.key.Key
import java.util.*
import java.util.stream.Collectors

/**
 * Adventure implementation resolution of [BukkitPlayer]
 */
open class AdventureBukkitPlayer(
    protected var _bukkitPlayer: _BukkitPlayer,
    protected var _adventureAudience: _AdventureAudience = adventureBukkitAudiences.player(_bukkitPlayer),
    application: Application,
) : BukkitPlayer, BukkitEntityImpl(_bukkitPlayer, application) {
    override fun toBukkitCommandSender(): _BukkitCommandSender {
        return super<BukkitEntityImpl>.toBukkitCommandSender()
    }

    override fun sendText(text: Text) {
        this.ensurePlayerOnline()
        _adventureAudience.sendMessage(text)
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
        this.ensurePlayerOnline()
        _adventureAudience.sendMessage(text)
    }

    /**
     * Convert this bukkit player to a bukkit player
     */
    override fun toBukkitPlayer(): _BukkitPlayer {
        return _bukkitPlayer
    }

    override var displayName: Text
        get() {
            this.ensurePlayerOnline()
            return _bukkitPlayer.displayName.deserializeFromLegacyToText()
        }
        set(value) {
            this.ensurePlayerOnline()
            _bukkitPlayer.setDisplayName(value.serializeFromTextToLegacy())
        }

    /**
     * `true` if this player is sneaking
     */
    override var isSneaking: Boolean
        get() = _bukkitPlayer.isSneaking
        set(value) {
            _bukkitPlayer.isSneaking = value
        }

    /**
     * `true` if this player is sprinting
     */
    override var isSprinting: Boolean
        get() = _bukkitPlayer.isSprinting
        set(value) {
            _bukkitPlayer.isSneaking = value
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

    override var velocity: Vector
        get() = _bukkitPlayer.velocity.asKingMC()
        set(value) {
            _bukkitPlayer.velocity = value.asBukkit()
        }

    private fun ensurePlayerOnline() {
        val bukkitPlayer = Bukkit.getOfflinePlayer(uuid)
        if (!bukkitPlayer.isOnline) {
            throw AudienceOfflineException("This player is not online")
        }
    }

    override fun sendActionBar(text: Text) {
        this.ensurePlayerOnline()
        _adventureAudience.sendActionBar(text)
    }

    override fun showBossBar(bossBar: BossBar) {
        this.ensurePlayerOnline()
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
            this.ensurePlayerOnline()
            return _bukkitPlayer.location.asKingMC()
        }
        set(value) {
            this.ensurePlayerOnline()
            _bukkitPlayer.teleport(value.asBukkit())
        }

    /**
     * Teleport this locatable to another [location]
     *
     * @since 0.0.3
     * @param location the location this locatable to teleport to
     * @see Location
     */
    override fun teleport(location: Location) {
        this.ensurePlayerOnline()
        _bukkitPlayer.teleport(location.asBukkit())
    }

    /**
     * Teleport [locatable] to another locatable location
     *
     * @since 0.0.3
     * @param locatable the locatable this locatable to teleport to
     * @see Location
     */
    override fun teleport(locatable: Locatable) {
        this.ensurePlayerOnline()
        _bukkitPlayer.teleport(locatable.location.asBukkit())
    }

    override val name: String
        get() {
            this.ensurePlayerOnline()
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
    override fun chat(message: String) {
        this.ensurePlayerOnline()
        this._bukkitPlayer.chat(message)
    }

    /**
     * Convert this object into a [Text]
     */
    override fun asText(): Text =
        this.displayName

    override fun clearTitle() {
        this.ensurePlayerOnline()
        this._adventureAudience.clearTitle()
    }

    override fun hideBossBar(bossBar: BossBar) {
        this.ensurePlayerOnline()
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

    @Deprecated("_BukkitPlayer.spawnParticle() method is not supported by 1.8")
    override fun sendParticle(particle: Particle<*>) {
        this.ensurePlayerOnline()
        this._bukkitPlayer.spawnParticle(particle.bukkit, particle.x, particle.y, particle.z, particle.count, particle.offsetX.toDouble(), particle.offsetY.toDouble(), particle.offsetZ.toDouble(), particle.maxSpeed.toDouble())
    }

    @Deprecated("_BukkitPlayer.spawnParticle() method is not supported by 1.8")
    override fun sendParticle(particleAnimation: ParticleAnimation): ParticleAnimationTask =
        BukkitParticleAnimationTask(particleAnimation, this)

    @Deprecated("_BukkitPlayer.spawnParticle() method is not supported by 1.8")
    override fun sendParticle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask =
        BukkitParticleAnimationTask(particleAnimation, this, speed)

    @Deprecated("_BukkitPlayer.spawnParticle() method is not supported by 1.8")
    override fun sendParticle(particleGroup: ParticleGroup) {
        particleGroup.particles.forEach { particle -> this.sendParticle(particle) }
    }

    @Deprecated("_BukkitPlayer.spawnParticle() method is not supported by 1.8")
    override fun resetTitle() {
        this.ensurePlayerOnline()
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
        this.ensurePlayerOnline()
        this._bukkitPlayer.sendPluginMessage(bukkitPlugin, channel, message.toByteArray())
    }

    override fun playSound(sound: Sound) {
        this.ensurePlayerOnline()
        this._adventureAudience.playSound(AdventureSound.sound(
            Key.key(sound.type.namespace(), sound.type.value()),
            AdventureSoundSource.valueOf(sound.source.name),
            sound.pitch,
            sound.volume
        ))
    }

    override fun playSound(sound: Sound, location: Location) {
        this.ensurePlayerOnline()
        this._adventureAudience.playSound(AdventureSound.sound(
            Key.key(sound.type.namespace(), sound.type.value()),
            AdventureSoundSource.valueOf(sound.source.name),
            sound.volume,
            sound.pitch
        ), location.x, location.y, location.z)
    }

    override fun sendTitle(title: Title) {
        this.ensurePlayerOnline()
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
        this.ensurePlayerOnline()
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

    override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {
        this.ensurePlayerOnline()
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

        other as AdventureBukkitPlayer

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