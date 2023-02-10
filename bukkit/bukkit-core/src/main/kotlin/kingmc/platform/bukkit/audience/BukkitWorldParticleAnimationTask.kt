package kingmc.platform.bukkit.audience

import kingmc.common.coroutine.syncMinecraftCoroutineDispatcher
import kingmc.platform.World
import kingmc.platform.audience.particle.AcceleratedParticleAnimationTask
import kingmc.platform.audience.particle.ParticleAnimation
import kotlinx.coroutines.*

/**
 * A task sending the particles to player
 *
 * @since 0.0.3
 * @author kingsthere
 */
class BukkitWorldParticleAnimationTask(private val particleAnimation: ParticleAnimation, val world: World, override val speed: Int = 0) : AcceleratedParticleAnimationTask {
    private val jobs: MutableSet<Job> = mutableSetOf()

    init {
        val scope = CoroutineScope(syncMinecraftCoroutineDispatcher)
        repeat(particleAnimation.particles.size) { index ->
            jobs.add( scope.launch {
                delay((index + 1) * speed.toLong())
                world.particle(particleAnimation.particles[index])
            } )
        }
    }

    override fun cancel(cause: CancellationException?) {
        this.jobs.forEach { job -> job.cancel(cause) }
    }
}