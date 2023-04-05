package kingmc.platform.bukkit.event

import kingmc.common.application.application
import kingmc.common.context.annotation.Component
import kingmc.platform.Awake
import kingmc.platform.Releasable
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.event.AbstractPublisher
import kingmc.platform.event.Subscription
import kingmc.util.reflect.findFunction
import org.bukkit.event.*
import org.bukkit.event.block.*
import org.bukkit.event.entity.*
import org.bukkit.event.inventory.*
import org.bukkit.event.player.*
import org.bukkit.event.server.*
import org.bukkit.event.vehicle.*
import org.bukkit.event.world.*
import org.bukkit.plugin.RegisteredListener
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * A `Publisher` for bukkit proxied events sent by bukkit api
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Component
@BukkitImplementation
class BukkitPublisher : AbstractPublisher(), Listener, Releasable {
    @Awake(3)
    fun init() {
        Bukkit.getPluginManager().registerEvents(this@BukkitPublisher, bukkitPlugin)
    }

    /**
     * Release
     */
    override fun release() {
        this.close()
    }

    override fun subscribe(subscription: Subscription<Any>) {
        val eventType = subscription.eventType
        if (eventType.isSubclassOf(Event::class)) {
            // Register handler to bukkit
            application {
                @Suppress("UNCHECKED_CAST")
                registerBukkitHandle(eventType as KClass<out Event>)
            }
        }
        super.subscribe(subscription)
    }

    override fun close() {
        clearBukkitHandle()
        super.close()
    }

    override fun checkEventCancelled(event: Any): Boolean {
        // Hook bukkit event cancellation
        if (event is Cancellable) {
            return event.isCancelled
        }
        return super.checkEventCancelled(event)
    }

    val registeredHandledBukkitEvent: MutableMap<KClass<out Event>, RegisteredListener> = mutableMapOf()

    /**
     * Register a bukkit handle
     */
    fun registerBukkitHandle(eventClass: KClass<out Event>) {
        if (!(registeredHandledBukkitEvent.contains(eventClass)) && eventClass.isSubclassOf(Event::class)) {
            val bukkitRegisteredListener =
                RegisteredListener(this@BukkitPublisher, { _, event -> this@BukkitPublisher.callEvent(event) }, EventPriority.MONITOR, bukkitPlugin, false)
            // Register listener to HandlerList
            (eventClass.findFunction("getHandlerList")!!.call() as HandlerList).register(bukkitRegisteredListener)
            registeredHandledBukkitEvent.put(eventClass, bukkitRegisteredListener)
        }
    }

    /**
     * Clear all bukkit handles
     */
    fun clearBukkitHandle() {
        registeredHandledBukkitEvent.forEach { (eventClass, registeredListener) ->
            (eventClass.findFunction("getHandlerList")!!.call() as HandlerList).unregister(registeredListener)
        }
        registeredHandledBukkitEvent.clear()
    }
}