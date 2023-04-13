package kingmc.platform.bukkit.inventory

import kingmc.common.application.Application
import kingmc.platform.inventory.Inventory
import kingmc.platform.inventory.InventoryView
import kingmc.platform.inventory.MainHand
import kingmc.platform.inventory.PlayerInventory
import kingmc.platform.inventoryFactory

/**
 * Convert this [org.bukkit.inventory.MainHand] to a `MainHand`
 *
 * @receiver the mainhand to convert to
 * @since 0.0.8
 * @author kingsthere
 */
fun _BukkitMainHand.asKingMC(): MainHand = MainHand.valueOf(this.name)

/**
 * Convert this bukkit inventory to an [Inventory]
 *
 * @receiver the inventory to convert to
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitInventory.asKingMC(application: Application): Inventory {
    return (application.inventoryFactory as BukkitInventoryFactory).createInventoryForBukkit(this)
}

/**
 * Convert this bukkit player inventory to an [PlayerInventory]
 *
 * @receiver the inventory to convert to
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitPlayerInventory.asKingMC(application: Application): PlayerInventory {
    return (application.inventoryFactory as BukkitInventoryFactory).createPlayerInventoryForBukkit(this)
}

/**
 * Convert this bukkit player inventory to an [PlayerInventory]
 *
 * @receiver the inventory to convert to
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitInventoryView.asKingMC(application: Application): InventoryView {
    return (application.inventoryFactory as BukkitInventoryFactory).createInventoryViewForBukkit(this)
}

/**
 * Convert this [Inventory] to a bukkit inventory
 *
 * @receiver the inventory to convert to
 * @since 0.0.8
 * @author kingsthere
 */
fun Inventory.asBukkit(): _BukkitInventory {
    return (this as BukkitInventory).toBukkitInventory()
}

/**
 * Convert this [Inventory] to a bukkit inventory
 *
 * @receiver the inventory to convert to
 * @since 0.0.8
 * @author kingsthere
 */
fun PlayerInventory.asBukkit(): _BukkitPlayerInventory {
    return (this as BukkitPlayerInventory).toBukkitPlayerInventory()
}

/**
 * Convert this [InventoryView] to a bukkit inventory view
 *
 * @receiver the inventory to convert to
 * @since 0.0.8
 * @author kingsthere
 */
fun InventoryView.asBukkit(): _BukkitInventoryView {
    return (this as BukkitInventoryView).toBukkitInventoryView()
}