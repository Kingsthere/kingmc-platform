package kingmc.platform.messaging

import com.ktil.annotation.getAnnotation
import com.ktil.annotation.hasAnnotation
import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor

@Component
object PluginMessageListenerRegistrar : BeanProcessor {
    val openedPluginMessagingChannels = mutableListOf<String>()

    /**
     * The lifecycle where this processor start to work, this
     * only work when you use it to process a [LifecycleContext]
     *
     * @see Lifecycle
     */
    override val lifecycle: Int
        get() = 1

    /**
     * The priority of this processor to process beans
     */
    override val priority: Byte
        get() = 0

    override fun process(context: Context, bean: Any): Boolean {
        val beanType = bean::class
        if (beanType.hasAnnotation<PluginMessageListener>()) {
            // Gets the messenger from context
            val messenger = context.getBean(Messenger::class)
                ?: throw IllegalStateException("Unable to register plugin messaging channel, because PluginMessagingChannelFactory is not defined")
            val pluginMessageListenerAnnotation = beanType.getAnnotation<PluginMessageListener>()!!
            val pluginMessagingChannel = messenger.getIncomingPluginMessagingChannel(pluginMessageListenerAnnotation.name)
            // Active the channel
            pluginMessagingChannel.active()
            // Register sub channels
            pluginMessagingChannel.registerListener(bean)
            return true
        }
        if (beanType.hasAnnotation<OpenPluginMessagingChannel>()) {
            // Gets the messenger from context
            val annotation = beanType.getAnnotation<OpenPluginMessagingChannel>()!!
            openedPluginMessagingChannels.addAll(annotation.channel)
        }
        return super.process(context, bean)
    }

    override fun end(context: Context) {
        val messenger = context.getBean(Messenger::class)
            ?: throw IllegalStateException("Unable to register plugin messaging channel, because PluginMessagingChannelFactory is not defined")
        openedPluginMessagingChannels.forEach { messenger.openOutgoingPluginMessagingChannel(it) }
    }

    override fun dispose(context: Context, bean: Any) {
        val beanType = bean::class
        if (beanType.hasAnnotation<PluginMessageListener>()) {
            // Gets the plugin messaging channel factory from context
            val factory = context.getBean(Messenger::class)
                ?: throw IllegalStateException("Unable to register plugin messaging channel, because PluginMessagingChannelFactory is not defined")
            val pluginMessageListenerAnnotation = beanType.getAnnotation<PluginMessageListener>()!!
            val pluginMessagingChannel = factory.getIncomingPluginMessagingChannel(pluginMessageListenerAnnotation.name)
            // Active the channel
            pluginMessagingChannel.active()
            // Register sub channels
            pluginMessagingChannel.unregisterListener(bean)
        }
        if (beanType.hasAnnotation<OpenPluginMessagingChannel>()) {
            // Gets the messenger from context
            val messenger = context.getBean(Messenger::class)
                ?: throw IllegalStateException("Unable to register plugin messaging channel, because PluginMessagingChannelFactory is not defined")
            val annotation = beanType.getAnnotation<OpenPluginMessagingChannel>()!!
            annotation.channel.forEach {
                if (openedPluginMessagingChannels.contains(it)) {
                    messenger.closeOutgoingPluginMessagingChannel(it)
                    openedPluginMessagingChannels.remove(it)
                }
            }
        }
        super.dispose(context, bean)
    }
}