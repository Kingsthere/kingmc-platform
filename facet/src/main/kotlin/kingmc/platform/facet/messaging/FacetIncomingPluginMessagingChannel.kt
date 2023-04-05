package kingmc.platform.facet.messaging

import kingmc.platform.messaging.IncomingPluginMessagingChannel
import kingmc.platform.messaging.InputMessage
import kingmc.platform.messaging.MessageHandler
import kingmc.platform.messaging.MessageHandlerRegistration

open class FacetIncomingPluginMessagingChannel(name: String) : IncomingPluginMessagingChannel(name) {
    final override var isActivated: Boolean = false
        private set
    final override var isClosed: Boolean = false
        private set

    override fun activate() {
        isActivated = true
    }

    override fun close() {
        isClosed = true
        isActivated = false
    }

    val registeredMessageHandlers: MutableSet<MessageHandlerRegistration> = mutableSetOf()

    fun createRegistrationForHandler(messageHandler: MessageHandler): MessageHandlerRegistration {
        return FacetMessageHandlerRegistration(this, messageHandler)
    }

    override fun registerHandler(handler: MessageHandler): MessageHandlerRegistration {
        check(isActivated) { "This messaging channel is not activated" }
        check(isClosed) { "This messaging channel is already closed" }
        return createRegistrationForHandler(handler)
    }

    override fun unregisterHandler(registration: MessageHandlerRegistration) {
        registeredMessageHandlers.remove(registration)
    }

    /**
     * Simulate to send a message into this channel and let it handle the message
     */
    protected fun handleMessage(message: InputMessage) {
        registeredMessageHandlers.forEach {
            it.handler(message)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FacetIncomingPluginMessagingChannel) return false

        if (isActivated != other.isActivated) return false
        if (isClosed != other.isClosed) return false
        if (registeredMessageHandlers != other.registeredMessageHandlers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = isActivated.hashCode()
        result = 31 * result + isClosed.hashCode()
        result = 31 * result + registeredMessageHandlers.hashCode()
        return result
    }

    override fun toString(): String {
        return "FacetIncomingPluginMessagingChannel(isActivated=$isActivated, isClosed=$isClosed, registeredMessageHandlers=$registeredMessageHandlers)"
    }
}