package kingmc.platform.audience.particle

import kingmc.util.builder.Buildable

/**
 * Represent a in-game particle that could send to a [ParticleRecipient]
 * and display on players client
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Particle {
    /**
     * The type of this particle
     *
     * @since 0.0.3
     */
    val type: ParticleType

    /**
     * If true, particle visible distance increases from 256 to 65536
     *
     * @since 0.0.3
     */
    val longDistance: Boolean

    /**
     * X position of the particle
     *
     * @since 0.0.3
     */
    val x: Double

    /**
     * Y position of the particle
     *
     * @since 0.0.3
     */
    val y: Double

    /**
     * Z position of the particle
     *
     * @since 0.0.3
     */
    val z: Double

    /**
     * This is added to the X position after being multiplied by random.nextGaussian()
     *
     * @since 0.0.3
     */
    val offsetX: Float

    /**
     * This is added to the Y position after being multiplied by random.nextGaussian()
     *
     * @since 0.0.3
     */
    val offsetY: Float

    /**
     * This is added to the Z position after being multiplied by random.nextGaussian()
     *
     * @since 0.0.3
     */
    val offsetZ: Float

    /**
     * The max speed of this particle
     *
     * @since 0.0.3
     */
    val maxSpeed: Float

    /**
     * The number of particles to create.
     *
     * @since 0.0.3
     */
    val count: Int

    interface Builder : Buildable.Builder<Particle> {
        fun type(type: ParticleType): Builder
        fun longDistance(longDistance: Boolean): Builder
        fun x(x: Double): Builder
        fun y(y: Double): Builder
        fun z(z: Double): Builder
        fun offsetX(x: Float): Builder
        fun offsetY(y: Float): Builder
        fun offsetZ(z: Float): Builder
        fun maxSpeed(maxSpeed: Float): Builder
        fun count(count: Int): Builder
    }
}