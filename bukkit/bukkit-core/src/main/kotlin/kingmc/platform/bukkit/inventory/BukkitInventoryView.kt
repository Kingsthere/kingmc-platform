package kingmc.platform.bukkit.inventory

import kingmc.platform.inventory.InventoryView

/**
 * A `InventoryView` capable to convert into a [org.bukkit.inventory.InventoryView]
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface BukkitInventoryView : InventoryView {
    /**
     * Convert this `InventoryView` to a [org.bukkit.inventory.InventoryView]
     */
    fun toBukkitInventoryView(): _BukkitInventoryView
}