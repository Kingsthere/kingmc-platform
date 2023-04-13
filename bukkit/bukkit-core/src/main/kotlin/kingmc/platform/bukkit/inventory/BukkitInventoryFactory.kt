package kingmc.platform.bukkit.inventory

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.inventory.InventoryFactory
import kingmc.platform.inventory.InventoryView

/**
 * A `InventoryFactory` capable to convert [org.bukkit.inventory.Inventory] to `Inventory`
 *
 * @since 0.0.6
 * @author kingsthere
 */
@BukkitImplementation
@Component
interface BukkitInventoryFactory : InventoryFactory {
    /**
     * Create a (chest) inventory for bukkit and return
     *
     * @param inventory bukkit inventory
     * @return the inventory created
     */
    fun createInventoryForBukkit(inventory: _BukkitInventory): BukkitInventory
    /**
     * Create a player inventory for bukkit and return
     *
     * @param inventory bukkit inventory
     * @return the inventory created
     */
    fun createPlayerInventoryForBukkit(inventory: _BukkitPlayerInventory): BukkitPlayerInventory

    /**
     * Create a anvil inventory for bukkit and return
     *
     * @param inventory bukkit inventory
     * @return the inventory created
     */
     fun createAnvilInventoryForBukkit(inventory: _BukkitAnvilInventory): BukkitAnvilInventory

    /**
     * Create a crafting inventory for bukkit and return
     *
     * @param inventory bukkit inventory
     * @return the inventory created
     */
    fun createCraftingInventoryForBukkit(inventory: _BukkitCraftingInventory): BukkitCraftingInventory

    /**
     * Create a inventory view for bukkit inventory view
     *
     * @param inventory bukkit inventory
     * @return the inventory created
     */
    fun createInventoryViewForBukkit(inventory: _BukkitInventoryView): InventoryView
}