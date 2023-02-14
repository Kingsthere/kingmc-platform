package kingmc.platform.audience

import kingmc.platform.*
import kingmc.platform.audience.capable.MinecraftIdentityCapable
import kingmc.platform.audience.particle.*
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.text.text
import kingmc.platform.block.Block
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.messaging.PluginMessageRecipient

/**
 * Represent a command sender that capable for send commands
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Audience
 */
interface CommandSender : Audience {
    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    fun chat(line: String)
}

/**
 * Extended [CommandSender], exposed the [location] of current
 * command sender
 *
 * @since 0.0.5
 * @author kingsthere
 */
interface LocatableCommandSender : CommandSender, Locatable

/**
 * Represent a console in minecraft server
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandSender
 * @see Audience
 */
interface Console : CommandSender

/**
 * Represent a command block in minecraft
 * server
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandSender
 * @see Audience
 */
interface BlockCommandSender : LocatableCommandSender, Block

/**
 * Represent a minecraft player
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandSender
 * @see Audience
 */
interface Player : OfflinePlayer, ParticleRecipient, LocatableCommandSender, HumanAudience, PluginMessageRecipient {
    /**
     * Cast this player to the player
     * in the original platform api copy, it may throw a [UnsupportedOperationException]
     *
     * @since 0.0.3
     */
    fun <T> cast(): T
}

/**
 * A receiver that forward the messages to one or more [Player]s
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Players : Iterable<Player>, ParticleRecipient, ForwardingAudience, CommandSender, MutablePhysical, PluginMessageRecipient {
    override fun audiences(): Iterable<Player>

    override fun iterator(): Iterator<Player> =
        audiences().iterator()

    override fun particle(particle: Particle) {
        audiences().forEach { it.particle(particle) }
    }

    override fun particle(particleAnimation: ParticleAnimation): ParticleAnimationTask =
        ParticleAnimationTask.supervisor(*audiences().map { it.particle(particleAnimation) }.toTypedArray(), speed = 1)

    override fun particle(particleGroup: ParticleGroup) {
        audiences().forEach { it.particle(particleGroup) }
    }

    override fun particle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask =
        ParticleAnimationTask.supervisor(*audiences().map { it.particle(particleAnimation, speed) }.toTypedArray(), speed = speed)

    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override fun chat(line: String) {
        audiences().forEach { it.chat(line) }
    }

    /**
     * Convert this object into a [text]
     */
    override fun asText(): Text =
        Text {
            audiences().forEach {
                append(it.displayName)
                append(Text.text(","))
            }
        }

    /**
     * The vector of this object
     *
     * @since 0.0.1
     * @see Vector
     */
    override var vector: Vector
        get() = throw UnsupportedOperationException()
        set(value) {
            audiences().forEach { it.vector = value }
        }

    /**
     * The location of this locatable
     *
     * @since 0.0.3
     */
    override var location: Location
        get() = throw UnsupportedOperationException()
        set(value) {
            audiences().forEach {
                @Suppress("DEPRECATION")
                it.location = value
            }
        }

    /**
     * Teleport this locatable to another [location]
     *
     * @since 0.0.3
     * @param location the location this locatable to teleport to
     * @see Location
     */
    override fun teleport(location: Location) {
        audiences().forEach { it.teleport(location) }
    }

    /**
     * Teleport [locatable] to another locatable location
     *
     * @since 0.0.3
     * @param locatable the locatable this locatable to teleport to
     * @see Location
     */
    override fun teleport(locatable: Locatable) {
        audiences().forEach { it.teleport(locatable) }
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
        audiences().first().sendPluginMessage(channel, message)
    }

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on.
     *
     * @return Set containing all the channels that this client may accept.
     */
    override val listeningPluginChannels: Set<String>
        get() = throw UnsupportedOperationException()
}

/**
 * Represent an offline player, defined extra information of a player could
 * hold when it is not online
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface OfflinePlayer : MinecraftIdentityCapable {
    /**
     * Checks if this player is currently online
     */
    val isOnline: Boolean

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
    val firstPlayed: Long

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
    val lastPlayed: Long

    /**
     * Checks if this player has played on this server before
     *
     * @return True if the player has played before, otherwise false
     */
    fun hasPlayedBefore(): Boolean
}