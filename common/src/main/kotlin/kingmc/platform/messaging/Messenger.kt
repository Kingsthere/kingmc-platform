package kingmc.platform.messaging

import kingmc.common.context.annotation.Service

/**
 * A manager for plugin messaging channels
 *
 * @since 0.0.4
 * @author kingsthere
 * @see IncomingPluginMessagingChannel
 */
@Service("messenger")
interface Messenger {
    /**
     * Gets a named incoming plugin messaging channel from this factory, this [Messenger]
     * should cache created plugin messaging channel instances, when calling [getIncomingPluginMessagingChannel]
     * with same name it should return the same [IncomingPluginMessagingChannel] instance
     *
     * @param name the name of the plugin messaging channel
     * @since 0.0.4
     */
    fun getIncomingPluginMessagingChannel(name: String): IncomingPluginMessagingChannel

    /**
     * Open a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    fun openOutgoingPluginMessagingChannel(name: String)

    /**
     * Close a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    fun closeOutgoingPluginMessagingChannel(name: String)
}