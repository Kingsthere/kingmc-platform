package kingmc.platform.bukkit.messaging

import kingmc.common.context.annotation.Service
import kingmc.platform.PlatformImplementation
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.bukkitPluginInstance
import kingmc.platform.messaging.IncomingPluginMessagingChannel
import kingmc.platform.messaging.Messenger

@Service("bukkitMessenger")
@PlatformImplementation
object BukkitMessenger : Messenger {
    /**
     * Close a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    override fun closeOutgoingPluginMessagingChannel(name: String) {
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(bukkitPluginInstance, name)
    }

    /**
     * Gets a named plugin messaging channel from this factory, this [Messenger]
     * should cache created plugin messaging channel instances, when calling [getIncomingPluginMessagingChannel]
     * with same name it should return the same [Messenger] instance
     *
     * @param name the name of the plugin messaging channel
     * @since 0.0.4
     */
    override fun getIncomingPluginMessagingChannel(name: String): IncomingPluginMessagingChannel =
        BukkitPluginMessagingChannel(name)

    /**
     * Open a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    override fun openOutgoingPluginMessagingChannel(name: String) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(bukkitPluginInstance, name)
    }
}