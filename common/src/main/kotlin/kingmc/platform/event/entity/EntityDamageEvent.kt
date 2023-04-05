package kingmc.platform.event.entity

import kingmc.platform.entity.DamageableEntity
import kingmc.platform.entity.damage.DamageModifier
import kingmc.platform.entity.damage.DamageSource
import kingmc.platform.entity.damage.ModifiedDamage
import kingmc.platform.event.Cancellable
import kingmc.platform.event.Event

/**
 * Fire when an `DamageableEntity` damaged
 *
 * @param entity the entity damaged
 * @param source the source of damage
 * @param damage the final damage deals
 * @since 0.0.7
 * @author kingsthere
 */
@Event
data class EntityDamageEvent(override val entity: DamageableEntity, val source: DamageSource, var damage: ModifiedDamage) : EntityEvent(entity), Cancellable {
    override var cancelled: Boolean = false
}

/**
 * Adds a damage modifier for the final damage of the event
 */
fun EntityDamageEvent.addDamageModifiers(vararg damageModifier: DamageModifier) {
    this.damage = this.damage.copy(modifiers = this.damage.modifiers + damageModifier)
}