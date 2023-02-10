package kingmc.platform.messaging

/**
 * Declared a registration of a plugin messaging channel listener
 *
 * @since 0.0.4
 * @author kingsthere
 */
interface PluginMessageListenerRegistration : Iterable<SubChannelHandler> {
    /**
     * The name of the channel that the plugin messaging channel listener is listened
     * to
     */
    val channel: String
}