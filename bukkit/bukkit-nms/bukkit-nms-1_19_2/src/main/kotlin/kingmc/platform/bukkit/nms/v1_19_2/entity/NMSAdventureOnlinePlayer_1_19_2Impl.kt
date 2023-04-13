package kingmc.platform.bukkit.nms.v1_19_2.entity

import kingmc.common.application.Application
import kingmc.common.text.Mark
import kingmc.common.text.Text
import kingmc.platform.Locatable
import kingmc.platform.Location
import kingmc.platform.Vector
import kingmc.platform.audience._AdventureAudience
import kingmc.platform.audience.bossbar.BossBar
import kingmc.platform.audience.particle.*
import kingmc.platform.audience.playerlist.PlayerList
import kingmc.platform.audience.sound.Sound
import kingmc.platform.audience.sound.SoundStop
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePartType
import kingmc.platform.bukkit.adventure.impl.audience.AdventureOnlineBukkitPlayerImpl
import kingmc.platform.bukkit.entity.player._BukkitPlayer
import kingmc.platform.bukkit.nms.PlayerNMS
import kingmc.platform.bukkit.nms.audience.NMSPlayer
import kingmc.platform.bukkit.nms.v1_19_2.NMSServerPlayer_1_19_2
import kingmc.platform.messaging.OutputMessage
import java.util.*

open class NMSAdventureOnlinePlayer_1_19_2Impl(val playerNMS: PlayerNMS<Any>,
                                               val adventureAudience: _AdventureAudience,
                                               val nms: NMSServerPlayer_1_19_2,
                                               application: Application)
    : AdventureOnlineBukkitPlayerImpl(nms.bukkitEntity, adventureAudience, application), NMSPlayer {
    /**
     * Convert this bukkit player to a bukkit player
     */
    override fun toBukkitPlayer(): _BukkitPlayer {
        return this.nms.bukkitEntity
    }

    /**
     * Checks if this player is currently online
     */
    override val isOnline: Boolean
        get() = TODO("Not yet implemented")

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
        get() = TODO("Not yet implemented")

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
        get() = TODO("Not yet implemented")

    /**
     * Checks if this player has played on this server before
     *
     * @return True if the player has played before, otherwise false
     */
    override fun hasPlayedBefore(): Boolean {
        TODO("Not yet implemented")
    }

    /**
     * The name of this receiver
     *
     * @since 0.0.3
     */
    override val name: String
        get() = TODO("Not yet implemented")

    /**
     * The displaying name of this receiver
     *
     * @since 0.0.3
     * @see text
     */
    override var displayName: Text
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * The uuid of this identity
     *
     * @since 0.0.3
     * @see UUID
     */
    override val uuid: UUID
        get() = TODO("Not yet implemented")

    /**
     * Send a particle to this particle recipient
     *
     * @param particle the particle to send
     * @since 0.0.3
     * @see Particle
     */
    override fun sendParticle(particle: Particle<*>) {
        TODO("Not yet implemented")
    }

    /**
     * Send a particle group to this particle recipient
     *
     * @param particleGroup the particle group to send
     * @since 0.0.3
     * @see ParticleGroup
     */
    override fun sendParticle(particleGroup: ParticleGroup) {
        TODO("Not yet implemented")
    }

    /**
     * Send a particle animation to this particle recipient
     *
     * @param particleAnimation the particle group to send
     * @since 0.0.3
     * @see ParticleAnimation
     */
    override fun sendParticle(particleAnimation: ParticleAnimation): ParticleAnimationTask {
        TODO("Not yet implemented")
    }

    /**
     * Send a particle animation to this particle recipient
     *
     * @param particleAnimation the particle group to send
     * @since 0.0.3
     * @see ParticleAnimation
     * @param speed the speed of this particle to show
     */
    override fun sendParticle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask {
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
        TODO("Not yet implemented")
    }

    /**
     * Send a text to this receivable
     *
     * @since 0.0.3
     * @see text
     */
    override fun sendText(text: Text) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

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
     * Show a bossBar to this receiver
     *
     * @since 0.0.3
     * @see BossBar
     */
    override fun showBossBar(bossBar: BossBar) {
        TODO("Not yet implemented")
    }

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
     * Send a title to this audience
     *
     * @param title the title to send to
     *              the audience
     * @since 0.0.3
     * @see Title
     */
    override fun sendTitle(title: Title) {
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
    override fun <T : Any> sendTitlePart(titlePart: TitlePartType<T>, value: T) {
        TODO("Not yet implemented")
    }

    /**
     * Clears the title, if one is being displayed
     *
     * @see Title
     * @since 0.0.3
     */
    override fun clearTitle() {
        TODO("Not yet implemented")
    }

    /**
     * Resets the title and timings back to their default
     *
     * @see Title
     * @since 0.0.3
     */
    override fun resetTitle() {
        TODO("Not yet implemented")
    }

    /**
     * Send an actionbar to this audience
     *
     * @see text
     * @since 0.0.3
     */
    override fun sendActionBar(text: Text) {
        TODO("Not yet implemented")
    }

    /**
     * Play a sound to this audience
     *
     * @since 0.0.5
     */
    override fun playSound(sound: Sound) {
        TODO("Not yet implemented")
    }

    /**
     * Play a sound to this audience in specifies location
     *
     * @since 0.0.5
     */
    override fun playSound(sound: Sound, location: Location) {
        TODO("Not yet implemented")
    }

    /**
     * Stop a sound
     *
     * @since 0.0.5
     */
    override fun stopSound(soundStop: SoundStop) {
        TODO("Not yet implemented")
    }

    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override var location: Location
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * The vector of this object
     *
     * @since 0.0.1
     * @see Vector
     */
    override var velocity: Vector
        get() = TODO("Not yet implemented")
        set(value) {}

    /**
     * Teleport this locatable to another [location]
     *
     * @since 0.0.3
     * @param location the location this locatable to teleport to
     * @see Location
     */
    override fun teleport(location: Location) {
        TODO("Not yet implemented")
    }

    /**
     * Teleport [locatable] to another locatable location
     *
     * @since 0.0.3
     * @param locatable the locatable this locatable to teleport to
     * @see Location
     */
    override fun teleport(locatable: Locatable) {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on.
     *
     * @return Set containing all the channels that this client may accept.
     */
    override val listeningPluginChannels: Set<String>
        get() = TODO("Not yet implemented")
}