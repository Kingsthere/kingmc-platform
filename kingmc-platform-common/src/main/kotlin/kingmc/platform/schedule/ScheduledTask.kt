package kingmc.platform.schedule

import kingmc.common.application.Application
import kingmc.common.application.Isolated

/**
 * Represents a task scheduled by a `Scheduler`
 *
 * @author kingsthere
 * @since 0.0.8
 */
@Isolated
interface ScheduledTask {
    /**
     * The application that scheduled this task
     */
    val application: Application

    /**
     * Check if this task is active
     */
    val isActive: Boolean

    /**
     * Cancel this task
     */
    fun cancel()
}