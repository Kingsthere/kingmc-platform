package kingmc.platform.bukkit.item

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.item.ItemStack
import kingmc.platform.item.itemFactory

/**
 * Create an `ItemStack` for specified [bukkitItemStack]
 *
 * @author kingsthere
 * @since 0.0.8
 */
@WithApplication
fun ItemStack(bukkitItemStack: _BukkitItemStack): ItemStack {
    return (currentApplication().itemFactory as BukkitItemFactory).createItemStackForBukkit(bukkitItemStack)
}