package kingmc.platform.messaging

/**
 * A superinterface represents a possible recipient of plugin messages
 *
 * @since 0.0.4
 * @author kingsthere
 */
interface PluginMessageSink {
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
    fun sendPluginMessage(channel: String, message: OutputMessage)

    /**
     * Gets a set containing all the Plugin Channels that this client is
     * listening on.
     *
     * @return Set containing all the channels that this client may accept.
     */
    val listeningPluginChannels: Set<String>
}