package kingmc.platform.bukkit.impl.schedule

import kingmc.common.application.Isolated
import kingmc.common.application.WithApplication
import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.bukkit.Bukkit
import kingmc.platform.bukkit.driver.bukkitPlugin
import kingmc.platform.schedule.ScheduledTask
import kingmc.platform.schedule.Scheduler
import kingmc.util.inTicks
import kotlin.time.Duration

/**
 * Bukkit [Scheduler] implementation
 *
 * @author kingsthere
 * @since 0.0.8
 */
@Component
@Scope(BeanScope.SINGLETON)
@Isolated // Cancel active tasks when dispose
class BukkitSchedulerImpl : Scheduler {
    private val _bukkitPlugin = withApplication { bukkitPlugin }

    // Tasks scheduled by this scheduler
    internal val _scheduledTasks: MutableSet<ScheduledTask> = mutableSetOf()

    /**
     * Schedule a delayed [task]
     *
     * @param task the task to schedule
     * @param delay the delay to the task
     * @return `ScheduledTask` instance to the scheduled [task]
     */
    override fun scheduleDelayedTask(task: @WithApplication Runnable, delay: Duration): ScheduledTask {
        val bukkitTask = Bukkit.getScheduler().runTaskLater(_bukkitPlugin, Runnable {
            withApplication {
                task.run()
            }
        }, delay.inTicks)
        val scheduledTask = BukkitScheduledTask(application, bukkitTask, this)
        _scheduledTasks.add(scheduledTask)
        return scheduledTask
    }

    /**
     * Schedule a [task] and run repeatedly in specified [interval]
     *
     * @param task the task to schedule
     * @param interval the interval to repeat task scheduled
     * @return `ScheduledTask` instance to the scheduled [task]
     */
    override fun scheduleRepeatedlyTask(task: @WithApplication Runnable, interval: Duration): ScheduledTask {
        val bukkitTask = Bukkit.getScheduler().runTaskTimer(_bukkitPlugin, Runnable {
            withApplication {
                task.run()
            }
        }, 0, interval.inTicks)
        val scheduledTask = BukkitScheduledTask(application, bukkitTask, this)
        _scheduledTasks.add(scheduledTask)
        return scheduledTask
    }

    override fun close() {
        _scheduledTasks.forEach(ScheduledTask::cancel)
        _scheduledTasks.clear()
    }

}