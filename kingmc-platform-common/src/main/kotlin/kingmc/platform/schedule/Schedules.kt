package kingmc.platform.schedule

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.scheduler
import kotlin.time.Duration

/**
 * Schedule and run a delayed [task]
 *
 * @param task the task to schedule
 * @param delay the delay to the task
 * @return `ScheduledTask` instance to the scheduled [task]
 */
@WithApplication
fun scheduleDelayedTask(delay: Duration = Duration.ZERO, task: Runnable) = currentApplication().scheduler.scheduleDelayedTask(task, delay)

/**
 * Schedule a [task] and run repeatedly in specified [interval]
 *
 * @param task the task to schedule
 * @param interval the interval to repeat task scheduled
 * @return `ScheduledTask` instance to the scheduled [task]
 */
@WithApplication
fun scheduleRepeatedlyTask(interval: Duration = Duration.ZERO, task: Runnable) = currentApplication().scheduler.scheduleRepeatedlyTask(task, interval)