package kingmc.platform.audience.particle

import kingmc.util.builder.Buildable

/**
 * Represent an in-game particle that could send to a [ParticleRecipient]
 * and display on players client
 *
 * @since 0.0.3
 * @author kingsthere
 * @param TData the type of extra data for this particle
 */
data class Particle<TData : Any>(
    /**
     * The type of this particle
     */
    val type: ParticleType<TData>,

    /**
     * The value of extra data for this particle
     */
    val data: TData
) : Buildable<Particle<TData>, Particle.Builder<TData>> {

    override fun toBuilder(): Builder<TData> = Builder(type, data)

    /**
     * A builder to build `Particle` instances
     */
    class Builder<TData : Any>(
        val type: ParticleType<TData>,
        var data: TData? = null
    ) : Buildable.Builder<Particle<TData>> {

        fun data(data: TData): Builder<TData> {
            this.data = data
            return this
        }

        override fun build(): Particle<TData> {
            require(data != null) { "Particle data can't be null" }
            return Particle(type, data!!)
        }
    }
}