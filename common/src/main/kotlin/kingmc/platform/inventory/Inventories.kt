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

/**
 * Set this an item into this inventory if this is a [MutableInventory]
 */
operator fun Inventory.set(slot: InventorySlot, itemStack: ItemStack) {
    if (this is MutableInventory) {
        put(slot, itemStack)
    } else {
        throw UnsupportedOperationException("This inventory is not mutable")
    }
}

/**
 * `InventorySlots.CRAFTING_RESULT`
 */
var CraftingInventory.result: ItemStack
    get() = get(InventorySlots.CRAFTING_RESULT)
    set(value) {
        this[InventorySlots.CRAFTING_RESULT] = value
    }