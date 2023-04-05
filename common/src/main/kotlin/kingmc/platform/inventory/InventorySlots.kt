package kingmc.platform.inventory

import kingmc.platform.SinceMinecraft

/**
 * An enum list [InventorySlot]s
 *
 * @since 0.0.6
 * @author kingsthere
 */
enum class InventorySlots : InventorySlot {
    /**
     * `hotbar.0
     */
    HOTBAR_0,

    /**
     * `hotbar.1`
     */
    HOTBAR_1,

    /**
     * `hotbar.2`
     */
    HOTBAR_2,

    /**
     * `hotbar.3`
     */
    HOTBAR_3,

    /**
     * `hotbar.4`
     */
    HOTBAR_4,

    /**
     * `hotbar.5`
     */
    HOTBAR_5,

    /**
     * `hotbar.6`
     */
    HOTBAR_6,

    /**
     * `hotbar.7`
     */
    HOTBAR_7,

    /**
     * `hotbar.8`
     */
    HOTBAR_8,

    /**
     * `container.0`
     */
    CONTAINER_0,

    /**
     * `container.1`
     */
    CONTAINER_1,

    /**
     * `container.2`
     */
    CONTAINER_2,

    /**
     * `container.3`
     */
    CONTAINER_3,

    /**
     * `container.4`
     */
    CONTAINER_4,

    /**
     * `container.5`
     */
    CONTAINER_5,

    /**
     * `container.6`
     */
    CONTAINER_6,

    /**
     * `container.7`
     */
    CONTAINER_7,

    /**
     * `container.8`
     */
    CONTAINER_8,

    /**
     * `container.9`
     */
    CONTAINER_9,

    /**
     * `container.10`
     */
    CONTAINER_10,

    /**
     * `container.11`
     */
    CONTAINER_11,

    /**
     * `container.12`
     */
    CONTAINER_12,

    /**
     * `container.13`
     */
    CONTAINER_13,

    /**
     * `container.14`
     */
    CONTAINER_14,

    /**
     * `container.15`
     */
    CONTAINER_15,

    /**
     * `container.16`
     */
    CONTAINER_16,

    /**
     * `container.17`
     */
    CONTAINER_17,

    /**
     * `container.18`
     */
    CONTAINER_18,

    /**
     * `container.19`
     */
    CONTAINER_19,

    /**
     * `container.20`
     */
    CONTAINER_20,

    /**
     * `container.21`
     */
    CONTAINER_21,

    /**
     * `container.22`
     */
    CONTAINER_22,

    /**
     * `container.23`
     */
    CONTAINER_23,

    /**
     * `container.24`
     */
    CONTAINER_24,

    /**
     * `container.25`
     */
    CONTAINER_25,

    /**
     * `container.26`
     */
    CONTAINER_26,

    /**
     * `container.27`
     */
    CONTAINER_27,

    /**
     * `container.28`
     */
    CONTAINER_28,

    /**
     * `container.29`
     */
    CONTAINER_29,

    /**
     * `container.30`
     */
    CONTAINER_30,

    /**
     * `container.31`
     */
    CONTAINER_31,

    /**
     * `container.32`
     */
    CONTAINER_32,

    /**
     * `container.33`
     */
    CONTAINER_33,

    /**
     * `container.34`
     */
    CONTAINER_34,

    /**
     * `container.35`
     */
    CONTAINER_35,

    /**
     * `container.36`
     */
    CONTAINER_36,

    /**
     * `container.37`
     */
    CONTAINER_37,

    /**
     * `container.38`
     */
    CONTAINER_38,

    /**
     * `container.39`
     */
    CONTAINER_39,

    /**
     * `container.40`
     */
    CONTAINER_40,

    /**
     * `container.41`
     */
    CONTAINER_41,

    /**
     * `container.42`
     */
    CONTAINER_42,

    /**
     * `container.43`
     */
    CONTAINER_43,

    /**
     * `container.44`
     */
    CONTAINER_44,

    /**
     * `container.45`
     */
    CONTAINER_45,

    /**
     * `container.46`
     */
    CONTAINER_46,

    /**
     * `container.47`
     */
    CONTAINER_47,

    /**
     * `container.48`
     */
    CONTAINER_48,

    /**
     * `container.49`
     */
    CONTAINER_49,

    /**
     * `container.50`
     */
    CONTAINER_50,

    /**
     * `container.51`
     */
    CONTAINER_51,

    /**
     * `container.52`
     */
    CONTAINER_52,

    /**
     * `container.53`
     */
    CONTAINER_53,

    /**
     * `armor.head`
     */
    ARMOR_HEAD,

    /**
     * `armor.chest`
     */
    ARMOR_CHEST,

    /**
     * `armor.legs`
     */
    ARMOR_LEGS,

    /**
     * `armor.feet`
     */
    ARMOR_FEET,

    /**
     * `enderchest.0`
     */
    ENDERCHEST_0,

    /**
     * `enderchest.1`
     */
    ENDERCHEST_1,

    /**
     * `enderchest.2`
     */
    ENDERCHEST_2,

    /**
     * `enderchest.3`
     */
    ENDERCHEST_3,

    /**
     * `enderchest.4`
     */
    ENDERCHEST_4,

    /**
     * `enderchest.5`
     */
    ENDERCHEST_5,

    /**
     * `enderchest.6`
     */
    ENDERCHEST_6,

    /**
     * `enderchest.7`
     */
    ENDERCHEST_7,

    /**
     * `enderchest.8`
     */
    ENDERCHEST_8,

    /**
     * `enderchest.9`
     */
    ENDERCHEST_9,

    /**
     * `enderchest.10`
     */
    ENDERCHEST_10,

    /**
     * `enderchest.11`
     */
    ENDERCHEST_11,

    /**
     * `enderchest.12`
     */
    ENDERCHEST_12,

    /**
     * `enderchest.13`
     */
    ENDERCHEST_13,

    /**
     * `enderchest.14`
     */
    ENDERCHEST_14,

    /**
     * `enderchest.15`
     */
    ENDERCHEST_15,

    /**
     * `enderchest.16`
     */
    ENDERCHEST_16,

    /**
     * `enderchest.17`
     */
    ENDERCHEST_17,

    /**
     * `enderchest.18`
     */
    ENDERCHEST_18,

    /**
     * `enderchest.19`
     */
    ENDERCHEST_19,

    /**
     * `enderchest.20`
     */
    ENDERCHEST_20,

    /**
     * `enderchest.21`
     */
    ENDERCHEST_21,

    /**
     * `enderchest.22`
     */
    ENDERCHEST_22,

    /**
     * `enderchest.23`
     */
    ENDERCHEST_23,

    /**
     * `enderchest.24`
     */
    ENDERCHEST_24,

    /**
     * `enderchest.25`
     */
    ENDERCHEST_25,

    /**
     * `enderchest.26`
     */
    ENDERCHEST_26,

    /**
     * `weapon`
     */
    WEAPON,

    /**
     * `weapon.mainhand`
     */
    WEAPON_MAINHAND,

    /**
     * `weapon.offhand`
     */
    @SinceMinecraft(minecraftVersion = "1.9..")
    WEAPON_OFFHAND,

    /**
     * `villager.0`
     */
    VILLAGER_0,

    /**
     * `villager.1`
     */
    VILLAGER_1,

    /**
     * `villager.2`
     */
    VILLAGER_2,

    /**
     * `villager.3`
     */
    VILLAGER_3,

    /**
     * `villager.4`
     */
    VILLAGER_4,

    /**
     * `villager.5`
     */
    VILLAGER_5,

    /**
     * `villager.6`
     */
    VILLAGER_6,

    /**
     * `villager.7`
     */
    VILLAGER_7,

    /**
     * `horse.0`
     */
    HORSE_0,

    /**
     * `horse.1`
     */
    HORSE_1,

    /**
     * `horse.2`
     */
    HORSE_2,

    /**
     * `horse.3`
     */
    HORSE_3,

    /**
     * `horse.4`
     */
    HORSE_4,

    /**
     * `horse.5`
     */
    HORSE_5,

    /**
     * `horse.6`
     */
    HORSE_6,

    /**
     * `horse.7`
     */
    HORSE_7,

    /**
     * `horse.8`
     */
    HORSE_8,

    /**
     * `horse.9`
     */
    HORSE_9,

    /**
     * `horse.10`
     */
    HORSE_10,

    /**
     * `horse.11`
     */
    HORSE_11,

    /**
     * `horse.12`
     */
    HORSE_12,

    /**
     * `horse.13`
     */
    HORSE_13,

    /**
     * `horse.14`
     */
    HORSE_14,

    /**
     * `horse.armor`
     */
    HORSE_ARMOR,

    /**
     * `horse.chest`
     */
    HORSE_CHEST,

    /**
     * `horse.saddle`
     */
    HORSE_SADDLE,

    /**
     * `llama.decor`
     */
    LLAMA_DECOR,

    /**
     * `llama.saddle`
     */
    LLAMA_SADDLE,

    /**
     * `crafting.matrix.1`
     */
    CRAFTING_MATRIX_1,

    /**
     * `crafting.matrix.2`
     */
    CRAFTING_MATRIX_2,

    /**
     * `crafting.matrix.3`
     */
    CRAFTING_MATRIX_3,

    /**
     * `crafting.matrix.4`
     */
    CRAFTING_MATRIX_4,

    /**
     * `crafting.result`
     */
    CRAFTING_RESULT,

    /**
     * `anvil.left`
     */
    ANVIL_LEFT,

    /**
     * `beacon`
     */
    BEACON,
}