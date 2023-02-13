package kingmc.platform.bukkit.messaging

import com.ktil.annotation.getAnnotation
import com.ktil.reflect.findFunctionsByAnnotation
import kingmc.common.application.application
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.audience.playerFromBukkit
import kingmc.platform.bukkit.bukkitPluginInstance
import kingmc.platform.messaging.*
import org.bukkit.entity.Player
import kotlin.reflect.KClass

class BukkitPluginMessagingChannel(name: String) : IncomingPluginMessagingChannel(name), BukkitPluginMessageListener {
    /**
     * Active this plugin messaging channel
     */
    override fun active() {
        Bukkit.getMessenger().registerIncomingPluginChannel(bukkitPluginInstance, name, this)
    }

    /**
     * Close this plugin messaging channel
     */
    override fun close() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(bukkitPluginInstance, name, this)
    }

    private val registeredListeners: MutableMap<Any, PluginMessageListenerRegistration> =
        mutableMapOf()

    /**
     * Register a listener into this `PluginMessagingChannel`
     */
    override fun registerListener(listener: Any): PluginMessageListenerRegistration {
        val listenerClass = listener::class
        val subChannelHandlers = buildList {
            listenerClass.findFunctionsByAnnotation<SubscribeSubChannel>().forEach {
                val annotation = it.getAnnotation<SubscribeSubChannel>()!!
                if (it.isSuspend) {
                    add(SuspendKFunctionSubChannelHandler(listener.application.environment, annotation.subChannel, it))
                } else {
                    add(KFunctionSubChannelHandler(annotation.subChannel, it))
                }
            }
        }
        val pluginMessageListenerRegistration = BukkitPluginMessageListenerRegistration(getTargetChannel(listenerClass), subChannelHandlers)
        registeredListeners.put(listener, pluginMessageListenerRegistration)
        return pluginMessageListenerRegistration
    }

    /**
     * Unregister a listener from this `PluginMessagingChannel`
     */
    override fun unregisterListener(listener: Any) {
        registeredListeners.remove(listener)
    }

    /**
     * A method that will be thrown when a PluginMessageSource sends a plugin
     * message on a registered channel.
     *
     * @param channel Channel that the message was sent through.
     * @param player Source of the message.
     * @param byteMessage The raw message that was sent.
     */
    override fun onPluginMessageReceived(channel: String, player: Player, byteMessage: ByteArray) {
        registeredListeners.forEach { entry ->
            if (getTargetChannel(entry.key::class) == channel) {
                val message = application(entry.key.application) { arrayDataInputMessage(playerFromBukkit(player), channel, byteMessage) }
                entry.value.forEach {
                    if (it.subChannel == message.subChannel) {
                        it(message)
                    }
                }
            }
        }
    }

    /**
     * Gets the target channel of a listener class
     *
     * @since 0.0.4
     */
    private fun getTargetChannel(listenerClass: KClass<*>) =
        listenerClass.getAnnotation<PluginMessageListener>()!!.pluginMessagingChannel
}