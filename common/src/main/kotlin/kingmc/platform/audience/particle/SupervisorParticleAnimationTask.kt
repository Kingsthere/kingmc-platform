package kingmc.platform.audience.particle

import kotlinx.coroutines.CancellationException

/**
 * A supervisor [ParticleAnimationTask] responsible for one or more child
 * [ParticleAnimationTask]s, this [SupervisorParticleAnimationTask] represent
 * all child tasks, when [cancel] this task all the child tasks [cancel]
 *
 * @since 0.0.3
 * @author kingsthere
 * @param child the child tasks
 */
open class SupervisorParticleAnimationTask internal constructor(private vararg val child: ParticleAnimationTask,
                                                                override val speed: Int,
) : AcceleratedParticleAnimationTask {
    /**
     * Cancel this task, so the particle animation
     * will stop
     *
     * @since 0.0.3
     */
    override fun cancel(cause: CancellationException?) {
        child.forEach { it.cancel(cause) }
    }
}