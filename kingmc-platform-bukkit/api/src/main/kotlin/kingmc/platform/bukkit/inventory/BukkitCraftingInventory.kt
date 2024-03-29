package kingmc.platform.bukkit.inventory

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.inventory.CraftingInventory
import kingmc.platform.inventory.MutableCraftingInventory

/**
 * Extended `CraftingInventory` capable to convert into a [org.bukkit.inventory.CraftingInventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface BukkitCraftingInventory : CraftingInventory, BukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.CraftingInventory]
     */
    fun toBukkitCraftingInventory(): _BukkitCraftingInventory
}

/**
 * Extended `MutableCraftingInventory` capable to convert into a [org.bukkit.inventory.CraftingInventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface MutableBukkitCraftingInventory : MutableCraftingInventory, MutableBukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.CraftingInventory]
     */
    fun toBukkitCraftingInventory(): _BukkitCraftingInventory
}