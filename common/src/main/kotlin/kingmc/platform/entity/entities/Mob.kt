package kingmc.platform.entity.entities

import kingmc.platform.entity.LivingEntity
import kingmc.util.key.Key

/**
 * Represents a Mob. Mobs are living entities with simple AI
 */
interface Mob : LivingEntity {
    /**
     * Instructs this Mob to set the specified LivingEntity as its target
     *
     *
     * Hostile creatures may attack their target, and friendly creatures may
     * follow their target
     */
    var target: LivingEntity?

    /**
     * Whether this mob is aware of its surroundings
     *
     * Unaware mobs will still move if pushed, attacked, etc. but will not move
     * or perform any actions on their own. Unaware mobs may also have other
     * unspecified behaviours disabled, such as drowning
     */
    var isAware: Boolean

    /**
     * The type of ambient sound this mob plays
     */
    val ambientSound: Key?
}