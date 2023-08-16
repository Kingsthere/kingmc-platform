package kingmc.platform.entity.player

import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.audience.particle.Particle
import kingmc.platform.audience.particle.ParticleRecipient
import kingmc.platform.command.CommandSender
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.messaging.PluginMessageSink

/**
 * A receiver that forward the messages to one or more [Player]s
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Players :
    Iterable<Player>,
    ParticleRecipient,
    ForwardingAudience,
    CommandSender,
    PluginMessageSink {
    override fun audiences(): Iterable<Player>

    override fun command(command: String) {
        audiences().forEach { it.command(command) }
    }

    override fun iterator(): Iterator<Player> =
        audiences().iterator()

    override fun sendParticle(
        particle: Particle<*>,
        x: Double,
        y: Double,
        z: Double,
        longDistance: Boolean,
        offsetX: Float,
        offsetY: Float,
        offsetZ: Float,
        maxSpeed: Float,
        count: Int
    ) {
        audiences().forEach {
            it.sendParticle(particle, x, y, z, longDistance, offsetX, offsetY, offsetZ, maxSpeed, count)
        }
    }

    /**
     * To let this command sender send a
     * chat message
     *
     * @since 0.0.3
     * @author kingsthere
     */
    override fun chat(message: String) {
        audiences().forEach { it.chat(message) }
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
