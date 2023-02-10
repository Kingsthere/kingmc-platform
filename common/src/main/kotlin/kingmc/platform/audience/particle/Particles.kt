package kingmc.platform.audience.particle

/**
 * An util for particles
 *
 * @since 0.0.5
 * @author kingsthere
 */
object Particles {
    /**
     * Create a simple particle by builder
     */
    fun particle(builderAction: Particle.Builder.() -> Unit): Particle {
        return SimpleParticle.SimpleParticleBuilder().apply(builderAction).build()
    }

    /**
     * Create a simple particle
     */
    fun particle(
        type: ParticleType,
        longDistance: Boolean = false,
        x: Double = 0.0,
        y: Double = 0.0,
        z: Double = 0.0,
        offsetX: Float = 0F,
        offsetY: Float = 0F,
        offsetZ: Float = 0F,
        maxSpeed: Float = 0F,
        count: Int = 1): Particle {
        return SimpleParticle(type, longDistance, x, y, z, offsetX, offsetY, offsetZ, maxSpeed, count)
    }
}