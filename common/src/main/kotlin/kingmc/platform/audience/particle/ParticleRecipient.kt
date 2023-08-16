package kingmc.platform.audience.particle

import kingmc.platform.Location
import kingmc.platform.audience.kind.AudienceKind

/**
 * Represent an object that could receive particles
 *
 * @since 0.1.0
 * @author kingsthere
 * @see Particle
 */
@AudienceKind
interface ParticleRecipient {
    /**
     * Send the given particle to this particle recipient
     *
     * @param particle the particle to send
     * @param x x-axis position of the particle
     * @param y y-axis position of the particle
     * @param z z-axis position of the particle
     * @param longDistance if `true`, particle distance increases from 256 to 65536
     * @param offsetX this is added to the X position after being multiplied by `random.nextGaussian()`
     * @param offsetY this is added to the Y position after being multiplied by `random.nextGaussian()`
     * @param offsetZ this is added to the Z position after being multiplied by `random.nextGaussian()`
     * @param maxSpeed maxSpeed
     * @param count the number of particles to create
     * @since 0.1.0
     */
    fun sendParticle(particle: Particle<*>, x: Double, y: Double, z: Double, longDistance: Boolean = false, offsetX: Float = 0F, offsetY: Float = 0F, offsetZ: Float = 0F, maxSpeed: Float = 0F, count: Int = 1)
}

/**
 * Send the given particle to the particle recipient
 *
 * @param particle the particle to send
 * @param location position of the particle
 * @param longDistance if `true`, particle distance increases from 256 to 65536
 * @param offsetX this is added to the X position after being multiplied by `random.nextGaussian()`
 * @param offsetY this is added to the Y position after being multiplied by `random.nextGaussian()`
 * @param offsetZ this is added to the Z position after being multiplied by `random.nextGaussian()`
 * @param maxSpeed maxSpeed
 * @param count the number of particles to create
 * @receiver the recipient to send particle to
 * @since 0.1.0
 */
fun ParticleRecipient.sendParticle(particle: Particle<*>, location: Location, longDistance: Boolean = false, offsetX: Float = 0F, offsetY: Float = 0F, offsetZ: Float = 0F, maxSpeed: Float = 0F, count: Int = 1) {
    this.sendParticle(particle, location.x, location.y, location.z, longDistance, offsetX, offsetY, offsetZ, maxSpeed, count)
}