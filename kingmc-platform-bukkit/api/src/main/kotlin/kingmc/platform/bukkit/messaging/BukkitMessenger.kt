package kingmc.platform.bukkit.messaging

import kingmc.common.application.application
import kingmc.common.context.annotation.Scope
import kingmc.common.context.annotation.Service
import kingmc.common.context.beans.BeanScope
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.facet.messaging.FacetMessenger
import kingmc.platform.messaging.IncomingPluginMessagingChannel

@Service
@BukkitImplementation
@Scope(BeanScope.SINGLETON)
class BukkitMessenger : FacetMessenger() {
    /**
     * Close a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    override fun closeOutgoingPluginMessagingChannel(name: String) {
        Bukkit.getMessenger().unregisterOutgoingPluginChannel(bukkitPlugin, name)
    }

    /**
     * Create an incoming plugin messaging channel
     */
    override fun createIncomingPluginMessagingChannel(name: String): IncomingPluginMessagingChannel {
        return BukkitPluginMessagingChannel(name, application)
    }

    /**
     * Open a out going plugin messaging channel
     *
     * @param name the name of the outgoing plugin messaging channel
     * @since 0.0.4
     */
    override fun openOutgoingPluginMessagingChannel(name: String) {
        Bukkit.getMessenger().registerOutgoingPluginChannel(bukkitPlugin, name)
    }

    override fun close() {
        // Close outgoing channels
        openedOutgoingChannels.forEach(::closeOutgoingPluginMessagingChannel)
        // Close incoming channels
        openedIncomingChannels.values.forEach { it.close() }

        super.close()
    }
}