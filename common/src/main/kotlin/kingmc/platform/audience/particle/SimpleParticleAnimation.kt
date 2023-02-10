package kingmc.platform.audience.particle

/**
 * A simple implement of [ParticleAnimation]
 *
 * @since 0.0.3
 * @author kingsthere
 */
data class SimpleParticleAnimation(
    override val particles: List<ParticleGroup>
) : ParticleAnimation