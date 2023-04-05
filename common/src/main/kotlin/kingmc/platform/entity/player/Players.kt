package kingmc.platform.entity.player

import kingmc.common.text.Text
import kingmc.platform.audience.ForwardingAudience
import kingmc.platform.audience.particle.*
import kingmc.platform.command.CommandSender
import kingmc.platform.messaging.OutputMessage
import kingmc.platform.messaging.PluginMessageRecipient

/**
 * A receiver that forward the messages to one or more [Player]s
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Players : Iterable<Player>, ParticleRecipient, ForwardingAudience, CommandSender, PluginMessageRecipient {
    override fun audiences(): Iterable<Player>

    override fun iterator(): Iterator<Player> =
        audiences().iterator()

    override fun sendParticle(particle: Particle<*>) {
        audiences().forEach { it.sendParticle(particle) }
    }

    override fun sendParticle(particleAnimation: ParticleAnimation): ParticleAnimationTask =
        ParticleAnimationTask.createSupervisorParticleAnimationTask(*audiences().map { it.sendParticle(particleAnimation) }.toTypedArray(), speed = 1)

    override fun sendParticle(particleGroup: ParticleGroup) {
        audiences().forEach { it.sendParticle(particleGroup) }
    }

    override fun sendParticle(particleAnimation: ParticleAnimation, speed: Int): AcceleratedParticleAnimationTask =
        ParticleAnimationTask.createSupervisorParticleAnimationTask(*audiences().map { it.sendParticle(particleAnimation, speed) }.toTypedArray(), speed = speed)

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
     * Convert this object into a [sendText]
     */
    override fun asText(): Text =
        Text {
            audiences().forEach {
                append(it.displayName)
                append(Text.text(","))
            }
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
