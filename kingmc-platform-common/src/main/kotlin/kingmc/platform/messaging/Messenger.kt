package kingmc.platform.messaging

import kingmc.common.context.annotation.Scope
import kingmc.common.context.annotation.Service
import kingmc.common.context.beans.BeanScope
import java.io.Closeable

/**
 * A manager for plugin messaging channels
 *
 * @author kingsthere
 * @since 0.0.4
 * @see IncomingPluginMessagingChannel
 */
@Service
@Scope(BeanScope.SINGLETON)
interface Messenger : Closeable {
    /**
     * `true` if this `Messenger` is closed
     */
    val isClosed: Boolean

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

    /**
     * Close this messenger
     */
    override fun close()
}