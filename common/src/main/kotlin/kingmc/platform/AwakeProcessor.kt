package kingmc.platform

import kingmc.common.application.withApplication
import kingmc.common.context.*
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor
import kingmc.common.logging.error
import kingmc.util.Utility
import kingmc.util.annotation.getAnnotation
import kingmc.util.reflect.findFunctionsByAnnotation
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
    private val _awakingFunctions: MutableMap<Byte, MutableList<Runnable>> = TreeMap(compareByDescending { it })

    override fun process(context: Context, bean: Any): Boolean {
        val beanClass = bean::class
        beanClass.findFunctionsByAnnotation<Awake>().forEach {
            val awake = it.getAnnotation<Awake>()!!
            val orderedFunctions = _awakingFunctions.computeIfAbsent(awake.priority) { ArrayList() }
            orderedFunctions.add {
                try {
                    if (awake.lifecycle == 1) {
                        bean.withApplication {
                            if (context.checkElementCondition(it)) {
                                context.callFunctionWithContext(it, bean)
                            }
                        }
                    } else {
                        bean.contextLifecycle.insertPlan(awake.lifecycle) {
                            bean.withApplication {
                                if (context.checkElementCondition(it)) {
                                    context.callFunctionWithContext(it, bean)
                                }
                            }
                        }
                    }
                } catch (e: Exception) {
                    bean.withApplication {
                        error("An error occurred trying to invoke method $it")
                        e.printStackTrace()
                    }
                }
            }

        }
        return true
    }

    override fun afterProcess(context: Context) {
        _awakingFunctions.values.forEach { orderedFunctions ->
            orderedFunctions.forEach {
                it.run()
            }
        }
        _awakingFunctions.clear()
    }

    override val lifecycle: Int = 1
}