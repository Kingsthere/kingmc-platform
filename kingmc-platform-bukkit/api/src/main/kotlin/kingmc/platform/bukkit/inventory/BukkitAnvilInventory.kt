package kingmc.platform.bukkit.inventory

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.inventory.AnvilInventory
import kingmc.platform.inventory.MutableAnvilInventory

/**
 * Extended `AnvilInventory` capable to convert into a [org.bukkit.inventory.AnvilInventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface BukkitAnvilInventory : AnvilInventory, BukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.AnvilInventory]
     */
    fun toBukkitAnvilInventory(): _BukkitAnvilInventory
}

/**
 * Extended `MutableAnvilInventory` capable to convert into a [org.bukkit.inventory.AnvilInventory]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
interface MutableBukkitAnvilInventory : MutableAnvilInventory, MutableBukkitInventory {
    /**
     * Convert this `BukkitInventory` to a [org.bukkit.inventory.AnvilInventory]
     */
    fun toBukkitAnvilInventory(): _BukkitAnvilInventory
}