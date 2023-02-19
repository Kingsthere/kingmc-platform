package kingmc.platform.audience.particle

import kingmc.common.application.WithApplication
import kingmc.platform.Locatable
import kingmc.platform.Location

/**
 * Create and return a particle builder
 *
 * @return the builder created
 */
@WithApplication
fun Particle(): Particle.Builder<Unit> {
    return ParticleImpl.BuilderImpl(ParticleType.FLAME)
}

/**
 * Create and return a particle
 *
 * @return the particle created
 */
@WithApplication
fun <TData : Any> Particle(
    type: ParticleType<TData>,
    data: TData = type.default ?: throw IllegalArgumentException("Particle $type must have a data set"),
    longDistance: Boolean = false,
    x: Double = 0.0,
    y: Double = 0.0,
    z: Double = 0.0,
    offsetX: Float = 0F,
    offsetY: Float = 0F,
    offsetZ: Float = 0F,
    maxSpeed: Float = 0F,
    count: Int = 1): Particle<TData> {
    return ParticleImpl(type, data, longDistance, Location(x, y, z), offsetX, offsetY, offsetZ, maxSpeed, count)
}

/**
 * Sets this particle location to [locatable]
 */
fun <TData : Any> Particle.Builder<TData>.location(locatable: Locatable): Particle.Builder<TData> =
    this.location(locatable.location)