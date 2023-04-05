package kingmc.platform.facet.messaging

import kingmc.common.context.annotation.Service
import kingmc.platform.facet.FacetImplementation
import kingmc.platform.messaging.IncomingPluginMessagingChannel
import kingmc.platform.messaging.Messenger

@Service
@FacetImplementation
open class FacetMessenger : Messenger {
    /**
     * `true` if this `Messenger` is closed
     */
    final override var isClosed: Boolean = false
        private set

    /**
     * Names of opened outgoing channels
     */
    protected val openedOutgoingChannels: MutableSet<String> = mutableSetOf()

    /**
     * Set of opened incoming channels
     */
    protected val openedIncomingChannels: MutableMap<String, IncomingPluginMessagingChannel> = mutableMapOf()

    /**
     * Gets a named incoming plugin messaging channel from this factory, this [Messenger]
     * should cache created plugin messaging channel instances, when calling [getIncomingPluginMessagingChannel]
     * with same name it should return the same [IncomingPluginMessagingChannel] instance
     *
     * For the implementing messengers, **DO NOT OVERRIDE THIS FUNCTION**, implement [createIncomingPluginMessagingChannel]
     * instead
     *
     * @param name the name of the plugin messaging channel
     * @since 0.0.4
     */
    final override fun getIncomingPluginMessagingChannel(name: String): IncomingPluginMessagingChannel {
        return openedIncomingChannels.computeIfAbsent(name) { createIncomingPluginMessagingChannel(name) }
    }

    /**
     * Create an incoming plugin messaging channel
     */
    protected open fun createIncomingPluginMessagingChannel(name: String): IncomingPluginMessagingChannel {
        return FacetIncomingPluginMessagingChannel(name)
    }

    /**
     * Open a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    override fun openOutgoingPluginMessagingChannel(name: String) {
        openedOutgoingChannels.add(name)
    }

    /**
     * Close a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    override fun closeOutgoingPluginMessagingChannel(name: String) {
        openedOutgoingChannels.remove(name)
    }

    /**
     * Close this messenger
     */
    override fun close() {
        isClosed = true
    }
}