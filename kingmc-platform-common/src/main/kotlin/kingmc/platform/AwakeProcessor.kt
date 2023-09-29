package kingmc.platform

import gnu.trove.map.TIntObjectMap
import gnu.trove.map.hash.TIntObjectHashMap
import kingmc.common.application.withApplication
import kingmc.common.context.Context
import kingmc.common.context.LifecycleContext
import kingmc.common.context.annotation.Component
import kingmc.common.context.callFunctionWithContext
import kingmc.common.context.process.BeanProcessor
import kingmc.common.logging.error
import kingmc.util.Utility
import kingmc.util.annotation.getAnnotation
import kingmc.util.lifecycle.Execution
import kingmc.util.lifecycle.Lifecycles
import kingmc.util.reflect.findFunctionsByAnnotation
import java.util.LinkedList
import kotlin.reflect.KFunction

/**
 * Call the functions that annotated with [Awake] when reach the lifecycle stage specified
 *
 * Since 0.0.8, awake functions supports dependency injection
 *
 * @author kingsthere
 * @since 0.1.3
 */
@Utility
@Component
object AwakeProcessor : BeanProcessor {
    private val _awakingFunctions: MutableMap<Context, TIntObjectMap<MutableList<AwakeFunction>>> = HashMap()

    /**
     * Process the given bean with awake annotated, note that this only register
     * the function, the execution of awake function happened in [afterProcess]
     */
    override fun process(context: Context, bean: Any): Boolean {
        val beanClass = bean::class
        beanClass.findFunctionsByAnnotation<Awake>().forEach { function ->
            val awake = function.getAnnotation<Awake>()!!
            val orderedFunctions = _awakingFunctions.computeIfAbsent(context) {
                TIntObjectHashMap(Lifecycles.DEFAULT_LENGTH)
            }.let { awakeFunctionMap ->
                val lifecycle = awake.lifecycle
                if (awakeFunctionMap.containsKey(lifecycle)) {
                    // Get the list for awake functions in lifecycle
                    awakeFunctionMap[lifecycle]
                } else {
                    val linkedList = LinkedList<AwakeFunction>()
                    // Get the list for awake functions in lifecycle
                    awakeFunctionMap.put(lifecycle, linkedList)
                    linkedList
                }
            }
            // Add the awake function instance to list
            orderedFunctions.add(AwakeFunction(AwakeRunnable(bean, function), awake.priority))
        }
        return true
    }

    /**
     * Execute executions registered in the given context, the registration
     * of beans in context should be done in [process]
     */
    override fun afterProcess(context: Context) {
        // Get lifecycle
        val lifecycle = (context as LifecycleContext).getLifecycle()
        _awakingFunctions[context]?.keys()?.forEach { lifecycleStage ->
            // Get registered functions
            val functionsToExecute = _awakingFunctions[context]!![lifecycleStage]?.sortedByDescending { it.priority } ?: return@forEach
            val awake = {
                functionsToExecute.forEach {
                    it.runnable.run()
                }
            }
            if (lifecycleStage == 0) {
                // Execute now
                awake()
            } else {
                // Schedule execution to lifecycle
                lifecycle.scheduleExecution(lifecycleStage, Execution(0, "awake_$lifecycleStage", awake))
            }
        }
        // Release awaken functions
        _awakingFunctions.remove(context)
    }

    override val priority: Byte = 2
    override val lifecycle: Int = Lifecycles.CONST

    /**
     * Data class described an awake function
     */
    data class AwakeFunction(val runnable: Runnable, val priority: Byte) : Comparable<AwakeFunction> {
        override fun compareTo(other: AwakeFunction): Int =
            priority.compareTo(other.priority)

    }

    /**
     * A [Runnable] instance for invoking an awake function
     */
    class AwakeRunnable(val bean: Any, val function: KFunction<*>) : Runnable {
        override fun run() {
            bean.withApplication { // Switch the bean's application
                try {
                    // Call the function with context so that dependency injection can be implemented
                    context.callFunctionWithContext(function, bean)
                } catch (e: IllegalStateException) {
                    error("An error occurred trying to awake function $function", e)
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