@file:Suppress("BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted",
    "BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted",
    "BooleanMethodIsAlwaysInverted"
)

package kingmc.platform.event

import com.esotericsoftware.reflectasm.MethodAccess
import com.ktil.annotation.getAnnotation
import com.ktil.annotation.hasAnnotation
import com.ktil.reflect.findFunctionsByAnnotation
import kingmc.common.application.application
import kingmc.common.application.suspendApplication
import kingmc.common.context.ConditionCapableContext
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.coroutineContext
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf

/**
 * Abstract implement for [Publisher] to
 * implement the default logics of `publisher`
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Publisher
 */
@Suppress("BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted",
    "BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted",
    "BooleanMethodIsAlwaysInverted", "BooleanMethodIsAlwaysInverted"
)
abstract class AbstractPublisher : Publisher {
    protected val listeners: MutableSet<RegisteredListener> = mutableSetOf()

    override fun <T : Any> callEvent(event: T): T {
        // Get the event type
        val eventType = event::class
        listeners
            .filter { listener -> listener.supports().any { eventType.isSubclassOf(it) } }
            .forEach { listener ->
                // Run handlers
                listener.handlers
                    .filter { eventType.isSubclassOf(it.key) }
                    .forEach { handles ->
                        runBlocking {
                            handles.value.forEach {
                                if (checkEventCancellable(event)) {
                                    if (!(isEventCancelled(event) && !it.ignoreCancelled)) {
                                        launch {
                                            it.invoke(event)
                                        }
                                    }
                                } else {
                                    launch {
                                        println("Invoking")
                                        it.invoke(event)
                                    }
                                }
                            }
                        }
                    }
            }
        return event
    }

    override suspend fun <T : Any> callEventSuspend(event: T): T {
        // Get the event type
        val eventType = event::class
        val scope = CoroutineScope(currentCoroutineContext())
        listeners
            .filter { listener -> listener.supports().any { eventType.isSubclassOf(it) } }
            .forEach { listener ->
                // Run handlers
                listener.handlers
                    .filter { eventType.isSubclassOf(it.key) }
                    .forEach { handles ->
                        handles.value.forEach {
                            scope.launch {
                                if (checkEventCancellable(event)) {
                                    if (!(isEventCancelled(event) && !it.ignoreCancelled)) {
                                        it.invoke(event)
                                    }
                                } else {
                                    it.invoke(event)
                                }
                            }
                        }
                    }
            }
        return event
    }

    protected open fun checkEventCancellable(event: Any): Boolean {
        return event is Cancellable
    }

    protected open fun isEventCancelled(event: Any): Boolean {
        return (event as? Cancellable)?.cancelled ?: false
    }

    override fun register(listener: Any): RegisteredListener {
        val listenerType = listener::class
        val handlers = HandlerList()
        val registeredListener = DefaultRegisteredListener(this, handlers, listenerType, listener)
        // Load the event handlers in the listener
        listenerType.findFunctionsByAnnotation<Subscribe>().forEach {
            val eventType = it.parameters[1].type.classifier as KClass<*>
            val priority = it.getAnnotation<Subscribe>()!!.priority
            val ignoreCancelled = it.hasAnnotation<IgnoreCancelled>()
            val handles = handlers.computeIfAbsent(eventType) { TreeSet() }

            // Setup properties to access listener
            val methodAccess = MethodAccess.get(listenerType.java)
            val methodName = it.name

            if (it.isSuspend) {
                handles.add(object : EventHandler<Any> {
                    override val priority: Byte
                        get() = priority

                    override val ignoreCancelled: Boolean
                        get() = ignoreCancelled

                    override suspend fun invoke(event: Any) {
                        listener.suspendApplication {
                            if (context is ConditionCapableContext && (context as ConditionCapableContext).testElementCondition(it)) {
                                methodAccess.invoke(listener, methodName, coroutineContext, event)
                            }
                        }
                    }
                })
            } else {
                handles.add(object : EventHandler<Any> {
                    override val priority: Byte
                        get() = priority

                    override val ignoreCancelled: Boolean
                        get() = ignoreCancelled

                    override suspend fun invoke(event: Any) {
                        listener.application {
                            if (context is ConditionCapableContext && (context as ConditionCapableContext).testElementCondition(it)) {
                                methodAccess.invoke(listener, methodName, event)
                            }
                        }
                    }
                })
            }
        }
        this.listeners.add(registeredListener)
        return registeredListener
    }

    override fun <T : Any> register(
        event: KClass<out T>,
        listener: suspend (T) -> Unit,
        priority: Byte,
        ignoreCancelled: Boolean
    ): RegisteredListener {
        val handlers = HandlerList()
        @Suppress("UNCHECKED_CAST")
        handlers.put(event, mutableSetOf(object : EventHandler<Any> {
            override val priority: Byte
                get() = priority

            /**
             * Whether to ignore cancelled events
             */
            override val ignoreCancelled: Boolean
                get() = ignoreCancelled

            override suspend fun invoke(event: Any) {
                suspendApplication {
                    listener.invoke(event as T)
                }
            }

        }))
        val registeredListener = AnonymousRegisteredListener(handlers, this)
        this.listeners.add(registeredListener)
        return registeredListener
    }

    override fun cancel(listener: RegisteredListener) {
        this.listeners.remove(listener)
    }

    override fun cancel(listener: Any) {
        this.listeners.removeIf { it is ClassRegisteredListener && it.instance == listener }
    }

    override fun clear() {
        this.listeners.clear()
    }
}