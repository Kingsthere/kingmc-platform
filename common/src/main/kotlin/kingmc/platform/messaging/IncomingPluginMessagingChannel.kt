package kingmc.platform.messaging

import java.io.Closeable

/**
 * Define a plugin messaging channel receive and forward plugin messages to [PluginMessageListener]s
 *
 * @since 0.0.4
 * @author kingsthere
 */
abstract class IncomingPluginMessagingChannel(val name: String): Closeable {
    /**
     * Active this plugin messaging channel
     */
    abstract fun active()

    /**
     * Close this plugin messaging channel
     */
    abstract override fun close()

    /**
     * Register a listener into this [IncomingPluginMessagingChannel]
     *
     * @throws PluginMessageListenerRegistrationException if the listener is already registered
     */
    abstract fun registerListener(listener: Any): PluginMessageListenerRegistration

    /**
     * Unregister a listener from this [IncomingPluginMessagingChannel]
     *
     * @throws PluginMessageListenerRegistrationException if the listener is not registered
     */
    abstract fun unregisterListener(listener: Any)
}