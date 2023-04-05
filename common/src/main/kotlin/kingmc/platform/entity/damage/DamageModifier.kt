package kingmc.platform.entity.damage

/**
 * Indicate for modifying damages and to calculate the final damage deals
 *
 * @since 0.0.7
 * @author kingsthere
 */
interface DamageModifier {
    /**
     * Invoke this modifier and modify the [damage]
     *
     * @param damage the input damage to modify
     * @return the modified damage
     */
    operator fun invoke(damage: Double): Double
}