package kingmc.platform.audience.particle

import kotlinx.coroutines.CancellationException

/**
 * A task represent a [ParticleAnimation] that is showing to
 * a player
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface ParticleAnimationTask {
    /**
     * Cancel this task, so the particle animation
     * will stop
     */
    fun cancel(cause: CancellationException? = null)

    companion object {
        /**
         * Create a supervisor particle animation task from [child]
         */
        @JvmStatic
        fun supervisor(vararg child: ParticleAnimationTask, speed: Int) =
            SupervisorParticleAnimationTask(*child, speed = speed)
    }
}