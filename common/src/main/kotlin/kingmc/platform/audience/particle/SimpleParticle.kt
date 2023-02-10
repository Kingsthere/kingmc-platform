package kingmc.platform.audience.particle

/**
 * A simple implement of [Particle]
 *
 * @since 0.0.3
 * @author kingsthere
 */
data class SimpleParticle(
    override val type: ParticleType,
    override val longDistance: Boolean,
    override val x: Double,
    override val y: Double,
    override val z: Double,
    override val offsetX: Float,
    override val offsetY: Float,
    override val offsetZ: Float,
    override val maxSpeed: Float,
    override val count: Int
) : Particle {
    class SimpleParticleBuilder : Particle.Builder {
        var type: ParticleType = ParticleType.FLAME
        var longDistance: Boolean = false
        var x: Double = 0.0
        var y: Double = 0.0
        var z: Double = 0.0
        var offsetX: Float = 0F
        var offsetY: Float = 0F
        var offsetZ: Float = 0F
        var maxSpeed: Float = 0F
        var count: Int = 0

        override fun type(type: ParticleType): Particle.Builder {
            this.type = type
            return this
        }

        override fun longDistance(longDistance: Boolean): Particle.Builder {
            this.longDistance = longDistance
            return this
        }

        override fun x(x: Double): Particle.Builder {
            this.x = x
            return this
        }

        override fun y(y: Double): Particle.Builder {
            this.y = y
            return this
        }

        override fun z(z: Double): Particle.Builder {
            this.z = z
            return this
        }

        override fun offsetX(x: Float): Particle.Builder {
            this.offsetX = x
            return this
        }

        override fun offsetY(y: Float): Particle.Builder {
            this.offsetY = y
            return this
        }

        override fun offsetZ(z: Float): Particle.Builder {
            this.offsetZ = z
            return this
        }

        override fun maxSpeed(maxSpeed: Float): Particle.Builder {
            this.maxSpeed = maxSpeed
            return this
        }

        override fun count(count: Int): Particle.Builder {
            this.count = count
            return this
        }

        /**
         * Builds.
         *
         * @return the built thing
         * @since 0.0.3
         */
        override fun build(): Particle =
            SimpleParticle(type, longDistance, x, y, z, offsetX, offsetY, offsetZ, maxSpeed, count)

    }
}