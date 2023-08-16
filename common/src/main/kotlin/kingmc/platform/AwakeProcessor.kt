package kingmc.platform

import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.context.Context
import kingmc.common.context.LifecycleContext
import kingmc.common.context.annotation.Component
import kingmc.common.context.callFunctionWithContext
import kingmc.common.context.checkElementCondition
import kingmc.common.context.process.BeanProcessor
import kingmc.common.logging.error
import kingmc.util.Utility
import kingmc.util.annotation.getAnnotation
import kingmc.util.reflect.findFunctionsByAnnotation
import kotlin.reflect.KFunction

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
@Component
object AwakeProcessor : BeanProcessor {
    private val _awakingFunctions: MutableMap<Context, MutableMap<Int, MutableList<AwakeFunction>>> = HashMap()

    override fun process(context: Context, bean: Any): Boolean {
        val beanClass = bean::class
        beanClass.findFunctionsByAnnotation<Awake>().forEach { function ->
            val awake = function.getAnnotation<Awake>()!!
            withApplication(bean.application) {
                val orderedFunctions = _awakingFunctions.computeIfAbsent(context) { HashMap() }.computeIfAbsent(awake.lifecycle) {
                    ArrayList()
                }
                orderedFunctions.add(AwakeFunction(AwakeRunnable(bean, function), awake.priority))
            }

        }
        return true
    }

    override fun afterProcess(context: Context) {
        val lifecycle = (context as LifecycleContext).getLifecycle()
        _awakingFunctions[context]?.keys?.forEach { lifecycleStage ->
            if (lifecycleStage == 1) {
                val awakingFunctions = _awakingFunctions[context]!![lifecycleStage] ?: return@forEach
                awakingFunctions.sortedByDescending { it.priority }.forEach {
                    it.runnable.run()
                }
            } else {
                lifecycle.insertPlan(lifecycleStage) {
                    val awakingFunctions = _awakingFunctions[context]!![lifecycleStage] ?: return@insertPlan
                    awakingFunctions.sortedByDescending { it.priority }.forEach {
                        it.runnable.run()
                    }
                }
            }
        }
    }

    override val priority: Byte = 2
    override val lifecycle: Int = 0

    data class AwakeFunction(val runnable: Runnable, val priority: Byte)

    class AwakeRunnable(val bean: Any, val function: KFunction<*>) : Runnable {
        override fun run() {
            bean.withApplication {
                try {
                    if (context.checkElementCondition(function)) {
                        context.callFunctionWithContext(function, bean)
                    }
                } catch (e: IllegalStateException) {
                    error("An error occurred trying to invoke @Awake method $function")
                    e.printStackTrace()
                }
            }
        }

        override fun toString(): String {
            return function.toString()
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is AwakeRunnable) return false

            if (bean != other.bean) return false
            return function == other.function
        }

        override fun hashCode(): Int {
            var result = bean.hashCode()
            result = 31 * result + function.hashCode()
            return result
        }

    }
}