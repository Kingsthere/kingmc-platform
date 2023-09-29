package kingmc.platform.bukkit.inventory

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.MutableInventory

/**
 * Extended `Inventory` capable to convert into a [org.bukkit.inventory.Inventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface BukkitInventory : Inventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.Inventory]
     */
    fun toBukkitInventory(): _BukkitInventory
}

/**
 * Extended `MutableInventory` capable to convert into a [org.bukkit.inventory.Inventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface MutableBukkitInventory : MutableInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.Inventory]
     */
    fun toBukkitInventory(): _BukkitInventory
}