package kingmc.platform.material

import kingmc.platform.material.block.ChestBlock
import kingmc.platform.util.Versioned
import kingmc.util.key.Key
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * Check if this material's data is an `Chest`
 */
@OptIn(ExperimentalContracts::class)
fun <TData> TData.isChest(): Boolean {
    contract {
        returns(true) implies (this@isChest is ChestBlock )
    }
    return this@isChest is ChestBlock
}

/**
 * Check if this material is an `Air`
 */
fun Material<*>.isAir(): Boolean {
    return type.key == Key("minecraft:air")
}

/**
 * A utility class for materials
 *
 * @since 0.0.6
 * @author kingsthere
 */
object Materials {
    // Air
    val AIR: Key = Key("minecraft:air")
    // Acacia Boat
    @Versioned(minecraftVersion = "1.9..", fallback = "OAK_BOAT")
    val ACACIA_BOAT: Key = Key("minecraft:acacia_boat")
    // Amethyst Shard
    @Versioned(minecraftVersion = "1.17..")
    val AMETHYST_SHARD: Key = Key("minecraft:amethyst_shard")
    // Apple
    val APPLE: Key = Key("minecraft:apple")
    // Armor Stand
    val ARMOR_STAND: Key = Key("minecraft:armor_stand")
    // Arrow
    val ARROW: Key = Key("minecraft:arrow")
    // Bucket of Axolotl
    @Versioned(minecraftVersion = "1.17..")
    val AXOLOTL_BUCKET: Key = Key("minecraft:axolotl_bucket")
    // Axolotl Spawn Egg
    @Versioned(minecraftVersion = "1.17..")
    val AXOLOTL_SPAWN_EGG: Key = Key("minecraft:axolotl_spawn_egg")
    // Baked Potato
    val BAKED_POTATO: Key = Key("minecraft:baked_potato")
    // Bat Spawn Egg
    val BAT_SPAWN_EGG: Key = Key("minecraft:bat_spawn_egg")
    // Bee Spawn Egg
    @Versioned(minecraftVersion = "1.15..")
    val BEE_SPAWN_EGG: Key = Key("minecraft:bee_spawn_egg")
    // Raw Beef
    val BEEF: Key = Key("minecraft:beef")
    // Beetroot
    @Versioned(minecraftVersion = "1.9..")
    val BEETROOT: Key = Key("minecraft:beetroot")
    // Beetroot Seeds
    @Versioned(minecraftVersion = "1.9..")
    val BEETROOT_SEEDS: Key = Key("minecraft:beetroot_seeds")
    // Beetroot Soup
    @Versioned(minecraftVersion = "1.9..")
    val BEETROOT_SOUP: Key = Key("minecraft:beetroot_soup")
    // Birch Boat
    @Versioned(minecraftVersion = "1.9..")
    val BIRCH_BOAT: Key = Key("minecraft:birch_boat")
    // Black Dye
    @Versioned(minecraftVersion = "1.14..")
    val BLACK_DYE: Key = Key("minecraft:black_dye")
    // Blaze Powder
    val BLAZE_POWDER: Key = Key("minecraft:blaze_powder")
    // Blaze Rod
    val BLAZE_ROD: Key = Key("minecraft:blaze_rod")
    // Blaze Spawn Egg
    val BLAZE_SPAWN_EGG: Key = Key("minecraft:blaze_spawn_egg")
    // Blue Dye
    val BLUE_DYE: Key = Key("minecraft:blue_dye")
    // Bone
    val BONE: Key = Key("minecraft:bone")
    // Bone Meal
    val BONE_MEAL: Key = Key("minecraft:bone_meal")
    // Book
    val BOOK: Key = Key("minecraft:book")
    // Bow
    val BOW: Key = Key("minecraft:bow")
    // Bowl
    val BOWL: Key = Key("minecraft:bowl")
    // Bread
    val BREAD: Key = Key("minecraft:bread")
    // Brewing Stand
    val BREWING_STAND: Key = Key("minecraft:brewing_stand")
    // Brick
    val BRICK: Key = Key("minecraft:brick")
    // Brown Dye
    @Versioned(minecraftVersion = "1.14..")
    val BROWN_DYE: Key = Key("minecraft:brown_dye")
    // Bucket
    val BUCKET: Key = Key("minecraft:bucket")
    // Bundle
    val BUNDLE: Key = Key("minecraft:bundle")
    // Carrot
    val CARROT: Key = Key("minecraft:carrot")
    // Carrot on a Stick
    val CARROT_ON_A_STICK: Key = Key("minecraft:carrot_on_a_stick")
    // Cat Spawn Egg
    val CAT_SPAWN_EGG: Key = Key("minecraft:cat_spawn_egg")
    // Cauldron
    val CAULDRON: Key = Key("minecraft:cauldron")
    // Cave Spider Spawn Egg
    val CAVE_SPIDER_SPAWN_EGG: Key = Key("minecraft:cave_spider_spawn_egg")
    // Chainmail Boots
    val CHAINMAIL_BOOTS: Key = Key("minecraft:chainmail_boots")
    // Chainmail Chestplate
    val CHAINMAIL_CHESTPLATE: Key = Key("minecraft:chainmail_chestplate")
    // Chainmail Helmet
    val CHAINMAIL_HELMET: Key = Key("minecraft:chainmail_helmet")
    // Chainmail Leggings
    val CHAINMAIL_LEGGINGS: Key = Key("minecraft:chainmail_leggings")
    // Charcoal
    val CHARCOAL: Key = Key("minecraft:charcoal")
    // Minecart with Chest
    val CHEST_MINECART: Key = Key("minecraft:chest_minecart")
    // Raw Chicken
    val CHICKEN: Key = Key("minecraft:chicken")
    // Chicken Spawn Egg
    val CHICKEN_SPAWN_EGG: Key = Key("minecraft:chicken_spawn_egg")
    // Chorus Fruit
    val CHORUS_FRUIT: Key = Key("minecraft:chorus_fruit")
    // Clay Ball
    val CLAY_BALL: Key = Key("minecraft:clay_ball")
    // Clock
    val CLOCK: Key = Key("minecraft:clock")
    // Coal
    val COAL: Key = Key("minecraft:coal")
    // Cocoa Beans
    val COCOA_BEANS: Key = Key("minecraft:cocoa_beans")
    // Raw Cod
    val COD: Key = Key("minecraft:cod")
    // Bucket of Cod
    val COD_BUCKET: Key = Key("minecraft:cod_bucket")
    // Cod Spawn Egg
    val COD_SPAWN_EGG: Key = Key("minecraft:cod_spawn_egg")
    // Minecart with Command Block
    val COMMAND_BLOCK_MINECART: Key = Key("minecraft:command_block_minecart")
    // Compass
    val COMPASS: Key = Key("minecraft:compass")
    // Steak
    val COOKED_BEEF: Key = Key("minecraft:cooked_beef")
    // Cooked Chicken
    val COOKED_CHICKEN: Key = Key("minecraft:cooked_chicken")
    // Cooked Cod
    val COOKED_COD: Key = Key("minecraft:cooked_cod")
    // Cooked Mutton
    val COOKED_MUTTON: Key = Key("minecraft:cooked_mutton")
    // Cooked Porkchop
    val COOKED_PORKCHOP: Key = Key("minecraft:cooked_porkchop")
    // Cooked Rabbit
    val COOKED_RABBIT: Key = Key("minecraft:cooked_rabbit")
    // Cooked Salmon
    val COOKED_SALMON: Key = Key("minecraft:cooked_salmon")
    // Cookie
    val COOKIE: Key = Key("minecraft:cookie")
    // Copper Ingot
    val COPPER_INGOT: Key = Key("minecraft:copper_ingot")
    // Cow Spawn Egg
    val COW_SPAWN_EGG: Key = Key("minecraft:cow_spawn_egg")
    // Banner Pattern
    val CREEPER_BANNER_PATTERN: Key = Key("minecraft:creeper_banner_pattern")
    // Creeper Spawn Egg
    val CREEPER_SPAWN_EGG: Key = Key("minecraft:creeper_spawn_egg")
    // Crossbow
    val CROSSBOW: Key = Key("minecraft:crossbow")
    // Cyan Dye
    val CYAN_DYE: Key = Key("minecraft:cyan_dye")
    // Dark Oak Boat
    val DARK_OAK_BOAT: Key = Key("minecraft:dark_oak_boat")
    // Debug Stick
    val DEBUG_STICK: Key = Key("minecraft:debug_stick")
    // Diamond
    val DIAMOND: Key = Key("minecraft:diamond")
    // Diamond Axe
    val DIAMOND_AXE: Key = Key("minecraft:diamond_axe")
    // Diamond Boots
    val DIAMOND_BOOTS: Key = Key("minecraft:diamond_boots")
    // Diamond Chestplate
    val DIAMOND_CHESTPLATE: Key = Key("minecraft:diamond_chestplate")
    // Diamond Helmet
    val DIAMOND_HELMET: Key = Key("minecraft:diamond_helmet")
    // Diamond Hoe
    val DIAMOND_HOE: Key = Key("minecraft:diamond_hoe")
    // Diamond Horse Armor
    val DIAMOND_HORSE_ARMOR: Key = Key("minecraft:diamond_horse_armor")
    // Diamond Leggings
    val DIAMOND_LEGGINGS: Key = Key("minecraft:diamond_leggings")
    // Diamond Pickaxe
    val DIAMOND_PICKAXE: Key = Key("minecraft:diamond_pickaxe")
    // Diamond Shovel
    val DIAMOND_SHOVEL: Key = Key("minecraft:diamond_shovel")
    // Diamond Sword
    val DIAMOND_SWORD: Key = Key("minecraft:diamond_sword")
    // Dolphin Spawn Egg
    val DOLPHIN_SPAWN_EGG: Key = Key("minecraft:dolphin_spawn_egg")
    // Donkey Spawn Egg
    val DONKEY_SPAWN_EGG: Key = Key("minecraft:donkey_spawn_egg")
    // Dragon's Breath
    val DRAGON_BREATH: Key = Key("minecraft:dragon_breath")
    // Dried Kelp
    val DRIED_KELP: Key = Key("minecraft:dried_kelp")
    // Drowned Spawn Egg
    val DROWNED_SPAWN_EGG: Key = Key("minecraft:drowned_spawn_egg")
    // Egg
    val EGG: Key = Key("minecraft:egg")
    // Elder Guardian Spawn Egg
    val ELDER_GUARDIAN_SPAWN_EGG: Key = Key("minecraft:elder_guardian_spawn_egg")
    // Elytra
    val ELYTRA: Key = Key("minecraft:elytra")
    // Emerald
    val EMERALD: Key = Key("minecraft:emerald")
    // Enchanted Book
    val ENCHANTED_BOOK: Key = Key("minecraft:enchanted_book")
    // Enchanted Golden Apple
    val ENCHANTED_GOLDEN_APPLE: Key = Key("minecraft:enchanted_golden_apple")
    // End Crystal
    val END_CRYSTAL: Key = Key("minecraft:end_crystal")
    // Eye of Ender
    val ENDER_EYE: Key = Key("minecraft:ender_eye")
    // Ender Pearl
    val ENDER_PEARL: Key = Key("minecraft:ender_pearl")
    // Enderman Spawn Egg
    val ENDERMAN_SPAWN_EGG: Key = Key("minecraft:enderman_spawn_egg")
    // Endermite Spawn Egg
    val ENDERMITE_SPAWN_EGG: Key = Key("minecraft:endermite_spawn_egg")
    // Evoker Spawn Egg
    val EVOKER_SPAWN_EGG: Key = Key("minecraft:evoker_spawn_egg")
    // Bottle o' Enchanting
    val EXPERIENCE_BOTTLE: Key = Key("minecraft:experience_bottle")
    // Feather
    val FEATHER: Key = Key("minecraft:feather")
    // Fermented Spider Eye
    val FERMENTED_SPIDER_EYE: Key = Key("minecraft:fermented_spider_eye")
    // Map
    val FILLED_MAP: Key = Key("minecraft:filled_map")
    // Fire Charge
    val FIRE_CHARGE: Key = Key("minecraft:fire_charge")
    // Firework Rocket
    val FIREWORK_ROCKET: Key = Key("minecraft:firework_rocket")
    // Firework Star
    val FIREWORK_STAR: Key = Key("minecraft:firework_star")
    // Fishing Rod
    val FISHING_ROD: Key = Key("minecraft:fishing_rod")
    // Flint
    val FLINT: Key = Key("minecraft:flint")
    // Flint and Steel
    val FLINT_AND_STEEL: Key = Key("minecraft:flint_and_steel")
    // Banner Pattern
    val FLOWER_BANNER_PATTERN: Key = Key("minecraft:flower_banner_pattern")
    // Flower Pot
    val FLOWER_POT: Key = Key("minecraft:flower_pot")
    // Fox Spawn Egg
    val FOX_SPAWN_EGG: Key = Key("minecraft:fox_spawn_egg")
    // Minecart with Furnace
    val FURNACE_MINECART: Key = Key("minecraft:furnace_minecart")
    // Ghast Spawn Egg
    val GHAST_SPAWN_EGG: Key = Key("minecraft:ghast_spawn_egg")
    // Ghast Tear
    val GHAST_TEAR: Key = Key("minecraft:ghast_tear")
    // Glass Bottle
    val GLASS_BOTTLE: Key = Key("minecraft:glass_bottle")
    // Glistering Melon Slice
    val GLISTERING_MELON_SLICE: Key = Key("minecraft:glistering_melon_slice")
    // Banner Pattern
    val GLOBE_BANNER_PATTERN: Key = Key("minecraft:globe_banner_pattern")
    // Glow Berries
    val GLOW_BERRIES: Key = Key("minecraft:glow_berries")
    // Glow Ink Sac
    val GLOW_INK_SAC: Key = Key("minecraft:glow_ink_sac")
    // Glow Item Frame
    val GLOW_ITEM_FRAME: Key = Key("minecraft:glow_item_frame")
    // Glow Squid Spawn Egg
    val GLOW_SQUID_SPAWN_EGG: Key = Key("minecraft:glow_squid_spawn_egg")
    // Glowstone Dust
    val GLOWSTONE_DUST: Key = Key("minecraft:glowstone_dust")
    // Goat Spawn Egg
    val GOAT_SPAWN_EGG: Key = Key("minecraft:goat_spawn_egg")
    // Gold Ingot
    val GOLD_INGOT: Key = Key("minecraft:gold_ingot")
    // Gold Nugget
    val GOLD_NUGGET: Key = Key("minecraft:gold_nugget")
    // Golden Apple
    val GOLDEN_APPLE: Key = Key("minecraft:golden_apple")
    // Golden Axe
    val GOLDEN_AXE: Key = Key("minecraft:golden_axe")
    // Golden Boots
    val GOLDEN_BOOTS: Key = Key("minecraft:golden_boots")
    // Golden Carrot
    val GOLDEN_CARROT: Key = Key("minecraft:golden_carrot")
    // Golden Chestplate
    val GOLDEN_CHESTPLATE: Key = Key("minecraft:golden_chestplate")
    // Golden Helmet
    val GOLDEN_HELMET: Key = Key("minecraft:golden_helmet")
    // Golden Hoe
    val GOLDEN_HOE: Key = Key("minecraft:golden_hoe")
    // Golden Horse Armor
    val GOLDEN_HORSE_ARMOR: Key = Key("minecraft:golden_horse_armor")
    // Golden Leggings
    val GOLDEN_LEGGINGS: Key = Key("minecraft:golden_leggings")
    // Golden Pickaxe
    val GOLDEN_PICKAXE: Key = Key("minecraft:golden_pickaxe")
    // Golden Shovel
    val GOLDEN_SHOVEL: Key = Key("minecraft:golden_shovel")
    // Golden Sword
    val GOLDEN_SWORD: Key = Key("minecraft:golden_sword")
    // Gray Dye
    val GRAY_DYE: Key = Key("minecraft:gray_dye")
    // Green Dye
    val GREEN_DYE: Key = Key("minecraft:green_dye")
    // Guardian Spawn Egg
    val GUARDIAN_SPAWN_EGG: Key = Key("minecraft:guardian_spawn_egg")
    // Gunpowder
    val GUNPOWDER: Key = Key("minecraft:gunpowder")
    // Heart of the Sea
    val HEART_OF_THE_SEA: Key = Key("minecraft:heart_of_the_sea")
    // Hoglin Spawn Egg
    val HOGLIN_SPAWN_EGG: Key = Key("minecraft:hoglin_spawn_egg")
    // Honey Bottle
    val HONEY_BOTTLE: Key = Key("minecraft:honey_bottle")
    // Honeycomb
    val HONEYCOMB: Key = Key("minecraft:honeycomb")
    // Minecart with Hopper
    val HOPPER_MINECART: Key = Key("minecraft:hopper_minecart")
    // Horse Spawn Egg
    val HORSE_SPAWN_EGG: Key = Key("minecraft:horse_spawn_egg")
    // Husk Spawn Egg
    val HUSK_SPAWN_EGG: Key = Key("minecraft:husk_spawn_egg")
    // Ink Sac
    val INK_SAC: Key = Key("minecraft:ink_sac")
    // Iron Axe
    val IRON_AXE: Key = Key("minecraft:iron_axe")
    // Iron Boots
    val IRON_BOOTS: Key = Key("minecraft:iron_boots")
    // Iron Chestplate
    val IRON_CHESTPLATE: Key = Key("minecraft:iron_chestplate")
    // Iron Helmet
    val IRON_HELMET: Key = Key("minecraft:iron_helmet")
    // Iron Hoe
    val IRON_HOE: Key = Key("minecraft:iron_hoe")
    // Iron Horse Armor
    val IRON_HORSE_ARMOR: Key = Key("minecraft:iron_horse_armor")
    // Iron Ingot
    val IRON_INGOT: Key = Key("minecraft:iron_ingot")
    // Iron Leggings
    val IRON_LEGGINGS: Key = Key("minecraft:iron_leggings")
    // Iron Nugget
    val IRON_NUGGET: Key = Key("minecraft:iron_nugget")
    // Iron Pickaxe
    val IRON_PICKAXE: Key = Key("minecraft:iron_pickaxe")
    // Iron Shovel
    val IRON_SHOVEL: Key = Key("minecraft:iron_shovel")
    // Iron Sword
    val IRON_SWORD: Key = Key("minecraft:iron_sword")
    // Item Frame
    val ITEM_FRAME: Key = Key("minecraft:item_frame")
    // Jungle Boat
    val JUNGLE_BOAT: Key = Key("minecraft:jungle_boat")
    // Knowledge Book
    val KNOWLEDGE_BOOK: Key = Key("minecraft:knowledge_book")
    // Lapis Lazuli
    val LAPIS_LAZULI: Key = Key("minecraft:lapis_lazuli")
    // Lava Bucket
    val LAVA_BUCKET: Key = Key("minecraft:lava_bucket")
    // Lead
    val LEAD: Key = Key("minecraft:lead")
    // Leather
    val LEATHER: Key = Key("minecraft:leather")
    // Leather Boots
    val LEATHER_BOOTS: Key = Key("minecraft:leather_boots")
    // Leather Tunic
    val LEATHER_CHESTPLATE: Key = Key("minecraft:leather_chestplate")
    // Leather Cap
    val LEATHER_HELMET: Key = Key("minecraft:leather_helmet")
    // Leather Horse Armor
    val LEATHER_HORSE_ARMOR: Key = Key("minecraft:leather_horse_armor")
    // Leather Pants
    val LEATHER_LEGGINGS: Key = Key("minecraft:leather_leggings")
    // Light Blue Dye
    val LIGHT_BLUE_DYE: Key = Key("minecraft:light_blue_dye")
    // Light Gray Dye
    val LIGHT_GRAY_DYE: Key = Key("minecraft:light_gray_dye")
    // Lime Dye
    val LIME_DYE: Key = Key("minecraft:lime_dye")
    // Lingering Potion
    val LINGERING_POTION: Key = Key("minecraft:lingering_potion")
    // Llama Spawn Egg
    val LLAMA_SPAWN_EGG: Key = Key("minecraft:llama_spawn_egg")
    // Lodestone Compass
    val LODESTONE_COMPASS: Key = Key("minecraft:lodestone_compass")
    // Magenta Dye
    val MAGENTA_DYE: Key = Key("minecraft:magenta_dye")
    // Magma Cream
    val MAGMA_CREAM: Key = Key("minecraft:magma_cream")
    // Magma Cube Spawn Egg
    val MAGMA_CUBE_SPAWN_EGG: Key = Key("minecraft:magma_cube_spawn_egg")
    // Empty Map
    val MAP: Key = Key("minecraft:map")
    // Melon Seeds
    val MELON_SEEDS: Key = Key("minecraft:melon_seeds")
    // Melon Slice
    val MELON_SLICE: Key = Key("minecraft:melon_slice")
    // Milk Bucket
    val MILK_BUCKET: Key = Key("minecraft:milk_bucket")
    // Minecart
    val MINECART: Key = Key("minecraft:minecart")
    // Banner Pattern
    val MOJANG_BANNER_PATTERN: Key = Key("minecraft:mojang_banner_pattern")
    // Mooshroom Spawn Egg
    val MOOSHROOM_SPAWN_EGG: Key = Key("minecraft:mooshroom_spawn_egg")
    // Mule Spawn Egg
    val MULE_SPAWN_EGG: Key = Key("minecraft:mule_spawn_egg")
    // Mushroom Stew
    val MUSHROOM_STEW: Key = Key("minecraft:mushroom_stew")
    // Music Disc
    val MUSIC_DISC_BLOCKS: Key = Key("minecraft:music_disc_blocks")
    // Music Disc
    val MUSIC_DISC_CAT: Key = Key("minecraft:music_disc_cat")
    // Music Disc
    val MUSIC_DISC_CHIRP: Key = Key("minecraft:music_disc_chirp")
    // Music Disc
    val MUSIC_DISC_FAR: Key = Key("minecraft:music_disc_far")
    // Music Disc
    val MUSIC_DISC_MALL: Key = Key("minecraft:music_disc_mall")
    // Music Disc
    val MUSIC_DISC_MELLOHI: Key = Key("minecraft:music_disc_mellohi")
    // Music Disc
    val MUSIC_DISC_OTHERSIDE: Key = Key("minecraft:music_disc_otherside")
    // Music Disc
    val MUSIC_DISC_PIGSTEP: Key = Key("minecraft:music_disc_pigstep")
    // Music Disc
    val MUSIC_DISC_STAL: Key = Key("minecraft:music_disc_stal")
    // Music Disc
    val MUSIC_DISC_STRAD: Key = Key("minecraft:music_disc_strad")
    // Music Disc
    val MUSIC_DISC_WAIT: Key = Key("minecraft:music_disc_wait")
    // Music Disc
    val MUSIC_DISC_WARD: Key = Key("minecraft:music_disc_ward")
    // Raw Mutton
    val MUTTON: Key = Key("minecraft:mutton")
    // Name Tag
    val NAME_TAG: Key = Key("minecraft:name_tag")
    // Nautilus Shell
    val NAUTILUS_SHELL: Key = Key("minecraft:nautilus_shell")
    // Nether Brick
    val NETHER_BRICK: Key = Key("minecraft:nether_brick")
    // Nether Star
    val NETHER_STAR: Key = Key("minecraft:nether_star")
    // Nether Wart
    val NETHER_WART: Key = Key("minecraft:nether_wart")
    // Netherite Axe
    val NETHERITE_AXE: Key = Key("minecraft:netherite_axe")
    // Netherite Boots
    val NETHERITE_BOOTS: Key = Key("minecraft:netherite_boots")
    // Netherite Chestplate
    val NETHERITE_CHESTPLATE: Key = Key("minecraft:netherite_chestplate")
    // Netherite Helmet
    val NETHERITE_HELMET: Key = Key("minecraft:netherite_helmet")
    // Netherite Hoe
    val NETHERITE_HOE: Key = Key("minecraft:netherite_hoe")
    // Netherite Ingot
    val NETHERITE_INGOT: Key = Key("minecraft:netherite_ingot")
    // Netherite Leggings
    val NETHERITE_LEGGINGS: Key = Key("minecraft:netherite_leggings")
    // Netherite Pickaxe
    val NETHERITE_PICKAXE: Key = Key("minecraft:netherite_pickaxe")
    // Netherite Scrap
    val NETHERITE_SCRAP: Key = Key("minecraft:netherite_scrap")
    // Netherite Shovel
    val NETHERITE_SHOVEL: Key = Key("minecraft:netherite_shovel")
    // Netherite Sword
    val NETHERITE_SWORD: Key = Key("minecraft:netherite_sword")
    // Oak Boat
    val OAK_BOAT: Key = Key("minecraft:oak_boat")
    // Ocelot Spawn Egg
    val OCELOT_SPAWN_EGG: Key = Key("minecraft:ocelot_spawn_egg")
    // Orange Dye
    val ORANGE_DYE: Key = Key("minecraft:orange_dye")
    // Painting
    val PAINTING: Key = Key("minecraft:painting")
    // Panda Spawn Egg
    val PANDA_SPAWN_EGG: Key = Key("minecraft:panda_spawn_egg")
    // Paper
    val PAPER: Key = Key("minecraft:paper")
    // Parrot Spawn Egg
    val PARROT_SPAWN_EGG: Key = Key("minecraft:parrot_spawn_egg")
    // Phantom Membrane
    val PHANTOM_MEMBRANE: Key = Key("minecraft:phantom_membrane")
    // Phantom Spawn Egg
    val PHANTOM_SPAWN_EGG: Key = Key("minecraft:phantom_spawn_egg")
    // Pig Spawn Egg
    val PIG_SPAWN_EGG: Key = Key("minecraft:pig_spawn_egg")
    // Banner Pattern
    val PIGLIN_BANNER_PATTERN: Key = Key("minecraft:piglin_banner_pattern")
    // Piglin Brute Spawn Egg
    val PIGLIN_BRUTE_SPAWN_EGG: Key = Key("minecraft:piglin_brute_spawn_egg")
    // Piglin Spawn Egg
    val PIGLIN_SPAWN_EGG: Key = Key("minecraft:piglin_spawn_egg")
    // Pillager Spawn Egg
    val PILLAGER_SPAWN_EGG: Key = Key("minecraft:pillager_spawn_egg")
    // Pink Dye
    val PINK_DYE: Key = Key("minecraft:pink_dye")
    // Poisonous Potato
    val POISONOUS_POTATO: Key = Key("minecraft:poisonous_potato")
    // Polar Bear Spawn Egg
    val POLAR_BEAR_SPAWN_EGG: Key = Key("minecraft:polar_bear_spawn_egg")
    // Popped Chorus Fruit
    val POPPED_CHORUS_FRUIT: Key = Key("minecraft:popped_chorus_fruit")
    // Raw Porkchop
    val PORKCHOP: Key = Key("minecraft:porkchop")
    // Potato
    val POTATO: Key = Key("minecraft:potato")
    // Potion
    val POTION: Key = Key("minecraft:potion")
    // Powder Snow Bucket
    val POWDER_SNOW_BUCKET: Key = Key("minecraft:powder_snow_bucket")
    // Prismarine Crystals
    val PRISMARINE_CRYSTALS: Key = Key("minecraft:prismarine_crystals")
    // Prismarine Shard
    val PRISMARINE_SHARD: Key = Key("minecraft:prismarine_shard")
    // Pufferfish
    val PUFFERFISH: Key = Key("minecraft:pufferfish")
    // Bucket of Pufferfish
    val PUFFERFISH_BUCKET: Key = Key("minecraft:pufferfish_bucket")
    // Pufferfish Spawn Egg
    val PUFFERFISH_SPAWN_EGG: Key = Key("minecraft:pufferfish_spawn_egg")
    // Pumpkin Pie
    val PUMPKIN_PIE: Key = Key("minecraft:pumpkin_pie")
    // Pumpkin Seeds
    val PUMPKIN_SEEDS: Key = Key("minecraft:pumpkin_seeds")
    // Purple Dye
    val PURPLE_DYE: Key = Key("minecraft:purple_dye")
    // Nether Quartz
    val QUARTZ: Key = Key("minecraft:quartz")
    // Raw Rabbit
    val RABBIT: Key = Key("minecraft:rabbit")
    // Rabbit's Foot
    val RABBIT_FOOT: Key = Key("minecraft:rabbit_foot")
    // Rabbit Hide
    val RABBIT_HIDE: Key = Key("minecraft:rabbit_hide")
    // Rabbit Spawn Egg
    val RABBIT_SPAWN_EGG: Key = Key("minecraft:rabbit_spawn_egg")
    // Rabbit Stew
    val RABBIT_STEW: Key = Key("minecraft:rabbit_stew")
    // Ravager Spawn Egg
    val RAVAGER_SPAWN_EGG: Key = Key("minecraft:ravager_spawn_egg")
    // Raw Copper
    val RAW_COPPER: Key = Key("minecraft:raw_copper")
    // Raw Gold
    val RAW_GOLD: Key = Key("minecraft:raw_gold")
    // Raw Iron
    val RAW_IRON: Key = Key("minecraft:raw_iron")
    // Red Dye
    val RED_DYE: Key = Key("minecraft:red_dye")
    // Redstone Dust
    val REDSTONE: Key = Key("minecraft:redstone")
    // Rotten Flesh
    val ROTTEN_FLESH: Key = Key("minecraft:rotten_flesh")
    // Saddle
    val SADDLE: Key = Key("minecraft:saddle")
    // Raw Salmon
    val SALMON: Key = Key("minecraft:salmon")
    // Bucket of Salmon
    val SALMON_BUCKET: Key = Key("minecraft:salmon_bucket")
    // Salmon Spawn Egg
    val SALMON_SPAWN_EGG: Key = Key("minecraft:salmon_spawn_egg")
    // Scute
    val SCUTE: Key = Key("minecraft:scute")
    // Shears
    val SHEARS: Key = Key("minecraft:shears")
    // Sheep Spawn Egg
    val SHEEP_SPAWN_EGG: Key = Key("minecraft:sheep_spawn_egg")
    // Shield
    val SHIELD: Key = Key("minecraft:shield")
    // Shulker Shell
    val SHULKER_SHELL: Key = Key("minecraft:shulker_shell")
    // Shulker Spawn Egg
    val SHULKER_SPAWN_EGG: Key = Key("minecraft:shulker_spawn_egg")
    // Sign
    val SIGN: Key = Key("minecraft:sign")
    // Silverfish Spawn Egg
    val SILVERFISH_SPAWN_EGG: Key = Key("minecraft:silverfish_spawn_egg")
    // Skeleton Horse Spawn Egg
    val SKELETON_HORSE_SPAWN_EGG: Key = Key("minecraft:skeleton_horse_spawn_egg")
    // Skeleton Spawn Egg
    val SKELETON_SPAWN_EGG: Key = Key("minecraft:skeleton_spawn_egg")
    // Banner Pattern
    val SKULL_BANNER_PATTERN: Key = Key("minecraft:skull_banner_pattern")
    // Slimeball
    val SLIME_BALL: Key = Key("minecraft:slime_ball")
    // Slime Spawn Egg
    val SLIME_SPAWN_EGG: Key = Key("minecraft:slime_spawn_egg")
    // Snowball
    val SNOWBALL: Key = Key("minecraft:snowball")
    // Spectral Arrow
    val SPECTRAL_ARROW: Key = Key("minecraft:spectral_arrow")
    // Spider Eye
    val SPIDER_EYE: Key = Key("minecraft:spider_eye")
    // Spider Spawn Egg
    val SPIDER_SPAWN_EGG: Key = Key("minecraft:spider_spawn_egg")
    // Splash Potion
    val SPLASH_POTION: Key = Key("minecraft:splash_potion")
    // Spruce Boat
    val SPRUCE_BOAT: Key = Key("minecraft:spruce_boat")
    // Spyglass
    val SPYGLASS: Key = Key("minecraft:spyglass")
    // Squid Spawn Egg
    val SQUID_SPAWN_EGG: Key = Key("minecraft:squid_spawn_egg")
    // Stick
    val STICK: Key = Key("minecraft:stick")
    // Stone Axe
    val STONE_AXE: Key = Key("minecraft:stone_axe")
    // Stone Hoe
    val STONE_HOE: Key = Key("minecraft:stone_hoe")
    // Stone Pickaxe
    val STONE_PICKAXE: Key = Key("minecraft:stone_pickaxe")
    // Stone Shovel
    val STONE_SHOVEL: Key = Key("minecraft:stone_shovel")
    // Stone Sword
    val STONE_SWORD: Key = Key("minecraft:stone_sword")
    // Stray Spawn Egg
    val STRAY_SPAWN_EGG: Key = Key("minecraft:stray_spawn_egg")
    // Strider Spawn Egg
    val STRIDER_SPAWN_EGG: Key = Key("minecraft:strider_spawn_egg")
    // String
    val STRING: Key = Key("minecraft:string")
    // Sugar
    val SUGAR: Key = Key("minecraft:sugar")
    // Suspicious Stew
    val SUSPICIOUS_STEW: Key = Key("minecraft:suspicious_stew")
    // Sweet Berries
    val SWEET_BERRIES: Key = Key("minecraft:sweet_berries")
    // Tipped Arrow
    val TIPPED_ARROW: Key = Key("minecraft:tipped_arrow")
    // Minecart with TNT
    val TNT_MINECART: Key = Key("minecraft:tnt_minecart")
    // Totem of Undying
    val TOTEM_OF_UNDYING: Key = Key("minecraft:totem_of_undying")
    // Trader Llama Spawn Egg
    val TRADER_LLAMA_SPAWN_EGG: Key = Key("minecraft:trader_llama_spawn_egg")
    // Trident
    val TRIDENT: Key = Key("minecraft:trident")
    // Tropical Fish
    val TROPICAL_FISH: Key = Key("minecraft:tropical_fish")
    // Bucket of Tropical Fish
    val TROPICAL_FISH_BUCKET: Key = Key("minecraft:tropical_fish_bucket")
    // Tropical Fish Spawn Egg
    val TROPICAL_FISH_SPAWN_EGG: Key = Key("minecraft:tropical_fish_spawn_egg")
    // Turtle Shell
    val TURTLE_HELMET: Key = Key("minecraft:turtle_helmet")
    // Turtle Spawn Egg
    val TURTLE_SPAWN_EGG: Key = Key("minecraft:turtle_spawn_egg")
    // Vex Spawn Egg
    @Versioned(minecraftVersion = "1.11..")
    val VEX_SPAWN_EGG: Key = Key("minecraft:vex_spawn_egg")
    // Villager Spawn Egg
    val VILLAGER_SPAWN_EGG: Key = Key("minecraft:villager_spawn_egg")
    // Vindicator Spawn Egg
    @Versioned(minecraftVersion = "1.11..")
    val VINDICATOR_SPAWN_EGG: Key = Key("minecraft:vindicator_spawn_egg")
    // Wandering Trader Spawn Egg
    @Versioned(minecraftVersion = "1.14..")
    val WANDERING_TRADER_SPAWN_EGG: Key = Key("minecraft:wandering_trader_spawn_egg")
    // Warped Fungus on a Stick
    @Versioned(minecraftVersion = "1.16..")
    val WARPED_FUNGUS_ON_A_STICK: Key = Key("minecraft:warped_fungus_on_a_stick")
    // Water Bucket
    val WATER_BUCKET: Key = Key("minecraft:water_bucket")
    // Wheat
    val WHEAT: Key = Key("minecraft:wheat")
    // Wheat Seeds
    val WHEAT_SEEDS: Key = Key("minecraft:wheat_seeds")
    // White Dye
    val WHITE_DYE: Key = Key("minecraft:white_dye")
    // Witch Spawn Egg
    val WITCH_SPAWN_EGG: Key = Key("minecraft:witch_spawn_egg")
    // Wither Skeleton Spawn Egg
    val WITHER_SKELETON_SPAWN_EGG: Key = Key("minecraft:wither_skeleton_spawn_egg")
    // Wolf Spawn Egg
    val WOLF_SPAWN_EGG: Key = Key("minecraft:wolf_spawn_egg")
    // Wooden Axe
    val WOODEN_AXE: Key = Key("minecraft:wooden_axe")
    // Wooden Hoe
    val WOODEN_HOE: Key = Key("minecraft:wooden_hoe")
    // Wooden Pickaxe
    val WOODEN_PICKAXE: Key = Key("minecraft:wooden_pickaxe")
    // Wooden Shovel
    val WOODEN_SHOVEL: Key = Key("minecraft:wooden_shovel")
    // Wooden Sword
    val WOODEN_SWORD: Key = Key("minecraft:wooden_sword")
    // Book and Quill
    val WRITABLE_BOOK: Key = Key("minecraft:writable_book")
    // Written Book
    val WRITTEN_BOOK: Key = Key("minecraft:written_book")
    // Yellow Dye
    val YELLOW_DYE: Key = Key("minecraft:yellow_dye")
    // Zoglin Spawn Egg
    val ZOGLIN_SPAWN_EGG: Key = Key("minecraft:zoglin_spawn_egg")
    // Zombie Horse Spawn Egg
    val ZOMBIE_HORSE_SPAWN_EGG: Key = Key("minecraft:zombie_horse_spawn_egg")
    // Zombie Spawn Egg
    val ZOMBIE_SPAWN_EGG: Key = Key("minecraft:zombie_spawn_egg")
    // Zombie Villager Spawn Egg
    val ZOMBIE_VILLAGER_SPAWN_EGG: Key = Key("minecraft:zombie_villager_spawn_egg")
    // Zombified Piglin Spawn Egg
    val ZOMBIFIED_PIGLIN_SPAWN_EGG: Key = Key("minecraft:zombified_piglin_spawn_egg")

    // Block materials:
    val STONE: Key = Key("minecraft:stone")
    val GRANITE: Key = Key("minecraft:granite")
    val POLISHED_GRANITE: Key = Key("minecraft:polished_granite")
    val DIORITE: Key = Key("minecraft:diorite")
    val POLISHED_DIORITE: Key = Key("minecraft:polished_diorite")
    val ANDESITE: Key = Key("minecraft:andesite")
    val POLISHED_ANDESITE: Key = Key("minecraft:polished_andesite")
    val GRASS_BLOCK: Key = Key("minecraft:grass_block")
    val DIRT: Key = Key("minecraft:dirt")
    val COARSE_DIRT: Key = Key("minecraft:coarse_dirt")
    val PODZOL: Key = Key("minecraft:podzol")
    val COBBLESTONE: Key = Key("minecraft:cobblestone")
    val OAK_PLANKS: Key = Key("minecraft:oak_planks")
    val SPRUCE_PLANKS: Key = Key("minecraft:spruce_planks")
    val BIRCH_PLANKS: Key = Key("minecraft:birch_planks")
    val JUNGLE_PLANKS: Key = Key("minecraft:jungle_planks")
    val ACACIA_PLANKS: Key = Key("minecraft:acacia_planks")
    val DARK_OAK_PLANKS: Key = Key("minecraft:dark_oak_planks")
    val OAK_SAPLING: Key = Key("minecraft:oak_sapling")
    val SPRUCE_SAPLING: Key = Key("minecraft:spruce_sapling")
    val BIRCH_SAPLING: Key = Key("minecraft:birch_sapling")
    val JUNGLE_SAPLING: Key = Key("minecraft:jungle_sapling")
    val ACACIA_SAPLING: Key = Key("minecraft:acacia_sapling")
    val DARK_OAK_SAPLING: Key = Key("minecraft:dark_oak_sapling")
    val BEDROCK: Key = Key("minecraft:bedrock")
    val WATER: Key = Key("minecraft:water")
    val LAVA: Key = Key("minecraft:lava")
    val SAND: Key = Key("minecraft:sand")
    val RED_SAND: Key = Key("minecraft:red_sand")
    val GRAVEL: Key = Key("minecraft:gravel")
    val GOLD_ORE: Key = Key("minecraft:gold_ore")
    val DEEPSLATE_GOLD_ORE: Key = Key("minecraft:deepslate_gold_ore")
    val IRON_ORE: Key = Key("minecraft:iron_ore")
    val DEEPSLATE_IRON_ORE: Key = Key("minecraft:deepslate_iron_ore")
    val COAL_ORE: Key = Key("minecraft:coal_ore")
    val DEEPSLATE_COAL_ORE: Key = Key("minecraft:deepslate_coal_ore")
    val NETHER_GOLD_ORE: Key = Key("minecraft:nether_gold_ore")
    val OAK_LOG: Key = Key("minecraft:oak_log")
    val SPRUCE_LOG: Key = Key("minecraft:spruce_log")
    val BIRCH_LOG: Key = Key("minecraft:birch_log")
    val JUNGLE_LOG: Key = Key("minecraft:jungle_log")
    val ACACIA_LOG: Key = Key("minecraft:acacia_log")
    val DARK_OAK_LOG: Key = Key("minecraft:dark_oak_log")
    val STRIPPED_SPRUCE_LOG: Key = Key("minecraft:stripped_spruce_log")
    val STRIPPED_BIRCH_LOG: Key = Key("minecraft:stripped_birch_log")
    val STRIPPED_JUNGLE_LOG: Key = Key("minecraft:stripped_jungle_log")
    val STRIPPED_ACACIA_LOG: Key = Key("minecraft:stripped_acacia_log")
    val STRIPPED_DARK_OAK_LOG: Key = Key("minecraft:stripped_dark_oak_log")
    val STRIPPED_OAK_LOG: Key = Key("minecraft:stripped_oak_log")
    val OAK_WOOD: Key = Key("minecraft:oak_wood")
    val SPRUCE_WOOD: Key = Key("minecraft:spruce_wood")
    val BIRCH_WOOD: Key = Key("minecraft:birch_wood")
    val JUNGLE_WOOD: Key = Key("minecraft:jungle_wood")
    val ACACIA_WOOD: Key = Key("minecraft:acacia_wood")
    val DARK_OAK_WOOD: Key = Key("minecraft:dark_oak_wood")
    val STRIPPED_OAK_WOOD: Key = Key("minecraft:stripped_oak_wood")
    val STRIPPED_SPRUCE_WOOD: Key = Key("minecraft:stripped_spruce_wood")
    val STRIPPED_BIRCH_WOOD: Key = Key("minecraft:stripped_birch_wood")
    val STRIPPED_JUNGLE_WOOD: Key = Key("minecraft:stripped_jungle_wood")
    val STRIPPED_ACACIA_WOOD: Key = Key("minecraft:stripped_acacia_wood")
    val STRIPPED_DARK_OAK_WOOD: Key = Key("minecraft:stripped_dark_oak_wood")
    val OAK_LEAVES: Key = Key("minecraft:oak_leaves")
    val SPRUCE_LEAVES: Key = Key("minecraft:spruce_leaves")
    val BIRCH_LEAVES: Key = Key("minecraft:birch_leaves")
    val JUNGLE_LEAVES: Key = Key("minecraft:jungle_leaves")
    val ACACIA_LEAVES: Key = Key("minecraft:acacia_leaves")
    val DARK_OAK_LEAVES: Key = Key("minecraft:dark_oak_leaves")
    val AZALEA_LEAVES: Key = Key("minecraft:azalea_leaves")
    val FLOWERING_AZALEA_LEAVES: Key = Key("minecraft:flowering_azalea_leaves")
    val SPONGE: Key = Key("minecraft:sponge")
    val WET_SPONGE: Key = Key("minecraft:wet_sponge")
    val GLASS: Key = Key("minecraft:glass")
    val LAPIS_ORE: Key = Key("minecraft:lapis_ore")
    val DEEPSLATE_LAPIS_ORE: Key = Key("minecraft:deepslate_lapis_ore")
    val LAPIS_BLOCK: Key = Key("minecraft:lapis_block")
    val DISPENSER: Key = Key("minecraft:dispenser")
    val SANDSTONE: Key = Key("minecraft:sandstone")
    val CHISELED_SANDSTONE: Key = Key("minecraft:chiseled_sandstone")
    val CUT_SANDSTONE: Key = Key("minecraft:cut_sandstone")
    val NOTE_BLOCK: Key = Key("minecraft:note_block")
    val WHITE_BED: Key = Key("minecraft:white_bed")
    val ORANGE_BED: Key = Key("minecraft:orange_bed")
    val MAGENTA_BED: Key = Key("minecraft:magenta_bed")
    val LIGHT_BLUE_BED: Key = Key("minecraft:light_blue_bed")
    val YELLOW_BED: Key = Key("minecraft:yellow_bed")
    val LIME_BED: Key = Key("minecraft:lime_bed")
    val PINK_BED: Key = Key("minecraft:pink_bed")
    val GRAY_BED: Key = Key("minecraft:gray_bed")
    val LIGHT_GRAY_BED: Key = Key("minecraft:light_gray_bed")
    val CYAN_BED: Key = Key("minecraft:cyan_bed")
    val PURPLE_BED: Key = Key("minecraft:purple_bed")
    val BLUE_BED: Key = Key("minecraft:blue_bed")
    val BROWN_BED: Key = Key("minecraft:brown_bed")
    val GREEN_BED: Key = Key("minecraft:green_bed")
    val RED_BED: Key = Key("minecraft:red_bed")
    val BLACK_BED: Key = Key("minecraft:black_bed")
    val POWERED_RAIL: Key = Key("minecraft:powered_rail")
    val DETECTOR_RAIL: Key = Key("minecraft:detector_rail")
    val STICKY_PISTON: Key = Key("minecraft:sticky_piston")
    val COBWEB: Key = Key("minecraft:cobweb")
    val GRASS: Key = Key("minecraft:grass")
    val FERN: Key = Key("minecraft:fern")
    val DEAD_BUSH: Key = Key("minecraft:dead_bush")
    val SEAGRASS: Key = Key("minecraft:seagrass")
    val TALL_SEAGRASS: Key = Key("minecraft:tall_seagrass")
    val PISTON: Key = Key("minecraft:piston")
    val PISTON_HEAD: Key = Key("minecraft:piston_head")
    val WHITE_WOOL: Key = Key("minecraft:white_wool")
    val ORANGE_WOOL: Key = Key("minecraft:orange_wool")
    val MAGENTA_WOOL: Key = Key("minecraft:magenta_wool")
    val LIGHT_BLUE_WOOL: Key = Key("minecraft:light_blue_wool")
    val YELLOW_WOOL: Key = Key("minecraft:yellow_wool")
    val LIME_WOOL: Key = Key("minecraft:lime_wool")
    val PINK_WOOL: Key = Key("minecraft:pink_wool")
    val GRAY_WOOL: Key = Key("minecraft:gray_wool")
    val LIGHT_GRAY_WOOL: Key = Key("minecraft:light_gray_wool")
    val CYAN_WOOL: Key = Key("minecraft:cyan_wool")
    val PURPLE_WOOL: Key = Key("minecraft:purple_wool")
    val BLUE_WOOL: Key = Key("minecraft:blue_wool")
    val BROWN_WOOL: Key = Key("minecraft:brown_wool")
    val GREEN_WOOL: Key = Key("minecraft:green_wool")
    val RED_WOOL: Key = Key("minecraft:red_wool")
    val BLACK_WOOL: Key = Key("minecraft:black_wool")
    val MOVING_PISTON: Key = Key("minecraft:moving_piston")
    val DANDELION: Key = Key("minecraft:dandelion")
    val POPPY: Key = Key("minecraft:poppy")
    val BLUE_ORCHID: Key = Key("minecraft:blue_orchid")
    val ALLIUM: Key = Key("minecraft:allium")
    val AZURE_BLUET: Key = Key("minecraft:azure_bluet")
    val RED_TULIP: Key = Key("minecraft:red_tulip")
    val ORANGE_TULIP: Key = Key("minecraft:orange_tulip")
    val WHITE_TULIP: Key = Key("minecraft:white_tulip")
    val PINK_TULIP: Key = Key("minecraft:pink_tulip")
    val OXEYE_DAISY: Key = Key("minecraft:oxeye_daisy")
    val CORNFLOWER: Key = Key("minecraft:cornflower")
    val WITHER_ROSE: Key = Key("minecraft:wither_rose")
    val LILY_OF_THE_VALLEY: Key = Key("minecraft:lily_of_the_valley")
    val BROWN_MUSHROOM: Key = Key("minecraft:brown_mushroom")
    val RED_MUSHROOM: Key = Key("minecraft:red_mushroom")
    val GOLD_BLOCK: Key = Key("minecraft:gold_block")
    val IRON_BLOCK: Key = Key("minecraft:iron_block")
    val BRICKS: Key = Key("minecraft:bricks")
    val TNT: Key = Key("minecraft:tnt")
    val BOOKSHELF: Key = Key("minecraft:bookshelf")
    val MOSSY_COBBLESTONE: Key = Key("minecraft:mossy_cobblestone")
    val OBSIDIAN: Key = Key("minecraft:obsidian")
    val TORCH: Key = Key("minecraft:torch")
    val WALL_TORCH: Key = Key("minecraft:wall_torch")
    val FIRE: Key = Key("minecraft:fire")
    val SOUL_FIRE: Key = Key("minecraft:soul_fire")
    val SPAWNER: Key = Key("minecraft:spawner")
    val OAK_STAIRS: Key = Key("minecraft:oak_stairs")
    val CHEST: Key = Key("minecraft:chest")
    val REDSTONE_WIRE: Key = Key("minecraft:redstone_wire")
    val DIAMOND_ORE: Key = Key("minecraft:diamond_ore")
    val DEEPSLATE_DIAMOND_ORE: Key = Key("minecraft:deepslate_diamond_ore")
    val DIAMOND_BLOCK: Key = Key("minecraft:diamond_block")
    val CRAFTING_TABLE: Key = Key("minecraft:crafting_table")
    val FARMLAND: Key = Key("minecraft:farmland")
    val FURNACE: Key = Key("minecraft:furnace")
    val OAK_SIGN: Key = Key("minecraft:oak_sign")
    val SPRUCE_SIGN: Key = Key("minecraft:spruce_sign")
    val BIRCH_SIGN: Key = Key("minecraft:birch_sign")
    val ACACIA_SIGN: Key = Key("minecraft:acacia_sign")
    val JUNGLE_SIGN: Key = Key("minecraft:jungle_sign")
    val DARK_OAK_SIGN: Key = Key("minecraft:dark_oak_sign")
    val OAK_DOOR: Key = Key("minecraft:oak_door")
    val LADDER: Key = Key("minecraft:ladder")
    val RAIL: Key = Key("minecraft:rail")
    val COBBLESTONE_STAIRS: Key = Key("minecraft:cobblestone_stairs")
    val OAK_WALL_SIGN: Key = Key("minecraft:oak_wall_sign")
    val SPRUCE_WALL_SIGN: Key = Key("minecraft:spruce_wall_sign")
    val BIRCH_WALL_SIGN: Key = Key("minecraft:birch_wall_sign")
    val ACACIA_WALL_SIGN: Key = Key("minecraft:acacia_wall_sign")
    val JUNGLE_WALL_SIGN: Key = Key("minecraft:jungle_wall_sign")
    val DARK_OAK_WALL_SIGN: Key = Key("minecraft:dark_oak_wall_sign")
    val LEVER: Key = Key("minecraft:lever")
    val STONE_PRESSURE_PLATE: Key = Key("minecraft:stone_pressure_plate")
    val IRON_DOOR: Key = Key("minecraft:iron_door")
    val OAK_PRESSURE_PLATE: Key = Key("minecraft:oak_pressure_plate")
    val SPRUCE_PRESSURE_PLATE: Key = Key("minecraft:spruce_pressure_plate")
    val BIRCH_PRESSURE_PLATE: Key = Key("minecraft:birch_pressure_plate")
    val JUNGLE_PRESSURE_PLATE: Key = Key("minecraft:jungle_pressure_plate")
    val ACACIA_PRESSURE_PLATE: Key = Key("minecraft:acacia_pressure_plate")
    val DARK_OAK_PRESSURE_PLATE: Key = Key("minecraft:dark_oak_pressure_plate")
    val REDSTONE_ORE: Key = Key("minecraft:redstone_ore")
    val DEEPSLATE_REDSTONE_ORE: Key = Key("minecraft:deepslate_redstone_ore")
    val REDSTONE_TORCH: Key = Key("minecraft:redstone_torch")
    val REDSTONE_WALL_TORCH: Key = Key("minecraft:redstone_wall_torch")
    val STONE_BUTTON: Key = Key("minecraft:stone_button")
    val SNOW: Key = Key("minecraft:snow")
    val ICE: Key = Key("minecraft:ice")
    val SNOW_BLOCK: Key = Key("minecraft:snow_block")
    val CACTUS: Key = Key("minecraft:cactus")
    val CLAY: Key = Key("minecraft:clay")
    val SUGAR_CANE: Key = Key("minecraft:sugar_cane")
    val JUKEBOX: Key = Key("minecraft:jukebox")
    val OAK_FENCE: Key = Key("minecraft:oak_fence")
    val PUMPKIN: Key = Key("minecraft:pumpkin")
    val NETHERRACK: Key = Key("minecraft:netherrack")
    val SOUL_SAND: Key = Key("minecraft:soul_sand")
    val SOUL_SOIL: Key = Key("minecraft:soul_soil")
    val BASALT: Key = Key("minecraft:basalt")
    val POLISHED_BASALT: Key = Key("minecraft:polished_basalt")
    val SOUL_TORCH: Key = Key("minecraft:soul_torch")
    val SOUL_WALL_TORCH: Key = Key("minecraft:soul_wall_torch")
    val GLOWSTONE: Key = Key("minecraft:glowstone")
    val NETHER_PORTAL: Key = Key("minecraft:nether_portal")
    val CARVED_PUMPKIN: Key = Key("minecraft:carved_pumpkin")
    val JACK_O_LANTERN: Key = Key("minecraft:jack_o_lantern")
    val CAKE: Key = Key("minecraft:cake")
    val REPEATER: Key = Key("minecraft:repeater")
    val WHITE_STAINED_GLASS: Key = Key("minecraft:white_stained_glass")
    val ORANGE_STAINED_GLASS: Key = Key("minecraft:orange_stained_glass")
    val MAGENTA_STAINED_GLASS: Key = Key("minecraft:magenta_stained_glass")
    val LIGHT_BLUE_STAINED_GLASS: Key = Key("minecraft:light_blue_stained_glass")
    val YELLOW_STAINED_GLASS: Key = Key("minecraft:yellow_stained_glass")
    val LIME_STAINED_GLASS: Key = Key("minecraft:lime_stained_glass")
    val PINK_STAINED_GLASS: Key = Key("minecraft:pink_stained_glass")
    val GRAY_STAINED_GLASS: Key = Key("minecraft:gray_stained_glass")
    val LIGHT_GRAY_STAINED_GLASS: Key = Key("minecraft:light_gray_stained_glass")
    val CYAN_STAINED_GLASS: Key = Key("minecraft:cyan_stained_glass")
    val PURPLE_STAINED_GLASS: Key = Key("minecraft:purple_stained_glass")
    val BLUE_STAINED_GLASS: Key = Key("minecraft:blue_stained_glass")
    val BROWN_STAINED_GLASS: Key = Key("minecraft:brown_stained_glass")
    val GREEN_STAINED_GLASS: Key = Key("minecraft:green_stained_glass")
    val RED_STAINED_GLASS: Key = Key("minecraft:red_stained_glass")
    val BLACK_STAINED_GLASS: Key = Key("minecraft:black_stained_glass")
    val OAK_TRAPDOOR: Key = Key("minecraft:oak_trapdoor")
    val SPRUCE_TRAPDOOR: Key = Key("minecraft:spruce_trapdoor")
    val BIRCH_TRAPDOOR: Key = Key("minecraft:birch_trapdoor")
    val JUNGLE_TRAPDOOR: Key = Key("minecraft:jungle_trapdoor")
    val ACACIA_TRAPDOOR: Key = Key("minecraft:acacia_trapdoor")
    val DARK_OAK_TRAPDOOR: Key = Key("minecraft:dark_oak_trapdoor")
    val STONE_BRICKS: Key = Key("minecraft:stone_bricks")
    val MOSSY_STONE_BRICKS: Key = Key("minecraft:mossy_stone_bricks")
    val CRACKED_STONE_BRICKS: Key = Key("minecraft:cracked_stone_bricks")
    val CHISELED_STONE_BRICKS: Key = Key("minecraft:chiseled_stone_bricks")
    val INFESTED_STONE: Key = Key("minecraft:infested_stone")
    val INFESTED_COBBLESTONE: Key = Key("minecraft:infested_cobblestone")
    val INFESTED_STONE_BRICKS: Key = Key("minecraft:infested_stone_bricks")
    val INFESTED_MOSSY_STONE_BRICKS: Key = Key("minecraft:infested_mossy_stone_bricks")
    val INFESTED_CRACKED_STONE_BRICKS: Key = Key("minecraft:infested_cracked_stone_bricks")
    val INFESTED_CHISELED_STONE_BRICKS: Key = Key("minecraft:infested_chiseled_stone_bricks")
    val BROWN_MUSHROOM_BLOCK: Key = Key("minecraft:brown_mushroom_block")
    val RED_MUSHROOM_BLOCK: Key = Key("minecraft:red_mushroom_block")
    val MUSHROOM_STEM: Key = Key("minecraft:mushroom_stem")
    val IRON_BARS: Key = Key("minecraft:iron_bars")
    val CHAIN: Key = Key("minecraft:chain")
    val GLASS_PANE: Key = Key("minecraft:glass_pane")
    val MELON: Key = Key("minecraft:melon")
    val ATTACHED_PUMPKIN_STEM: Key = Key("minecraft:attached_pumpkin_stem")
    val ATTACHED_MELON_STEM: Key = Key("minecraft:attached_melon_stem")
    val PUMPKIN_STEM: Key = Key("minecraft:pumpkin_stem")
    val MELON_STEM: Key = Key("minecraft:melon_stem")
    val VINE: Key = Key("minecraft:vine")
    val GLOW_LICHEN: Key = Key("minecraft:glow_lichen")
    val OAK_FENCE_GATE: Key = Key("minecraft:oak_fence_gate")
    val BRICK_STAIRS: Key = Key("minecraft:brick_stairs")
    val STONE_BRICK_STAIRS: Key = Key("minecraft:stone_brick_stairs")
    val MYCELIUM: Key = Key("minecraft:mycelium")
    val LILY_PAD: Key = Key("minecraft:lily_pad")
    val NETHER_BRICKS: Key = Key("minecraft:nether_bricks")
    val NETHER_BRICK_FENCE: Key = Key("minecraft:nether_brick_fence")
    val NETHER_BRICK_STAIRS: Key = Key("minecraft:nether_brick_stairs")
    val ENCHANTING_TABLE: Key = Key("minecraft:enchanting_table")
    val WATER_CAULDRON: Key = Key("minecraft:water_cauldron")
    val LAVA_CAULDRON: Key = Key("minecraft:lava_cauldron")
    val POWDER_SNOW_CAULDRON: Key = Key("minecraft:powder_snow_cauldron")
    val END_PORTAL: Key = Key("minecraft:end_portal")
    val END_PORTAL_FRAME: Key = Key("minecraft:end_portal_frame")
    val END_STONE: Key = Key("minecraft:end_stone")
    val DRAGON_EGG: Key = Key("minecraft:dragon_egg")
    val REDSTONE_LAMP: Key = Key("minecraft:redstone_lamp")
    val COCOA: Key = Key("minecraft:cocoa")
    val SANDSTONE_STAIRS: Key = Key("minecraft:sandstone_stairs")
    val EMERALD_ORE: Key = Key("minecraft:emerald_ore")
    val DEEPSLATE_EMERALD_ORE: Key = Key("minecraft:deepslate_emerald_ore")
    val ENDER_CHEST: Key = Key("minecraft:ender_chest")
    val TRIPWIRE_HOOK: Key = Key("minecraft:tripwire_hook")
    val TRIPWIRE: Key = Key("minecraft:tripwire")
    val EMERALD_BLOCK: Key = Key("minecraft:emerald_block")
    val SPRUCE_STAIRS: Key = Key("minecraft:spruce_stairs")
    val BIRCH_STAIRS: Key = Key("minecraft:birch_stairs")
    val JUNGLE_STAIRS: Key = Key("minecraft:jungle_stairs")
    val COMMAND_BLOCK: Key = Key("minecraft:command_block")
    val BEACON: Key = Key("minecraft:beacon")
    val COBBLESTONE_WALL: Key = Key("minecraft:cobblestone_wall")
    val MOSSY_COBBLESTONE_WALL: Key = Key("minecraft:mossy_cobblestone_wall")
    val POTTED_OAK_SAPLING: Key = Key("minecraft:potted_oak_sapling")
    val POTTED_SPRUCE_SAPLING: Key = Key("minecraft:potted_spruce_sapling")
    val POTTED_BIRCH_SAPLING: Key = Key("minecraft:potted_birch_sapling")
    val POTTED_JUNGLE_SAPLING: Key = Key("minecraft:potted_jungle_sapling")
    val POTTED_ACACIA_SAPLING: Key = Key("minecraft:potted_acacia_sapling")
    val POTTED_DARK_OAK_SAPLING: Key = Key("minecraft:potted_dark_oak_sapling")
    val POTTED_FERN: Key = Key("minecraft:potted_fern")
    val POTTED_DANDELION: Key = Key("minecraft:potted_dandelion")
    val POTTED_POPPY: Key = Key("minecraft:potted_poppy")
    val POTTED_BLUE_ORCHID: Key = Key("minecraft:potted_blue_orchid")
    val POTTED_ALLIUM: Key = Key("minecraft:potted_allium")
    val POTTED_AZURE_BLUET: Key = Key("minecraft:potted_azure_bluet")
    val POTTED_RED_TULIP: Key = Key("minecraft:potted_red_tulip")
    val POTTED_ORANGE_TULIP: Key = Key("minecraft:potted_orange_tulip")
    val POTTED_WHITE_TULIP: Key = Key("minecraft:potted_white_tulip")
    val POTTED_PINK_TULIP: Key = Key("minecraft:potted_pink_tulip")
    val POTTED_OXEYE_DAISY: Key = Key("minecraft:potted_oxeye_daisy")
    val POTTED_CORNFLOWER: Key = Key("minecraft:potted_cornflower")
    val POTTED_LILY_OF_THE_VALLEY: Key = Key("minecraft:potted_lily_of_the_valley")
    val POTTED_WITHER_ROSE: Key = Key("minecraft:potted_wither_rose")
    val POTTED_RED_MUSHROOM: Key = Key("minecraft:potted_red_mushroom")
    val POTTED_BROWN_MUSHROOM: Key = Key("minecraft:potted_brown_mushroom")
    val POTTED_DEAD_BUSH: Key = Key("minecraft:potted_dead_bush")
    val POTTED_CACTUS: Key = Key("minecraft:potted_cactus")
    val CARROTS: Key = Key("minecraft:carrots")
    val POTATOES: Key = Key("minecraft:potatoes")
    val OAK_BUTTON: Key = Key("minecraft:oak_button")
    val SPRUCE_BUTTON: Key = Key("minecraft:spruce_button")
    val BIRCH_BUTTON: Key = Key("minecraft:birch_button")
    val JUNGLE_BUTTON: Key = Key("minecraft:jungle_button")
    val ACACIA_BUTTON: Key = Key("minecraft:acacia_button")
    val DARK_OAK_BUTTON: Key = Key("minecraft:dark_oak_button")
    val SKELETON_SKULL: Key = Key("minecraft:skeleton_skull")
    val SKELETON_WALL_SKULL: Key = Key("minecraft:skeleton_wall_skull")
    val WITHER_SKELETON_SKULL: Key = Key("minecraft:wither_skeleton_skull")
    val WITHER_SKELETON_WALL_SKULL: Key = Key("minecraft:wither_skeleton_wall_skull")
    val ZOMBIE_HEAD: Key = Key("minecraft:zombie_head")
    val ZOMBIE_WALL_HEAD: Key = Key("minecraft:zombie_wall_head")
    val PLAYER_HEAD: Key = Key("minecraft:player_head")
    val PLAYER_WALL_HEAD: Key = Key("minecraft:player_wall_head")
    val CREEPER_HEAD: Key = Key("minecraft:creeper_head")
    val CREEPER_WALL_HEAD: Key = Key("minecraft:creeper_wall_head")
    val DRAGON_HEAD: Key = Key("minecraft:dragon_head")
    val DRAGON_WALL_HEAD: Key = Key("minecraft:dragon_wall_head")
    val ANVIL: Key = Key("minecraft:anvil")
    val CHIPPED_ANVIL: Key = Key("minecraft:chipped_anvil")
    val DAMAGED_ANVIL: Key = Key("minecraft:damaged_anvil")
    val TRAPPED_CHEST: Key = Key("minecraft:trapped_chest")
    val LIGHT_WEIGHTED_PRESSURE_PLATE: Key = Key("minecraft:light_weighted_pressure_plate")
    val HEAVY_WEIGHTED_PRESSURE_PLATE: Key = Key("minecraft:heavy_weighted_pressure_plate")
    val COMPARATOR: Key = Key("minecraft:comparator")
    val DAYLIGHT_DETECTOR: Key = Key("minecraft:daylight_detector")
    val REDSTONE_BLOCK: Key = Key("minecraft:redstone_block")
    val NETHER_QUARTZ_ORE: Key = Key("minecraft:nether_quartz_ore")
    val HOPPER: Key = Key("minecraft:hopper")
    val QUARTZ_BLOCK: Key = Key("minecraft:quartz_block")
    val CHISELED_QUARTZ_BLOCK: Key = Key("minecraft:chiseled_quartz_block")
    val QUARTZ_PILLAR: Key = Key("minecraft:quartz_pillar")
    val QUARTZ_STAIRS: Key = Key("minecraft:quartz_stairs")
    val ACTIVATOR_RAIL: Key = Key("minecraft:activator_rail")
    val DROPPER: Key = Key("minecraft:dropper")
    val WHITE_TERRACOTTA: Key = Key("minecraft:white_terracotta")
    val ORANGE_TERRACOTTA: Key = Key("minecraft:orange_terracotta")
    val MAGENTA_TERRACOTTA: Key = Key("minecraft:magenta_terracotta")
    val LIGHT_BLUE_TERRACOTTA: Key = Key("minecraft:light_blue_terracotta")
    val YELLOW_TERRACOTTA: Key = Key("minecraft:yellow_terracotta")
    val LIME_TERRACOTTA: Key = Key("minecraft:lime_terracotta")
    val PINK_TERRACOTTA: Key = Key("minecraft:pink_terracotta")
    val GRAY_TERRACOTTA: Key = Key("minecraft:gray_terracotta")
    val LIGHT_GRAY_TERRACOTTA: Key = Key("minecraft:light_gray_terracotta")
    val CYAN_TERRACOTTA: Key = Key("minecraft:cyan_terracotta")
    val PURPLE_TERRACOTTA: Key = Key("minecraft:purple_terracotta")
    val BLUE_TERRACOTTA: Key = Key("minecraft:blue_terracotta")
    val BROWN_TERRACOTTA: Key = Key("minecraft:brown_terracotta")
    val GREEN_TERRACOTTA: Key = Key("minecraft:green_terracotta")
    val RED_TERRACOTTA: Key = Key("minecraft:red_terracotta")
    val BLACK_TERRACOTTA: Key = Key("minecraft:black_terracotta")
    val WHITE_STAINED_GLASS_PANE: Key = Key("minecraft:white_stained_glass_pane")
    val ORANGE_STAINED_GLASS_PANE: Key = Key("minecraft:orange_stained_glass_pane")
    val MAGENTA_STAINED_GLASS_PANE: Key = Key("minecraft:magenta_stained_glass_pane")
    val LIGHT_BLUE_STAINED_GLASS_PANE: Key = Key("minecraft:light_blue_stained_glass_pane")
    val YELLOW_STAINED_GLASS_PANE: Key = Key("minecraft:yellow_stained_glass_pane")
    val LIME_STAINED_GLASS_PANE: Key = Key("minecraft:lime_stained_glass_pane")
    val PINK_STAINED_GLASS_PANE: Key = Key("minecraft:pink_stained_glass_pane")
    val GRAY_STAINED_GLASS_PANE: Key = Key("minecraft:gray_stained_glass_pane")
    val LIGHT_GRAY_STAINED_GLASS_PANE: Key = Key("minecraft:light_gray_stained_glass_pane")
    val CYAN_STAINED_GLASS_PANE: Key = Key("minecraft:cyan_stained_glass_pane")
    val PURPLE_STAINED_GLASS_PANE: Key = Key("minecraft:purple_stained_glass_pane")
    val BLUE_STAINED_GLASS_PANE: Key = Key("minecraft:blue_stained_glass_pane")
    val BROWN_STAINED_GLASS_PANE: Key = Key("minecraft:brown_stained_glass_pane")
    val GREEN_STAINED_GLASS_PANE: Key = Key("minecraft:green_stained_glass_pane")
    val RED_STAINED_GLASS_PANE: Key = Key("minecraft:red_stained_glass_pane")
    val BLACK_STAINED_GLASS_PANE: Key = Key("minecraft:black_stained_glass_pane")
    val ACACIA_STAIRS: Key = Key("minecraft:acacia_stairs")
    val DARK_OAK_STAIRS: Key = Key("minecraft:dark_oak_stairs")
    val SLIME_BLOCK: Key = Key("minecraft:slime_block")
    val BARRIER: Key = Key("minecraft:barrier")
    val LIGHT: Key = Key("minecraft:light")
    val IRON_TRAPDOOR: Key = Key("minecraft:iron_trapdoor")
    val PRISMARINE: Key = Key("minecraft:prismarine")
    val PRISMARINE_BRICKS: Key = Key("minecraft:prismarine_bricks")
    val DARK_PRISMARINE: Key = Key("minecraft:dark_prismarine")
    val PRISMARINE_STAIRS: Key = Key("minecraft:prismarine_stairs")
    val PRISMARINE_BRICK_STAIRS: Key = Key("minecraft:prismarine_brick_stairs")
    val DARK_PRISMARINE_STAIRS: Key = Key("minecraft:dark_prismarine_stairs")
    val PRISMARINE_SLAB: Key = Key("minecraft:prismarine_slab")
    val PRISMARINE_BRICK_SLAB: Key = Key("minecraft:prismarine_brick_slab")
    val DARK_PRISMARINE_SLAB: Key = Key("minecraft:dark_prismarine_slab")
    val SEA_LANTERN: Key = Key("minecraft:sea_lantern")
    val HAY_BLOCK: Key = Key("minecraft:hay_block")
    val WHITE_CARPET: Key = Key("minecraft:white_carpet")
    val ORANGE_CARPET: Key = Key("minecraft:orange_carpet")
    val MAGENTA_CARPET: Key = Key("minecraft:magenta_carpet")
    val LIGHT_BLUE_CARPET: Key = Key("minecraft:light_blue_carpet")
    val YELLOW_CARPET: Key = Key("minecraft:yellow_carpet")
    val LIME_CARPET: Key = Key("minecraft:lime_carpet")
    val PINK_CARPET: Key = Key("minecraft:pink_carpet")
    val GRAY_CARPET: Key = Key("minecraft:gray_carpet")
    val LIGHT_GRAY_CARPET: Key = Key("minecraft:light_gray_carpet")
    val CYAN_CARPET: Key = Key("minecraft:cyan_carpet")
    val PURPLE_CARPET: Key = Key("minecraft:purple_carpet")
    val BLUE_CARPET: Key = Key("minecraft:blue_carpet")
    val BROWN_CARPET: Key = Key("minecraft:brown_carpet")
    val GREEN_CARPET: Key = Key("minecraft:green_carpet")
    val RED_CARPET: Key = Key("minecraft:red_carpet")
    val BLACK_CARPET: Key = Key("minecraft:black_carpet")
    val TERRACOTTA: Key = Key("minecraft:terracotta")
    val COAL_BLOCK: Key = Key("minecraft:coal_block")
    val PACKED_ICE: Key = Key("minecraft:packed_ice")
    val SUNFLOWER: Key = Key("minecraft:sunflower")
    val LILAC: Key = Key("minecraft:lilac")
    val ROSE_BUSH: Key = Key("minecraft:rose_bush")
    val PEONY: Key = Key("minecraft:peony")
    val TALL_GRASS: Key = Key("minecraft:tall_grass")
    val LARGE_FERN: Key = Key("minecraft:large_fern")
    val WHITE_BANNER: Key = Key("minecraft:white_banner")
    val ORANGE_BANNER: Key = Key("minecraft:orange_banner")
    val MAGENTA_BANNER: Key = Key("minecraft:magenta_banner")
    val LIGHT_BLUE_BANNER: Key = Key("minecraft:light_blue_banner")
    val YELLOW_BANNER: Key = Key("minecraft:yellow_banner")
    val LIME_BANNER: Key = Key("minecraft:lime_banner")
    val PINK_BANNER: Key = Key("minecraft:pink_banner")
    val GRAY_BANNER: Key = Key("minecraft:gray_banner")
    val LIGHT_GRAY_BANNER: Key = Key("minecraft:light_gray_banner")
    val CYAN_BANNER: Key = Key("minecraft:cyan_banner")
    val PURPLE_BANNER: Key = Key("minecraft:purple_banner")
    val BLUE_BANNER: Key = Key("minecraft:blue_banner")
    val BROWN_BANNER: Key = Key("minecraft:brown_banner")
    val GREEN_BANNER: Key = Key("minecraft:green_banner")
    val RED_BANNER: Key = Key("minecraft:red_banner")
    val BLACK_BANNER: Key = Key("minecraft:black_banner")
    val WHITE_WALL_BANNER: Key = Key("minecraft:white_wall_banner")
    val ORANGE_WALL_BANNER: Key = Key("minecraft:orange_wall_banner")
    val MAGENTA_WALL_BANNER: Key = Key("minecraft:magenta_wall_banner")
    val LIGHT_BLUE_WALL_BANNER: Key = Key("minecraft:light_blue_wall_banner")
    val YELLOW_WALL_BANNER: Key = Key("minecraft:yellow_wall_banner")
    val LIME_WALL_BANNER: Key = Key("minecraft:lime_wall_banner")
    val PINK_WALL_BANNER: Key = Key("minecraft:pink_wall_banner")
    val GRAY_WALL_BANNER: Key = Key("minecraft:gray_wall_banner")
    val LIGHT_GRAY_WALL_BANNER: Key = Key("minecraft:light_gray_wall_banner")
    val CYAN_WALL_BANNER: Key = Key("minecraft:cyan_wall_banner")
    val PURPLE_WALL_BANNER: Key = Key("minecraft:purple_wall_banner")
    val BLUE_WALL_BANNER: Key = Key("minecraft:blue_wall_banner")
    val BROWN_WALL_BANNER: Key = Key("minecraft:brown_wall_banner")
    val GREEN_WALL_BANNER: Key = Key("minecraft:green_wall_banner")
    val RED_WALL_BANNER: Key = Key("minecraft:red_wall_banner")
    val BLACK_WALL_BANNER: Key = Key("minecraft:black_wall_banner")
    val RED_SANDSTONE: Key = Key("minecraft:red_sandstone")
    val CHISELED_RED_SANDSTONE: Key = Key("minecraft:chiseled_red_sandstone")
    val CUT_RED_SANDSTONE: Key = Key("minecraft:cut_red_sandstone")
    val RED_SANDSTONE_STAIRS: Key = Key("minecraft:red_sandstone_stairs")
    val OAK_SLAB: Key = Key("minecraft:oak_slab")
    val SPRUCE_SLAB: Key = Key("minecraft:spruce_slab")
    val BIRCH_SLAB: Key = Key("minecraft:birch_slab")
    val JUNGLE_SLAB: Key = Key("minecraft:jungle_slab")
    val ACACIA_SLAB: Key = Key("minecraft:acacia_slab")
    val DARK_OAK_SLAB: Key = Key("minecraft:dark_oak_slab")
    val STONE_SLAB: Key = Key("minecraft:stone_slab")
    val SMOOTH_STONE_SLAB: Key = Key("minecraft:smooth_stone_slab")
    val SANDSTONE_SLAB: Key = Key("minecraft:sandstone_slab")
    val CUT_SANDSTONE_SLAB: Key = Key("minecraft:cut_sandstone_slab")
    val PETRIFIED_OAK_SLAB: Key = Key("minecraft:petrified_oak_slab")
    val COBBLESTONE_SLAB: Key = Key("minecraft:cobblestone_slab")
    val BRICK_SLAB: Key = Key("minecraft:brick_slab")
    val STONE_BRICK_SLAB: Key = Key("minecraft:stone_brick_slab")
    val NETHER_BRICK_SLAB: Key = Key("minecraft:nether_brick_slab")
    val QUARTZ_SLAB: Key = Key("minecraft:quartz_slab")
    val RED_SANDSTONE_SLAB: Key = Key("minecraft:red_sandstone_slab")
    val CUT_RED_SANDSTONE_SLAB: Key = Key("minecraft:cut_red_sandstone_slab")
    val PURPUR_SLAB: Key = Key("minecraft:purpur_slab")
    val SMOOTH_STONE: Key = Key("minecraft:smooth_stone")
    val SMOOTH_SANDSTONE: Key = Key("minecraft:smooth_sandstone")
    val SMOOTH_QUARTZ: Key = Key("minecraft:smooth_quartz")
    val SMOOTH_RED_SANDSTONE: Key = Key("minecraft:smooth_red_sandstone")
    val SPRUCE_FENCE_GATE: Key = Key("minecraft:spruce_fence_gate")
    val BIRCH_FENCE_GATE: Key = Key("minecraft:birch_fence_gate")
    val JUNGLE_FENCE_GATE: Key = Key("minecraft:jungle_fence_gate")
    val ACACIA_FENCE_GATE: Key = Key("minecraft:acacia_fence_gate")
    val DARK_OAK_FENCE_GATE: Key = Key("minecraft:dark_oak_fence_gate")
    val SPRUCE_FENCE: Key = Key("minecraft:spruce_fence")
    val BIRCH_FENCE: Key = Key("minecraft:birch_fence")
    val JUNGLE_FENCE: Key = Key("minecraft:jungle_fence")
    val ACACIA_FENCE: Key = Key("minecraft:acacia_fence")
    val DARK_OAK_FENCE: Key = Key("minecraft:dark_oak_fence")
    val SPRUCE_DOOR: Key = Key("minecraft:spruce_door")
    val BIRCH_DOOR: Key = Key("minecraft:birch_door")
    val JUNGLE_DOOR: Key = Key("minecraft:jungle_door")
    val ACACIA_DOOR: Key = Key("minecraft:acacia_door")
    val DARK_OAK_DOOR: Key = Key("minecraft:dark_oak_door")
    val END_ROD: Key = Key("minecraft:end_rod")
    val CHORUS_PLANT: Key = Key("minecraft:chorus_plant")
    val CHORUS_FLOWER: Key = Key("minecraft:chorus_flower")
    val PURPUR_BLOCK: Key = Key("minecraft:purpur_block")
    val PURPUR_PILLAR: Key = Key("minecraft:purpur_pillar")
    val PURPUR_STAIRS: Key = Key("minecraft:purpur_stairs")
    val END_STONE_BRICKS: Key = Key("minecraft:end_stone_bricks")
    val BEETROOTS: Key = Key("minecraft:beetroots")
    val DIRT_PATH: Key = Key("minecraft:dirt_path")
    val END_GATEWAY: Key = Key("minecraft:end_gateway")
    val REPEATING_COMMAND_BLOCK: Key = Key("minecraft:repeating_command_block")
    val CHAIN_COMMAND_BLOCK: Key = Key("minecraft:chain_command_block")
    val FROSTED_ICE: Key = Key("minecraft:frosted_ice")
    val MAGMA_BLOCK: Key = Key("minecraft:magma_block")
    val NETHER_WART_BLOCK: Key = Key("minecraft:nether_wart_block")
    val RED_NETHER_BRICKS: Key = Key("minecraft:red_nether_bricks")
    val BONE_BLOCK: Key = Key("minecraft:bone_block")
    val STRUCTURE_VOID: Key = Key("minecraft:structure_void")
    val OBSERVER: Key = Key("minecraft:observer")
    val SHULKER_BOX: Key = Key("minecraft:shulker_box")
    val WHITE_SHULKER_BOX: Key = Key("minecraft:white_shulker_box")
    val ORANGE_SHULKER_BOX: Key = Key("minecraft:orange_shulker_box")
    val MAGENTA_SHULKER_BOX: Key = Key("minecraft:magenta_shulker_box")
    val LIGHT_BLUE_SHULKER_BOX: Key = Key("minecraft:light_blue_shulker_box")
    val YELLOW_SHULKER_BOX: Key = Key("minecraft:yellow_shulker_box")
    val LIME_SHULKER_BOX: Key = Key("minecraft:lime_shulker_box")
    val PINK_SHULKER_BOX: Key = Key("minecraft:pink_shulker_box")
    val GRAY_SHULKER_BOX: Key = Key("minecraft:gray_shulker_box")
    val LIGHT_GRAY_SHULKER_BOX: Key = Key("minecraft:light_gray_shulker_box")
    val CYAN_SHULKER_BOX: Key = Key("minecraft:cyan_shulker_box")
    val PURPLE_SHULKER_BOX: Key = Key("minecraft:purple_shulker_box")
    val BLUE_SHULKER_BOX: Key = Key("minecraft:blue_shulker_box")
    val BROWN_SHULKER_BOX: Key = Key("minecraft:brown_shulker_box")
    val GREEN_SHULKER_BOX: Key = Key("minecraft:green_shulker_box")
    val RED_SHULKER_BOX: Key = Key("minecraft:red_shulker_box")
    val BLACK_SHULKER_BOX: Key = Key("minecraft:black_shulker_box")
    val WHITE_GLAZED_TERRACOTTA: Key = Key("minecraft:white_glazed_terracotta")
    val ORANGE_GLAZED_TERRACOTTA: Key = Key("minecraft:orange_glazed_terracotta")
    val MAGENTA_GLAZED_TERRACOTTA: Key = Key("minecraft:magenta_glazed_terracotta")
    val LIGHT_BLUE_GLAZED_TERRACOTTA: Key = Key("minecraft:light_blue_glazed_terracotta")
    val YELLOW_GLAZED_TERRACOTTA: Key = Key("minecraft:yellow_glazed_terracotta")
    val LIME_GLAZED_TERRACOTTA: Key = Key("minecraft:lime_glazed_terracotta")
    val PINK_GLAZED_TERRACOTTA: Key = Key("minecraft:pink_glazed_terracotta")
    val GRAY_GLAZED_TERRACOTTA: Key = Key("minecraft:gray_glazed_terracotta")
    val LIGHT_GRAY_GLAZED_TERRACOTTA: Key = Key("minecraft:light_gray_glazed_terracotta")
    val CYAN_GLAZED_TERRACOTTA: Key = Key("minecraft:cyan_glazed_terracotta")
    val PURPLE_GLAZED_TERRACOTTA: Key = Key("minecraft:purple_glazed_terracotta")
    val BLUE_GLAZED_TERRACOTTA: Key = Key("minecraft:blue_glazed_terracotta")
    val BROWN_GLAZED_TERRACOTTA: Key = Key("minecraft:brown_glazed_terracotta")
    val GREEN_GLAZED_TERRACOTTA: Key = Key("minecraft:green_glazed_terracotta")
    val RED_GLAZED_TERRACOTTA: Key = Key("minecraft:red_glazed_terracotta")
    val BLACK_GLAZED_TERRACOTTA: Key = Key("minecraft:black_glazed_terracotta")
    val WHITE_CONCRETE: Key = Key("minecraft:white_concrete")
    val ORANGE_CONCRETE: Key = Key("minecraft:orange_concrete")
    val MAGENTA_CONCRETE: Key = Key("minecraft:magenta_concrete")
    val LIGHT_BLUE_CONCRETE: Key = Key("minecraft:light_blue_concrete")
    val YELLOW_CONCRETE: Key = Key("minecraft:yellow_concrete")
    val LIME_CONCRETE: Key = Key("minecraft:lime_concrete")
    val PINK_CONCRETE: Key = Key("minecraft:pink_concrete")
    val GRAY_CONCRETE: Key = Key("minecraft:gray_concrete")
    val LIGHT_GRAY_CONCRETE: Key = Key("minecraft:light_gray_concrete")
    val CYAN_CONCRETE: Key = Key("minecraft:cyan_concrete")
    val PURPLE_CONCRETE: Key = Key("minecraft:purple_concrete")
    val BLUE_CONCRETE: Key = Key("minecraft:blue_concrete")
    val BROWN_CONCRETE: Key = Key("minecraft:brown_concrete")
    val GREEN_CONCRETE: Key = Key("minecraft:green_concrete")
    val RED_CONCRETE: Key = Key("minecraft:red_concrete")
    val BLACK_CONCRETE: Key = Key("minecraft:black_concrete")
    val WHITE_CONCRETE_POWDER: Key = Key("minecraft:white_concrete_powder")
    val ORANGE_CONCRETE_POWDER: Key = Key("minecraft:orange_concrete_powder")
    val MAGENTA_CONCRETE_POWDER: Key = Key("minecraft:magenta_concrete_powder")
    val LIGHT_BLUE_CONCRETE_POWDER: Key = Key("minecraft:light_blue_concrete_powder")
    val YELLOW_CONCRETE_POWDER: Key = Key("minecraft:yellow_concrete_powder")
    val LIME_CONCRETE_POWDER: Key = Key("minecraft:lime_concrete_powder")
    val PINK_CONCRETE_POWDER: Key = Key("minecraft:pink_concrete_powder")
    val GRAY_CONCRETE_POWDER: Key = Key("minecraft:gray_concrete_powder")
    val LIGHT_GRAY_CONCRETE_POWDER: Key = Key("minecraft:light_gray_concrete_powder")
    val CYAN_CONCRETE_POWDER: Key = Key("minecraft:cyan_concrete_powder")
    val PURPLE_CONCRETE_POWDER: Key = Key("minecraft:purple_concrete_powder")
    val BLUE_CONCRETE_POWDER: Key = Key("minecraft:blue_concrete_powder")
    val BROWN_CONCRETE_POWDER: Key = Key("minecraft:brown_concrete_powder")
    val GREEN_CONCRETE_POWDER: Key = Key("minecraft:green_concrete_powder")
    val RED_CONCRETE_POWDER: Key = Key("minecraft:red_concrete_powder")
    val BLACK_CONCRETE_POWDER: Key = Key("minecraft:black_concrete_powder")
    val KELP: Key = Key("minecraft:kelp")
    val KELP_PLANT: Key = Key("minecraft:kelp_plant")
    val DRIED_KELP_BLOCK: Key = Key("minecraft:dried_kelp_block")
    val TURTLE_EGG: Key = Key("minecraft:turtle_egg")
    val DEAD_TUBE_CORAL_BLOCK: Key = Key("minecraft:dead_tube_coral_block")
    val DEAD_BRAIN_CORAL_BLOCK: Key = Key("minecraft:dead_brain_coral_block")
    val DEAD_BUBBLE_CORAL_BLOCK: Key = Key("minecraft:dead_bubble_coral_block")
    val DEAD_FIRE_CORAL_BLOCK: Key = Key("minecraft:dead_fire_coral_block")
    val DEAD_HORN_CORAL_BLOCK: Key = Key("minecraft:dead_horn_coral_block")
    val TUBE_CORAL_BLOCK: Key = Key("minecraft:tube_coral_block")
    val BRAIN_CORAL_BLOCK: Key = Key("minecraft:brain_coral_block")
    val BUBBLE_CORAL_BLOCK: Key = Key("minecraft:bubble_coral_block")
    val FIRE_CORAL_BLOCK: Key = Key("minecraft:fire_coral_block")
    val HORN_CORAL_BLOCK: Key = Key("minecraft:horn_coral_block")
    val DEAD_TUBE_CORAL: Key = Key("minecraft:dead_tube_coral")
    val DEAD_BRAIN_CORAL: Key = Key("minecraft:dead_brain_coral")
    val DEAD_BUBBLE_CORAL: Key = Key("minecraft:dead_bubble_coral")
    val DEAD_FIRE_CORAL: Key = Key("minecraft:dead_fire_coral")
    val DEAD_HORN_CORAL: Key = Key("minecraft:dead_horn_coral")
    val TUBE_CORAL: Key = Key("minecraft:tube_coral")
    val BRAIN_CORAL: Key = Key("minecraft:brain_coral")
    val BUBBLE_CORAL: Key = Key("minecraft:bubble_coral")
    val FIRE_CORAL: Key = Key("minecraft:fire_coral")
    val HORN_CORAL: Key = Key("minecraft:horn_coral")
    val DEAD_TUBE_CORAL_FAN: Key = Key("minecraft:dead_tube_coral_fan")
    val DEAD_BRAIN_CORAL_FAN: Key = Key("minecraft:dead_brain_coral_fan")
    val DEAD_BUBBLE_CORAL_FAN: Key = Key("minecraft:dead_bubble_coral_fan")
    val DEAD_FIRE_CORAL_FAN: Key = Key("minecraft:dead_fire_coral_fan")
    val DEAD_HORN_CORAL_FAN: Key = Key("minecraft:dead_horn_coral_fan")
    val TUBE_CORAL_FAN: Key = Key("minecraft:tube_coral_fan")
    val BRAIN_CORAL_FAN: Key = Key("minecraft:brain_coral_fan")
    val BUBBLE_CORAL_FAN: Key = Key("minecraft:bubble_coral_fan")
    val FIRE_CORAL_FAN: Key = Key("minecraft:fire_coral_fan")
    val HORN_CORAL_FAN: Key = Key("minecraft:horn_coral_fan")
    val DEAD_TUBE_CORAL_WALL_FAN: Key = Key("minecraft:dead_tube_coral_wall_fan")
    val DEAD_BRAIN_CORAL_WALL_FAN: Key = Key("minecraft:dead_brain_coral_wall_fan")
    val DEAD_BUBBLE_CORAL_WALL_FAN: Key = Key("minecraft:dead_bubble_coral_wall_fan")
    val DEAD_FIRE_CORAL_WALL_FAN: Key = Key("minecraft:dead_fire_coral_wall_fan")
    val DEAD_HORN_CORAL_WALL_FAN: Key = Key("minecraft:dead_horn_coral_wall_fan")
    val TUBE_CORAL_WALL_FAN: Key = Key("minecraft:tube_coral_wall_fan")
    val BRAIN_CORAL_WALL_FAN: Key = Key("minecraft:brain_coral_wall_fan")
    val BUBBLE_CORAL_WALL_FAN: Key = Key("minecraft:bubble_coral_wall_fan")
    val FIRE_CORAL_WALL_FAN: Key = Key("minecraft:fire_coral_wall_fan")
    val HORN_CORAL_WALL_FAN: Key = Key("minecraft:horn_coral_wall_fan")
    val SEA_PICKLE: Key = Key("minecraft:sea_pickle")
    val BLUE_ICE: Key = Key("minecraft:blue_ice")
    val CONDUIT: Key = Key("minecraft:conduit")
    val BAMBOO_SAPLING: Key = Key("minecraft:bamboo_sapling")
    val BAMBOO: Key = Key("minecraft:bamboo")
    val POTTED_BAMBOO: Key = Key("minecraft:potted_bamboo")
    val VOID_AIR: Key = Key("minecraft:void_air")
    val CAVE_AIR: Key = Key("minecraft:cave_air")
    val BUBBLE_COLUMN: Key = Key("minecraft:bubble_column")
    val POLISHED_GRANITE_STAIRS: Key = Key("minecraft:polished_granite_stairs")
    val SMOOTH_RED_SANDSTONE_STAIRS: Key = Key("minecraft:smooth_red_sandstone_stairs")
    val MOSSY_STONE_BRICK_STAIRS: Key = Key("minecraft:mossy_stone_brick_stairs")
    val POLISHED_DIORITE_STAIRS: Key = Key("minecraft:polished_diorite_stairs")
    val MOSSY_COBBLESTONE_STAIRS: Key = Key("minecraft:mossy_cobblestone_stairs")
    val END_STONE_BRICK_STAIRS: Key = Key("minecraft:end_stone_brick_stairs")
    val STONE_STAIRS: Key = Key("minecraft:stone_stairs")
    val SMOOTH_SANDSTONE_STAIRS: Key = Key("minecraft:smooth_sandstone_stairs")
    val SMOOTH_QUARTZ_STAIRS: Key = Key("minecraft:smooth_quartz_stairs")
    val GRANITE_STAIRS: Key = Key("minecraft:granite_stairs")
    val ANDESITE_STAIRS: Key = Key("minecraft:andesite_stairs")
    val RED_NETHER_BRICK_STAIRS: Key = Key("minecraft:red_nether_brick_stairs")
    val POLISHED_ANDESITE_STAIRS: Key = Key("minecraft:polished_andesite_stairs")
    val DIORITE_STAIRS: Key = Key("minecraft:diorite_stairs")
    val POLISHED_GRANITE_SLAB: Key = Key("minecraft:polished_granite_slab")
    val SMOOTH_RED_SANDSTONE_SLAB: Key = Key("minecraft:smooth_red_sandstone_slab")
    val MOSSY_STONE_BRICK_SLAB: Key = Key("minecraft:mossy_stone_brick_slab")
    val POLISHED_DIORITE_SLAB: Key = Key("minecraft:polished_diorite_slab")
    val MOSSY_COBBLESTONE_SLAB: Key = Key("minecraft:mossy_cobblestone_slab")
    val END_STONE_BRICK_SLAB: Key = Key("minecraft:end_stone_brick_slab")
    val SMOOTH_SANDSTONE_SLAB: Key = Key("minecraft:smooth_sandstone_slab")
    val SMOOTH_QUARTZ_SLAB: Key = Key("minecraft:smooth_quartz_slab")
    val GRANITE_SLAB: Key = Key("minecraft:granite_slab")
    val ANDESITE_SLAB: Key = Key("minecraft:andesite_slab")
    val RED_NETHER_BRICK_SLAB: Key = Key("minecraft:red_nether_brick_slab")
    val POLISHED_ANDESITE_SLAB: Key = Key("minecraft:polished_andesite_slab")
    val DIORITE_SLAB: Key = Key("minecraft:diorite_slab")
    val BRICK_WALL: Key = Key("minecraft:brick_wall")
    val PRISMARINE_WALL: Key = Key("minecraft:prismarine_wall")
    val RED_SANDSTONE_WALL: Key = Key("minecraft:red_sandstone_wall")
    val MOSSY_STONE_BRICK_WALL: Key = Key("minecraft:mossy_stone_brick_wall")
    val GRANITE_WALL: Key = Key("minecraft:granite_wall")
    val STONE_BRICK_WALL: Key = Key("minecraft:stone_brick_wall")
    val NETHER_BRICK_WALL: Key = Key("minecraft:nether_brick_wall")
    val ANDESITE_WALL: Key = Key("minecraft:andesite_wall")
    val RED_NETHER_BRICK_WALL: Key = Key("minecraft:red_nether_brick_wall")
    val SANDSTONE_WALL: Key = Key("minecraft:sandstone_wall")
    val END_STONE_BRICK_WALL: Key = Key("minecraft:end_stone_brick_wall")
    val DIORITE_WALL: Key = Key("minecraft:diorite_wall")
    val SCAFFOLDING: Key = Key("minecraft:scaffolding")
    val LOOM: Key = Key("minecraft:loom")
    val BARREL: Key = Key("minecraft:barrel")
    val SMOKER: Key = Key("minecraft:smoker")
    val BLAST_FURNACE: Key = Key("minecraft:blast_furnace")
    val CARTOGRAPHY_TABLE: Key = Key("minecraft:cartography_table")
    val FLETCHING_TABLE: Key = Key("minecraft:fletching_table")
    val GRINDSTONE: Key = Key("minecraft:grindstone")
    val LECTERN: Key = Key("minecraft:lectern")
    val SMITHING_TABLE: Key = Key("minecraft:smithing_table")
    val STONECUTTER: Key = Key("minecraft:stonecutter")
    val BELL: Key = Key("minecraft:bell")
    val LANTERN: Key = Key("minecraft:lantern")
    val SOUL_LANTERN: Key = Key("minecraft:soul_lantern")
    val CAMPFIRE: Key = Key("minecraft:campfire")
    val SOUL_CAMPFIRE: Key = Key("minecraft:soul_campfire")
    val SWEET_BERRY_BUSH: Key = Key("minecraft:sweet_berry_bush")
    val WARPED_STEM: Key = Key("minecraft:warped_stem")
    val STRIPPED_WARPED_STEM: Key = Key("minecraft:stripped_warped_stem")
    val WARPED_HYPHAE: Key = Key("minecraft:warped_hyphae")
    val STRIPPED_WARPED_HYPHAE: Key = Key("minecraft:stripped_warped_hyphae")
    val WARPED_NYLIUM: Key = Key("minecraft:warped_nylium")
    val WARPED_FUNGUS: Key = Key("minecraft:warped_fungus")
    val WARPED_WART_BLOCK: Key = Key("minecraft:warped_wart_block")
    val WARPED_ROOTS: Key = Key("minecraft:warped_roots")
    val NETHER_SPROUTS: Key = Key("minecraft:nether_sprouts")
    val CRIMSON_STEM: Key = Key("minecraft:crimson_stem")
    val STRIPPED_CRIMSON_STEM: Key = Key("minecraft:stripped_crimson_stem")
    val CRIMSON_HYPHAE: Key = Key("minecraft:crimson_hyphae")
    val STRIPPED_CRIMSON_HYPHAE: Key = Key("minecraft:stripped_crimson_hyphae")
    val CRIMSON_NYLIUM: Key = Key("minecraft:crimson_nylium")
    val CRIMSON_FUNGUS: Key = Key("minecraft:crimson_fungus")
    val SHROOMLIGHT: Key = Key("minecraft:shroomlight")
    val WEEPING_VINES: Key = Key("minecraft:weeping_vines")
    val WEEPING_VINES_PLANT: Key = Key("minecraft:weeping_vines_plant")
    val TWISTING_VINES: Key = Key("minecraft:twisting_vines")
    val TWISTING_VINES_PLANT: Key = Key("minecraft:twisting_vines_plant")
    val CRIMSON_ROOTS: Key = Key("minecraft:crimson_roots")
    val CRIMSON_PLANKS: Key = Key("minecraft:crimson_planks")
    val WARPED_PLANKS: Key = Key("minecraft:warped_planks")
    val CRIMSON_SLAB: Key = Key("minecraft:crimson_slab")
    val WARPED_SLAB: Key = Key("minecraft:warped_slab")
    val CRIMSON_PRESSURE_PLATE: Key = Key("minecraft:crimson_pressure_plate")
    val WARPED_PRESSURE_PLATE: Key = Key("minecraft:warped_pressure_plate")
    val CRIMSON_FENCE: Key = Key("minecraft:crimson_fence")
    val WARPED_FENCE: Key = Key("minecraft:warped_fence")
    val CRIMSON_TRAPDOOR: Key = Key("minecraft:crimson_trapdoor")
    val WARPED_TRAPDOOR: Key = Key("minecraft:warped_trapdoor")
    val CRIMSON_FENCE_GATE: Key = Key("minecraft:crimson_fence_gate")
    val WARPED_FENCE_GATE: Key = Key("minecraft:warped_fence_gate")
    val CRIMSON_STAIRS: Key = Key("minecraft:crimson_stairs")
    val WARPED_STAIRS: Key = Key("minecraft:warped_stairs")
    val CRIMSON_BUTTON: Key = Key("minecraft:crimson_button")
    val WARPED_BUTTON: Key = Key("minecraft:warped_button")
    val CRIMSON_DOOR: Key = Key("minecraft:crimson_door")
    val WARPED_DOOR: Key = Key("minecraft:warped_door")
    val CRIMSON_SIGN: Key = Key("minecraft:crimson_sign")
    val WARPED_SIGN: Key = Key("minecraft:warped_sign")
    val CRIMSON_WALL_SIGN: Key = Key("minecraft:crimson_wall_sign")
    val WARPED_WALL_SIGN: Key = Key("minecraft:warped_wall_sign")
    val STRUCTURE_BLOCK: Key = Key("minecraft:structure_block")
    val JIGSAW: Key = Key("minecraft:jigsaw")
    val COMPOSTER: Key = Key("minecraft:composter")
    val TARGET: Key = Key("minecraft:target")
    val BEE_NEST: Key = Key("minecraft:bee_nest")
    val BEEHIVE: Key = Key("minecraft:beehive")
    val HONEY_BLOCK: Key = Key("minecraft:honey_block")
    val HONEYCOMB_BLOCK: Key = Key("minecraft:honeycomb_block")
    val NETHERITE_BLOCK: Key = Key("minecraft:netherite_block")
    val ANCIENT_DEBRIS: Key = Key("minecraft:ancient_debris")
    val CRYING_OBSIDIAN: Key = Key("minecraft:crying_obsidian")
    val RESPAWN_ANCHOR: Key = Key("minecraft:respawn_anchor")
    val POTTED_CRIMSON_FUNGUS: Key = Key("minecraft:potted_crimson_fungus")
    val POTTED_WARPED_FUNGUS: Key = Key("minecraft:potted_warped_fungus")
    val POTTED_CRIMSON_ROOTS: Key = Key("minecraft:potted_crimson_roots")
    val POTTED_WARPED_ROOTS: Key = Key("minecraft:potted_warped_roots")
    val LODESTONE: Key = Key("minecraft:lodestone")
    val BLACKSTONE: Key = Key("minecraft:blackstone")
    val BLACKSTONE_STAIRS: Key = Key("minecraft:blackstone_stairs")
    val BLACKSTONE_WALL: Key = Key("minecraft:blackstone_wall")
    val BLACKSTONE_SLAB: Key = Key("minecraft:blackstone_slab")
    val POLISHED_BLACKSTONE: Key = Key("minecraft:polished_blackstone")
    val POLISHED_BLACKSTONE_BRICKS: Key = Key("minecraft:polished_blackstone_bricks")
    val CRACKED_POLISHED_BLACKSTONE_BRICKS: Key = Key("minecraft:cracked_polished_blackstone_bricks")
    val CHISELED_POLISHED_BLACKSTONE: Key = Key("minecraft:chiseled_polished_blackstone")
    val POLISHED_BLACKSTONE_BRICK_SLAB: Key = Key("minecraft:polished_blackstone_brick_slab")
    val POLISHED_BLACKSTONE_BRICK_STAIRS: Key = Key("minecraft:polished_blackstone_brick_stairs")
    val POLISHED_BLACKSTONE_BRICK_WALL: Key = Key("minecraft:polished_blackstone_brick_wall")
    val GILDED_BLACKSTONE: Key = Key("minecraft:gilded_blackstone")
    val POLISHED_BLACKSTONE_STAIRS: Key = Key("minecraft:polished_blackstone_stairs")
    val POLISHED_BLACKSTONE_SLAB: Key = Key("minecraft:polished_blackstone_slab")
    val POLISHED_BLACKSTONE_PRESSURE_PLATE: Key = Key("minecraft:polished_blackstone_pressure_plate")
    val POLISHED_BLACKSTONE_BUTTON: Key = Key("minecraft:polished_blackstone_button")
    val POLISHED_BLACKSTONE_WALL: Key = Key("minecraft:polished_blackstone_wall")
    val CHISELED_NETHER_BRICKS: Key = Key("minecraft:chiseled_nether_bricks")
    val CRACKED_NETHER_BRICKS: Key = Key("minecraft:cracked_nether_bricks")
    val QUARTZ_BRICKS: Key = Key("minecraft:quartz_bricks")
    val CANDLE: Key = Key("minecraft:candle")
    val WHITE_CANDLE: Key = Key("minecraft:white_candle")
    val ORANGE_CANDLE: Key = Key("minecraft:orange_candle")
    val MAGENTA_CANDLE: Key = Key("minecraft:magenta_candle")
    val LIGHT_BLUE_CANDLE: Key = Key("minecraft:light_blue_candle")
    val YELLOW_CANDLE: Key = Key("minecraft:yellow_candle")
    val LIME_CANDLE: Key = Key("minecraft:lime_candle")
    val PINK_CANDLE: Key = Key("minecraft:pink_candle")
    val GRAY_CANDLE: Key = Key("minecraft:gray_candle")
    val LIGHT_GRAY_CANDLE: Key = Key("minecraft:light_gray_candle")
    val CYAN_CANDLE: Key = Key("minecraft:cyan_candle")
    val PURPLE_CANDLE: Key = Key("minecraft:purple_candle")
    val BLUE_CANDLE: Key = Key("minecraft:blue_candle")
    val BROWN_CANDLE: Key = Key("minecraft:brown_candle")
    val GREEN_CANDLE: Key = Key("minecraft:green_candle")
    val RED_CANDLE: Key = Key("minecraft:red_candle")
    val BLACK_CANDLE: Key = Key("minecraft:black_candle")
    val CANDLE_CAKE: Key = Key("minecraft:candle_cake")
    val WHITE_CANDLE_CAKE: Key = Key("minecraft:white_candle_cake")
    val ORANGE_CANDLE_CAKE: Key = Key("minecraft:orange_candle_cake")
    val MAGENTA_CANDLE_CAKE: Key = Key("minecraft:magenta_candle_cake")
    val LIGHT_BLUE_CANDLE_CAKE: Key = Key("minecraft:light_blue_candle_cake")
    val YELLOW_CANDLE_CAKE: Key = Key("minecraft:yellow_candle_cake")
    val LIME_CANDLE_CAKE: Key = Key("minecraft:lime_candle_cake")
    val PINK_CANDLE_CAKE: Key = Key("minecraft:pink_candle_cake")
    val GRAY_CANDLE_CAKE: Key = Key("minecraft:gray_candle_cake")
    val LIGHT_GRAY_CANDLE_CAKE: Key = Key("minecraft:light_gray_candle_cake")
    val CYAN_CANDLE_CAKE: Key = Key("minecraft:cyan_candle_cake")
    val PURPLE_CANDLE_CAKE: Key = Key("minecraft:purple_candle_cake")
    val BLUE_CANDLE_CAKE: Key = Key("minecraft:blue_candle_cake")
    val BROWN_CANDLE_CAKE: Key = Key("minecraft:brown_candle_cake")
    val GREEN_CANDLE_CAKE: Key = Key("minecraft:green_candle_cake")
    val RED_CANDLE_CAKE: Key = Key("minecraft:red_candle_cake")
    val BLACK_CANDLE_CAKE: Key = Key("minecraft:black_candle_cake")
    val AMETHYST_BLOCK: Key = Key("minecraft:amethyst_block")
    val BUDDING_AMETHYST: Key = Key("minecraft:budding_amethyst")
    val AMETHYST_CLUSTER: Key = Key("minecraft:amethyst_cluster")
    val LARGE_AMETHYST_BUD: Key = Key("minecraft:large_amethyst_bud")
    val MEDIUM_AMETHYST_BUD: Key = Key("minecraft:medium_amethyst_bud")
    val SMALL_AMETHYST_BUD: Key = Key("minecraft:small_amethyst_bud")
    val TUFF: Key = Key("minecraft:tuff")
    val CALCITE: Key = Key("minecraft:calcite")
    val TINTED_GLASS: Key = Key("minecraft:tinted_glass")
    val POWDER_SNOW: Key = Key("minecraft:powder_snow")
    val SCULK_SENSOR: Key = Key("minecraft:sculk_sensor")
    val OXIDIZED_COPPER: Key = Key("minecraft:oxidized_copper")
    val WEATHERED_COPPER: Key = Key("minecraft:weathered_copper")
    val EXPOSED_COPPER: Key = Key("minecraft:exposed_copper")
    val COPPER_BLOCK: Key = Key("minecraft:copper_block")
    val COPPER_ORE: Key = Key("minecraft:copper_ore")
    val DEEPSLATE_COPPER_ORE: Key = Key("minecraft:deepslate_copper_ore")
    val OXIDIZED_CUT_COPPER: Key = Key("minecraft:oxidized_cut_copper")
    val WEATHERED_CUT_COPPER: Key = Key("minecraft:weathered_cut_copper")
    val EXPOSED_CUT_COPPER: Key = Key("minecraft:exposed_cut_copper")
    val CUT_COPPER: Key = Key("minecraft:cut_copper")
    val OXIDIZED_CUT_COPPER_STAIRS: Key = Key("minecraft:oxidized_cut_copper_stairs")
    val WEATHERED_CUT_COPPER_STAIRS: Key = Key("minecraft:weathered_cut_copper_stairs")
    val EXPOSED_CUT_COPPER_STAIRS: Key = Key("minecraft:exposed_cut_copper_stairs")
    val CUT_COPPER_STAIRS: Key = Key("minecraft:cut_copper_stairs")
    val OXIDIZED_CUT_COPPER_SLAB: Key = Key("minecraft:oxidized_cut_copper_slab")
    val WEATHERED_CUT_COPPER_SLAB: Key = Key("minecraft:weathered_cut_copper_slab")
    val EXPOSED_CUT_COPPER_SLAB: Key = Key("minecraft:exposed_cut_copper_slab")
    val CUT_COPPER_SLAB: Key = Key("minecraft:cut_copper_slab")
    val WAXED_COPPER_BLOCK: Key = Key("minecraft:waxed_copper_block")
    val WAXED_WEATHERED_COPPER: Key = Key("minecraft:waxed_weathered_copper")
    val WAXED_EXPOSED_COPPER: Key = Key("minecraft:waxed_exposed_copper")
    val WAXED_OXIDIZED_COPPER: Key = Key("minecraft:waxed_oxidized_copper")
    val WAXED_OXIDIZED_CUT_COPPER: Key = Key("minecraft:waxed_oxidized_cut_copper")
    val WAXED_WEATHERED_CUT_COPPER: Key = Key("minecraft:waxed_weathered_cut_copper")
    val WAXED_EXPOSED_CUT_COPPER: Key = Key("minecraft:waxed_exposed_cut_copper")
    val WAXED_CUT_COPPER: Key = Key("minecraft:waxed_cut_copper")
    val WAXED_OXIDIZED_CUT_COPPER_STAIRS: Key = Key("minecraft:waxed_oxidized_cut_copper_stairs")
    val WAXED_WEATHERED_CUT_COPPER_STAIRS: Key = Key("minecraft:waxed_weathered_cut_copper_stairs")
    val WAXED_EXPOSED_CUT_COPPER_STAIRS: Key = Key("minecraft:waxed_exposed_cut_copper_stairs")
    val WAXED_CUT_COPPER_STAIRS: Key = Key("minecraft:waxed_cut_copper_stairs")
    val WAXED_OXIDIZED_CUT_COPPER_SLAB: Key = Key("minecraft:waxed_oxidized_cut_copper_slab")
    val WAXED_WEATHERED_CUT_COPPER_SLAB: Key = Key("minecraft:waxed_weathered_cut_copper_slab")
    val WAXED_EXPOSED_CUT_COPPER_SLAB: Key = Key("minecraft:waxed_exposed_cut_copper_slab")
    val WAXED_CUT_COPPER_SLAB: Key = Key("minecraft:waxed_cut_copper_slab")
    val LIGHTNING_ROD: Key = Key("minecraft:lightning_rod")
    val POINTED_DRIPSTONE: Key = Key("minecraft:pointed_dripstone")
    val DRIPSTONE_BLOCK: Key = Key("minecraft:dripstone_block")
    val CAVE_VINES: Key = Key("minecraft:cave_vines")
    val CAVE_VINES_PLANT: Key = Key("minecraft:cave_vines_plant")
    val SPORE_BLOSSOM: Key = Key("minecraft:spore_blossom")
    val AZALEA: Key = Key("minecraft:azalea")
    val FLOWERING_AZALEA: Key = Key("minecraft:flowering_azalea")
    val MOSS_CARPET: Key = Key("minecraft:moss_carpet")
    val MOSS_BLOCK: Key = Key("minecraft:moss_block")
    val BIG_DRIPLEAF: Key = Key("minecraft:big_dripleaf")
    val BIG_DRIPLEAF_STEM: Key = Key("minecraft:big_dripleaf_stem")
    val SMALL_DRIPLEAF: Key = Key("minecraft:small_dripleaf")
    val HANGING_ROOTS: Key = Key("minecraft:hanging_roots")
    val ROOTED_DIRT: Key = Key("minecraft:rooted_dirt")
    val DEEPSLATE: Key = Key("minecraft:deepslate")
    val COBBLED_DEEPSLATE: Key = Key("minecraft:cobbled_deepslate")
    val COBBLED_DEEPSLATE_STAIRS: Key = Key("minecraft:cobbled_deepslate_stairs")
    val COBBLED_DEEPSLATE_SLAB: Key = Key("minecraft:cobbled_deepslate_slab")
    val COBBLED_DEEPSLATE_WALL: Key = Key("minecraft:cobbled_deepslate_wall")
    val POLISHED_DEEPSLATE: Key = Key("minecraft:polished_deepslate")
    val POLISHED_DEEPSLATE_STAIRS: Key = Key("minecraft:polished_deepslate_stairs")
    val POLISHED_DEEPSLATE_SLAB: Key = Key("minecraft:polished_deepslate_slab")
    val POLISHED_DEEPSLATE_WALL: Key = Key("minecraft:polished_deepslate_wall")
    val DEEPSLATE_TILES: Key = Key("minecraft:deepslate_tiles")
    val DEEPSLATE_TILE_STAIRS: Key = Key("minecraft:deepslate_tile_stairs")
    val DEEPSLATE_TILE_SLAB: Key = Key("minecraft:deepslate_tile_slab")
    val DEEPSLATE_TILE_WALL: Key = Key("minecraft:deepslate_tile_wall")
    val DEEPSLATE_BRICKS: Key = Key("minecraft:deepslate_bricks")
    val DEEPSLATE_BRICK_STAIRS: Key = Key("minecraft:deepslate_brick_stairs")
    val DEEPSLATE_BRICK_SLAB: Key = Key("minecraft:deepslate_brick_slab")
    val DEEPSLATE_BRICK_WALL: Key = Key("minecraft:deepslate_brick_wall")
    val CHISELED_DEEPSLATE: Key = Key("minecraft:chiseled_deepslate")
    val CRACKED_DEEPSLATE_BRICKS: Key = Key("minecraft:cracked_deepslate_bricks")
    val CRACKED_DEEPSLATE_TILES: Key = Key("minecraft:cracked_deepslate_tiles")
    val INFESTED_DEEPSLATE: Key = Key("minecraft:infested_deepslate")
    val SMOOTH_BASALT: Key = Key("minecraft:smooth_basalt")
    val RAW_IRON_BLOCK: Key = Key("minecraft:raw_iron_block")
    val RAW_COPPER_BLOCK: Key = Key("minecraft:raw_copper_block")
    val RAW_GOLD_BLOCK: Key = Key("minecraft:raw_gold_block")
    val POTTED_AZALEA_BUSH: Key = Key("minecraft:potted_azalea_bush")
    val POTTED_FLOWERING_AZALEA_BUSH: Key = Key("minecraft:potted_flowering_azalea_bush")
}