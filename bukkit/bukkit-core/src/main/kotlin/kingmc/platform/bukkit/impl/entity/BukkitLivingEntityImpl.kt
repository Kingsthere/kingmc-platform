package kingmc.platform.bukkit.impl.entity

import kingmc.common.application.Application
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.entity._BukkitLivingEntity
import kingmc.platform.entity.LivingEntity
import kingmc.platform.entity.damage.DamageSource
import kingmc.platform.entity.damage.ModifiedDamage

/**
 * Bukkit [LivingEntity] implementation
 */
@BukkitImplementation
open class BukkitLivingEntityImpl(val _bukkitLivingEntity: _BukkitLivingEntity,
                             application: Application
) : BukkitEntityImpl(_bukkitLivingEntity, application), LivingEntity {
    /**
     * The amount of air that the living entity has remaining, (in game ticks)
     */
    override var remainingAir: Int
        get() = _bukkitLivingEntity.remainingAir
        set(value) {
            _bukkitLivingEntity.remainingAir = value
        }

    /**
     * The maximum amount of air the living entity can have, (in game ticks)
     */
    override var maximumAir: Int
        get() = _bukkitLivingEntity.maximumAir
        set(value) {
            _bukkitLivingEntity.maximumAir = value
        }

    /**
     * `true` if an entity is gliding, such as using an `Elytra`
     */
    override var isGliding: Boolean
        get() = _bukkitLivingEntity.isGliding
        set(value) {
            _bukkitLivingEntity.isGliding = value
        }

    /**
     * `true` if an entity is swimming
     */
    override var isSwimming: Boolean
        get() = _bukkitLivingEntity.isSwimming
        set(value) {
            _bukkitLivingEntity.isSwimming = value
        }

    /**
     * Deal damage to this damageable
     *
     * @param amount the amount of damage to deal
     * @param damageSource the kind of source to the damage to deal
     * @return the final damage deal to this entity
     */
    override fun damage(amount: Double, damageSource: DamageSource): ModifiedDamage {
        TODO("Not yet implemented")
    }

    /**
     * The health of current entity
     */
    override var health: Double
        get() = _bukkitLivingEntity.health
        set(value) {
            _bukkitLivingEntity.health = value
        }

    /**
     * The entity's absorption amount
     */
    override var absorptionAmount: Double
        get() = _bukkitLivingEntity.absorptionAmount
        set(value) {
            _bukkitLivingEntity.absorptionAmount = value
        }

    /**
     * The max health of this entity
     */
    @Suppress("DEPRECATION")
    @Deprecated("Use `Attribute.GENERIC_MAX_HEALTH` instead")
    override var maxHealth: Double
        get() = _bukkitLivingEntity.maxHealth
        set(value) {
            _bukkitLivingEntity.maxHealth = value
        }

    /**
     * Resets the max health to the original amount (For players is 20.0)
     */
    @Suppress("DEPRECATION")
    @Deprecated("Use `Attribute.GENERIC_MAX_HEALTH` instead")
    override fun resetMaxHealth() {
        _bukkitLivingEntity.resetMaxHealth()
    }
}