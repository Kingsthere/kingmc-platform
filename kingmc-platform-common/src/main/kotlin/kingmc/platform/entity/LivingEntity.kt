package kingmc.platform.entity

/**
 * Extended [Entity] represents a living entity, such as
 *  + Players
 *  + Animals
 *  + Mobs
 *
 * @author kingsthere
 * @since 0.0.7
 */
interface LivingEntity : Entity, DamageableEntity {
    /**
     * The amount of air that the living entity has remaining, (in game ticks)
     */
    var remainingAir: Int

    /**
     * The maximum amount of air the living entity can have, (in game ticks)
     */
    var maximumAir: Int

    /**
     * `true` if an entity is gliding, such as using an `Elytra`
     */
    var isGliding: Boolean

    /**
     * `true` if an entity is swimming
     */
    var isSwimming: Boolean
}