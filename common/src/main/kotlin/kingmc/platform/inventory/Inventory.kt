package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Interface refer an inventory in minecraft
 *
 * @since 0.0.3
 * @author kingsthere
 */
interface Inventory : Iterable<ItemStack> {
}