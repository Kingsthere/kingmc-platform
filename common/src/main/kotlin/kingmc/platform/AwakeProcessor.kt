package kingmc.platform

import com.ktil.annotation.getAnnotation
import com.ktil.reflect.findFunctionsByAnnotation
import kingmc.common.application.application
import kingmc.common.context.*
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor
import kingmc.common.logging.error
import kingmc.util.Utility
import java.util.*

/**
 * Call the functions that annotated with [Awake]
 * when reach the lifecycle stage specified
 *
 *
 * The functions to call when the lifecycle reach
 * must is an **empty parameter function**
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Utility
@Component("awakeProcessor")
object AwakeProcessor : BeanProcessor {
    private val awakingFunctions: TreeMap<Byte, MutableList<Runnable>> = TreeMap()

    override fun process(context: Context, bean: Any): Boolean {
        val beanClass = bean::class
        beanClass.findFunctionsByAnnotation<Awake>().forEach {
            val awake = it.getAnnotation<Awake>()!!
            val orderedFunctions = awakingFunctions.computeIfAbsent(awake.priority) { ArrayList() }
            orderedFunctions.add {
                try {
                    if (awake.lifecycle == 1) {
                        bean.application {
                            if (context.checkElementCondition(it)) {
                                context.callFunctionWithContext(it, bean)
                            }
                        }
                    } else {
                        bean.contextLifecycle.insertPlan(awake.lifecycle) {
                            bean.application {
                                if (context.checkElementCondition(it)) {
                                    context.callFunctionWithContext(it, bean)
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    bean.application {
                        error("An error occurred trying to invoke method $it")
                        e.printStackTrace()
                    }
                }
            }

        }
        return true
    }

    override fun end(context: Context) {
        awakingFunctions.values.forEach { orderedFunctions ->
            orderedFunctions.forEach {
                it.run()
            }
        }
        awakingFunctions.clear()
    }

    override val lifecycle: Int = 1
}