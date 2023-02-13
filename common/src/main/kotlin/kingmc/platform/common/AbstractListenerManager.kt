package kingmc.platform.common

import kingmc.common.context.Context
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.aware.ContextAware
import kingmc.platform.event.ListenerManager
import kingmc.platform.event.Publisher

/**
 * An abstract implement of [ListenerManager]
 */
@Component
abstract class AbstractListenerManager : ListenerManager, ContextAware {
    /**
     * The publisher this is managing
     *
     * @since 0.0.3
     */
    @Autowired
    override lateinit var publisher: Publisher

    override lateinit var context: Context
}