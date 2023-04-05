package kingmc.platform.messaging

/**
 * Declared a registration of a plugin messaging channel listener
 *
 * @since 0.0.4
 * @author kingsthere
 */
interface MessageHandlerRegistration {
    /**
     * The channel that the plugin messaging channel listener is listened
     * to
     */
    val channel: IncomingPluginMessagingChannel

    /**
     * The handler to handle the messages that this listener received
     */
    val handler: MessageHandler
}