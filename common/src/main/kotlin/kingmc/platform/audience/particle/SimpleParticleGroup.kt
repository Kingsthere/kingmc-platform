package kingmc.platform.audience.particle

/**
 * A simple implement of [ParticleGroup]
 *
 * @since 0.0.3
 * @author kingsthere
 */
data class SimpleParticleGroup(
    override val particles: MutableSet<Particle>
) : ParticleGroup