package kingmc.platform.event.subscription

import kingmc.common.application.Application
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.event.Publisher
import kotlin.reflect.KClass

/**
 * A simple implementation of [BlockingSubscription]
 *
 * @author kingsthere
 * @since 0.0.9
 */
class BlockingSubscriptionImpl<TEvent : Any>(override val eventType: KClass<TEvent>,
                                             override val handler: @WithApplication (TEvent) -> Unit,
                                             override val priority: Byte = 0,
                                             override val ignoreCancelled: Boolean = true,
                                             override val publisher: Publisher,
                                             override val application: Application = currentApplication()
) : BlockingSubscription<TEvent> {
    override fun toString(): String {
        return "LambdaBlockingSubscription(eventType=$eventType, handler=$handler, priority=$priority, ignoreCancelled=$ignoreCancelled, publisher=$publisher, application=$application)"
    }
}

/**
 * A simple implementation of [BlockingSubscription]
 *
 * @author kingsthere
 * @since 0.0.9
 */
class SuspendSubscriptionImpl<TEvent : Any>(override val eventType: KClass<TEvent>,
                                             override val handler: @WithApplication suspend (TEvent) -> Unit,
                                             override val priority: Byte = 0,
                                             override val ignoreCancelled: Boolean = true,
                                             override val publisher: Publisher,
                                             override val application: Application = currentApplication()
) : SuspendSubscription<TEvent> {
    override fun toString(): String {
        return "LambdaBlockingSubscription(eventType=$eventType, handler=$handler, priority=$priority, ignoreCancelled=$ignoreCancelled, publisher=$publisher, application=$application)"
    }
}