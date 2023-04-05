package kingmc.platform.bukkit.messaging

import kingmc.common.application.Application
import kingmc.common.application.application
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.bukkit.getPlayerForBukkit
import kingmc.platform.facet.messaging.FacetIncomingPluginMessagingChannel
import kingmc.platform.messaging.PluginMessageChannelHandler
import kingmc.platform.messaging.arrayDataInputMessage
import kingmc.util.annotation.getAnnotation
import org.bukkit.entity.Player
import kotlin.reflect.KClass

class BukkitPluginMessagingChannel(name: String, val application: Application) : FacetIncomingPluginMessagingChannel(name), BukkitPluginMessageListener {
    /**
     * Active this plugin messaging channel
     */
    override fun activate() {
        Bukkit.getMessenger().registerIncomingPluginChannel(bukkitPlugin, name, this)
        super.activate()
    }

    /**
     * Close this plugin messaging channel
     */
    override fun close() {
        Bukkit.getMessenger().unregisterIncomingPluginChannel(bukkitPlugin, name, this)
        super.close()
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
        registeredMessageHandlers.forEach { entry ->
            if (entry.channel.name == channel) {
                val message = application(application) {
                    arrayDataInputMessage(getPlayerForBukkit(player), channel, byteMessage)
                }
                entry.handler(message)
            }
        }
    }

    /**
     * Gets the target channel of a listener class
     *
     * @since 0.0.4
     */
    private fun getTargetChannel(listenerClass: KClass<*>) =
        listenerClass.getAnnotation<PluginMessageChannelHandler>()!!.pluginMessagingChannel
}