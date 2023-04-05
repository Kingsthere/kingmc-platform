package kingmc.platform.facet.messaging

import kingmc.platform.messaging.*
import kingmc.util.annotation.getAnnotation
import kingmc.util.reflect.findFunctionsByAnnotation
import kotlin.reflect.KClass
import kotlin.reflect.KFunction

/**
 * Extended [MessageHandler] to declare a message handler from a class
 *
 * @since 0.0.6
 * @author kingsthere
 * @param clazz the class to load [MessageHandler] from
 */
open class ClassMessageHandler(val clazz: KClass<*>) : MessageHandler {
    init {
        // @PluginMessageHandler check
        requireNotNull(clazz.getAnnotation<PluginMessageChannelHandler>()) { "A message handler must annotated with @PluginMessageChannelHandler" }
    }

    /**
     * The channel that the specifies class is listening to
     */
    val channel: String = clazz.getAnnotation<PluginMessageChannelHandler>()!!.pluginMessagingChannel

    /**
     * Sub channels that the class is listening to
     */
    val subChannels: List<SubChannelMessageHandler> = buildList {
        fun buildSubChannelHandler(function: KFunction<*>) : SubChannelMessageHandler {
            val subChannel = function.getAnnotation<SubscribeSubChannel>()!!.subChannel
            return FunctionSubChannelMessageHandler(subChannel, function)
        }
        clazz.findFunctionsByAnnotation<SubscribeSubChannel>().map { buildSubChannelHandler(it) }
    }


    override fun invoke(message: InputMessage) {
        val messageSubChannel = message.subChannel
        subChannels.filter { it.subChannel == messageSubChannel }.forEach { it.invoke(message) }
    }

    override fun toString(): String {
        return "ClassMessageHandler(clazz=$clazz, channel='$channel', subChannels=$subChannels)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ClassMessageHandler) return false

        if (clazz != other.clazz) return false

        return true
    }

    override fun hashCode(): Int {
        return clazz.hashCode()
    }

    class FunctionSubChannelMessageHandler(override val subChannel: String, val function: KFunction<*>) : SubChannelMessageHandler {
        override fun invoke(message: InputMessage) {
            function.call(message)
        }

        override fun toString(): String {
            return "FunctionSubChannelMessageHandler(subChannel='$subChannel',function=$function)"
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is FunctionSubChannelMessageHandler) return false

            if (subChannel != other.subChannel) return false
            if (function != other.function) return false

            return true
        }

        override fun hashCode(): Int {
            var result = subChannel.hashCode()
            result = 31 * result + function.hashCode()
            return result
        }
    }
}