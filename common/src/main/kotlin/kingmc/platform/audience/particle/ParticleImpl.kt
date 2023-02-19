package kingmc.platform.audience.particle

import kingmc.platform.Location3D

/**
 * A simple implement of [Particle]
 *
 * @since 0.0.3
 * @author kingsthere
 */
data class ParticleImpl<TData : Any>(
    override val type: ParticleType<TData>,
    override val data: TData,
    override val longDistance: Boolean,
    override val location: Location3D,
    override val offsetX: Float,
    override val offsetY: Float,
    override val offsetZ: Float,
    override val maxSpeed: Float,
    override val count: Int
) : Particle<TData> {
    override fun toBuilder(): Particle.Builder<TData> {
        return BuilderImpl(
            type = type,
            data = data,
            longDistance = longDistance,
            location = location,
            offsetX = offsetX,
            offsetY = offsetY,
            offsetZ = offsetZ,
            maxSpeed = maxSpeed,
            count = count
        )
    }

    class BuilderImpl<TData : Any>(var type: ParticleType<TData>,
                                   var data: TData? = null,
                                   var longDistance: Boolean = false,
                                   var location: Location3D = Location3D(0, 0, 0),
                                   var offsetX: Float = 0f,
                                   var offsetY: Float = 0f,
                                   var offsetZ: Float = 0f,
                                   var maxSpeed: Float = 0f,
                                   var count: Int = 1) : Particle.Builder<TData> {

        override fun build(): Particle<TData> {
            return ParticleImpl(
                type = type,
                data = data ?: type.default ?: throw IllegalArgumentException("Particle $type must have a data set"),
                longDistance = longDistance,
                location = location,
                offsetX = offsetX,
                offsetY = offsetY,
                offsetZ = offsetZ,
                maxSpeed = maxSpeed,
                count = count
            )
        }

        override fun <TNewData : Any> type(type: ParticleType<TNewData>): Particle.Builder<TNewData> {
            return BuilderImpl(type, null, longDistance, location, offsetX, offsetY, offsetZ, maxSpeed, count)
        }

        override fun longDistance(longDistance: Boolean): Particle.Builder<TData> {
            this.longDistance = longDistance
            return this
        }

        override fun location(location3D: Location3D): Particle.Builder<TData> {
            this.location = location3D
            return this
        }

        override fun offsetX(x: Float): Particle.Builder<TData> {
            this.offsetX = x
            return this
        }

        override fun offsetY(y: Float): Particle.Builder<TData> {
            this.offsetY = y
            return this
        }

        override fun offsetZ(z: Float): Particle.Builder<TData> {
            this.offsetZ = z
            return this
        }

        override fun maxSpeed(maxSpeed: Float): Particle.Builder<TData> {
            this.maxSpeed = maxSpeed
            return this
        }

        override fun count(count: Int): Particle.Builder<TData> {
            this.count = count
            return this
        }
    }
}