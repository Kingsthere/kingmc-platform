package kingmc.platform.bukkit.audience

import kingmc.common.coroutine.syncMinecraftCoroutineDispatcher
import kingmc.platform.audience.Player
import kingmc.platform.audience.particle.AcceleratedParticleAnimationTask
import kingmc.platform.audience.particle.ParticleAnimation
import kotlinx.coroutines.*

/**
 * A task sending the particles to player
 *
 * @since 0.0.3
 * @author kingsthere
 */
class BukkitParticleAnimationTask(private val particleAnimation: ParticleAnimation, val player: Player, override val speed: Int = 0) : AcceleratedParticleAnimationTask {
    private val jobs: Job

    init {
        val scope = CoroutineScope(syncMinecraftCoroutineDispatcher)
        jobs = scope.launch {
            repeat(particleAnimation.particles.size) { index ->
                delay(speed.toLong())
                player.particle(particleAnimation.particles[index])
            }
        }

    }

    override fun cancel(cause: CancellationException?) {
        this.jobs.cancel(cause)
    }
}