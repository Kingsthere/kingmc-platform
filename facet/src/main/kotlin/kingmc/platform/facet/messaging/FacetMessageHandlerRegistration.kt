package kingmc.platform.facet.messaging

import kingmc.platform.messaging.IncomingPluginMessagingChannel
import kingmc.platform.messaging.MessageHandler
import kingmc.platform.messaging.MessageHandlerRegistration

data class FacetMessageHandlerRegistration(override val channel: IncomingPluginMessagingChannel, override val handler: MessageHandler) : MessageHandlerRegistration