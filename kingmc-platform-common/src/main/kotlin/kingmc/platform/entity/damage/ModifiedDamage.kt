package kingmc.platform.entity.damage

import com.google.errorprone.annotations.Immutable

/**
 * Data class represent a modified damage(considered to target armors, sword blocking...) to a damageable entity
 *
 * @param base the base amount of damage
 * @param modifiers the modifiers to calculate the final damage
 * @author kingsthere
 * @since 0.0.7
 */
@Immutable
data class ModifiedDamage(val base: Double, val modifiers: List<DamageModifier> = emptyList()) {
    /**
     * The final calculated damage
     */
    val finalDamage: Double
        get() {
            var damage = base
            modifiers.forEach { modifier -> damage = modifier(damage) }
            return damage
        }
}
