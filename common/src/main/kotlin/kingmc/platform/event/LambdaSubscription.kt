package kingmc.platform.event

import kingmc.common.application.Application
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kotlin.reflect.KClass

/**
 * A simple implementation of [Subscription] handles the event by lambdas
 *
 * @since 0.0.7
 * @author kingsthere
 */
class LambdaSubscription<TEvent : Any>(override val eventType: KClass<TEvent>,
                                       val callback: @WithApplication suspend (TEvent) -> Unit,
                                       override val priority: Byte = 0,
                                       override val ignoreCancelled: Boolean = true,
                                       override val publisher: Publisher,
                                       override val application: Application = currentApplication()
) : Subscription<TEvent> {
    override suspend fun invoke(p1: TEvent) = callback.invoke(p1)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LambdaSubscription<*>) return false

        if (eventType != other.eventType) return false
        if (callback != other.callback) return false
        if (priority != other.priority) return false
        if (ignoreCancelled != other.ignoreCancelled) return false

        return true
    }

    override fun hashCode(): Int {
        var result = eventType.hashCode()
        result = 31 * result + callback.hashCode()
        result = 31 * result + priority
        result = 31 * result + ignoreCancelled.hashCode()
        return result
    }

    override fun toString(): String {
        return "LambdaSubscription(eventType=$eventType, callback=$callback, priority=$priority, ignoreCancelled=$ignoreCancelled)"
    }
}