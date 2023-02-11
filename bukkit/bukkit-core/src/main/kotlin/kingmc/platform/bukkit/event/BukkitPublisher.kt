package kingmc.platform.bukkit.event

import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.aware.ContextAware
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Awake
import kingmc.platform.PlatformImplementation
import kingmc.platform.Releasable
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.bukkitPluginInstance
import kingmc.platform.event.Proxy
import kingmc.platform.logging.infoColored
import org.bukkit.event.Cancellable
import org.bukkit.event.EventPriority
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.block.*
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.*
import org.bukkit.event.player.*
import org.bukkit.event.server.*
import org.bukkit.event.vehicle.*
import org.bukkit.event.world.*
import org.bukkit.plugin.RegisteredListener


/**
 * A `Publisher` for bukkit proxied events sent by bukkit api
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Component("bukkitProxy")
@PlatformImplementation
@Scope(BeanScope.SINGLETON)
class BukkitPublisher : Proxy(), Listener, Releasable, ContextAware {
    override lateinit var context: Context

    @Awake(3)
    fun init() {
        // Add bukkit event hook
        Bukkit.getPluginManager().registerEvents(this@BukkitPublisher, bukkitPluginInstance)
        val registeredListener =
            RegisteredListener(this@BukkitPublisher, { _, event -> this@BukkitPublisher.callEvent(event) }, EventPriority.MONITOR, bukkitPluginInstance, false)
        for (handler in HandlerList.getHandlerLists()) {
            handler.register(registeredListener)
        }
        AsyncPlayerChatEvent.getHandlerList().register(registeredListener)
        infoColored("<dark_grey>Hooked into bukkit events successfully")
    }

    /**
     * Release
     */
    override fun release() {
        // Clear event listeners
        clear()
    }

    override fun checkEventCancellable(event: Any): Boolean {
        if (event is Cancellable) {
            return true
        }
        return super.checkEventCancellable(event)
    }

    override fun isEventCancelled(event: Any): Boolean {
        // Hook bukkit event cancellation
        if (event is Cancellable) {
            return event.isCancelled
        }
        return super.isEventCancelled(event)
    }
}