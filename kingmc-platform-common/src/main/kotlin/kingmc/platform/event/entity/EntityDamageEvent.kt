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
 * @author kingsthere
 * @since 0.0.7
 */
@Event
class EntityDamageEvent(
    entity: DamageableEntity,
    val source: DamageSource,
    var damage: ModifiedDamage
) : EntityEvent(entity), Cancellable {
    override var cancelled: Boolean = false

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EntityDamageEvent) return false

        if (source != other.source) return false
        if (damage != other.damage) return false
        return cancelled == other.cancelled
    }

    override fun hashCode(): Int {
        var result = source.hashCode()
        result = 31 * result + damage.hashCode()
        result = 31 * result + cancelled.hashCode()
        return result
    }

    override fun toString(): String {
        return "EntityDamageEvent(source=$source, damage=$damage, cancelled=$cancelled)"
    }

}

/**
 * Adds a damage modifier for the final damage of the event
 */
fun EntityDamageEvent.addDamageModifiers(vararg damageModifier: DamageModifier) {
    this.damage = this.damage.copy(modifiers = this.damage.modifiers + damageModifier)
}