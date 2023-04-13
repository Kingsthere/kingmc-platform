package kingmc.platform.schedule

import kingmc.common.application.Isolated
import kingmc.common.application.WithApplication
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.aware.ContextAware
import kingmc.common.context.beans.BeanScope
import java.io.Closeable
import kotlin.time.Duration

/**
 * A scheduler to schedule repeating tasks when [Lifecycles.ACTIVE][kingmc.platform.Lifecycles.ACTIVE]
 *
 * @since 0.0.8
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@Isolated // Cancel active tasks when dispose
interface Scheduler : Closeable, ContextAware {
    /**
     * Schedule and run a delayed [task]
     *
     * @param task the task to schedule
     * @param delay the delay to the task
     * @return `ScheduledTask` instance to the scheduled [task]
     */
    fun scheduleDelayedTask(task: @WithApplication Runnable, delay: Duration): ScheduledTask

    /**
     * Schedule a [task] and run repeatedly in specified [interval]
     *
     * @param task the task to schedule
     * @param interval the interval to repeat task scheduled
     * @return `ScheduledTask` instance to the scheduled [task]
     */
    fun scheduleRepeatedlyTask(task: @WithApplication Runnable, interval: Duration): ScheduledTask
}