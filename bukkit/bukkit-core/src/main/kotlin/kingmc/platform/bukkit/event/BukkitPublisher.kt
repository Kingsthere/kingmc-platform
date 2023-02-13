package kingmc.platform.bukkit.event

import com.ktil.reflect.findFunction
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
import kingmc.platform.event.ClassRegisteredListener
import kingmc.platform.event.Proxy
import kingmc.platform.logging.infoColored
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
@Component("bukkitProxy")
@PlatformImplementation
@Scope(BeanScope.SINGLETON)
class BukkitPublisher : Proxy(), Listener, Releasable, ContextAware {
    override lateinit var context: Context

    @Awake(3)
    fun init() {
        // Add bukkit event hook
        Bukkit.getPluginManager().registerEvents(this@BukkitPublisher, bukkitPluginInstance)
        infoColored("using $this as event publisher support")
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

    override fun register(listener: Any): kingmc.platform.event.RegisteredListener {
        val registeredListener = super.register(listener)
        registeredListener.handlers.forEach { (eventType, _) ->
            if (eventType.isSubclassOf(Event::class)) {
                // Register handler to bukkit
                @Suppress("UNCHECKED_CAST")
                registerBukkitHandle(eventType as KClass<out Event>)
            }
        }
        return registeredListener
    }

    override fun <T : Any> register(
        event: KClass<out T>,
        listener: suspend (T) -> Unit,
        priority: Byte,
        ignoreCancelled: Boolean,
    ): kingmc.platform.event.RegisteredListener {
        val registeredListener = super.register(event, listener, priority, ignoreCancelled)
        if (event.isSubclassOf(Event::class)) {
            // Register handler to bukkit
            @Suppress("UNCHECKED_CAST")
            registerBukkitHandle(event as KClass<out Event>)
        }
        return registeredListener
    }

    override fun cancel(listener: kingmc.platform.event.RegisteredListener) {
        listener.handlers.forEach { (eventType, _) ->
            if (eventType.isSubclassOf(Event::class)) {
                // Register handler to bukkit
                @Suppress("UNCHECKED_CAST")
                removeBukkitHandle(eventType as KClass<out Event>)
            }
        }
        super.cancel(listener)
    }

    override fun cancel(listener: Any) {
        val listenerFound = listeners.find { it is ClassRegisteredListener }
            ?: throw IllegalArgumentException("No listener $listener found")
        listenerFound.handlers.forEach { (eventType, _) ->
            if (eventType.isSubclassOf(Event::class)) {
                // Register handler to bukkit
                @Suppress("UNCHECKED_CAST")
                removeBukkitHandle(eventType as KClass<out Event>)
            }
        }
        super.cancel(listenerFound)
    }

    override fun clear() {
        clearBukkitHandle()
        super.clear()
    }

    override fun isEventCancelled(event: Any): Boolean {
        // Hook bukkit event cancellation
        if (event is Cancellable) {
            return event.isCancelled
        }
        return super.isEventCancelled(event)
    }

    val registeredHandledBukkitEvent: MutableMap<KClass<out Event>, RegisteredListener> = mutableMapOf()

    /**
     * Register a bukkit handle
     */
    fun registerBukkitHandle(eventClass: KClass<out Event>) {
        if (!(registeredHandledBukkitEvent.contains(eventClass)) && eventClass.isSubclassOf(Event::class)) {
            val bukkitRegisteredListener =
                RegisteredListener(this@BukkitPublisher, { _, event -> this@BukkitPublisher.callEvent(event) }, EventPriority.MONITOR, bukkitPluginInstance, false)
            // Register listener to HandlerList
            (eventClass.findFunction("getHandlerList")!!.call() as HandlerList).register(bukkitRegisteredListener)
            registeredHandledBukkitEvent.put(eventClass, bukkitRegisteredListener)
        }
    }

    /**
     * Remove a bukkit handle
     */
    fun removeBukkitHandle(eventClass: KClass<out Event>) {
        registeredHandledBukkitEvent[eventClass]?.let {
            (eventClass.findFunction("getHandlerList")!!.call() as HandlerList).unregister(
                it
            )
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