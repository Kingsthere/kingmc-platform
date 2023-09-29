package kingmc.platform.audience.particle

import kingmc.common.application.WithApplication

/**
 * Create a particle by builder
 *
 * @return the builder created
 */
@WithApplication
fun <TData : Any> Particle(type: ParticleType<TData>, block: Particle.Builder<TData>.() -> Unit): Particle<TData> {
    return Particle.Builder(type).apply(block).build()
}