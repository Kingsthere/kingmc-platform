package kingmc.platform.audience.particle

/**
 * Represent a batch of in-game particle that could send to a [ParticleRecipient]
 * and display on players' client, a particle group represent a group of particle
 * that could send to a particle in a tick with a many [Particle]
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface ParticleGroup {
    /**
     * The particles in this group
     *
     * @since 0.0.3
     * @author kingsthere
     */
    val particles: MutableSet<Particle<*>>
}