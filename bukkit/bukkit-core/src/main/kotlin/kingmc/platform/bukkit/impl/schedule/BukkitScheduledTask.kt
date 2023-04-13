package kingmc.platform.bukkit.impl.schedule

import kingmc.common.application.Application
import kingmc.platform.schedule.ScheduledTask
import org.bukkit.scheduler.BukkitTask

/**
 * Bukkit [ScheduledTask] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
class BukkitScheduledTask(override val application: Application, val _bukkitTask: BukkitTask, val _bukkitSchedulerImpl: BukkitSchedulerImpl) : ScheduledTask {
    override var isActive: Boolean = false
        private set

    /**
     * Cancel this task
     */
    override fun cancel() {
        _bukkitTask.cancel()
        _bukkitSchedulerImpl._scheduledTasks.remove(this)
    }
}