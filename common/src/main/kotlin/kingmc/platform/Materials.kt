package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.util.Versioned
import kingmc.util.key.Key

/**
 * A package class for a [MaterialType] and it's [data]
 *
 * @param TData the type of the data of the material
 * @param type the material
 * @param data the data of the material
 * @since 0.0.6
 * @author kingsthere
 */
data class Material<TData>(val type: MaterialType<TData>, val data: TData)

/**
 * A wrapper to wrap Material
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Component
interface MaterialProvider {
    /**
     * Gets a type of `Material` by its name
     *
     * @since 0.0.1
     * @param name the name of the Material
     * @return material with name [name], or `null` if material with name [name] is not exists
     */
    @Deprecated("Name is no longer the identifier for MaterialType(s)")
    fun getTypeByName(name: String): MaterialType<*>?

    /**
     * Gets a type of `Material` by its KEY
     *
     * @since 0.0.1
     * @param key the key of the Material
     * @return material with key [key], or `null` if material with key [key] is not exists
     */
    fun getTypeByKey(key: Key): MaterialType<*>?

    /**
     * Gets all type of `Material`
     *
     * @since 0.0.6
     * @return the materials got
     */
    fun getTypes(): List<MaterialType<*>>
}

/**
 * A shortcut to get a type of `Material` with specifies data from [MaterialProvider]
 *
 * @throws IllegalArgumentException if the providing Material
 *                                  name not valid
 * @since 0.0.6
 * @author kingsthere
 * @see MaterialProvider
 * @see MaterialType
 */
@Suppress("UNCHECKED_CAST")
@Deprecated("Name is no longer the identifier for MaterialType(s)", replaceWith = ReplaceWith("MaterialType(key)"))
@WithApplication
fun <TData> MaterialType(name: String): MaterialType<TData> =
    currentApplication().materialProvider.getTypeByName(name) as MaterialType<TData>

/**
 * A shortcut to get a type of `Material` with specifies data from [MaterialProvider]
 *
 * @throws IllegalArgumentException if the providing Material
 *                                  name not valid
 * @since 0.0.6
 * @author kingsthere
 * @param key the key of the material
 * @see MaterialProvider
 * @see MaterialType
 */
@Suppress("UNCHECKED_CAST")
@WithApplication
fun <TData> MaterialType(key: Key): MaterialType<TData> =
    currentApplication().materialProvider.getTypeByKey(key) as MaterialType<TData>

/**
 * Check if this material is an `Air`
 */
fun Material<*>.isAir(): Boolean {
    return type.key == Key("minecraft:air")
}

/**
 * An utility class for materials
 *
 * @since 0.0.6
 * @author kingsthere
 */
object Materials {
    // Air
    val AIR: Material<Unit> = Material(MaterialType(Key("minecraft:air")), Unit)
    // Acacia Boat
    @Versioned(minecraftVersion = "1.9..", fallback = "OAK_BOAT")
    val ACACIA_BOAT: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_boat")), Unit)
    // Amethyst Shard
    @Versioned(minecraftVersion = "1.17..")
    val AMETHYST_SHARD: Material<Unit> = Material(MaterialType(Key("minecraft:amethyst_shard")), Unit)
    // Apple
    val APPLE: Material<Unit> = Material(MaterialType(Key("minecraft:apple")), Unit)
    // Armor Stand
    val ARMOR_STAND: Material<Unit> = Material(MaterialType(Key("minecraft:armor_stand")), Unit)
    // Arrow
    val ARROW: Material<Unit> = Material(MaterialType(Key("minecraft:arrow")), Unit)
    // Bucket of Axolotl
    @Versioned(minecraftVersion = "1.17..")
    val AXOLOTL_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:axolotl_bucket")), Unit)
    // Axolotl Spawn Egg
    @Versioned(minecraftVersion = "1.17..")
    val AXOLOTL_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:axolotl_spawn_egg")), Unit)
    // Baked Potato
    val BAKED_POTATO: Material<Unit> = Material(MaterialType(Key("minecraft:baked_potato")), Unit)
    // Bat Spawn Egg
    val BAT_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:bat_spawn_egg")), Unit)
    // Bee Spawn Egg
    @Versioned(minecraftVersion = "1.15..")
    val BEE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:bee_spawn_egg")), Unit)
    // Raw Beef
    val BEEF: Material<Unit> = Material(MaterialType(Key("minecraft:beef")), Unit)
    // Beetroot
    @Versioned(minecraftVersion = "1.9..")
    val BEETROOT: Material<Unit> = Material(MaterialType(Key("minecraft:beetroot")), Unit)
    // Beetroot Seeds
    @Versioned(minecraftVersion = "1.9..")
    val BEETROOT_SEEDS: Material<Unit> = Material(MaterialType(Key("minecraft:beetroot_seeds")), Unit)
    // Beetroot Soup
    @Versioned(minecraftVersion = "1.9..")
    val BEETROOT_SOUP: Material<Unit> = Material(MaterialType(Key("minecraft:beetroot_soup")), Unit)
    // Birch Boat
    @Versioned(minecraftVersion = "1.9..")
    val BIRCH_BOAT: Material<Unit> = Material(MaterialType(Key("minecraft:birch_boat")), Unit)
    // Black Dye
    @Versioned(minecraftVersion = "1.14..")
    val BLACK_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:black_dye")), Unit)
    // Blaze Powder
    val BLAZE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:blaze_powder")), Unit)
    // Blaze Rod
    val BLAZE_ROD: Material<Unit> = Material(MaterialType(Key("minecraft:blaze_rod")), Unit)
    // Blaze Spawn Egg
    val BLAZE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:blaze_spawn_egg")), Unit)
    // Blue Dye
    val BLUE_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:blue_dye")), Unit)
    // Bone
    val BONE: Material<Unit> = Material(MaterialType(Key("minecraft:bone")), Unit)
    // Bone Meal
    val BONE_MEAL: Material<Unit> = Material(MaterialType(Key("minecraft:bone_meal")), Unit)
    // Book
    val BOOK: Material<Unit> = Material(MaterialType(Key("minecraft:book")), Unit)
    // Bow
    val BOW: Material<Unit> = Material(MaterialType(Key("minecraft:bow")), Unit)
    // Bowl
    val BOWL: Material<Unit> = Material(MaterialType(Key("minecraft:bowl")), Unit)
    // Bread
    val BREAD: Material<Unit> = Material(MaterialType(Key("minecraft:bread")), Unit)
    // Brewing Stand
    val BREWING_STAND: Material<Unit> = Material(MaterialType(Key("minecraft:brewing_stand")), Unit)
    // Brick
    val BRICK: Material<Unit> = Material(MaterialType(Key("minecraft:brick")), Unit)
    // Brown Dye
    @Versioned(minecraftVersion = "1.14..")
    val BROWN_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:brown_dye")), Unit)
    // Bucket
    val BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:bucket")), Unit)
    // Bundle
    val BUNDLE: Material<Unit> = Material(MaterialType(Key("minecraft:bundle")), Unit)
    // Carrot
    val CARROT: Material<Unit> = Material(MaterialType(Key("minecraft:carrot")), Unit)
    // Carrot on a Stick
    val CARROT_ON_A_STICK: Material<Unit> = Material(MaterialType(Key("minecraft:carrot_on_a_stick")), Unit)
    // Cat Spawn Egg
    val CAT_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:cat_spawn_egg")), Unit)
    // Cauldron
    val CAULDRON: Material<Unit> = Material(MaterialType(Key("minecraft:cauldron")), Unit)
    // Cave Spider Spawn Egg
    val CAVE_SPIDER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:cave_spider_spawn_egg")), Unit)
    // Chainmail Boots
    val CHAINMAIL_BOOTS: Material<Unit> = Material(MaterialType(Key("minecraft:chainmail_boots")), Unit)
    // Chainmail Chestplate
    val CHAINMAIL_CHESTPLATE: Material<Unit> = Material(MaterialType(Key("minecraft:chainmail_chestplate")), Unit)
    // Chainmail Helmet
    val CHAINMAIL_HELMET: Material<Unit> = Material(MaterialType(Key("minecraft:chainmail_helmet")), Unit)
    // Chainmail Leggings
    val CHAINMAIL_LEGGINGS: Material<Unit> = Material(MaterialType(Key("minecraft:chainmail_leggings")), Unit)
    // Charcoal
    val CHARCOAL: Material<Unit> = Material(MaterialType(Key("minecraft:charcoal")), Unit)
    // Minecart with Chest
    val CHEST_MINECART: Material<Unit> = Material(MaterialType(Key("minecraft:chest_minecart")), Unit)
    // Raw Chicken
    val CHICKEN: Material<Unit> = Material(MaterialType(Key("minecraft:chicken")), Unit)
    // Chicken Spawn Egg
    val CHICKEN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:chicken_spawn_egg")), Unit)
    // Chorus Fruit
    val CHORUS_FRUIT: Material<Unit> = Material(MaterialType(Key("minecraft:chorus_fruit")), Unit)
    // Clay Ball
    val CLAY_BALL: Material<Unit> = Material(MaterialType(Key("minecraft:clay_ball")), Unit)
    // Clock
    val CLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:clock")), Unit)
    // Coal
    val COAL: Material<Unit> = Material(MaterialType(Key("minecraft:coal")), Unit)
    // Cocoa Beans
    val COCOA_BEANS: Material<Unit> = Material(MaterialType(Key("minecraft:cocoa_beans")), Unit)
    // Raw Cod
    val COD: Material<Unit> = Material(MaterialType(Key("minecraft:cod")), Unit)
    // Bucket of Cod
    val COD_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:cod_bucket")), Unit)
    // Cod Spawn Egg
    val COD_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:cod_spawn_egg")), Unit)
    // Minecart with Command Block
    val COMMAND_BLOCK_MINECART: Material<Unit> = Material(MaterialType(Key("minecraft:command_block_minecart")), Unit)
    // Compass
    val COMPASS: Material<Unit> = Material(MaterialType(Key("minecraft:compass")), Unit)
    // Steak
    val COOKED_BEEF: Material<Unit> = Material(MaterialType(Key("minecraft:cooked_beef")), Unit)
    // Cooked Chicken
    val COOKED_CHICKEN: Material<Unit> = Material(MaterialType(Key("minecraft:cooked_chicken")), Unit)
    // Cooked Cod
    val COOKED_COD: Material<Unit> = Material(MaterialType(Key("minecraft:cooked_cod")), Unit)
    // Cooked Mutton
    val COOKED_MUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:cooked_mutton")), Unit)
    // Cooked Porkchop
    val COOKED_PORKCHOP: Material<Unit> = Material(MaterialType(Key("minecraft:cooked_porkchop")), Unit)
    // Cooked Rabbit
    val COOKED_RABBIT: Material<Unit> = Material(MaterialType(Key("minecraft:cooked_rabbit")), Unit)
    // Cooked Salmon
    val COOKED_SALMON: Material<Unit> = Material(MaterialType(Key("minecraft:cooked_salmon")), Unit)
    // Cookie
    val COOKIE: Material<Unit> = Material(MaterialType(Key("minecraft:cookie")), Unit)
    // Copper Ingot
    val COPPER_INGOT: Material<Unit> = Material(MaterialType(Key("minecraft:copper_ingot")), Unit)
    // Cow Spawn Egg
    val COW_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:cow_spawn_egg")), Unit)
    // Banner Pattern
    val CREEPER_BANNER_PATTERN: Material<Unit> = Material(MaterialType(Key("minecraft:creeper_banner_pattern")), Unit)
    // Creeper Spawn Egg
    val CREEPER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:creeper_spawn_egg")), Unit)
    // Crossbow
    val CROSSBOW: Material<Unit> = Material(MaterialType(Key("minecraft:crossbow")), Unit)
    // Cyan Dye
    val CYAN_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_dye")), Unit)
    // Dark Oak Boat
    val DARK_OAK_BOAT: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_boat")), Unit)
    // Debug Stick
    val DEBUG_STICK: Material<Unit> = Material(MaterialType(Key("minecraft:debug_stick")), Unit)
    // Diamond
    val DIAMOND: Material<Unit> = Material(MaterialType(Key("minecraft:diamond")), Unit)
    // Diamond Axe
    val DIAMOND_AXE: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_axe")), Unit)
    // Diamond Boots
    val DIAMOND_BOOTS: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_boots")), Unit)
    // Diamond Chestplate
    val DIAMOND_CHESTPLATE: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_chestplate")), Unit)
    // Diamond Helmet
    val DIAMOND_HELMET: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_helmet")), Unit)
    // Diamond Hoe
    val DIAMOND_HOE: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_hoe")), Unit)
    // Diamond Horse Armor
    val DIAMOND_HORSE_ARMOR: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_horse_armor")), Unit)
    // Diamond Leggings
    val DIAMOND_LEGGINGS: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_leggings")), Unit)
    // Diamond Pickaxe
    val DIAMOND_PICKAXE: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_pickaxe")), Unit)
    // Diamond Shovel
    val DIAMOND_SHOVEL: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_shovel")), Unit)
    // Diamond Sword
    val DIAMOND_SWORD: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_sword")), Unit)
    // Dolphin Spawn Egg
    val DOLPHIN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:dolphin_spawn_egg")), Unit)
    // Donkey Spawn Egg
    val DONKEY_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:donkey_spawn_egg")), Unit)
    // Dragon's Breath
    val DRAGON_BREATH: Material<Unit> = Material(MaterialType(Key("minecraft:dragon_breath")), Unit)
    // Dried Kelp
    val DRIED_KELP: Material<Unit> = Material(MaterialType(Key("minecraft:dried_kelp")), Unit)
    // Drowned Spawn Egg
    val DROWNED_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:drowned_spawn_egg")), Unit)
    // Egg
    val EGG: Material<Unit> = Material(MaterialType(Key("minecraft:egg")), Unit)
    // Elder Guardian Spawn Egg
    val ELDER_GUARDIAN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:elder_guardian_spawn_egg")), Unit)
    // Elytra
    val ELYTRA: Material<Unit> = Material(MaterialType(Key("minecraft:elytra")), Unit)
    // Emerald
    val EMERALD: Material<Unit> = Material(MaterialType(Key("minecraft:emerald")), Unit)
    // Enchanted Book
    val ENCHANTED_BOOK: Material<Unit> = Material(MaterialType(Key("minecraft:enchanted_book")), Unit)
    // Enchanted Golden Apple
    val ENCHANTED_GOLDEN_APPLE: Material<Unit> = Material(MaterialType(Key("minecraft:enchanted_golden_apple")), Unit)
    // End Crystal
    val END_CRYSTAL: Material<Unit> = Material(MaterialType(Key("minecraft:end_crystal")), Unit)
    // Eye of Ender
    val ENDER_EYE: Material<Unit> = Material(MaterialType(Key("minecraft:ender_eye")), Unit)
    // Ender Pearl
    val ENDER_PEARL: Material<Unit> = Material(MaterialType(Key("minecraft:ender_pearl")), Unit)
    // Enderman Spawn Egg
    val ENDERMAN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:enderman_spawn_egg")), Unit)
    // Endermite Spawn Egg
    val ENDERMITE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:endermite_spawn_egg")), Unit)
    // Evoker Spawn Egg
    val EVOKER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:evoker_spawn_egg")), Unit)
    // Bottle o' Enchanting
    val EXPERIENCE_BOTTLE: Material<Unit> = Material(MaterialType(Key("minecraft:experience_bottle")), Unit)
    // Feather
    val FEATHER: Material<Unit> = Material(MaterialType(Key("minecraft:feather")), Unit)
    // Fermented Spider Eye
    val FERMENTED_SPIDER_EYE: Material<Unit> = Material(MaterialType(Key("minecraft:fermented_spider_eye")), Unit)
    // Map
    val FILLED_MAP: Material<Unit> = Material(MaterialType(Key("minecraft:filled_map")), Unit)
    // Fire Charge
    val FIRE_CHARGE: Material<Unit> = Material(MaterialType(Key("minecraft:fire_charge")), Unit)
    // Firework Rocket
    val FIREWORK_ROCKET: Material<Unit> = Material(MaterialType(Key("minecraft:firework_rocket")), Unit)
    // Firework Star
    val FIREWORK_STAR: Material<Unit> = Material(MaterialType(Key("minecraft:firework_star")), Unit)
    // Fishing Rod
    val FISHING_ROD: Material<Unit> = Material(MaterialType(Key("minecraft:fishing_rod")), Unit)
    // Flint
    val FLINT: Material<Unit> = Material(MaterialType(Key("minecraft:flint")), Unit)
    // Flint and Steel
    val FLINT_AND_STEEL: Material<Unit> = Material(MaterialType(Key("minecraft:flint_and_steel")), Unit)
    // Banner Pattern
    val FLOWER_BANNER_PATTERN: Material<Unit> = Material(MaterialType(Key("minecraft:flower_banner_pattern")), Unit)
    // Flower Pot
    val FLOWER_POT: Material<Unit> = Material(MaterialType(Key("minecraft:flower_pot")), Unit)
    // Fox Spawn Egg
    val FOX_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:fox_spawn_egg")), Unit)
    // Minecart with Furnace
    val FURNACE_MINECART: Material<Unit> = Material(MaterialType(Key("minecraft:furnace_minecart")), Unit)
    // Ghast Spawn Egg
    val GHAST_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:ghast_spawn_egg")), Unit)
    // Ghast Tear
    val GHAST_TEAR: Material<Unit> = Material(MaterialType(Key("minecraft:ghast_tear")), Unit)
    // Glass Bottle
    val GLASS_BOTTLE: Material<Unit> = Material(MaterialType(Key("minecraft:glass_bottle")), Unit)
    // Glistering Melon Slice
    val GLISTERING_MELON_SLICE: Material<Unit> = Material(MaterialType(Key("minecraft:glistering_melon_slice")), Unit)
    // Banner Pattern
    val GLOBE_BANNER_PATTERN: Material<Unit> = Material(MaterialType(Key("minecraft:globe_banner_pattern")), Unit)
    // Glow Berries
    val GLOW_BERRIES: Material<Unit> = Material(MaterialType(Key("minecraft:glow_berries")), Unit)
    // Glow Ink Sac
    val GLOW_INK_SAC: Material<Unit> = Material(MaterialType(Key("minecraft:glow_ink_sac")), Unit)
    // Glow Item Frame
    val GLOW_ITEM_FRAME: Material<Unit> = Material(MaterialType(Key("minecraft:glow_item_frame")), Unit)
    // Glow Squid Spawn Egg
    val GLOW_SQUID_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:glow_squid_spawn_egg")), Unit)
    // Glowstone Dust
    val GLOWSTONE_DUST: Material<Unit> = Material(MaterialType(Key("minecraft:glowstone_dust")), Unit)
    // Goat Spawn Egg
    val GOAT_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:goat_spawn_egg")), Unit)
    // Gold Ingot
    val GOLD_INGOT: Material<Unit> = Material(MaterialType(Key("minecraft:gold_ingot")), Unit)
    // Gold Nugget
    val GOLD_NUGGET: Material<Unit> = Material(MaterialType(Key("minecraft:gold_nugget")), Unit)
    // Golden Apple
    val GOLDEN_APPLE: Material<Unit> = Material(MaterialType(Key("minecraft:golden_apple")), Unit)
    // Golden Axe
    val GOLDEN_AXE: Material<Unit> = Material(MaterialType(Key("minecraft:golden_axe")), Unit)
    // Golden Boots
    val GOLDEN_BOOTS: Material<Unit> = Material(MaterialType(Key("minecraft:golden_boots")), Unit)
    // Golden Carrot
    val GOLDEN_CARROT: Material<Unit> = Material(MaterialType(Key("minecraft:golden_carrot")), Unit)
    // Golden Chestplate
    val GOLDEN_CHESTPLATE: Material<Unit> = Material(MaterialType(Key("minecraft:golden_chestplate")), Unit)
    // Golden Helmet
    val GOLDEN_HELMET: Material<Unit> = Material(MaterialType(Key("minecraft:golden_helmet")), Unit)
    // Golden Hoe
    val GOLDEN_HOE: Material<Unit> = Material(MaterialType(Key("minecraft:golden_hoe")), Unit)
    // Golden Horse Armor
    val GOLDEN_HORSE_ARMOR: Material<Unit> = Material(MaterialType(Key("minecraft:golden_horse_armor")), Unit)
    // Golden Leggings
    val GOLDEN_LEGGINGS: Material<Unit> = Material(MaterialType(Key("minecraft:golden_leggings")), Unit)
    // Golden Pickaxe
    val GOLDEN_PICKAXE: Material<Unit> = Material(MaterialType(Key("minecraft:golden_pickaxe")), Unit)
    // Golden Shovel
    val GOLDEN_SHOVEL: Material<Unit> = Material(MaterialType(Key("minecraft:golden_shovel")), Unit)
    // Golden Sword
    val GOLDEN_SWORD: Material<Unit> = Material(MaterialType(Key("minecraft:golden_sword")), Unit)
    // Gray Dye
    val GRAY_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:gray_dye")), Unit)
    // Green Dye
    val GREEN_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:green_dye")), Unit)
    // Guardian Spawn Egg
    val GUARDIAN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:guardian_spawn_egg")), Unit)
    // Gunpowder
    val GUNPOWDER: Material<Unit> = Material(MaterialType(Key("minecraft:gunpowder")), Unit)
    // Heart of the Sea
    val HEART_OF_THE_SEA: Material<Unit> = Material(MaterialType(Key("minecraft:heart_of_the_sea")), Unit)
    // Hoglin Spawn Egg
    val HOGLIN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:hoglin_spawn_egg")), Unit)
    // Honey Bottle
    val HONEY_BOTTLE: Material<Unit> = Material(MaterialType(Key("minecraft:honey_bottle")), Unit)
    // Honeycomb
    val HONEYCOMB: Material<Unit> = Material(MaterialType(Key("minecraft:honeycomb")), Unit)
    // Minecart with Hopper
    val HOPPER_MINECART: Material<Unit> = Material(MaterialType(Key("minecraft:hopper_minecart")), Unit)
    // Horse Spawn Egg
    val HORSE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:horse_spawn_egg")), Unit)
    // Husk Spawn Egg
    val HUSK_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:husk_spawn_egg")), Unit)
    // Ink Sac
    val INK_SAC: Material<Unit> = Material(MaterialType(Key("minecraft:ink_sac")), Unit)
    // Iron Axe
    val IRON_AXE: Material<Unit> = Material(MaterialType(Key("minecraft:iron_axe")), Unit)
    // Iron Boots
    val IRON_BOOTS: Material<Unit> = Material(MaterialType(Key("minecraft:iron_boots")), Unit)
    // Iron Chestplate
    val IRON_CHESTPLATE: Material<Unit> = Material(MaterialType(Key("minecraft:iron_chestplate")), Unit)
    // Iron Helmet
    val IRON_HELMET: Material<Unit> = Material(MaterialType(Key("minecraft:iron_helmet")), Unit)
    // Iron Hoe
    val IRON_HOE: Material<Unit> = Material(MaterialType(Key("minecraft:iron_hoe")), Unit)
    // Iron Horse Armor
    val IRON_HORSE_ARMOR: Material<Unit> = Material(MaterialType(Key("minecraft:iron_horse_armor")), Unit)
    // Iron Ingot
    val IRON_INGOT: Material<Unit> = Material(MaterialType(Key("minecraft:iron_ingot")), Unit)
    // Iron Leggings
    val IRON_LEGGINGS: Material<Unit> = Material(MaterialType(Key("minecraft:iron_leggings")), Unit)
    // Iron Nugget
    val IRON_NUGGET: Material<Unit> = Material(MaterialType(Key("minecraft:iron_nugget")), Unit)
    // Iron Pickaxe
    val IRON_PICKAXE: Material<Unit> = Material(MaterialType(Key("minecraft:iron_pickaxe")), Unit)
    // Iron Shovel
    val IRON_SHOVEL: Material<Unit> = Material(MaterialType(Key("minecraft:iron_shovel")), Unit)
    // Iron Sword
    val IRON_SWORD: Material<Unit> = Material(MaterialType(Key("minecraft:iron_sword")), Unit)
    // Item Frame
    val ITEM_FRAME: Material<Unit> = Material(MaterialType(Key("minecraft:item_frame")), Unit)
    // Jungle Boat
    val JUNGLE_BOAT: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_boat")), Unit)
    // Knowledge Book
    val KNOWLEDGE_BOOK: Material<Unit> = Material(MaterialType(Key("minecraft:knowledge_book")), Unit)
    // Lapis Lazuli
    val LAPIS_LAZULI: Material<Unit> = Material(MaterialType(Key("minecraft:lapis_lazuli")), Unit)
    // Lava Bucket
    val LAVA_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:lava_bucket")), Unit)
    // Lead
    val LEAD: Material<Unit> = Material(MaterialType(Key("minecraft:lead")), Unit)
    // Leather
    val LEATHER: Material<Unit> = Material(MaterialType(Key("minecraft:leather")), Unit)
    // Leather Boots
    val LEATHER_BOOTS: Material<Unit> = Material(MaterialType(Key("minecraft:leather_boots")), Unit)
    // Leather Tunic
    val LEATHER_CHESTPLATE: Material<Unit> = Material(MaterialType(Key("minecraft:leather_chestplate")), Unit)
    // Leather Cap
    val LEATHER_HELMET: Material<Unit> = Material(MaterialType(Key("minecraft:leather_helmet")), Unit)
    // Leather Horse Armor
    val LEATHER_HORSE_ARMOR: Material<Unit> = Material(MaterialType(Key("minecraft:leather_horse_armor")), Unit)
    // Leather Pants
    val LEATHER_LEGGINGS: Material<Unit> = Material(MaterialType(Key("minecraft:leather_leggings")), Unit)
    // Light Blue Dye
    val LIGHT_BLUE_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_dye")), Unit)
    // Light Gray Dye
    val LIGHT_GRAY_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_dye")), Unit)
    // Lime Dye
    val LIME_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:lime_dye")), Unit)
    // Lingering Potion
    val LINGERING_POTION: Material<Unit> = Material(MaterialType(Key("minecraft:lingering_potion")), Unit)
    // Llama Spawn Egg
    val LLAMA_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:llama_spawn_egg")), Unit)
    // Lodestone Compass
    val LODESTONE_COMPASS: Material<Unit> = Material(MaterialType(Key("minecraft:lodestone_compass")), Unit)
    // Magenta Dye
    val MAGENTA_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_dye")), Unit)
    // Magma Cream
    val MAGMA_CREAM: Material<Unit> = Material(MaterialType(Key("minecraft:magma_cream")), Unit)
    // Magma Cube Spawn Egg
    val MAGMA_CUBE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:magma_cube_spawn_egg")), Unit)
    // Empty Map
    val MAP: Material<Unit> = Material(MaterialType(Key("minecraft:map")), Unit)
    // Melon Seeds
    val MELON_SEEDS: Material<Unit> = Material(MaterialType(Key("minecraft:melon_seeds")), Unit)
    // Melon Slice
    val MELON_SLICE: Material<Unit> = Material(MaterialType(Key("minecraft:melon_slice")), Unit)
    // Milk Bucket
    val MILK_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:milk_bucket")), Unit)
    // Minecart
    val MINECART: Material<Unit> = Material(MaterialType(Key("minecraft:minecart")), Unit)
    // Banner Pattern
    val MOJANG_BANNER_PATTERN: Material<Unit> = Material(MaterialType(Key("minecraft:mojang_banner_pattern")), Unit)
    // Mooshroom Spawn Egg
    val MOOSHROOM_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:mooshroom_spawn_egg")), Unit)
    // Mule Spawn Egg
    val MULE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:mule_spawn_egg")), Unit)
    // Mushroom Stew
    val MUSHROOM_STEW: Material<Unit> = Material(MaterialType(Key("minecraft:mushroom_stew")), Unit)
    // Music Disc
    val MUSIC_DISC_BLOCKS: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_blocks")), Unit)
    // Music Disc
    val MUSIC_DISC_CAT: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_cat")), Unit)
    // Music Disc
    val MUSIC_DISC_CHIRP: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_chirp")), Unit)
    // Music Disc
    val MUSIC_DISC_FAR: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_far")), Unit)
    // Music Disc
    val MUSIC_DISC_MALL: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_mall")), Unit)
    // Music Disc
    val MUSIC_DISC_MELLOHI: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_mellohi")), Unit)
    // Music Disc
    val MUSIC_DISC_OTHERSIDE: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_otherside")), Unit)
    // Music Disc
    val MUSIC_DISC_PIGSTEP: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_pigstep")), Unit)
    // Music Disc
    val MUSIC_DISC_STAL: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_stal")), Unit)
    // Music Disc
    val MUSIC_DISC_STRAD: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_strad")), Unit)
    // Music Disc
    val MUSIC_DISC_WAIT: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_wait")), Unit)
    // Music Disc
    val MUSIC_DISC_WARD: Material<Unit> = Material(MaterialType(Key("minecraft:music_disc_ward")), Unit)
    // Raw Mutton
    val MUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:mutton")), Unit)
    // Name Tag
    val NAME_TAG: Material<Unit> = Material(MaterialType(Key("minecraft:name_tag")), Unit)
    // Nautilus Shell
    val NAUTILUS_SHELL: Material<Unit> = Material(MaterialType(Key("minecraft:nautilus_shell")), Unit)
    // Nether Brick
    val NETHER_BRICK: Material<Unit> = Material(MaterialType(Key("minecraft:nether_brick")), Unit)
    // Nether Star
    val NETHER_STAR: Material<Unit> = Material(MaterialType(Key("minecraft:nether_star")), Unit)
    // Nether Wart
    val NETHER_WART: Material<Unit> = Material(MaterialType(Key("minecraft:nether_wart")), Unit)
    // Netherite Axe
    val NETHERITE_AXE: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_axe")), Unit)
    // Netherite Boots
    val NETHERITE_BOOTS: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_boots")), Unit)
    // Netherite Chestplate
    val NETHERITE_CHESTPLATE: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_chestplate")), Unit)
    // Netherite Helmet
    val NETHERITE_HELMET: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_helmet")), Unit)
    // Netherite Hoe
    val NETHERITE_HOE: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_hoe")), Unit)
    // Netherite Ingot
    val NETHERITE_INGOT: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_ingot")), Unit)
    // Netherite Leggings
    val NETHERITE_LEGGINGS: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_leggings")), Unit)
    // Netherite Pickaxe
    val NETHERITE_PICKAXE: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_pickaxe")), Unit)
    // Netherite Scrap
    val NETHERITE_SCRAP: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_scrap")), Unit)
    // Netherite Shovel
    val NETHERITE_SHOVEL: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_shovel")), Unit)
    // Netherite Sword
    val NETHERITE_SWORD: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_sword")), Unit)
    // Oak Boat
    val OAK_BOAT: Material<Unit> = Material(MaterialType(Key("minecraft:oak_boat")), Unit)
    // Ocelot Spawn Egg
    val OCELOT_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:ocelot_spawn_egg")), Unit)
    // Orange Dye
    val ORANGE_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:orange_dye")), Unit)
    // Painting
    val PAINTING: Material<Unit> = Material(MaterialType(Key("minecraft:painting")), Unit)
    // Panda Spawn Egg
    val PANDA_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:panda_spawn_egg")), Unit)
    // Paper
    val PAPER: Material<Unit> = Material(MaterialType(Key("minecraft:paper")), Unit)
    // Parrot Spawn Egg
    val PARROT_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:parrot_spawn_egg")), Unit)
    // Phantom Membrane
    val PHANTOM_MEMBRANE: Material<Unit> = Material(MaterialType(Key("minecraft:phantom_membrane")), Unit)
    // Phantom Spawn Egg
    val PHANTOM_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:phantom_spawn_egg")), Unit)
    // Pig Spawn Egg
    val PIG_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:pig_spawn_egg")), Unit)
    // Banner Pattern
    val PIGLIN_BANNER_PATTERN: Material<Unit> = Material(MaterialType(Key("minecraft:piglin_banner_pattern")), Unit)
    // Piglin Brute Spawn Egg
    val PIGLIN_BRUTE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:piglin_brute_spawn_egg")), Unit)
    // Piglin Spawn Egg
    val PIGLIN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:piglin_spawn_egg")), Unit)
    // Pillager Spawn Egg
    val PILLAGER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:pillager_spawn_egg")), Unit)
    // Pink Dye
    val PINK_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:pink_dye")), Unit)
    // Poisonous Potato
    val POISONOUS_POTATO: Material<Unit> = Material(MaterialType(Key("minecraft:poisonous_potato")), Unit)
    // Polar Bear Spawn Egg
    val POLAR_BEAR_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:polar_bear_spawn_egg")), Unit)
    // Popped Chorus Fruit
    val POPPED_CHORUS_FRUIT: Material<Unit> = Material(MaterialType(Key("minecraft:popped_chorus_fruit")), Unit)
    // Raw Porkchop
    val PORKCHOP: Material<Unit> = Material(MaterialType(Key("minecraft:porkchop")), Unit)
    // Potato
    val POTATO: Material<Unit> = Material(MaterialType(Key("minecraft:potato")), Unit)
    // Potion
    val POTION: Material<Unit> = Material(MaterialType(Key("minecraft:potion")), Unit)
    // Powder Snow Bucket
    val POWDER_SNOW_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:powder_snow_bucket")), Unit)
    // Prismarine Crystals
    val PRISMARINE_CRYSTALS: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_crystals")), Unit)
    // Prismarine Shard
    val PRISMARINE_SHARD: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_shard")), Unit)
    // Pufferfish
    val PUFFERFISH: Material<Unit> = Material(MaterialType(Key("minecraft:pufferfish")), Unit)
    // Bucket of Pufferfish
    val PUFFERFISH_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:pufferfish_bucket")), Unit)
    // Pufferfish Spawn Egg
    val PUFFERFISH_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:pufferfish_spawn_egg")), Unit)
    // Pumpkin Pie
    val PUMPKIN_PIE: Material<Unit> = Material(MaterialType(Key("minecraft:pumpkin_pie")), Unit)
    // Pumpkin Seeds
    val PUMPKIN_SEEDS: Material<Unit> = Material(MaterialType(Key("minecraft:pumpkin_seeds")), Unit)
    // Purple Dye
    val PURPLE_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:purple_dye")), Unit)
    // Nether Quartz
    val QUARTZ: Material<Unit> = Material(MaterialType(Key("minecraft:quartz")), Unit)
    // Raw Rabbit
    val RABBIT: Material<Unit> = Material(MaterialType(Key("minecraft:rabbit")), Unit)
    // Rabbit's Foot
    val RABBIT_FOOT: Material<Unit> = Material(MaterialType(Key("minecraft:rabbit_foot")), Unit)
    // Rabbit Hide
    val RABBIT_HIDE: Material<Unit> = Material(MaterialType(Key("minecraft:rabbit_hide")), Unit)
    // Rabbit Spawn Egg
    val RABBIT_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:rabbit_spawn_egg")), Unit)
    // Rabbit Stew
    val RABBIT_STEW: Material<Unit> = Material(MaterialType(Key("minecraft:rabbit_stew")), Unit)
    // Ravager Spawn Egg
    val RAVAGER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:ravager_spawn_egg")), Unit)
    // Raw Copper
    val RAW_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:raw_copper")), Unit)
    // Raw Gold
    val RAW_GOLD: Material<Unit> = Material(MaterialType(Key("minecraft:raw_gold")), Unit)
    // Raw Iron
    val RAW_IRON: Material<Unit> = Material(MaterialType(Key("minecraft:raw_iron")), Unit)
    // Red Dye
    val RED_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:red_dye")), Unit)
    // Redstone Dust
    val REDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:redstone")), Unit)
    // Rotten Flesh
    val ROTTEN_FLESH: Material<Unit> = Material(MaterialType(Key("minecraft:rotten_flesh")), Unit)
    // Saddle
    val SADDLE: Material<Unit> = Material(MaterialType(Key("minecraft:saddle")), Unit)
    // Raw Salmon
    val SALMON: Material<Unit> = Material(MaterialType(Key("minecraft:salmon")), Unit)
    // Bucket of Salmon
    val SALMON_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:salmon_bucket")), Unit)
    // Salmon Spawn Egg
    val SALMON_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:salmon_spawn_egg")), Unit)
    // Scute
    val SCUTE: Material<Unit> = Material(MaterialType(Key("minecraft:scute")), Unit)
    // Shears
    val SHEARS: Material<Unit> = Material(MaterialType(Key("minecraft:shears")), Unit)
    // Sheep Spawn Egg
    val SHEEP_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:sheep_spawn_egg")), Unit)
    // Shield
    val SHIELD: Material<Unit> = Material(MaterialType(Key("minecraft:shield")), Unit)
    // Shulker Shell
    val SHULKER_SHELL: Material<Unit> = Material(MaterialType(Key("minecraft:shulker_shell")), Unit)
    // Shulker Spawn Egg
    val SHULKER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:shulker_spawn_egg")), Unit)
    // Sign
    val SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:sign")), Unit)
    // Silverfish Spawn Egg
    val SILVERFISH_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:silverfish_spawn_egg")), Unit)
    // Skeleton Horse Spawn Egg
    val SKELETON_HORSE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:skeleton_horse_spawn_egg")), Unit)
    // Skeleton Spawn Egg
    val SKELETON_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:skeleton_spawn_egg")), Unit)
    // Banner Pattern
    val SKULL_BANNER_PATTERN: Material<Unit> = Material(MaterialType(Key("minecraft:skull_banner_pattern")), Unit)
    // Slimeball
    val SLIME_BALL: Material<Unit> = Material(MaterialType(Key("minecraft:slime_ball")), Unit)
    // Slime Spawn Egg
    val SLIME_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:slime_spawn_egg")), Unit)
    // Snowball
    val SNOWBALL: Material<Unit> = Material(MaterialType(Key("minecraft:snowball")), Unit)
    // Spectral Arrow
    val SPECTRAL_ARROW: Material<Unit> = Material(MaterialType(Key("minecraft:spectral_arrow")), Unit)
    // Spider Eye
    val SPIDER_EYE: Material<Unit> = Material(MaterialType(Key("minecraft:spider_eye")), Unit)
    // Spider Spawn Egg
    val SPIDER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:spider_spawn_egg")), Unit)
    // Splash Potion
    val SPLASH_POTION: Material<Unit> = Material(MaterialType(Key("minecraft:splash_potion")), Unit)
    // Spruce Boat
    val SPRUCE_BOAT: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_boat")), Unit)
    // Spyglass
    val SPYGLASS: Material<Unit> = Material(MaterialType(Key("minecraft:spyglass")), Unit)
    // Squid Spawn Egg
    val SQUID_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:squid_spawn_egg")), Unit)
    // Stick
    val STICK: Material<Unit> = Material(MaterialType(Key("minecraft:stick")), Unit)
    // Stone Axe
    val STONE_AXE: Material<Unit> = Material(MaterialType(Key("minecraft:stone_axe")), Unit)
    // Stone Hoe
    val STONE_HOE: Material<Unit> = Material(MaterialType(Key("minecraft:stone_hoe")), Unit)
    // Stone Pickaxe
    val STONE_PICKAXE: Material<Unit> = Material(MaterialType(Key("minecraft:stone_pickaxe")), Unit)
    // Stone Shovel
    val STONE_SHOVEL: Material<Unit> = Material(MaterialType(Key("minecraft:stone_shovel")), Unit)
    // Stone Sword
    val STONE_SWORD: Material<Unit> = Material(MaterialType(Key("minecraft:stone_sword")), Unit)
    // Stray Spawn Egg
    val STRAY_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:stray_spawn_egg")), Unit)
    // Strider Spawn Egg
    val STRIDER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:strider_spawn_egg")), Unit)
    // String
    val STRING: Material<Unit> = Material(MaterialType(Key("minecraft:string")), Unit)
    // Sugar
    val SUGAR: Material<Unit> = Material(MaterialType(Key("minecraft:sugar")), Unit)
    // Suspicious Stew
    val SUSPICIOUS_STEW: Material<Unit> = Material(MaterialType(Key("minecraft:suspicious_stew")), Unit)
    // Sweet Berries
    val SWEET_BERRIES: Material<Unit> = Material(MaterialType(Key("minecraft:sweet_berries")), Unit)
    // Tipped Arrow
    val TIPPED_ARROW: Material<Unit> = Material(MaterialType(Key("minecraft:tipped_arrow")), Unit)
    // Minecart with TNT
    val TNT_MINECART: Material<Unit> = Material(MaterialType(Key("minecraft:tnt_minecart")), Unit)
    // Totem of Undying
    val TOTEM_OF_UNDYING: Material<Unit> = Material(MaterialType(Key("minecraft:totem_of_undying")), Unit)
    // Trader Llama Spawn Egg
    val TRADER_LLAMA_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:trader_llama_spawn_egg")), Unit)
    // Trident
    val TRIDENT: Material<Unit> = Material(MaterialType(Key("minecraft:trident")), Unit)
    // Tropical Fish
    val TROPICAL_FISH: Material<Unit> = Material(MaterialType(Key("minecraft:tropical_fish")), Unit)
    // Bucket of Tropical Fish
    val TROPICAL_FISH_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:tropical_fish_bucket")), Unit)
    // Tropical Fish Spawn Egg
    val TROPICAL_FISH_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:tropical_fish_spawn_egg")), Unit)
    // Turtle Shell
    val TURTLE_HELMET: Material<Unit> = Material(MaterialType(Key("minecraft:turtle_helmet")), Unit)
    // Turtle Spawn Egg
    val TURTLE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:turtle_spawn_egg")), Unit)
    // Vex Spawn Egg
    @Versioned(minecraftVersion = "1.11..")
    val VEX_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:vex_spawn_egg")), Unit)
    // Villager Spawn Egg
    val VILLAGER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:villager_spawn_egg")), Unit)
    // Vindicator Spawn Egg
    @Versioned(minecraftVersion = "1.11..")
    val VINDICATOR_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:vindicator_spawn_egg")), Unit)
    // Wandering Trader Spawn Egg
    @Versioned(minecraftVersion = "1.14..")
    val WANDERING_TRADER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:wandering_trader_spawn_egg")), Unit)
    // Warped Fungus on a Stick
    @Versioned(minecraftVersion = "1.16..")
    val WARPED_FUNGUS_ON_A_STICK: Material<Unit> = Material(MaterialType(Key("minecraft:warped_fungus_on_a_stick")), Unit)
    // Water Bucket
    val WATER_BUCKET: Material<Unit> = Material(MaterialType(Key("minecraft:water_bucket")), Unit)
    // Wheat
    val WHEAT: Material<Unit> = Material(MaterialType(Key("minecraft:wheat")), Unit)
    // Wheat Seeds
    val WHEAT_SEEDS: Material<Unit> = Material(MaterialType(Key("minecraft:wheat_seeds")), Unit)
    // White Dye
    val WHITE_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:white_dye")), Unit)
    // Witch Spawn Egg
    val WITCH_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:witch_spawn_egg")), Unit)
    // Wither Skeleton Spawn Egg
    val WITHER_SKELETON_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:wither_skeleton_spawn_egg")), Unit)
    // Wolf Spawn Egg
    val WOLF_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:wolf_spawn_egg")), Unit)
    // Wooden Axe
    val WOODEN_AXE: Material<Unit> = Material(MaterialType(Key("minecraft:wooden_axe")), Unit)
    // Wooden Hoe
    val WOODEN_HOE: Material<Unit> = Material(MaterialType(Key("minecraft:wooden_hoe")), Unit)
    // Wooden Pickaxe
    val WOODEN_PICKAXE: Material<Unit> = Material(MaterialType(Key("minecraft:wooden_pickaxe")), Unit)
    // Wooden Shovel
    val WOODEN_SHOVEL: Material<Unit> = Material(MaterialType(Key("minecraft:wooden_shovel")), Unit)
    // Wooden Sword
    val WOODEN_SWORD: Material<Unit> = Material(MaterialType(Key("minecraft:wooden_sword")), Unit)
    // Book and Quill
    val WRITABLE_BOOK: Material<Unit> = Material(MaterialType(Key("minecraft:writable_book")), Unit)
    // Written Book
    val WRITTEN_BOOK: Material<Unit> = Material(MaterialType(Key("minecraft:written_book")), Unit)
    // Yellow Dye
    val YELLOW_DYE: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_dye")), Unit)
    // Zoglin Spawn Egg
    val ZOGLIN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:zoglin_spawn_egg")), Unit)
    // Zombie Horse Spawn Egg
    val ZOMBIE_HORSE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:zombie_horse_spawn_egg")), Unit)
    // Zombie Spawn Egg
    val ZOMBIE_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:zombie_spawn_egg")), Unit)
    // Zombie Villager Spawn Egg
    val ZOMBIE_VILLAGER_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:zombie_villager_spawn_egg")), Unit)
    // Zombified Piglin Spawn Egg
    val ZOMBIFIED_PIGLIN_SPAWN_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:zombified_piglin_spawn_egg")), Unit)

    // Block materials:
    val STONE: Material<Unit> = Material(MaterialType(Key("minecraft:stone")), Unit)
    val GRANITE: Material<Unit> = Material(MaterialType(Key("minecraft:granite")), Unit)
    val POLISHED_GRANITE: Material<Unit> = Material(MaterialType(Key("minecraft:polished_granite")), Unit)
    val DIORITE: Material<Unit> = Material(MaterialType(Key("minecraft:diorite")), Unit)
    val POLISHED_DIORITE: Material<Unit> = Material(MaterialType(Key("minecraft:polished_diorite")), Unit)
    val ANDESITE: Material<Unit> = Material(MaterialType(Key("minecraft:andesite")), Unit)
    val POLISHED_ANDESITE: Material<Unit> = Material(MaterialType(Key("minecraft:polished_andesite")), Unit)
    val GRASS_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:grass_block")), Unit)
    val DIRT: Material<Unit> = Material(MaterialType(Key("minecraft:dirt")), Unit)
    val COARSE_DIRT: Material<Unit> = Material(MaterialType(Key("minecraft:coarse_dirt")), Unit)
    val PODZOL: Material<Unit> = Material(MaterialType(Key("minecraft:podzol")), Unit)
    val COBBLESTONE: Material<Unit> = Material(MaterialType(Key("minecraft:cobblestone")), Unit)
    val OAK_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:oak_planks")), Unit)
    val SPRUCE_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_planks")), Unit)
    val BIRCH_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:birch_planks")), Unit)
    val JUNGLE_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_planks")), Unit)
    val ACACIA_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_planks")), Unit)
    val DARK_OAK_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_planks")), Unit)
    val OAK_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:oak_sapling")), Unit)
    val SPRUCE_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_sapling")), Unit)
    val BIRCH_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:birch_sapling")), Unit)
    val JUNGLE_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_sapling")), Unit)
    val ACACIA_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_sapling")), Unit)
    val DARK_OAK_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_sapling")), Unit)
    val BEDROCK: Material<Unit> = Material(MaterialType(Key("minecraft:bedrock")), Unit)
    val WATER: Material<Unit> = Material(MaterialType(Key("minecraft:water")), Unit)
    val LAVA: Material<Unit> = Material(MaterialType(Key("minecraft:lava")), Unit)
    val SAND: Material<Unit> = Material(MaterialType(Key("minecraft:sand")), Unit)
    val RED_SAND: Material<Unit> = Material(MaterialType(Key("minecraft:red_sand")), Unit)
    val GRAVEL: Material<Unit> = Material(MaterialType(Key("minecraft:gravel")), Unit)
    val GOLD_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:gold_ore")), Unit)
    val DEEPSLATE_GOLD_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_gold_ore")), Unit)
    val IRON_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:iron_ore")), Unit)
    val DEEPSLATE_IRON_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_iron_ore")), Unit)
    val COAL_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:coal_ore")), Unit)
    val DEEPSLATE_COAL_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_coal_ore")), Unit)
    val NETHER_GOLD_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:nether_gold_ore")), Unit)
    val OAK_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:oak_log")), Unit)
    val SPRUCE_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_log")), Unit)
    val BIRCH_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:birch_log")), Unit)
    val JUNGLE_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_log")), Unit)
    val ACACIA_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_log")), Unit)
    val DARK_OAK_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_log")), Unit)
    val STRIPPED_SPRUCE_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_spruce_log")), Unit)
    val STRIPPED_BIRCH_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_birch_log")), Unit)
    val STRIPPED_JUNGLE_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_jungle_log")), Unit)
    val STRIPPED_ACACIA_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_acacia_log")), Unit)
    val STRIPPED_DARK_OAK_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_dark_oak_log")), Unit)
    val STRIPPED_OAK_LOG: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_oak_log")), Unit)
    val OAK_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:oak_wood")), Unit)
    val SPRUCE_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_wood")), Unit)
    val BIRCH_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:birch_wood")), Unit)
    val JUNGLE_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_wood")), Unit)
    val ACACIA_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_wood")), Unit)
    val DARK_OAK_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_wood")), Unit)
    val STRIPPED_OAK_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_oak_wood")), Unit)
    val STRIPPED_SPRUCE_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_spruce_wood")), Unit)
    val STRIPPED_BIRCH_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_birch_wood")), Unit)
    val STRIPPED_JUNGLE_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_jungle_wood")), Unit)
    val STRIPPED_ACACIA_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_acacia_wood")), Unit)
    val STRIPPED_DARK_OAK_WOOD: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_dark_oak_wood")), Unit)
    val OAK_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:oak_leaves")), Unit)
    val SPRUCE_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_leaves")), Unit)
    val BIRCH_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:birch_leaves")), Unit)
    val JUNGLE_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_leaves")), Unit)
    val ACACIA_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_leaves")), Unit)
    val DARK_OAK_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_leaves")), Unit)
    val AZALEA_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:azalea_leaves")), Unit)
    val FLOWERING_AZALEA_LEAVES: Material<Unit> = Material(MaterialType(Key("minecraft:flowering_azalea_leaves")), Unit)
    val SPONGE: Material<Unit> = Material(MaterialType(Key("minecraft:sponge")), Unit)
    val WET_SPONGE: Material<Unit> = Material(MaterialType(Key("minecraft:wet_sponge")), Unit)
    val GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:glass")), Unit)
    val LAPIS_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:lapis_ore")), Unit)
    val DEEPSLATE_LAPIS_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_lapis_ore")), Unit)
    val LAPIS_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:lapis_block")), Unit)
    val DISPENSER: Material<Unit> = Material(MaterialType(Key("minecraft:dispenser")), Unit)
    val SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:sandstone")), Unit)
    val CHISELED_SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:chiseled_sandstone")), Unit)
    val CUT_SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:cut_sandstone")), Unit)
    val NOTE_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:note_block")), Unit)
    val WHITE_BED: Material<Unit> = Material(MaterialType(Key("minecraft:white_bed")), Unit)
    val ORANGE_BED: Material<Unit> = Material(MaterialType(Key("minecraft:orange_bed")), Unit)
    val MAGENTA_BED: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_bed")), Unit)
    val LIGHT_BLUE_BED: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_bed")), Unit)
    val YELLOW_BED: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_bed")), Unit)
    val LIME_BED: Material<Unit> = Material(MaterialType(Key("minecraft:lime_bed")), Unit)
    val PINK_BED: Material<Unit> = Material(MaterialType(Key("minecraft:pink_bed")), Unit)
    val GRAY_BED: Material<Unit> = Material(MaterialType(Key("minecraft:gray_bed")), Unit)
    val LIGHT_GRAY_BED: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_bed")), Unit)
    val CYAN_BED: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_bed")), Unit)
    val PURPLE_BED: Material<Unit> = Material(MaterialType(Key("minecraft:purple_bed")), Unit)
    val BLUE_BED: Material<Unit> = Material(MaterialType(Key("minecraft:blue_bed")), Unit)
    val BROWN_BED: Material<Unit> = Material(MaterialType(Key("minecraft:brown_bed")), Unit)
    val GREEN_BED: Material<Unit> = Material(MaterialType(Key("minecraft:green_bed")), Unit)
    val RED_BED: Material<Unit> = Material(MaterialType(Key("minecraft:red_bed")), Unit)
    val BLACK_BED: Material<Unit> = Material(MaterialType(Key("minecraft:black_bed")), Unit)
    val POWERED_RAIL: Material<Unit> = Material(MaterialType(Key("minecraft:powered_rail")), Unit)
    val DETECTOR_RAIL: Material<Unit> = Material(MaterialType(Key("minecraft:detector_rail")), Unit)
    val STICKY_PISTON: Material<Unit> = Material(MaterialType(Key("minecraft:sticky_piston")), Unit)
    val COBWEB: Material<Unit> = Material(MaterialType(Key("minecraft:cobweb")), Unit)
    val GRASS: Material<Unit> = Material(MaterialType(Key("minecraft:grass")), Unit)
    val FERN: Material<Unit> = Material(MaterialType(Key("minecraft:fern")), Unit)
    val DEAD_BUSH: Material<Unit> = Material(MaterialType(Key("minecraft:dead_bush")), Unit)
    val SEAGRASS: Material<Unit> = Material(MaterialType(Key("minecraft:seagrass")), Unit)
    val TALL_SEAGRASS: Material<Unit> = Material(MaterialType(Key("minecraft:tall_seagrass")), Unit)
    val PISTON: Material<Unit> = Material(MaterialType(Key("minecraft:piston")), Unit)
    val PISTON_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:piston_head")), Unit)
    val WHITE_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:white_wool")), Unit)
    val ORANGE_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:orange_wool")), Unit)
    val MAGENTA_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_wool")), Unit)
    val LIGHT_BLUE_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_wool")), Unit)
    val YELLOW_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_wool")), Unit)
    val LIME_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:lime_wool")), Unit)
    val PINK_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:pink_wool")), Unit)
    val GRAY_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:gray_wool")), Unit)
    val LIGHT_GRAY_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_wool")), Unit)
    val CYAN_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_wool")), Unit)
    val PURPLE_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:purple_wool")), Unit)
    val BLUE_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:blue_wool")), Unit)
    val BROWN_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:brown_wool")), Unit)
    val GREEN_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:green_wool")), Unit)
    val RED_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:red_wool")), Unit)
    val BLACK_WOOL: Material<Unit> = Material(MaterialType(Key("minecraft:black_wool")), Unit)
    val MOVING_PISTON: Material<Unit> = Material(MaterialType(Key("minecraft:moving_piston")), Unit)
    val DANDELION: Material<Unit> = Material(MaterialType(Key("minecraft:dandelion")), Unit)
    val POPPY: Material<Unit> = Material(MaterialType(Key("minecraft:poppy")), Unit)
    val BLUE_ORCHID: Material<Unit> = Material(MaterialType(Key("minecraft:blue_orchid")), Unit)
    val ALLIUM: Material<Unit> = Material(MaterialType(Key("minecraft:allium")), Unit)
    val AZURE_BLUET: Material<Unit> = Material(MaterialType(Key("minecraft:azure_bluet")), Unit)
    val RED_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:red_tulip")), Unit)
    val ORANGE_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:orange_tulip")), Unit)
    val WHITE_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:white_tulip")), Unit)
    val PINK_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:pink_tulip")), Unit)
    val OXEYE_DAISY: Material<Unit> = Material(MaterialType(Key("minecraft:oxeye_daisy")), Unit)
    val CORNFLOWER: Material<Unit> = Material(MaterialType(Key("minecraft:cornflower")), Unit)
    val WITHER_ROSE: Material<Unit> = Material(MaterialType(Key("minecraft:wither_rose")), Unit)
    val LILY_OF_THE_VALLEY: Material<Unit> = Material(MaterialType(Key("minecraft:lily_of_the_valley")), Unit)
    val BROWN_MUSHROOM: Material<Unit> = Material(MaterialType(Key("minecraft:brown_mushroom")), Unit)
    val RED_MUSHROOM: Material<Unit> = Material(MaterialType(Key("minecraft:red_mushroom")), Unit)
    val GOLD_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:gold_block")), Unit)
    val IRON_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:iron_block")), Unit)
    val BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:bricks")), Unit)
    val TNT: Material<Unit> = Material(MaterialType(Key("minecraft:tnt")), Unit)
    val BOOKSHELF: Material<Unit> = Material(MaterialType(Key("minecraft:bookshelf")), Unit)
    val MOSSY_COBBLESTONE: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_cobblestone")), Unit)
    val OBSIDIAN: Material<Unit> = Material(MaterialType(Key("minecraft:obsidian")), Unit)
    val TORCH: Material<Unit> = Material(MaterialType(Key("minecraft:torch")), Unit)
    val WALL_TORCH: Material<Unit> = Material(MaterialType(Key("minecraft:wall_torch")), Unit)
    val FIRE: Material<Unit> = Material(MaterialType(Key("minecraft:fire")), Unit)
    val SOUL_FIRE: Material<Unit> = Material(MaterialType(Key("minecraft:soul_fire")), Unit)
    val SPAWNER: Material<Unit> = Material(MaterialType(Key("minecraft:spawner")), Unit)
    val OAK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:oak_stairs")), Unit)
    val CHEST: Material<Unit> = Material(MaterialType(Key("minecraft:chest")), Unit)
    val REDSTONE_WIRE: Material<Unit> = Material(MaterialType(Key("minecraft:redstone_wire")), Unit)
    val DIAMOND_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_ore")), Unit)
    val DEEPSLATE_DIAMOND_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_diamond_ore")), Unit)
    val DIAMOND_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:diamond_block")), Unit)
    val CRAFTING_TABLE: Material<Unit> = Material(MaterialType(Key("minecraft:crafting_table")), Unit)
    val FARMLAND: Material<Unit> = Material(MaterialType(Key("minecraft:farmland")), Unit)
    val FURNACE: Material<Unit> = Material(MaterialType(Key("minecraft:furnace")), Unit)
    val OAK_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:oak_sign")), Unit)
    val SPRUCE_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_sign")), Unit)
    val BIRCH_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:birch_sign")), Unit)
    val ACACIA_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_sign")), Unit)
    val JUNGLE_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_sign")), Unit)
    val DARK_OAK_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_sign")), Unit)
    val OAK_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:oak_door")), Unit)
    val LADDER: Material<Unit> = Material(MaterialType(Key("minecraft:ladder")), Unit)
    val RAIL: Material<Unit> = Material(MaterialType(Key("minecraft:rail")), Unit)
    val COBBLESTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:cobblestone_stairs")), Unit)
    val OAK_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:oak_wall_sign")), Unit)
    val SPRUCE_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_wall_sign")), Unit)
    val BIRCH_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:birch_wall_sign")), Unit)
    val ACACIA_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_wall_sign")), Unit)
    val JUNGLE_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_wall_sign")), Unit)
    val DARK_OAK_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_wall_sign")), Unit)
    val LEVER: Material<Unit> = Material(MaterialType(Key("minecraft:lever")), Unit)
    val STONE_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:stone_pressure_plate")), Unit)
    val IRON_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:iron_door")), Unit)
    val OAK_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:oak_pressure_plate")), Unit)
    val SPRUCE_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_pressure_plate")), Unit)
    val BIRCH_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:birch_pressure_plate")), Unit)
    val JUNGLE_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_pressure_plate")), Unit)
    val ACACIA_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_pressure_plate")), Unit)
    val DARK_OAK_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_pressure_plate")), Unit)
    val REDSTONE_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:redstone_ore")), Unit)
    val DEEPSLATE_REDSTONE_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_redstone_ore")), Unit)
    val REDSTONE_TORCH: Material<Unit> = Material(MaterialType(Key("minecraft:redstone_torch")), Unit)
    val REDSTONE_WALL_TORCH: Material<Unit> = Material(MaterialType(Key("minecraft:redstone_wall_torch")), Unit)
    val STONE_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:stone_button")), Unit)
    val SNOW: Material<Unit> = Material(MaterialType(Key("minecraft:snow")), Unit)
    val ICE: Material<Unit> = Material(MaterialType(Key("minecraft:ice")), Unit)
    val SNOW_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:snow_block")), Unit)
    val CACTUS: Material<Unit> = Material(MaterialType(Key("minecraft:cactus")), Unit)
    val CLAY: Material<Unit> = Material(MaterialType(Key("minecraft:clay")), Unit)
    val SUGAR_CANE: Material<Unit> = Material(MaterialType(Key("minecraft:sugar_cane")), Unit)
    val JUKEBOX: Material<Unit> = Material(MaterialType(Key("minecraft:jukebox")), Unit)
    val OAK_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:oak_fence")), Unit)
    val PUMPKIN: Material<Unit> = Material(MaterialType(Key("minecraft:pumpkin")), Unit)
    val NETHERRACK: Material<Unit> = Material(MaterialType(Key("minecraft:netherrack")), Unit)
    val SOUL_SAND: Material<Unit> = Material(MaterialType(Key("minecraft:soul_sand")), Unit)
    val SOUL_SOIL: Material<Unit> = Material(MaterialType(Key("minecraft:soul_soil")), Unit)
    val BASALT: Material<Unit> = Material(MaterialType(Key("minecraft:basalt")), Unit)
    val POLISHED_BASALT: Material<Unit> = Material(MaterialType(Key("minecraft:polished_basalt")), Unit)
    val SOUL_TORCH: Material<Unit> = Material(MaterialType(Key("minecraft:soul_torch")), Unit)
    val SOUL_WALL_TORCH: Material<Unit> = Material(MaterialType(Key("minecraft:soul_wall_torch")), Unit)
    val GLOWSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:glowstone")), Unit)
    val NETHER_PORTAL: Material<Unit> = Material(MaterialType(Key("minecraft:nether_portal")), Unit)
    val CARVED_PUMPKIN: Material<Unit> = Material(MaterialType(Key("minecraft:carved_pumpkin")), Unit)
    val JACK_O_LANTERN: Material<Unit> = Material(MaterialType(Key("minecraft:jack_o_lantern")), Unit)
    val CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:cake")), Unit)
    val REPEATER: Material<Unit> = Material(MaterialType(Key("minecraft:repeater")), Unit)
    val WHITE_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:white_stained_glass")), Unit)
    val ORANGE_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:orange_stained_glass")), Unit)
    val MAGENTA_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_stained_glass")), Unit)
    val LIGHT_BLUE_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_stained_glass")), Unit)
    val YELLOW_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_stained_glass")), Unit)
    val LIME_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:lime_stained_glass")), Unit)
    val PINK_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:pink_stained_glass")), Unit)
    val GRAY_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:gray_stained_glass")), Unit)
    val LIGHT_GRAY_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_stained_glass")), Unit)
    val CYAN_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_stained_glass")), Unit)
    val PURPLE_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:purple_stained_glass")), Unit)
    val BLUE_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:blue_stained_glass")), Unit)
    val BROWN_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:brown_stained_glass")), Unit)
    val GREEN_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:green_stained_glass")), Unit)
    val RED_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:red_stained_glass")), Unit)
    val BLACK_STAINED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:black_stained_glass")), Unit)
    val OAK_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:oak_trapdoor")), Unit)
    val SPRUCE_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_trapdoor")), Unit)
    val BIRCH_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:birch_trapdoor")), Unit)
    val JUNGLE_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_trapdoor")), Unit)
    val ACACIA_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_trapdoor")), Unit)
    val DARK_OAK_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_trapdoor")), Unit)
    val STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:stone_bricks")), Unit)
    val MOSSY_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_stone_bricks")), Unit)
    val CRACKED_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:cracked_stone_bricks")), Unit)
    val CHISELED_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:chiseled_stone_bricks")), Unit)
    val INFESTED_STONE: Material<Unit> = Material(MaterialType(Key("minecraft:infested_stone")), Unit)
    val INFESTED_COBBLESTONE: Material<Unit> = Material(MaterialType(Key("minecraft:infested_cobblestone")), Unit)
    val INFESTED_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:infested_stone_bricks")), Unit)
    val INFESTED_MOSSY_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:infested_mossy_stone_bricks")), Unit)
    val INFESTED_CRACKED_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:infested_cracked_stone_bricks")), Unit)
    val INFESTED_CHISELED_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:infested_chiseled_stone_bricks")), Unit)
    val BROWN_MUSHROOM_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:brown_mushroom_block")), Unit)
    val RED_MUSHROOM_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:red_mushroom_block")), Unit)
    val MUSHROOM_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:mushroom_stem")), Unit)
    val IRON_BARS: Material<Unit> = Material(MaterialType(Key("minecraft:iron_bars")), Unit)
    val CHAIN: Material<Unit> = Material(MaterialType(Key("minecraft:chain")), Unit)
    val GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:glass_pane")), Unit)
    val MELON: Material<Unit> = Material(MaterialType(Key("minecraft:melon")), Unit)
    val ATTACHED_PUMPKIN_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:attached_pumpkin_stem")), Unit)
    val ATTACHED_MELON_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:attached_melon_stem")), Unit)
    val PUMPKIN_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:pumpkin_stem")), Unit)
    val MELON_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:melon_stem")), Unit)
    val VINE: Material<Unit> = Material(MaterialType(Key("minecraft:vine")), Unit)
    val GLOW_LICHEN: Material<Unit> = Material(MaterialType(Key("minecraft:glow_lichen")), Unit)
    val OAK_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:oak_fence_gate")), Unit)
    val BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:brick_stairs")), Unit)
    val STONE_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:stone_brick_stairs")), Unit)
    val MYCELIUM: Material<Unit> = Material(MaterialType(Key("minecraft:mycelium")), Unit)
    val LILY_PAD: Material<Unit> = Material(MaterialType(Key("minecraft:lily_pad")), Unit)
    val NETHER_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:nether_bricks")), Unit)
    val NETHER_BRICK_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:nether_brick_fence")), Unit)
    val NETHER_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:nether_brick_stairs")), Unit)
    val ENCHANTING_TABLE: Material<Unit> = Material(MaterialType(Key("minecraft:enchanting_table")), Unit)
    val WATER_CAULDRON: Material<Unit> = Material(MaterialType(Key("minecraft:water_cauldron")), Unit)
    val LAVA_CAULDRON: Material<Unit> = Material(MaterialType(Key("minecraft:lava_cauldron")), Unit)
    val POWDER_SNOW_CAULDRON: Material<Unit> = Material(MaterialType(Key("minecraft:powder_snow_cauldron")), Unit)
    val END_PORTAL: Material<Unit> = Material(MaterialType(Key("minecraft:end_portal")), Unit)
    val END_PORTAL_FRAME: Material<Unit> = Material(MaterialType(Key("minecraft:end_portal_frame")), Unit)
    val END_STONE: Material<Unit> = Material(MaterialType(Key("minecraft:end_stone")), Unit)
    val DRAGON_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:dragon_egg")), Unit)
    val REDSTONE_LAMP: Material<Unit> = Material(MaterialType(Key("minecraft:redstone_lamp")), Unit)
    val COCOA: Material<Unit> = Material(MaterialType(Key("minecraft:cocoa")), Unit)
    val SANDSTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:sandstone_stairs")), Unit)
    val EMERALD_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:emerald_ore")), Unit)
    val DEEPSLATE_EMERALD_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_emerald_ore")), Unit)
    val ENDER_CHEST: Material<Unit> = Material(MaterialType(Key("minecraft:ender_chest")), Unit)
    val TRIPWIRE_HOOK: Material<Unit> = Material(MaterialType(Key("minecraft:tripwire_hook")), Unit)
    val TRIPWIRE: Material<Unit> = Material(MaterialType(Key("minecraft:tripwire")), Unit)
    val EMERALD_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:emerald_block")), Unit)
    val SPRUCE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_stairs")), Unit)
    val BIRCH_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:birch_stairs")), Unit)
    val JUNGLE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_stairs")), Unit)
    val COMMAND_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:command_block")), Unit)
    val BEACON: Material<Unit> = Material(MaterialType(Key("minecraft:beacon")), Unit)
    val COBBLESTONE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:cobblestone_wall")), Unit)
    val MOSSY_COBBLESTONE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_cobblestone_wall")), Unit)
    val POTTED_OAK_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:potted_oak_sapling")), Unit)
    val POTTED_SPRUCE_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:potted_spruce_sapling")), Unit)
    val POTTED_BIRCH_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:potted_birch_sapling")), Unit)
    val POTTED_JUNGLE_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:potted_jungle_sapling")), Unit)
    val POTTED_ACACIA_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:potted_acacia_sapling")), Unit)
    val POTTED_DARK_OAK_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:potted_dark_oak_sapling")), Unit)
    val POTTED_FERN: Material<Unit> = Material(MaterialType(Key("minecraft:potted_fern")), Unit)
    val POTTED_DANDELION: Material<Unit> = Material(MaterialType(Key("minecraft:potted_dandelion")), Unit)
    val POTTED_POPPY: Material<Unit> = Material(MaterialType(Key("minecraft:potted_poppy")), Unit)
    val POTTED_BLUE_ORCHID: Material<Unit> = Material(MaterialType(Key("minecraft:potted_blue_orchid")), Unit)
    val POTTED_ALLIUM: Material<Unit> = Material(MaterialType(Key("minecraft:potted_allium")), Unit)
    val POTTED_AZURE_BLUET: Material<Unit> = Material(MaterialType(Key("minecraft:potted_azure_bluet")), Unit)
    val POTTED_RED_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:potted_red_tulip")), Unit)
    val POTTED_ORANGE_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:potted_orange_tulip")), Unit)
    val POTTED_WHITE_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:potted_white_tulip")), Unit)
    val POTTED_PINK_TULIP: Material<Unit> = Material(MaterialType(Key("minecraft:potted_pink_tulip")), Unit)
    val POTTED_OXEYE_DAISY: Material<Unit> = Material(MaterialType(Key("minecraft:potted_oxeye_daisy")), Unit)
    val POTTED_CORNFLOWER: Material<Unit> = Material(MaterialType(Key("minecraft:potted_cornflower")), Unit)
    val POTTED_LILY_OF_THE_VALLEY: Material<Unit> = Material(MaterialType(Key("minecraft:potted_lily_of_the_valley")), Unit)
    val POTTED_WITHER_ROSE: Material<Unit> = Material(MaterialType(Key("minecraft:potted_wither_rose")), Unit)
    val POTTED_RED_MUSHROOM: Material<Unit> = Material(MaterialType(Key("minecraft:potted_red_mushroom")), Unit)
    val POTTED_BROWN_MUSHROOM: Material<Unit> = Material(MaterialType(Key("minecraft:potted_brown_mushroom")), Unit)
    val POTTED_DEAD_BUSH: Material<Unit> = Material(MaterialType(Key("minecraft:potted_dead_bush")), Unit)
    val POTTED_CACTUS: Material<Unit> = Material(MaterialType(Key("minecraft:potted_cactus")), Unit)
    val CARROTS: Material<Unit> = Material(MaterialType(Key("minecraft:carrots")), Unit)
    val POTATOES: Material<Unit> = Material(MaterialType(Key("minecraft:potatoes")), Unit)
    val OAK_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:oak_button")), Unit)
    val SPRUCE_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_button")), Unit)
    val BIRCH_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:birch_button")), Unit)
    val JUNGLE_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_button")), Unit)
    val ACACIA_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_button")), Unit)
    val DARK_OAK_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_button")), Unit)
    val SKELETON_SKULL: Material<Unit> = Material(MaterialType(Key("minecraft:skeleton_skull")), Unit)
    val SKELETON_WALL_SKULL: Material<Unit> = Material(MaterialType(Key("minecraft:skeleton_wall_skull")), Unit)
    val WITHER_SKELETON_SKULL: Material<Unit> = Material(MaterialType(Key("minecraft:wither_skeleton_skull")), Unit)
    val WITHER_SKELETON_WALL_SKULL: Material<Unit> = Material(MaterialType(Key("minecraft:wither_skeleton_wall_skull")), Unit)
    val ZOMBIE_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:zombie_head")), Unit)
    val ZOMBIE_WALL_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:zombie_wall_head")), Unit)
    val PLAYER_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:player_head")), Unit)
    val PLAYER_WALL_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:player_wall_head")), Unit)
    val CREEPER_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:creeper_head")), Unit)
    val CREEPER_WALL_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:creeper_wall_head")), Unit)
    val DRAGON_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:dragon_head")), Unit)
    val DRAGON_WALL_HEAD: Material<Unit> = Material(MaterialType(Key("minecraft:dragon_wall_head")), Unit)
    val ANVIL: Material<Unit> = Material(MaterialType(Key("minecraft:anvil")), Unit)
    val CHIPPED_ANVIL: Material<Unit> = Material(MaterialType(Key("minecraft:chipped_anvil")), Unit)
    val DAMAGED_ANVIL: Material<Unit> = Material(MaterialType(Key("minecraft:damaged_anvil")), Unit)
    val TRAPPED_CHEST: Material<Unit> = Material(MaterialType(Key("minecraft:trapped_chest")), Unit)
    val LIGHT_WEIGHTED_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:light_weighted_pressure_plate")), Unit)
    val HEAVY_WEIGHTED_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:heavy_weighted_pressure_plate")), Unit)
    val COMPARATOR: Material<Unit> = Material(MaterialType(Key("minecraft:comparator")), Unit)
    val DAYLIGHT_DETECTOR: Material<Unit> = Material(MaterialType(Key("minecraft:daylight_detector")), Unit)
    val REDSTONE_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:redstone_block")), Unit)
    val NETHER_QUARTZ_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:nether_quartz_ore")), Unit)
    val HOPPER: Material<Unit> = Material(MaterialType(Key("minecraft:hopper")), Unit)
    val QUARTZ_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:quartz_block")), Unit)
    val CHISELED_QUARTZ_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:chiseled_quartz_block")), Unit)
    val QUARTZ_PILLAR: Material<Unit> = Material(MaterialType(Key("minecraft:quartz_pillar")), Unit)
    val QUARTZ_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:quartz_stairs")), Unit)
    val ACTIVATOR_RAIL: Material<Unit> = Material(MaterialType(Key("minecraft:activator_rail")), Unit)
    val DROPPER: Material<Unit> = Material(MaterialType(Key("minecraft:dropper")), Unit)
    val WHITE_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:white_terracotta")), Unit)
    val ORANGE_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:orange_terracotta")), Unit)
    val MAGENTA_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_terracotta")), Unit)
    val LIGHT_BLUE_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_terracotta")), Unit)
    val YELLOW_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_terracotta")), Unit)
    val LIME_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:lime_terracotta")), Unit)
    val PINK_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:pink_terracotta")), Unit)
    val GRAY_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:gray_terracotta")), Unit)
    val LIGHT_GRAY_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_terracotta")), Unit)
    val CYAN_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_terracotta")), Unit)
    val PURPLE_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:purple_terracotta")), Unit)
    val BLUE_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:blue_terracotta")), Unit)
    val BROWN_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:brown_terracotta")), Unit)
    val GREEN_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:green_terracotta")), Unit)
    val RED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:red_terracotta")), Unit)
    val BLACK_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:black_terracotta")), Unit)
    val WHITE_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:white_stained_glass_pane")), Unit)
    val ORANGE_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:orange_stained_glass_pane")), Unit)
    val MAGENTA_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_stained_glass_pane")), Unit)
    val LIGHT_BLUE_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_stained_glass_pane")), Unit)
    val YELLOW_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_stained_glass_pane")), Unit)
    val LIME_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:lime_stained_glass_pane")), Unit)
    val PINK_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:pink_stained_glass_pane")), Unit)
    val GRAY_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:gray_stained_glass_pane")), Unit)
    val LIGHT_GRAY_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_stained_glass_pane")), Unit)
    val CYAN_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_stained_glass_pane")), Unit)
    val PURPLE_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:purple_stained_glass_pane")), Unit)
    val BLUE_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:blue_stained_glass_pane")), Unit)
    val BROWN_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:brown_stained_glass_pane")), Unit)
    val GREEN_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:green_stained_glass_pane")), Unit)
    val RED_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:red_stained_glass_pane")), Unit)
    val BLACK_STAINED_GLASS_PANE: Material<Unit> = Material(MaterialType(Key("minecraft:black_stained_glass_pane")), Unit)
    val ACACIA_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_stairs")), Unit)
    val DARK_OAK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_stairs")), Unit)
    val SLIME_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:slime_block")), Unit)
    val BARRIER: Material<Unit> = Material(MaterialType(Key("minecraft:barrier")), Unit)
    val LIGHT: Material<Unit> = Material(MaterialType(Key("minecraft:light")), Unit)
    val IRON_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:iron_trapdoor")), Unit)
    val PRISMARINE: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine")), Unit)
    val PRISMARINE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_bricks")), Unit)
    val DARK_PRISMARINE: Material<Unit> = Material(MaterialType(Key("minecraft:dark_prismarine")), Unit)
    val PRISMARINE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_stairs")), Unit)
    val PRISMARINE_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_brick_stairs")), Unit)
    val DARK_PRISMARINE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:dark_prismarine_stairs")), Unit)
    val PRISMARINE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_slab")), Unit)
    val PRISMARINE_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_brick_slab")), Unit)
    val DARK_PRISMARINE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:dark_prismarine_slab")), Unit)
    val SEA_LANTERN: Material<Unit> = Material(MaterialType(Key("minecraft:sea_lantern")), Unit)
    val HAY_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:hay_block")), Unit)
    val WHITE_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:white_carpet")), Unit)
    val ORANGE_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:orange_carpet")), Unit)
    val MAGENTA_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_carpet")), Unit)
    val LIGHT_BLUE_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_carpet")), Unit)
    val YELLOW_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_carpet")), Unit)
    val LIME_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:lime_carpet")), Unit)
    val PINK_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:pink_carpet")), Unit)
    val GRAY_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:gray_carpet")), Unit)
    val LIGHT_GRAY_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_carpet")), Unit)
    val CYAN_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_carpet")), Unit)
    val PURPLE_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:purple_carpet")), Unit)
    val BLUE_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:blue_carpet")), Unit)
    val BROWN_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:brown_carpet")), Unit)
    val GREEN_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:green_carpet")), Unit)
    val RED_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:red_carpet")), Unit)
    val BLACK_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:black_carpet")), Unit)
    val TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:terracotta")), Unit)
    val COAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:coal_block")), Unit)
    val PACKED_ICE: Material<Unit> = Material(MaterialType(Key("minecraft:packed_ice")), Unit)
    val SUNFLOWER: Material<Unit> = Material(MaterialType(Key("minecraft:sunflower")), Unit)
    val LILAC: Material<Unit> = Material(MaterialType(Key("minecraft:lilac")), Unit)
    val ROSE_BUSH: Material<Unit> = Material(MaterialType(Key("minecraft:rose_bush")), Unit)
    val PEONY: Material<Unit> = Material(MaterialType(Key("minecraft:peony")), Unit)
    val TALL_GRASS: Material<Unit> = Material(MaterialType(Key("minecraft:tall_grass")), Unit)
    val LARGE_FERN: Material<Unit> = Material(MaterialType(Key("minecraft:large_fern")), Unit)
    val WHITE_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:white_banner")), Unit)
    val ORANGE_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:orange_banner")), Unit)
    val MAGENTA_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_banner")), Unit)
    val LIGHT_BLUE_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_banner")), Unit)
    val YELLOW_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_banner")), Unit)
    val LIME_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:lime_banner")), Unit)
    val PINK_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:pink_banner")), Unit)
    val GRAY_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:gray_banner")), Unit)
    val LIGHT_GRAY_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_banner")), Unit)
    val CYAN_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_banner")), Unit)
    val PURPLE_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:purple_banner")), Unit)
    val BLUE_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:blue_banner")), Unit)
    val BROWN_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:brown_banner")), Unit)
    val GREEN_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:green_banner")), Unit)
    val RED_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:red_banner")), Unit)
    val BLACK_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:black_banner")), Unit)
    val WHITE_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:white_wall_banner")), Unit)
    val ORANGE_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:orange_wall_banner")), Unit)
    val MAGENTA_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_wall_banner")), Unit)
    val LIGHT_BLUE_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_wall_banner")), Unit)
    val YELLOW_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_wall_banner")), Unit)
    val LIME_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:lime_wall_banner")), Unit)
    val PINK_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:pink_wall_banner")), Unit)
    val GRAY_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:gray_wall_banner")), Unit)
    val LIGHT_GRAY_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_wall_banner")), Unit)
    val CYAN_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_wall_banner")), Unit)
    val PURPLE_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:purple_wall_banner")), Unit)
    val BLUE_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:blue_wall_banner")), Unit)
    val BROWN_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:brown_wall_banner")), Unit)
    val GREEN_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:green_wall_banner")), Unit)
    val RED_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:red_wall_banner")), Unit)
    val BLACK_WALL_BANNER: Material<Unit> = Material(MaterialType(Key("minecraft:black_wall_banner")), Unit)
    val RED_SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:red_sandstone")), Unit)
    val CHISELED_RED_SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:chiseled_red_sandstone")), Unit)
    val CUT_RED_SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:cut_red_sandstone")), Unit)
    val RED_SANDSTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:red_sandstone_stairs")), Unit)
    val OAK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:oak_slab")), Unit)
    val SPRUCE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_slab")), Unit)
    val BIRCH_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:birch_slab")), Unit)
    val JUNGLE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_slab")), Unit)
    val ACACIA_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_slab")), Unit)
    val DARK_OAK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_slab")), Unit)
    val STONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:stone_slab")), Unit)
    val SMOOTH_STONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_stone_slab")), Unit)
    val SANDSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:sandstone_slab")), Unit)
    val CUT_SANDSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:cut_sandstone_slab")), Unit)
    val PETRIFIED_OAK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:petrified_oak_slab")), Unit)
    val COBBLESTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:cobblestone_slab")), Unit)
    val BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:brick_slab")), Unit)
    val STONE_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:stone_brick_slab")), Unit)
    val NETHER_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:nether_brick_slab")), Unit)
    val QUARTZ_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:quartz_slab")), Unit)
    val RED_SANDSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:red_sandstone_slab")), Unit)
    val CUT_RED_SANDSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:cut_red_sandstone_slab")), Unit)
    val PURPUR_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:purpur_slab")), Unit)
    val SMOOTH_STONE: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_stone")), Unit)
    val SMOOTH_SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_sandstone")), Unit)
    val SMOOTH_QUARTZ: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_quartz")), Unit)
    val SMOOTH_RED_SANDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_red_sandstone")), Unit)
    val SPRUCE_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_fence_gate")), Unit)
    val BIRCH_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:birch_fence_gate")), Unit)
    val JUNGLE_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_fence_gate")), Unit)
    val ACACIA_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_fence_gate")), Unit)
    val DARK_OAK_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_fence_gate")), Unit)
    val SPRUCE_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_fence")), Unit)
    val BIRCH_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:birch_fence")), Unit)
    val JUNGLE_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_fence")), Unit)
    val ACACIA_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_fence")), Unit)
    val DARK_OAK_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_fence")), Unit)
    val SPRUCE_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:spruce_door")), Unit)
    val BIRCH_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:birch_door")), Unit)
    val JUNGLE_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:jungle_door")), Unit)
    val ACACIA_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:acacia_door")), Unit)
    val DARK_OAK_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:dark_oak_door")), Unit)
    val END_ROD: Material<Unit> = Material(MaterialType(Key("minecraft:end_rod")), Unit)
    val CHORUS_PLANT: Material<Unit> = Material(MaterialType(Key("minecraft:chorus_plant")), Unit)
    val CHORUS_FLOWER: Material<Unit> = Material(MaterialType(Key("minecraft:chorus_flower")), Unit)
    val PURPUR_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:purpur_block")), Unit)
    val PURPUR_PILLAR: Material<Unit> = Material(MaterialType(Key("minecraft:purpur_pillar")), Unit)
    val PURPUR_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:purpur_stairs")), Unit)
    val END_STONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:end_stone_bricks")), Unit)
    val BEETROOTS: Material<Unit> = Material(MaterialType(Key("minecraft:beetroots")), Unit)
    val DIRT_PATH: Material<Unit> = Material(MaterialType(Key("minecraft:dirt_path")), Unit)
    val END_GATEWAY: Material<Unit> = Material(MaterialType(Key("minecraft:end_gateway")), Unit)
    val REPEATING_COMMAND_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:repeating_command_block")), Unit)
    val CHAIN_COMMAND_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:chain_command_block")), Unit)
    val FROSTED_ICE: Material<Unit> = Material(MaterialType(Key("minecraft:frosted_ice")), Unit)
    val MAGMA_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:magma_block")), Unit)
    val NETHER_WART_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:nether_wart_block")), Unit)
    val RED_NETHER_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:red_nether_bricks")), Unit)
    val BONE_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:bone_block")), Unit)
    val STRUCTURE_VOID: Material<Unit> = Material(MaterialType(Key("minecraft:structure_void")), Unit)
    val OBSERVER: Material<Unit> = Material(MaterialType(Key("minecraft:observer")), Unit)
    val SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:shulker_box")), Unit)
    val WHITE_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:white_shulker_box")), Unit)
    val ORANGE_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:orange_shulker_box")), Unit)
    val MAGENTA_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_shulker_box")), Unit)
    val LIGHT_BLUE_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_shulker_box")), Unit)
    val YELLOW_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_shulker_box")), Unit)
    val LIME_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:lime_shulker_box")), Unit)
    val PINK_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:pink_shulker_box")), Unit)
    val GRAY_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:gray_shulker_box")), Unit)
    val LIGHT_GRAY_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_shulker_box")), Unit)
    val CYAN_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_shulker_box")), Unit)
    val PURPLE_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:purple_shulker_box")), Unit)
    val BLUE_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:blue_shulker_box")), Unit)
    val BROWN_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:brown_shulker_box")), Unit)
    val GREEN_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:green_shulker_box")), Unit)
    val RED_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:red_shulker_box")), Unit)
    val BLACK_SHULKER_BOX: Material<Unit> = Material(MaterialType(Key("minecraft:black_shulker_box")), Unit)
    val WHITE_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:white_glazed_terracotta")), Unit)
    val ORANGE_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:orange_glazed_terracotta")), Unit)
    val MAGENTA_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_glazed_terracotta")), Unit)
    val LIGHT_BLUE_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_glazed_terracotta")), Unit)
    val YELLOW_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_glazed_terracotta")), Unit)
    val LIME_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:lime_glazed_terracotta")), Unit)
    val PINK_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:pink_glazed_terracotta")), Unit)
    val GRAY_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:gray_glazed_terracotta")), Unit)
    val LIGHT_GRAY_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_glazed_terracotta")), Unit)
    val CYAN_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_glazed_terracotta")), Unit)
    val PURPLE_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:purple_glazed_terracotta")), Unit)
    val BLUE_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:blue_glazed_terracotta")), Unit)
    val BROWN_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:brown_glazed_terracotta")), Unit)
    val GREEN_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:green_glazed_terracotta")), Unit)
    val RED_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:red_glazed_terracotta")), Unit)
    val BLACK_GLAZED_TERRACOTTA: Material<Unit> = Material(MaterialType(Key("minecraft:black_glazed_terracotta")), Unit)
    val WHITE_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:white_concrete")), Unit)
    val ORANGE_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:orange_concrete")), Unit)
    val MAGENTA_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_concrete")), Unit)
    val LIGHT_BLUE_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_concrete")), Unit)
    val YELLOW_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_concrete")), Unit)
    val LIME_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:lime_concrete")), Unit)
    val PINK_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:pink_concrete")), Unit)
    val GRAY_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:gray_concrete")), Unit)
    val LIGHT_GRAY_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_concrete")), Unit)
    val CYAN_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_concrete")), Unit)
    val PURPLE_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:purple_concrete")), Unit)
    val BLUE_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:blue_concrete")), Unit)
    val BROWN_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:brown_concrete")), Unit)
    val GREEN_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:green_concrete")), Unit)
    val RED_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:red_concrete")), Unit)
    val BLACK_CONCRETE: Material<Unit> = Material(MaterialType(Key("minecraft:black_concrete")), Unit)
    val WHITE_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:white_concrete_powder")), Unit)
    val ORANGE_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:orange_concrete_powder")), Unit)
    val MAGENTA_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_concrete_powder")), Unit)
    val LIGHT_BLUE_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_concrete_powder")), Unit)
    val YELLOW_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_concrete_powder")), Unit)
    val LIME_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:lime_concrete_powder")), Unit)
    val PINK_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:pink_concrete_powder")), Unit)
    val GRAY_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:gray_concrete_powder")), Unit)
    val LIGHT_GRAY_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_concrete_powder")), Unit)
    val CYAN_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_concrete_powder")), Unit)
    val PURPLE_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:purple_concrete_powder")), Unit)
    val BLUE_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:blue_concrete_powder")), Unit)
    val BROWN_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:brown_concrete_powder")), Unit)
    val GREEN_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:green_concrete_powder")), Unit)
    val RED_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:red_concrete_powder")), Unit)
    val BLACK_CONCRETE_POWDER: Material<Unit> = Material(MaterialType(Key("minecraft:black_concrete_powder")), Unit)
    val KELP: Material<Unit> = Material(MaterialType(Key("minecraft:kelp")), Unit)
    val KELP_PLANT: Material<Unit> = Material(MaterialType(Key("minecraft:kelp_plant")), Unit)
    val DRIED_KELP_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:dried_kelp_block")), Unit)
    val TURTLE_EGG: Material<Unit> = Material(MaterialType(Key("minecraft:turtle_egg")), Unit)
    val DEAD_TUBE_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:dead_tube_coral_block")), Unit)
    val DEAD_BRAIN_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:dead_brain_coral_block")), Unit)
    val DEAD_BUBBLE_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:dead_bubble_coral_block")), Unit)
    val DEAD_FIRE_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:dead_fire_coral_block")), Unit)
    val DEAD_HORN_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:dead_horn_coral_block")), Unit)
    val TUBE_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:tube_coral_block")), Unit)
    val BRAIN_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:brain_coral_block")), Unit)
    val BUBBLE_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:bubble_coral_block")), Unit)
    val FIRE_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:fire_coral_block")), Unit)
    val HORN_CORAL_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:horn_coral_block")), Unit)
    val DEAD_TUBE_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:dead_tube_coral")), Unit)
    val DEAD_BRAIN_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:dead_brain_coral")), Unit)
    val DEAD_BUBBLE_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:dead_bubble_coral")), Unit)
    val DEAD_FIRE_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:dead_fire_coral")), Unit)
    val DEAD_HORN_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:dead_horn_coral")), Unit)
    val TUBE_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:tube_coral")), Unit)
    val BRAIN_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:brain_coral")), Unit)
    val BUBBLE_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:bubble_coral")), Unit)
    val FIRE_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:fire_coral")), Unit)
    val HORN_CORAL: Material<Unit> = Material(MaterialType(Key("minecraft:horn_coral")), Unit)
    val DEAD_TUBE_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_tube_coral_fan")), Unit)
    val DEAD_BRAIN_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_brain_coral_fan")), Unit)
    val DEAD_BUBBLE_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_bubble_coral_fan")), Unit)
    val DEAD_FIRE_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_fire_coral_fan")), Unit)
    val DEAD_HORN_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_horn_coral_fan")), Unit)
    val TUBE_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:tube_coral_fan")), Unit)
    val BRAIN_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:brain_coral_fan")), Unit)
    val BUBBLE_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:bubble_coral_fan")), Unit)
    val FIRE_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:fire_coral_fan")), Unit)
    val HORN_CORAL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:horn_coral_fan")), Unit)
    val DEAD_TUBE_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_tube_coral_wall_fan")), Unit)
    val DEAD_BRAIN_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_brain_coral_wall_fan")), Unit)
    val DEAD_BUBBLE_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_bubble_coral_wall_fan")), Unit)
    val DEAD_FIRE_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_fire_coral_wall_fan")), Unit)
    val DEAD_HORN_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:dead_horn_coral_wall_fan")), Unit)
    val TUBE_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:tube_coral_wall_fan")), Unit)
    val BRAIN_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:brain_coral_wall_fan")), Unit)
    val BUBBLE_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:bubble_coral_wall_fan")), Unit)
    val FIRE_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:fire_coral_wall_fan")), Unit)
    val HORN_CORAL_WALL_FAN: Material<Unit> = Material(MaterialType(Key("minecraft:horn_coral_wall_fan")), Unit)
    val SEA_PICKLE: Material<Unit> = Material(MaterialType(Key("minecraft:sea_pickle")), Unit)
    val BLUE_ICE: Material<Unit> = Material(MaterialType(Key("minecraft:blue_ice")), Unit)
    val CONDUIT: Material<Unit> = Material(MaterialType(Key("minecraft:conduit")), Unit)
    val BAMBOO_SAPLING: Material<Unit> = Material(MaterialType(Key("minecraft:bamboo_sapling")), Unit)
    val BAMBOO: Material<Unit> = Material(MaterialType(Key("minecraft:bamboo")), Unit)
    val POTTED_BAMBOO: Material<Unit> = Material(MaterialType(Key("minecraft:potted_bamboo")), Unit)
    val VOID_AIR: Material<Unit> = Material(MaterialType(Key("minecraft:void_air")), Unit)
    val CAVE_AIR: Material<Unit> = Material(MaterialType(Key("minecraft:cave_air")), Unit)
    val BUBBLE_COLUMN: Material<Unit> = Material(MaterialType(Key("minecraft:bubble_column")), Unit)
    val POLISHED_GRANITE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:polished_granite_stairs")), Unit)
    val SMOOTH_RED_SANDSTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_red_sandstone_stairs")), Unit)
    val MOSSY_STONE_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_stone_brick_stairs")), Unit)
    val POLISHED_DIORITE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:polished_diorite_stairs")), Unit)
    val MOSSY_COBBLESTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_cobblestone_stairs")), Unit)
    val END_STONE_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:end_stone_brick_stairs")), Unit)
    val STONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:stone_stairs")), Unit)
    val SMOOTH_SANDSTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_sandstone_stairs")), Unit)
    val SMOOTH_QUARTZ_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_quartz_stairs")), Unit)
    val GRANITE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:granite_stairs")), Unit)
    val ANDESITE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:andesite_stairs")), Unit)
    val RED_NETHER_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:red_nether_brick_stairs")), Unit)
    val POLISHED_ANDESITE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:polished_andesite_stairs")), Unit)
    val DIORITE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:diorite_stairs")), Unit)
    val POLISHED_GRANITE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:polished_granite_slab")), Unit)
    val SMOOTH_RED_SANDSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_red_sandstone_slab")), Unit)
    val MOSSY_STONE_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_stone_brick_slab")), Unit)
    val POLISHED_DIORITE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:polished_diorite_slab")), Unit)
    val MOSSY_COBBLESTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_cobblestone_slab")), Unit)
    val END_STONE_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:end_stone_brick_slab")), Unit)
    val SMOOTH_SANDSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_sandstone_slab")), Unit)
    val SMOOTH_QUARTZ_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_quartz_slab")), Unit)
    val GRANITE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:granite_slab")), Unit)
    val ANDESITE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:andesite_slab")), Unit)
    val RED_NETHER_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:red_nether_brick_slab")), Unit)
    val POLISHED_ANDESITE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:polished_andesite_slab")), Unit)
    val DIORITE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:diorite_slab")), Unit)
    val BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:brick_wall")), Unit)
    val PRISMARINE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:prismarine_wall")), Unit)
    val RED_SANDSTONE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:red_sandstone_wall")), Unit)
    val MOSSY_STONE_BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:mossy_stone_brick_wall")), Unit)
    val GRANITE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:granite_wall")), Unit)
    val STONE_BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:stone_brick_wall")), Unit)
    val NETHER_BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:nether_brick_wall")), Unit)
    val ANDESITE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:andesite_wall")), Unit)
    val RED_NETHER_BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:red_nether_brick_wall")), Unit)
    val SANDSTONE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:sandstone_wall")), Unit)
    val END_STONE_BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:end_stone_brick_wall")), Unit)
    val DIORITE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:diorite_wall")), Unit)
    val SCAFFOLDING: Material<Unit> = Material(MaterialType(Key("minecraft:scaffolding")), Unit)
    val LOOM: Material<Unit> = Material(MaterialType(Key("minecraft:loom")), Unit)
    val BARREL: Material<Unit> = Material(MaterialType(Key("minecraft:barrel")), Unit)
    val SMOKER: Material<Unit> = Material(MaterialType(Key("minecraft:smoker")), Unit)
    val BLAST_FURNACE: Material<Unit> = Material(MaterialType(Key("minecraft:blast_furnace")), Unit)
    val CARTOGRAPHY_TABLE: Material<Unit> = Material(MaterialType(Key("minecraft:cartography_table")), Unit)
    val FLETCHING_TABLE: Material<Unit> = Material(MaterialType(Key("minecraft:fletching_table")), Unit)
    val GRINDSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:grindstone")), Unit)
    val LECTERN: Material<Unit> = Material(MaterialType(Key("minecraft:lectern")), Unit)
    val SMITHING_TABLE: Material<Unit> = Material(MaterialType(Key("minecraft:smithing_table")), Unit)
    val STONECUTTER: Material<Unit> = Material(MaterialType(Key("minecraft:stonecutter")), Unit)
    val BELL: Material<Unit> = Material(MaterialType(Key("minecraft:bell")), Unit)
    val LANTERN: Material<Unit> = Material(MaterialType(Key("minecraft:lantern")), Unit)
    val SOUL_LANTERN: Material<Unit> = Material(MaterialType(Key("minecraft:soul_lantern")), Unit)
    val CAMPFIRE: Material<Unit> = Material(MaterialType(Key("minecraft:campfire")), Unit)
    val SOUL_CAMPFIRE: Material<Unit> = Material(MaterialType(Key("minecraft:soul_campfire")), Unit)
    val SWEET_BERRY_BUSH: Material<Unit> = Material(MaterialType(Key("minecraft:sweet_berry_bush")), Unit)
    val WARPED_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:warped_stem")), Unit)
    val STRIPPED_WARPED_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_warped_stem")), Unit)
    val WARPED_HYPHAE: Material<Unit> = Material(MaterialType(Key("minecraft:warped_hyphae")), Unit)
    val STRIPPED_WARPED_HYPHAE: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_warped_hyphae")), Unit)
    val WARPED_NYLIUM: Material<Unit> = Material(MaterialType(Key("minecraft:warped_nylium")), Unit)
    val WARPED_FUNGUS: Material<Unit> = Material(MaterialType(Key("minecraft:warped_fungus")), Unit)
    val WARPED_WART_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:warped_wart_block")), Unit)
    val WARPED_ROOTS: Material<Unit> = Material(MaterialType(Key("minecraft:warped_roots")), Unit)
    val NETHER_SPROUTS: Material<Unit> = Material(MaterialType(Key("minecraft:nether_sprouts")), Unit)
    val CRIMSON_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_stem")), Unit)
    val STRIPPED_CRIMSON_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_crimson_stem")), Unit)
    val CRIMSON_HYPHAE: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_hyphae")), Unit)
    val STRIPPED_CRIMSON_HYPHAE: Material<Unit> = Material(MaterialType(Key("minecraft:stripped_crimson_hyphae")), Unit)
    val CRIMSON_NYLIUM: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_nylium")), Unit)
    val CRIMSON_FUNGUS: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_fungus")), Unit)
    val SHROOMLIGHT: Material<Unit> = Material(MaterialType(Key("minecraft:shroomlight")), Unit)
    val WEEPING_VINES: Material<Unit> = Material(MaterialType(Key("minecraft:weeping_vines")), Unit)
    val WEEPING_VINES_PLANT: Material<Unit> = Material(MaterialType(Key("minecraft:weeping_vines_plant")), Unit)
    val TWISTING_VINES: Material<Unit> = Material(MaterialType(Key("minecraft:twisting_vines")), Unit)
    val TWISTING_VINES_PLANT: Material<Unit> = Material(MaterialType(Key("minecraft:twisting_vines_plant")), Unit)
    val CRIMSON_ROOTS: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_roots")), Unit)
    val CRIMSON_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_planks")), Unit)
    val WARPED_PLANKS: Material<Unit> = Material(MaterialType(Key("minecraft:warped_planks")), Unit)
    val CRIMSON_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_slab")), Unit)
    val WARPED_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:warped_slab")), Unit)
    val CRIMSON_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_pressure_plate")), Unit)
    val WARPED_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:warped_pressure_plate")), Unit)
    val CRIMSON_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_fence")), Unit)
    val WARPED_FENCE: Material<Unit> = Material(MaterialType(Key("minecraft:warped_fence")), Unit)
    val CRIMSON_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_trapdoor")), Unit)
    val WARPED_TRAPDOOR: Material<Unit> = Material(MaterialType(Key("minecraft:warped_trapdoor")), Unit)
    val CRIMSON_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_fence_gate")), Unit)
    val WARPED_FENCE_GATE: Material<Unit> = Material(MaterialType(Key("minecraft:warped_fence_gate")), Unit)
    val CRIMSON_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_stairs")), Unit)
    val WARPED_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:warped_stairs")), Unit)
    val CRIMSON_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_button")), Unit)
    val WARPED_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:warped_button")), Unit)
    val CRIMSON_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_door")), Unit)
    val WARPED_DOOR: Material<Unit> = Material(MaterialType(Key("minecraft:warped_door")), Unit)
    val CRIMSON_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_sign")), Unit)
    val WARPED_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:warped_sign")), Unit)
    val CRIMSON_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:crimson_wall_sign")), Unit)
    val WARPED_WALL_SIGN: Material<Unit> = Material(MaterialType(Key("minecraft:warped_wall_sign")), Unit)
    val STRUCTURE_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:structure_block")), Unit)
    val JIGSAW: Material<Unit> = Material(MaterialType(Key("minecraft:jigsaw")), Unit)
    val COMPOSTER: Material<Unit> = Material(MaterialType(Key("minecraft:composter")), Unit)
    val TARGET: Material<Unit> = Material(MaterialType(Key("minecraft:target")), Unit)
    val BEE_NEST: Material<Unit> = Material(MaterialType(Key("minecraft:bee_nest")), Unit)
    val BEEHIVE: Material<Unit> = Material(MaterialType(Key("minecraft:beehive")), Unit)
    val HONEY_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:honey_block")), Unit)
    val HONEYCOMB_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:honeycomb_block")), Unit)
    val NETHERITE_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:netherite_block")), Unit)
    val ANCIENT_DEBRIS: Material<Unit> = Material(MaterialType(Key("minecraft:ancient_debris")), Unit)
    val CRYING_OBSIDIAN: Material<Unit> = Material(MaterialType(Key("minecraft:crying_obsidian")), Unit)
    val RESPAWN_ANCHOR: Material<Unit> = Material(MaterialType(Key("minecraft:respawn_anchor")), Unit)
    val POTTED_CRIMSON_FUNGUS: Material<Unit> = Material(MaterialType(Key("minecraft:potted_crimson_fungus")), Unit)
    val POTTED_WARPED_FUNGUS: Material<Unit> = Material(MaterialType(Key("minecraft:potted_warped_fungus")), Unit)
    val POTTED_CRIMSON_ROOTS: Material<Unit> = Material(MaterialType(Key("minecraft:potted_crimson_roots")), Unit)
    val POTTED_WARPED_ROOTS: Material<Unit> = Material(MaterialType(Key("minecraft:potted_warped_roots")), Unit)
    val LODESTONE: Material<Unit> = Material(MaterialType(Key("minecraft:lodestone")), Unit)
    val BLACKSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:blackstone")), Unit)
    val BLACKSTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:blackstone_stairs")), Unit)
    val BLACKSTONE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:blackstone_wall")), Unit)
    val BLACKSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:blackstone_slab")), Unit)
    val POLISHED_BLACKSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone")), Unit)
    val POLISHED_BLACKSTONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_bricks")), Unit)
    val CRACKED_POLISHED_BLACKSTONE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:cracked_polished_blackstone_bricks")), Unit)
    val CHISELED_POLISHED_BLACKSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:chiseled_polished_blackstone")), Unit)
    val POLISHED_BLACKSTONE_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_brick_slab")), Unit)
    val POLISHED_BLACKSTONE_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_brick_stairs")), Unit)
    val POLISHED_BLACKSTONE_BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_brick_wall")), Unit)
    val GILDED_BLACKSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:gilded_blackstone")), Unit)
    val POLISHED_BLACKSTONE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_stairs")), Unit)
    val POLISHED_BLACKSTONE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_slab")), Unit)
    val POLISHED_BLACKSTONE_PRESSURE_PLATE: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_pressure_plate")), Unit)
    val POLISHED_BLACKSTONE_BUTTON: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_button")), Unit)
    val POLISHED_BLACKSTONE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:polished_blackstone_wall")), Unit)
    val CHISELED_NETHER_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:chiseled_nether_bricks")), Unit)
    val CRACKED_NETHER_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:cracked_nether_bricks")), Unit)
    val QUARTZ_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:quartz_bricks")), Unit)
    val CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:candle")), Unit)
    val WHITE_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:white_candle")), Unit)
    val ORANGE_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:orange_candle")), Unit)
    val MAGENTA_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_candle")), Unit)
    val LIGHT_BLUE_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_candle")), Unit)
    val YELLOW_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_candle")), Unit)
    val LIME_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:lime_candle")), Unit)
    val PINK_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:pink_candle")), Unit)
    val GRAY_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:gray_candle")), Unit)
    val LIGHT_GRAY_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_candle")), Unit)
    val CYAN_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_candle")), Unit)
    val PURPLE_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:purple_candle")), Unit)
    val BLUE_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:blue_candle")), Unit)
    val BROWN_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:brown_candle")), Unit)
    val GREEN_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:green_candle")), Unit)
    val RED_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:red_candle")), Unit)
    val BLACK_CANDLE: Material<Unit> = Material(MaterialType(Key("minecraft:black_candle")), Unit)
    val CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:candle_cake")), Unit)
    val WHITE_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:white_candle_cake")), Unit)
    val ORANGE_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:orange_candle_cake")), Unit)
    val MAGENTA_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:magenta_candle_cake")), Unit)
    val LIGHT_BLUE_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:light_blue_candle_cake")), Unit)
    val YELLOW_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:yellow_candle_cake")), Unit)
    val LIME_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:lime_candle_cake")), Unit)
    val PINK_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:pink_candle_cake")), Unit)
    val GRAY_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:gray_candle_cake")), Unit)
    val LIGHT_GRAY_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:light_gray_candle_cake")), Unit)
    val CYAN_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:cyan_candle_cake")), Unit)
    val PURPLE_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:purple_candle_cake")), Unit)
    val BLUE_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:blue_candle_cake")), Unit)
    val BROWN_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:brown_candle_cake")), Unit)
    val GREEN_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:green_candle_cake")), Unit)
    val RED_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:red_candle_cake")), Unit)
    val BLACK_CANDLE_CAKE: Material<Unit> = Material(MaterialType(Key("minecraft:black_candle_cake")), Unit)
    val AMETHYST_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:amethyst_block")), Unit)
    val BUDDING_AMETHYST: Material<Unit> = Material(MaterialType(Key("minecraft:budding_amethyst")), Unit)
    val AMETHYST_CLUSTER: Material<Unit> = Material(MaterialType(Key("minecraft:amethyst_cluster")), Unit)
    val LARGE_AMETHYST_BUD: Material<Unit> = Material(MaterialType(Key("minecraft:large_amethyst_bud")), Unit)
    val MEDIUM_AMETHYST_BUD: Material<Unit> = Material(MaterialType(Key("minecraft:medium_amethyst_bud")), Unit)
    val SMALL_AMETHYST_BUD: Material<Unit> = Material(MaterialType(Key("minecraft:small_amethyst_bud")), Unit)
    val TUFF: Material<Unit> = Material(MaterialType(Key("minecraft:tuff")), Unit)
    val CALCITE: Material<Unit> = Material(MaterialType(Key("minecraft:calcite")), Unit)
    val TINTED_GLASS: Material<Unit> = Material(MaterialType(Key("minecraft:tinted_glass")), Unit)
    val POWDER_SNOW: Material<Unit> = Material(MaterialType(Key("minecraft:powder_snow")), Unit)
    val SCULK_SENSOR: Material<Unit> = Material(MaterialType(Key("minecraft:sculk_sensor")), Unit)
    val OXIDIZED_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:oxidized_copper")), Unit)
    val WEATHERED_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:weathered_copper")), Unit)
    val EXPOSED_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:exposed_copper")), Unit)
    val COPPER_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:copper_block")), Unit)
    val COPPER_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:copper_ore")), Unit)
    val DEEPSLATE_COPPER_ORE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_copper_ore")), Unit)
    val OXIDIZED_CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:oxidized_cut_copper")), Unit)
    val WEATHERED_CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:weathered_cut_copper")), Unit)
    val EXPOSED_CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:exposed_cut_copper")), Unit)
    val CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:cut_copper")), Unit)
    val OXIDIZED_CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:oxidized_cut_copper_stairs")), Unit)
    val WEATHERED_CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:weathered_cut_copper_stairs")), Unit)
    val EXPOSED_CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:exposed_cut_copper_stairs")), Unit)
    val CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:cut_copper_stairs")), Unit)
    val OXIDIZED_CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:oxidized_cut_copper_slab")), Unit)
    val WEATHERED_CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:weathered_cut_copper_slab")), Unit)
    val EXPOSED_CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:exposed_cut_copper_slab")), Unit)
    val CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:cut_copper_slab")), Unit)
    val WAXED_COPPER_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_copper_block")), Unit)
    val WAXED_WEATHERED_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_weathered_copper")), Unit)
    val WAXED_EXPOSED_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_exposed_copper")), Unit)
    val WAXED_OXIDIZED_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_oxidized_copper")), Unit)
    val WAXED_OXIDIZED_CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_oxidized_cut_copper")), Unit)
    val WAXED_WEATHERED_CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_weathered_cut_copper")), Unit)
    val WAXED_EXPOSED_CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_exposed_cut_copper")), Unit)
    val WAXED_CUT_COPPER: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_cut_copper")), Unit)
    val WAXED_OXIDIZED_CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_oxidized_cut_copper_stairs")), Unit)
    val WAXED_WEATHERED_CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_weathered_cut_copper_stairs")), Unit)
    val WAXED_EXPOSED_CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_exposed_cut_copper_stairs")), Unit)
    val WAXED_CUT_COPPER_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_cut_copper_stairs")), Unit)
    val WAXED_OXIDIZED_CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_oxidized_cut_copper_slab")), Unit)
    val WAXED_WEATHERED_CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_weathered_cut_copper_slab")), Unit)
    val WAXED_EXPOSED_CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_exposed_cut_copper_slab")), Unit)
    val WAXED_CUT_COPPER_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:waxed_cut_copper_slab")), Unit)
    val LIGHTNING_ROD: Material<Unit> = Material(MaterialType(Key("minecraft:lightning_rod")), Unit)
    val POINTED_DRIPSTONE: Material<Unit> = Material(MaterialType(Key("minecraft:pointed_dripstone")), Unit)
    val DRIPSTONE_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:dripstone_block")), Unit)
    val CAVE_VINES: Material<Unit> = Material(MaterialType(Key("minecraft:cave_vines")), Unit)
    val CAVE_VINES_PLANT: Material<Unit> = Material(MaterialType(Key("minecraft:cave_vines_plant")), Unit)
    val SPORE_BLOSSOM: Material<Unit> = Material(MaterialType(Key("minecraft:spore_blossom")), Unit)
    val AZALEA: Material<Unit> = Material(MaterialType(Key("minecraft:azalea")), Unit)
    val FLOWERING_AZALEA: Material<Unit> = Material(MaterialType(Key("minecraft:flowering_azalea")), Unit)
    val MOSS_CARPET: Material<Unit> = Material(MaterialType(Key("minecraft:moss_carpet")), Unit)
    val MOSS_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:moss_block")), Unit)
    val BIG_DRIPLEAF: Material<Unit> = Material(MaterialType(Key("minecraft:big_dripleaf")), Unit)
    val BIG_DRIPLEAF_STEM: Material<Unit> = Material(MaterialType(Key("minecraft:big_dripleaf_stem")), Unit)
    val SMALL_DRIPLEAF: Material<Unit> = Material(MaterialType(Key("minecraft:small_dripleaf")), Unit)
    val HANGING_ROOTS: Material<Unit> = Material(MaterialType(Key("minecraft:hanging_roots")), Unit)
    val ROOTED_DIRT: Material<Unit> = Material(MaterialType(Key("minecraft:rooted_dirt")), Unit)
    val DEEPSLATE: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate")), Unit)
    val COBBLED_DEEPSLATE: Material<Unit> = Material(MaterialType(Key("minecraft:cobbled_deepslate")), Unit)
    val COBBLED_DEEPSLATE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:cobbled_deepslate_stairs")), Unit)
    val COBBLED_DEEPSLATE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:cobbled_deepslate_slab")), Unit)
    val COBBLED_DEEPSLATE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:cobbled_deepslate_wall")), Unit)
    val POLISHED_DEEPSLATE: Material<Unit> = Material(MaterialType(Key("minecraft:polished_deepslate")), Unit)
    val POLISHED_DEEPSLATE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:polished_deepslate_stairs")), Unit)
    val POLISHED_DEEPSLATE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:polished_deepslate_slab")), Unit)
    val POLISHED_DEEPSLATE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:polished_deepslate_wall")), Unit)
    val DEEPSLATE_TILES: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_tiles")), Unit)
    val DEEPSLATE_TILE_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_tile_stairs")), Unit)
    val DEEPSLATE_TILE_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_tile_slab")), Unit)
    val DEEPSLATE_TILE_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_tile_wall")), Unit)
    val DEEPSLATE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_bricks")), Unit)
    val DEEPSLATE_BRICK_STAIRS: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_brick_stairs")), Unit)
    val DEEPSLATE_BRICK_SLAB: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_brick_slab")), Unit)
    val DEEPSLATE_BRICK_WALL: Material<Unit> = Material(MaterialType(Key("minecraft:deepslate_brick_wall")), Unit)
    val CHISELED_DEEPSLATE: Material<Unit> = Material(MaterialType(Key("minecraft:chiseled_deepslate")), Unit)
    val CRACKED_DEEPSLATE_BRICKS: Material<Unit> = Material(MaterialType(Key("minecraft:cracked_deepslate_bricks")), Unit)
    val CRACKED_DEEPSLATE_TILES: Material<Unit> = Material(MaterialType(Key("minecraft:cracked_deepslate_tiles")), Unit)
    val INFESTED_DEEPSLATE: Material<Unit> = Material(MaterialType(Key("minecraft:infested_deepslate")), Unit)
    val SMOOTH_BASALT: Material<Unit> = Material(MaterialType(Key("minecraft:smooth_basalt")), Unit)
    val RAW_IRON_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:raw_iron_block")), Unit)
    val RAW_COPPER_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:raw_copper_block")), Unit)
    val RAW_GOLD_BLOCK: Material<Unit> = Material(MaterialType(Key("minecraft:raw_gold_block")), Unit)
    val POTTED_AZALEA_BUSH: Material<Unit> = Material(MaterialType(Key("minecraft:potted_azalea_bush")), Unit)
    val POTTED_FLOWERING_AZALEA_BUSH: Material<Unit> = Material(MaterialType(Key("minecraft:potted_flowering_azalea_bush")), Unit)
}