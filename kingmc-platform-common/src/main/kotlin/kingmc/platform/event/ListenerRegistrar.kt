package kingmc.platform.event

import kingmc.common.application.application
import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor
import kingmc.platform.listenerManager

/**
 * Process the listeners that annotated
 * with [Listener] to the specified publishers
 *
 * @author kingsthere
 * @since 0.0.1
 */
@Component
object ListenerRegistrar : BeanProcessor {
    override val priority: Byte = 1
    override val lifecycle: Int = 3

    override fun process(context: Context, bean: Any): Boolean {
        if (bean is Listener) {
            bean.application.listenerManager.registerListener(bean)
        }
        return super.process(context, bean)
    }

    // override fun dispose(context: Context, bean: Any) {
    //     if (bean is Listener) {
    //         bean.close()
    //     }
    //     super.dispose(context, bean)
    // }
}