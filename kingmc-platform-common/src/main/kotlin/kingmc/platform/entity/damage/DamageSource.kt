package kingmc.platform.entity.damage

/**
 * Class for describing different kind of injuries to a `Damageable` deal
 *
 * @author kingsthere
 * @since 0.0.7
 */
interface DamageSource {
    /**
     * A [String] id determine which kind of injury is this
     */
    val id: String

    /**
     * `true` if this injury will damage the target's helmet
     */
    var damageHelmet : Boolean

    /**
     * `true` if this injury will bypass the armor, directly affect
     * to the target
     */
    var bypassArmor : Boolean

    /**
     * `true` if this injury will affect to an invulnerable entity
     */
    var bypassInvulnerable : Boolean

    /**
     * TODO I don't know what this means but I implemented it
     */
    var bypassMagic : Boolean

    /**
     * `true` if this injury will bypass enchantments from target's armor, such
     * as `protection`, `blast protections`...
     */
    var bypassEnchantments : Boolean

    /**
     * The food exhaustion that this injury deals
     *
     * Every action the player takes increases it. The initial value is 0. When the hunger level reaches 4.0, it automatically
     * returns to zero and subtracts 1 point from food saturation or food level (when food saturation equals 0)
     *
     * Note that if saturation is less than 1 it will be reduced to 0 and no points will be deducted from the hunger value
     */
    var exhaustion: Float

    /**
     * `true` if this injury is come from fire
     */
    var isFireSource : Boolean

    /**
     * `true` if this injury is come from a projectile
     */
    var isProjectile : Boolean

    /**
     * If `true`, the [amount] of this injury will increase by current difficult
     */
    var scalesWithDifficulty : Boolean

    /**
     * `true` if this injury deals a magic damage
     */
    var isMagic : Boolean

    /**
     * `true` if this injury is a explosion damage
     */
    var isExplosion : Boolean

    /**
     * `true` if this injury is a falling damage
     */
    var isFall: Boolean

    companion object DamageSources {
        val IN_FIRE: DamageSource = DamageSource("inFire") {
            bypassArmor = true
            isFireSource = true
        }
        val LIGHTNING_BOLT: DamageSource = DamageSource("lightningBolt") {  }
        val ON_FIRE: DamageSource = DamageSource("onFire") {
            bypassArmor = true
            isFireSource = true
        }
        val LAVA: DamageSource = DamageSource("lava") {
            isFireSource = true
        }
        val HOT_FLOOR: DamageSource = DamageSource("hotFloor") {
            isFireSource = true
        }
        val IN_WALL: DamageSource = DamageSource("inWall") {
            bypassArmor = true
        }
        val CRAMMING: DamageSource = DamageSource("cramming") {
            bypassArmor = true

        }
        val DROWN: DamageSource = DamageSource("inFire") {
            bypassArmor = true
        }
        val STARVE: DamageSource = DamageSource("starve") {
            bypassArmor = true
        }
        val CACTUS: DamageSource = DamageSource("cactus") {

        }
        val FALL: DamageSource = DamageSource("fall") {
            bypassArmor = true
            isFall = true
        }
        val FLY_INTO_WALL: DamageSource = DamageSource("flyIntoWall") {
            bypassArmor = true
        }
        val OUT_OF_WORLD: DamageSource = DamageSource("outOfWorld") {
            bypassArmor = true
        }
        val GENERIC: DamageSource = DamageSource("generic") {  }
        val MAGIC: DamageSource = DamageSource("magic") {
            bypassArmor = true
            bypassMagic = true
        }
        val WITHER: DamageSource = DamageSource("wither") {
            bypassArmor = true
        }
        val ANVIL: DamageSource = DamageSource("anvil") {
            damageHelmet = true
        }
        val FALLING_BLOCK: DamageSource = DamageSource("fallingBlock") {
            damageHelmet = true
        }
        val DRAGON_BREATH: DamageSource = DamageSource("dragonBreath") {
            bypassArmor = true
        }
        val DRY_OUT: DamageSource = DamageSource("dryout") {  }
        val SWEET_BERRY_BUSH: DamageSource = DamageSource("sweetBerryBush") {  }
        val FREEZE: DamageSource = DamageSource("inFire") {
            bypassArmor = true
        }
        val FALLING_STALACTITE: DamageSource = DamageSource("inFire") {
            damageHelmet = true
        }
        val STALAGMITE: DamageSource = DamageSource("stalagmite") {
            bypassArmor = true
            isFall = true
        }
    }
}

/**
 * Create and adjust a [DamageSource]
 *
 * @param id the id of the damage source
 * @param block block to adjust the `DamageSource`
 */
fun DamageSource(id: String = "generic", block: DamageSource.() -> Unit): DamageSource {
    return GenericDamageSource(id).apply(block)
}