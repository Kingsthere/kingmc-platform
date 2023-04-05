package kingmc.platform.bukkit.item

import kingmc.platform.item.Item
import kingmc.platform.item.ItemStack

/**
 * Extended [ItemStack] represent an item stack capable to convert to a [org.bukkit.inventory.ItemStack]
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface BukkitItemStack : ItemStack {
    /**
     * Convert this `ItemStack` to a [org.bukkit.inventory.ItemStack]
     *
     * @see Item
     */
    fun toBukkitItemStack(): _BukkitItemStack
}