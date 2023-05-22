package kingmc.platform.bukkit.adventure.impl.audience

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
import kingmc.platform.bukkit.audience.particle.BukkitParticleAnimationTask
import kingmc.platform.bukkit.audience.particle.bukkit
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.bukkit.entity.player.BukkitPlayer
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.impl.entity.BukkitHumanEntityImpl
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryView
import kingmc.platform.inventory.MainHand
import kingmc.platform.inventory.PlayerInventory
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionState
import kingmc.platform.util.ProtocolVersion
import net.kyori.adventure.key.Key
import java.net.InetSocketAddress
import java.util.*
import java.util.stream.Collectors
import kotlin.time.toJavaDuration

/**
 * An implementation of [BukkitPlayer], to use this implementation of
 * player you have to ensure that the player is online, otherwise it will throw an
 * [AudienceOfflineException] instead of correct result
 *
 * @since 0.0.8
 * @author kingsthere
 */
open class AdventureOnlineBukkitPlayerImpl(
    protected var _bukkitPlayer: _BukkitPlayer,
    protected var _adventureAudience: _AdventureAudience,
    application: Application,
) : BukkitPlayer,
    BukkitHumanEntityImpl(_bukkitPlayer, application) {

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

    override fun toBukkitCommandSender(): _BukkitCommandSender {
        return _bukkitPlayer
    }

    override fun command(command: String) {
        this.ensurePlayerOnline()
        _bukkitPlayer.server.dispatchCommand(_bukkitPlayer, command)
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
        get() {
            this.ensurePlayerOnline()
            return _bukkitPlayer.isSneaking
        }
        set(value) {
            this.ensurePlayerOnline()
            _bukkitPlayer.isSneaking = value
        }

    /**
     * `true` if this player is sprinting
     */
    override var isSprinting: Boolean
        get() {
            this.ensurePlayerOnline()
            return _bukkitPlayer.isSprinting
        }
        set(value) {
            this.ensurePlayerOnline()
            _bukkitPlayer.isSneaking = value
        }
    override val ping: Long
        get() = _bukkitPlayer.ping.toLong()
    override val clientBrand: String?
        get() = TODO()

    override fun disconnect(reason: Text) {
        _bukkitPlayer.kickPlayer(reason.serializeFromTextToLegacy())
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
        get() {
            this.ensurePlayerOnline()
            return _bukkitPlayer.listeningPluginChannels
        }

    /**
     * Get a permission from this `Permissible` by the name of
     * the permission
     *
     * @param permission the permission
     */
    override fun getPermission(permission: Permission): PermissionState {
        val name = permission.name
        return if (_bukkitPlayer.isPermissionSet(name)) {
            if (_bukkitPlayer.hasPermission(name)) {
                PermissionState.TRUE
            } else {
                PermissionState.FALSE
            }
        } else {
            PermissionState.NOT_SET
        }
    }

    /**
     * The player's IP address
     */
    override val remoteAddress: InetSocketAddress
        get() = _bukkitPlayer.address!!

    /**
     * The hostname that the user entered into the client, if applicable
     */
    override val virtualHost: InetSocketAddress?
        get() = null

    /**
     * Determine whether the player remains online
     */
    override val isActive: Boolean
        get() = _bukkitPlayer.isOnline

    /**
     * Returns the current protocol version this connection uses.
     */
    override val protocolVersion: ProtocolVersion
        get() = TODO("Not yet implemented")

    override var velocity: Vector
        get() {
            this.ensurePlayerOnline()
            return _bukkitPlayer.velocity.asKingMC()
        }
        set(value) {
            this.ensurePlayerOnline()
            _bukkitPlayer.velocity = value.asBukkit()
        }

    private fun ensurePlayerOnline() {
        if (Bukkit.getPlayer(uuid) == null) {
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
            return _bukkitPlayer.location.asKingMC(application)
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

    /**
     * The inventory that opened by this inventory viewer
     */
    override val openedInventory: InventoryView?
        get() {
            this.ensurePlayerOnline()
            return super.openedInventory
        }

    /**
     * Open an inventory for this inventory viewer
     *
     * @param inventory the inventory to open
     * @return The newly opened inventory view
     */
    override fun openInventory(inventory: Inventory): InventoryView {
        this.ensurePlayerOnline()
        return super.openInventory(inventory)
    }

    /**
     * Force-closes the currently open inventory view for this player, if any
     */
    override fun closeInventory() {
        this.ensurePlayerOnline()
        return super.closeInventory()
    }

    override val name: String
        get() {
            this.ensurePlayerOnline()
            return _bukkitPlayer.name
        }

    /**
     * The inventory of this player
     */
    override val inventory: PlayerInventory
        get() {
            this.ensurePlayerOnline()
            return super.inventory
        }

    /**
     * Check if the player is currently blocking
     *
     * For minecraft version before 1.9 (e.g. 1.8.9, 1.8.8) it checks if player blocking with a sword
     *
     * For minecraft version after or equals 1.9 (e.g. 1.9.4 1.12.2) it checks if player blocking with a shield
     */
    override val isBlocking: Boolean
        get() {
            this.ensurePlayerOnline()
            return super.isBlocking
        }

    /**
     * The ender chest inventory of this player
     */
    override val enderChest: Inventory
        get() {
            this.ensurePlayerOnline()
            return super.enderChest
        }

    /**
     * The selected `MainHand` of this player
     */
    override val mainHand: MainHand
        get() {
            this.ensurePlayerOnline()
            return super.mainHand
        }

    /**
     * The player's current exhaustion level of this player
     *
     * Exhaustion controls how fast the food level drops. While you have a certain
     * amount of exhaustion, your saturation will drop to zero, and then your food will drop to zero
     */
    override var exhaustion: Float
        get() {
            this.ensurePlayerOnline()
            return super.exhaustion
        }
        set(value) {
            this.ensurePlayerOnline()
            super.exhaustion = value
        }

    /**
     * The player's current saturation level
     */
    override var saturation: Float
        get() {
            this.ensurePlayerOnline()
            return super.saturation
        }
        set(value) {
            this.ensurePlayerOnline()
            super.saturation = value
        }

    /**
     * The player's current food level
     */
    override var foodLevel: Int
        get() {
            this.ensurePlayerOnline()
            return super.foodLevel
        }
        set(value) {
            this.ensurePlayerOnline()
            super.foodLevel = value
        }

    /**
     * the regeneration rate (1 health per x ticks) of the `HumanEntity` when they
     * have saturation and their food level is >= 20. Default is 10
     */
    override var saturatedRegenRate: Int
        get() {
            this.ensurePlayerOnline()
            return super.saturatedRegenRate
        }
        set(value) {
            this.ensurePlayerOnline()
            super.saturatedRegenRate = value
        }

    /**
     * the regeneration rate (1 health per x ticks) of the HumanEntity when they
     * have no saturation and their food level is >= 18. Default is 80
     */
    override var unsaturatedRegenRate: Int
        get() {
            this.ensurePlayerOnline()
            return super.unsaturatedRegenRate
        }
        set(value) {
            this.ensurePlayerOnline()
            super.unsaturatedRegenRate = value
        }

    /**
     * Get the starvation rate (1 health per x ticks) of the HumanEntity. Default is 80
     */
    override var starvationRate: Int
        get() {
            this.ensurePlayerOnline()
            return super.starvationRate
        }
        set(value) {
            this.ensurePlayerOnline()
            super.starvationRate = value
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

    @Suppress("DEPRECATION")
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
                title.times.fadeIn.toJavaDuration(),
                title.times.stay.toJavaDuration(),
                title.times.fadeOut.toJavaDuration()
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
                    (value as Title.Times).fadeIn.toJavaDuration(),
                    (value as Title.Times).stay.toJavaDuration(),
                    (value as Title.Times).fadeOut.toJavaDuration()
                ))
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdventureOnlineBukkitPlayerImpl

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