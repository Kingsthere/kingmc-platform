package kingmc.platform.bukkit.item

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.item.ItemFactory
import kingmc.platform.item.ItemStack

/**
 * A `ItemFactory` capable to create `ItemStack` instances for [org.bukkit.inventory.ItemStack]
 *
 * @since 0.0.8
 * @author kingsthere
 * @see ItemStack
 */
@Component
@BukkitImplementation
interface BukkitItemFactory : ItemFactory {
    /**
     * Create an `ItemStack` for [org.bukkit.inventory.ItemStack]
     *
     * @param itemStack [org.bukkit.inventory.ItemStack]
     * @return the item stack created
     */
    fun createItemStackForBukkit(itemStack: _BukkitItemStack): ItemStack
}