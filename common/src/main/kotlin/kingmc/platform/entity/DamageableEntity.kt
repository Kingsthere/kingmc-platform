package kingmc.platform.entity

import kingmc.platform.entity.damage.DamageSource
import kingmc.platform.entity.damage.ModifiedDamage

/**
 * Represents an `Entity` that has health and can take damage
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface DamageableEntity : Entity {
    /**
     * Deal damage to this damageable
     *
     * @param amount the amount of damage to deal
     * @param damageSource the kind of source to the damage to deal
     * @return the final damage deal to this entity
     */
    fun damage(amount: Double, damageSource: DamageSource = DamageSource.GENERIC): ModifiedDamage

    /**
     * The health of current entity
     */
    var health: Double

    /**
     * The entity's absorption amount
     */
    var absorptionAmount: Double

    /**
     * The max health of this entity
     */
    @property:Deprecated("Use `Attribute.GENERIC_MAX_HEALTH` instead")
    var maxHealth: Double

    /**
     * Resets the max health to the original amount (For players is 20.0)
     */
    @Deprecated("Use `Attribute.GENERIC_MAX_HEALTH` instead")
    fun resetMaxHealth()
}