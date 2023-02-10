package kingmc.platform.audience.particle

/**
 * Represent a batch of in-game particle that could have multiple
 * of ticks with particles
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface ParticleAnimation {
    /**
     * The particles to show in this animation sort
     * by the index of the list
     *
     * @since 0.0.3
     * @author kingsthere
     */
    val particles: List<ParticleGroup>
}