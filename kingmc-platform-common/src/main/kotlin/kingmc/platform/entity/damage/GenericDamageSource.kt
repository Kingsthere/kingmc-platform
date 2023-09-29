package kingmc.platform.entity.damage

/**
 * A generic implementation of [DamageSource] represents a generic kind of injury
 *
 * @author kingsthere
 * @since 0.0.7
 * @see DamageSource
 */
open class GenericDamageSource(
    override val id: String = "generic"
) : DamageSource {
    override var damageHelmet: Boolean = false
    override var bypassArmor: Boolean = false
    override var bypassInvulnerable: Boolean = false
    override var bypassMagic: Boolean = false
    override var bypassEnchantments: Boolean = false
    override var exhaustion: Float = 0.1F
    override var isFireSource: Boolean = false
    override var isProjectile: Boolean = false
    override var scalesWithDifficulty: Boolean = false
    override var isMagic: Boolean = false
    override var isExplosion: Boolean = false
    override var isFall: Boolean = false

    override fun toString(): String {
        return "GenericInjury(id='$id')"
    }
}