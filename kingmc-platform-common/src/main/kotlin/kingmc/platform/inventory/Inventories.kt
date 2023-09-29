package kingmc.platform.inventory

import kingmc.platform.item.ItemStack

/**
 * Add multiple `ItemStack`s into this inventory
 */
fun MutableInventory.add(vararg itemStack: ItemStack) {
    itemStack.forEach {
        this.add(it)
    }
}

/**
 * Add a list of `ItemStack`s into this inventory
 */
fun MutableInventory.addAll(itemStacks: List<ItemStack>) {
    itemStacks.forEach {
        this.add(it)
    }
}

/**
 * Check if this `Inventory` is empty (`size = 0`)
 */
fun Inventory.isEmpty(): Boolean =
    this.size == 0
