package kingmc.platform

import kingmc.common.application.application
import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor
import kotlin.reflect.full.isSubclassOf

/**
 * Call the classes that is inherited from [Releasable] that annotated with [Awake]
 * when the context dispose
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Component("releasableProcessor")
object ReleasableProcessor : BeanProcessor {
    override fun dispose(context: Context, bean: Any) {
        val beanClass = bean::class
        if (beanClass.isSubclassOf(Releasable::class)) {
            bean.application {
                (bean as Releasable).release()
            }
        }
    }

    override val lifecycle: Int = 1
}