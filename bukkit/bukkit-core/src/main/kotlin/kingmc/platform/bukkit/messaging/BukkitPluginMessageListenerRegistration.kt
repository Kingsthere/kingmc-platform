package kingmc.platform.bukkit.messaging

import kingmc.platform.messaging.PluginMessageListenerRegistration
import kingmc.platform.messaging.SubChannelHandler

class BukkitPluginMessageListenerRegistration(
    override val channel: String,
    private val handlers: Iterable<SubChannelHandler>
) : PluginMessageListenerRegistration {
    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<SubChannelHandler> =
        handlers.iterator()
}