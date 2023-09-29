package kingmc.platform.schedule

import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor
import kingmc.util.annotation.getAnnotation
import kingmc.util.lifecycle.Lifecycles
import kingmc.util.reflect.findFunctionsByAnnotation
import kotlin.time.Duration.Companion.milliseconds

/**
 * Bean processor to process [Schedule] beans
 *
 * @author kingsthere
 * @since 0.1.0
 */
@Component
object ScheduleProcessor : BeanProcessor {
    override val lifecycle: Int get() = Lifecycles.ACTIVE

    override fun process(context: Context, bean: Any): Boolean {
        bean::class.findFunctionsByAnnotation<Schedule>().forEach { scheduleDefinition ->
            val scheduleAnnotation = scheduleDefinition.getAnnotation<Schedule>()!!
            withApplication(bean.application) {
                scheduleRepeatedlyTask(scheduleAnnotation.intervalInMills.milliseconds) {
                    scheduleDefinition.call()
                }
            }
        }
        return super.process(context, bean)
    }
}