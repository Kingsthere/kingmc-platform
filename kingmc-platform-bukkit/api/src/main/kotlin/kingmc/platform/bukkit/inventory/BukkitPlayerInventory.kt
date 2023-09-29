package kingmc.platform.bukkit.inventory

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.inventory.MutablePlayerInventory
import kingmc.platform.inventory.PlayerInventory

/**
 * Extended `PlayerInventory` capable to convert into a [org.bukkit.inventory.CraftingInventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface BukkitPlayerInventory : PlayerInventory, BukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.CraftingInventory]
     */
    fun toBukkitPlayerInventory(): _BukkitPlayerInventory
}

/**
 * Extended `MutableCraftingInventory` capable to convert into a [org.bukkit.inventory.CraftingInventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface MutableBukkitPlayerInventory : MutablePlayerInventory, MutableBukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.PlayerInventory]
     */
    fun toBukkitPlayerInventory(): _BukkitPlayerInventory
}