package kingmc.platform.audience.particle

import kingmc.util.key.Key
import kingmc.util.key.Keyed
import kotlin.reflect.KClass

/**
 * The types of particle that could send to
 * a particle visual
 *
 * @since 0.0.3
 * @author kingsthere
 * @param TData the type of data of this particle
 * @param typeClass the class of the type of this particle
 * @param default the default value of this particle
 */
sealed class ParticleType<TData : Any>(
    val typeClass: KClass<out TData>,
    val default: TData? = null,
    override val key: Key,
): Keyed {
    object EXPLOSION_NORMAL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "explosion_normal"))
    object EXPLOSION_LARGE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "explosion_large"))
    object EXPLOSION_HUGE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "explosion_huge"))
    object FIREWORKS_SPARK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "fireworks_spark"))
    object WATER_BUBBLE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "water_bubble"))
    object WATER_SPLASH : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "water_splash"))
    object WATER_WAKE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "water_wake"))
    object SUSPENDED : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "suspended"))
    object SUSPENDED_DEPTH : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "suspended_depth"))
    object CRIT : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "crit"))
    object CRIT_MAGIC : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "crit_magic"))
    object SMOKE_NORMAL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "smoke_normal"))
    object SMOKE_LARGE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "smoke_large"))
    object SPELL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "spell"))
    object SPELL_INSTANT : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "spell_instant"))
    object SPELL_MOB : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "spell_mob"))
    object SPELL_MOB_AMBIENT : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "spell_mob_ambient"))
    object SPELL_WITCH : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "spell_witch"))
    object DRIP_WATER : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "drip_water"))
    object DRIP_LAVA : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "drip_lava"))
    object VILLAGER_ANGRY : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "villager_angry"))
    object VILLAGER_HAPPY : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "villager_happy"))
    object TOWN_AURA : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "town_aura"))
    object NOTE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "note"))
    object PORTAL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "portal"))
    object ENCHANTMENT_TABLE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "enchantment_table"))
    object FLAME : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "flame"))
    object LAVA : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "lava"))
    object CLOUD : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "cloud"))
    object REDSTONE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "redstone"))
    object SNOWBALL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "snowball"))
    object SNOW_SHOVEL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "snow_shovel"))
    object SLIME : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "slime"))
    object HEART : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "heart"))
    object ITEM_CRACK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "item_crack"))
    object BLOCK_CRACK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "block_crack"))
    object BLOCK_DUST : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "block_dust"))
    object WATER_DROP : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "water_drop"))
    object MOB_APPEARANCE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "mob_appearance"))
    object DRAGON_BREATH : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "dragon_breath"))
    object END_ROD : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "end_rod"))
    object DAMAGE_INDICATOR : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "damage_indicator"))
    object SWEEP_ATTACK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "sweep_attack"))
    object FALLING_DUST : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_dust"))
    object TOTEM : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "totem"))
    object SPIT : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "spit"))
    object SQUID_INK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "squid_ink"))
    object BUBBLE_POP : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "bubble_pop"))
    object CURRENT_DOWN : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "current_down"))
    object BUBBLE_COLUMN_UP : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "bubble_column_up"))
    object NAUTILUS : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "nautilus"))
    object DOLPHIN : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "dolphin"))
    object SNEEZE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "sneeze"))
    object CAMPFIRE_COSY_SMOKE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "campfire_cosy_smoke"))
    object CAMPFIRE_SIGNAL_SMOKE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "campfire_signal_smoke"))
    object COMPOSTER : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "composter"))
    object FLASH : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "flash"))
    object FALLING_LAVA : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_lava"))
    object LANDING_LAVA : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "landing_lava"))
    object FALLING_WATER : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_water"))
    object DRIPPING_HONEY : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "dripping_honey"))
    object FALLING_HONEY : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_honey"))
    object LANDING_HONEY : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "landing_honey"))
    object FALLING_NECTAR : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_nectar"))
    object SOUL_FIRE_FLAME : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "soul_fire_flame"))
    object ASH : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "ash"))
    object CRIMSON_SPORE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "crimson_spore"))
    object WARPED_SPORE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "warped_spore"))
    object SOUL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "soul"))
    object DRIPPING_OBSIDIAN_TEAR : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "dripping_obsidian_tear"))
    object FALLING_OBSIDIAN_TEAR : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_obsidian_tear"))
    object LANDING_OBSIDIAN_TEAR : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "landing_obsidian_tear"))
    object REVERSE_PORTAL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "reverse_portal"))
    object WHITE_ASH : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "white_ash"))
    object DUST_COLOR_TRANSITION : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "dust_color_transition"))
    object VIBRATION : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "vibration"))
    object FALLING_SPORE_BLOSSOM : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_spore_blossom"))
    object SPORE_BLOSSOM_AIR : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "spore_blossom_air"))
    object SMALL_FLAME : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "small_flame"))
    object SNOWFLAKE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "snowflake"))
    object DRIPPING_DRIPSTONE_LAVA : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "dripping_dripstone_lava"))
    object FALLING_DRIPSTONE_LAVA : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_dripstone_lava"))
    object DRIPPING_DRIPSTONE_WATER : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "dripping_dripstone_wateR"))
    object FALLING_DRIPSTONE_WATER : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "falling_dripstone_water"))
    object GLOW_SQUID_INK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "glow_squid_ink"))
    object GLOW : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "glow"))
    object WAX_ON : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "wax_on"))
    object WAX_OFF : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "wax_off"))
    object ELECTRIC_SPARK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "electric_spark"))
    object SCRAPE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "scrape"))
    object SONIC_BOOM : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "sonic_boom"))
    object SCULK_SOUL : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "sculk_soul"))
    object SCULK_CHARGE : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "sculk_charge"))
    object SCULK_CHARGE_POP : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "sculk_charge_pop"))
    object SHRIEK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "shriek"))
    object BLOCK_MARKER : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "block_marker"))
    object LEGACY_BLOCK_CRACK : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "legacy_block_crack"))
    object LEGACY_BLOCK_DUST : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "legacy_block_dust"))
    object LEGACY_FALLING_DUST : ParticleType<Unit>(Unit::class, Unit, Key("minecraft", "legacy_falling_dust"))

    companion object {
        fun values(): Array<ParticleType<*>> {
            return arrayOf(
                EXPLOSION_NORMAL,
                EXPLOSION_LARGE,
                EXPLOSION_HUGE,
                FIREWORKS_SPARK,
                WATER_BUBBLE,
                WATER_SPLASH,
                WATER_WAKE,
                SUSPENDED,
                SUSPENDED_DEPTH,
                CRIT,
                CRIT_MAGIC,
                SMOKE_NORMAL,
                SMOKE_LARGE,
                SPELL,
                SPELL_INSTANT,
                SPELL_MOB,
                SPELL_MOB_AMBIENT,
                SPELL_WITCH,
                DRIP_WATER,
                DRIP_LAVA,
                VILLAGER_ANGRY,
                VILLAGER_HAPPY,
                TOWN_AURA,
                NOTE,
                PORTAL,
                ENCHANTMENT_TABLE,
                FLAME,
                LAVA,
                CLOUD,
                REDSTONE,
                SNOWBALL,
                SNOW_SHOVEL,
                SLIME,
                HEART,
                ITEM_CRACK,
                BLOCK_CRACK,
                BLOCK_DUST,
                WATER_DROP,
                MOB_APPEARANCE,
                DRAGON_BREATH,
                END_ROD,
                DAMAGE_INDICATOR,
                SWEEP_ATTACK,
                FALLING_DUST,
                TOTEM,
                SPIT,
                SQUID_INK,
                BUBBLE_POP,
                CURRENT_DOWN,
                BUBBLE_COLUMN_UP,
                NAUTILUS,
                DOLPHIN,
                SNEEZE,
                CAMPFIRE_COSY_SMOKE,
                CAMPFIRE_SIGNAL_SMOKE,
                COMPOSTER,
                FLASH,
                FALLING_LAVA,
                LANDING_LAVA,
                FALLING_WATER,
                DRIPPING_HONEY,
                FALLING_HONEY,
                LANDING_HONEY,
                FALLING_NECTAR,
                SOUL_FIRE_FLAME,
                ASH,
                CRIMSON_SPORE,
                WARPED_SPORE,
                SOUL,
                DRIPPING_OBSIDIAN_TEAR,
                FALLING_OBSIDIAN_TEAR,
                LANDING_OBSIDIAN_TEAR,
                REVERSE_PORTAL,
                WHITE_ASH,
                DUST_COLOR_TRANSITION,
                VIBRATION,
                FALLING_SPORE_BLOSSOM,
                SPORE_BLOSSOM_AIR,
                SMALL_FLAME,
                SNOWFLAKE,
                DRIPPING_DRIPSTONE_LAVA,
                FALLING_DRIPSTONE_LAVA,
                DRIPPING_DRIPSTONE_WATER,
                FALLING_DRIPSTONE_WATER,
                GLOW_SQUID_INK,
                GLOW,
                WAX_ON,
                WAX_OFF,
                ELECTRIC_SPARK,
                SCRAPE,
                SONIC_BOOM,
                SCULK_SOUL,
                SCULK_CHARGE,
                SCULK_CHARGE_POP,
                SHRIEK,
                BLOCK_MARKER,
                LEGACY_BLOCK_CRACK,
                LEGACY_BLOCK_DUST,
                LEGACY_FALLING_DUST
            )
        }

        fun valueOf(value: String): ParticleType<*> {
            return when (value) {
                "EXPLOSION_NORMAL" -> EXPLOSION_NORMAL
                "EXPLOSION_LARGE" -> EXPLOSION_LARGE
                "EXPLOSION_HUGE" -> EXPLOSION_HUGE
                "FIREWORKS_SPARK" -> FIREWORKS_SPARK
                "WATER_BUBBLE" -> WATER_BUBBLE
                "WATER_SPLASH" -> WATER_SPLASH
                "WATER_WAKE" -> WATER_WAKE
                "SUSPENDED" -> SUSPENDED
                "SUSPENDED_DEPTH" -> SUSPENDED_DEPTH
                "CRIT" -> CRIT
                "CRIT_MAGIC" -> CRIT_MAGIC
                "SMOKE_NORMAL" -> SMOKE_NORMAL
                "SMOKE_LARGE" -> SMOKE_LARGE
                "SPELL" -> SPELL
                "SPELL_INSTANT" -> SPELL_INSTANT
                "SPELL_MOB" -> SPELL_MOB
                "SPELL_MOB_AMBIENT" -> SPELL_MOB_AMBIENT
                "SPELL_WITCH" -> SPELL_WITCH
                "DRIP_WATER" -> DRIP_WATER
                "DRIP_LAVA" -> DRIP_LAVA
                "VILLAGER_ANGRY" -> VILLAGER_ANGRY
                "VILLAGER_HAPPY" -> VILLAGER_HAPPY
                "TOWN_AURA" -> TOWN_AURA
                "NOTE" -> NOTE
                "PORTAL" -> PORTAL
                "ENCHANTMENT_TABLE" -> ENCHANTMENT_TABLE
                "FLAME" -> FLAME
                "LAVA" -> LAVA
                "CLOUD" -> CLOUD
                "REDSTONE" -> REDSTONE
                "SNOWBALL" -> SNOWBALL
                "SNOW_SHOVEL" -> SNOW_SHOVEL
                "SLIME" -> SLIME
                "HEART" -> HEART
                "ITEM_CRACK" -> ITEM_CRACK
                "BLOCK_CRACK" -> BLOCK_CRACK
                "BLOCK_DUST" -> BLOCK_DUST
                "WATER_DROP" -> WATER_DROP
                "MOB_APPEARANCE" -> MOB_APPEARANCE
                "DRAGON_BREATH" -> DRAGON_BREATH
                "END_ROD" -> END_ROD
                "DAMAGE_INDICATOR" -> DAMAGE_INDICATOR
                "SWEEP_ATTACK" -> SWEEP_ATTACK
                "FALLING_DUST" -> FALLING_DUST
                "TOTEM" -> TOTEM
                "SPIT" -> SPIT
                "SQUID_INK" -> SQUID_INK
                "BUBBLE_POP" -> BUBBLE_POP
                "CURRENT_DOWN" -> CURRENT_DOWN
                "BUBBLE_COLUMN_UP" -> BUBBLE_COLUMN_UP
                "NAUTILUS" -> NAUTILUS
                "DOLPHIN" -> DOLPHIN
                "SNEEZE" -> SNEEZE
                "CAMPFIRE_COSY_SMOKE" -> CAMPFIRE_COSY_SMOKE
                "CAMPFIRE_SIGNAL_SMOKE" -> CAMPFIRE_SIGNAL_SMOKE
                "COMPOSTER" -> COMPOSTER
                "FLASH" -> FLASH
                "FALLING_LAVA" -> FALLING_LAVA
                "LANDING_LAVA" -> LANDING_LAVA
                "FALLING_WATER" -> FALLING_WATER
                "DRIPPING_HONEY" -> DRIPPING_HONEY
                "FALLING_HONEY" -> FALLING_HONEY
                "LANDING_HONEY" -> LANDING_HONEY
                "FALLING_NECTAR" -> FALLING_NECTAR
                "SOUL_FIRE_FLAME" -> SOUL_FIRE_FLAME
                "ASH" -> ASH
                "CRIMSON_SPORE" -> CRIMSON_SPORE
                "WARPED_SPORE" -> WARPED_SPORE
                "SOUL" -> SOUL
                "DRIPPING_OBSIDIAN_TEAR" -> DRIPPING_OBSIDIAN_TEAR
                "FALLING_OBSIDIAN_TEAR" -> FALLING_OBSIDIAN_TEAR
                "LANDING_OBSIDIAN_TEAR" -> LANDING_OBSIDIAN_TEAR
                "REVERSE_PORTAL" -> REVERSE_PORTAL
                "WHITE_ASH" -> WHITE_ASH
                "DUST_COLOR_TRANSITION" -> DUST_COLOR_TRANSITION
                "VIBRATION" -> VIBRATION
                "FALLING_SPORE_BLOSSOM" -> FALLING_SPORE_BLOSSOM
                "SPORE_BLOSSOM_AIR" -> SPORE_BLOSSOM_AIR
                "SMALL_FLAME" -> SMALL_FLAME
                "SNOWFLAKE" -> SNOWFLAKE
                "DRIPPING_DRIPSTONE_LAVA" -> DRIPPING_DRIPSTONE_LAVA
                "FALLING_DRIPSTONE_LAVA" -> FALLING_DRIPSTONE_LAVA
                "DRIPPING_DRIPSTONE_WATER" -> DRIPPING_DRIPSTONE_WATER
                "FALLING_DRIPSTONE_WATER" -> FALLING_DRIPSTONE_WATER
                "GLOW_SQUID_INK" -> GLOW_SQUID_INK
                "GLOW" -> GLOW
                "WAX_ON" -> WAX_ON
                "WAX_OFF" -> WAX_OFF
                "ELECTRIC_SPARK" -> ELECTRIC_SPARK
                "SCRAPE" -> SCRAPE
                "SONIC_BOOM" -> SONIC_BOOM
                "SCULK_SOUL" -> SCULK_SOUL
                "SCULK_CHARGE" -> SCULK_CHARGE
                "SCULK_CHARGE_POP" -> SCULK_CHARGE_POP
                "SHRIEK" -> SHRIEK
                "BLOCK_MARKER" -> BLOCK_MARKER
                "LEGACY_BLOCK_CRACK" -> LEGACY_BLOCK_CRACK
                "LEGACY_BLOCK_DUST" -> LEGACY_BLOCK_DUST
                "LEGACY_FALLING_DUST" -> LEGACY_FALLING_DUST
                else -> throw IllegalArgumentException("No object kingmc.platform.audience.particle.ParticleType.$value")
            }
        }
    }
}