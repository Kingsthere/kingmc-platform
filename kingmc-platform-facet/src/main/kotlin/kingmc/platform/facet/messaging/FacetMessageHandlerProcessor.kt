package kingmc.platform.facet.messaging

import kingmc.common.application.withApplication
import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor
import kingmc.platform.messaging.MessageHandlerRegistration
import kingmc.platform.messaging.PluginMessageChannelHandler
import kingmc.platform.messaging.messenger
import kotlin.reflect.full.hasAnnotation

/**
 * A processor to load `MessageHandler`(s)
 *
 * @author kingsthere
 * @since 0.0.6
 */
@Component
object FacetMessageHandlerProcessor : BeanProcessor {
    override val lifecycle: Int
        get() = 3

    private val _registeredMessageHandlers = mutableMapOf<ClassMessageHandler, MessageHandlerRegistration>()

    override fun process(context: Context, bean: Any): Boolean {
        if (bean::class.hasAnnotation<PluginMessageChannelHandler>()) {
            bean.withApplication {
                val messageHandler = ClassMessageHandler(bean::class)
                val messageChannel = messenger.getIncomingPluginMessagingChannel(messageHandler.channel)
                _registeredMessageHandlers.put(messageHandler, messageChannel.registerHandler(messageHandler))
                messageChannel.activate()
            }
        }
        return true
    }

    override fun dispose(context: Context, bean: Any) {
        if (bean::class.hasAnnotation<PluginMessageChannelHandler>()) {
            bean.withApplication {
                val messageHandler = ClassMessageHandler(bean::class)
                val messageChannel = messenger.getIncomingPluginMessagingChannel(messageHandler.channel)
                messageChannel.unregisterHandler(_registeredMessageHandlers[messageHandler]!!)
            }
        }
    }
}