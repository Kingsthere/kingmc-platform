package kingmc.platform.audience.particle

/**
 * A [ParticleAnimationTask] exposed the [speed] of playing particle animation
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface AcceleratedParticleAnimationTask : ParticleAnimationTask {
    /**
     * The speed of this accelerated [ParticleAnimationTask]
     */
    val speed: Int
}