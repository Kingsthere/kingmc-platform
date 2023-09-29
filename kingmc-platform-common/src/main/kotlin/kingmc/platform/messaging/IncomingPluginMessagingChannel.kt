package kingmc.platform.messaging

import java.io.Closeable

/**
 * Define a plugin messaging channel receive and forward plugin messages to [PluginMessageChannelHandler]s
 *
 * @author kingsthere
 * @since 0.0.4
 */
abstract class IncomingPluginMessagingChannel(val name: String): Closeable {
    /**
     * `true` if this messaging channel is activated and available for using it
     */
    abstract val isActivated: Boolean

    /**
     * `true` if this messaging channel is closed, and you cannot register any handlers into
     * it or [activate] it again
     */
    abstract val isClosed: Boolean

    /**
     * Activate this plugin messaging channel
     */
    abstract fun activate()

    /**
     * Close this plugin messaging channel
     */
    abstract override fun close()

    /**
     * Register a handler from [handler] provided into this [IncomingPluginMessagingChannel]
     *
     * @param handler the handler to handle messages
     * @throws PluginMessageListenerRegistrationException if the listener is already registered
     */
    abstract fun registerHandler(handler: MessageHandler): MessageHandlerRegistration

    /**
     * Unregister a handler from this [IncomingPluginMessagingChannel]
     *
     * @param registration the registration to unregister
     * @throws PluginMessageListenerRegistrationException if the listener is not registered
     */
    abstract fun unregisterHandler(registration: MessageHandlerRegistration)
}