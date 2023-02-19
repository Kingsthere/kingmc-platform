package kingmc.platform.audience.particle

import kingmc.platform.audience.kind.AudienceKind

/**
 * Represent an object that could receive particles
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Particle
 * @see ParticleGroup
 */
@AudienceKind
interface ParticleRecipient {
    /**
     * Send a particle to this particle recipient
     *
     * @param particle the particle to send
     * @since 0.0.3
     * @see Particle
     */
    fun particle(particle: Particle<*>)

    /**
     * Send a particle group to this particle recipient
     *
     * @param particleGroup the particle group to send
     * @since 0.0.3
     * @see ParticleGroup
     */
    fun particle(particleGroup: ParticleGroup)

    /**
     * Send a particle animation to this particle recipient
     *
     * @param particleAnimation the particle group to send
     * @since 0.0.3
     * @see ParticleAnimation
     */
    fun particle(particleAnimation: ParticleAnimation): ParticleAnimationTask

    /**
     * Send a particle animation to this particle recipient
     *
     * @param particleAnimation the particle group to send
     * @since 0.0.3
     * @see ParticleAnimation
     * @param speed the speed of this particle to show
     */
    fun particle(particleAnimation: ParticleAnimation, speed: Int = 1): AcceleratedParticleAnimationTask
}