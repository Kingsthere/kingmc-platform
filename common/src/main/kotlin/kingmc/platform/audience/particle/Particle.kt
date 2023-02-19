package kingmc.platform.audience.particle

import kingmc.platform.Locatable3D
import kingmc.platform.Location3D
import kingmc.util.builder.Buildable

/**
 * Represent an in-game particle that could send to a [ParticleRecipient]
 * and display on players client
 *
 * @since 0.0.3
 * @author kingsthere
 * @param TData the type of extra data for this particle
 */
interface Particle<TData : Any> : Locatable3D, Buildable<Particle<TData>, Particle.Builder<TData>> {
    /**
     * The type of this particle
     *
     * @since 0.0.3
     */
    val type: ParticleType<TData>

    /**
     * The value of extra data for this particle
     */
    val data: TData

    /**
     * If true, particle visible distance increases from 256 to 65536
     *
     * @since 0.0.3
     */
    val longDistance: Boolean

    /**
     * Location of the particle
     *
     * @since 0.0.3
     */
    override val location: Location3D

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

    /**
     * A builder to build `Particle` instances
     */
    interface Builder<TData : Any> : Buildable.Builder<Particle<TData>> {
        fun <TNewData : Any> type(type: ParticleType<TNewData>): Builder<TNewData>
        fun longDistance(longDistance: Boolean): Builder<TData>
        fun location(location3D: Location3D): Builder<TData>
        fun offsetX(x: Float): Builder<TData>
        fun offsetY(y: Float): Builder<TData>
        fun offsetZ(z: Float): Builder<TData>
        fun maxSpeed(maxSpeed: Float): Builder<TData>
        fun count(count: Int): Builder<TData>
    }
}