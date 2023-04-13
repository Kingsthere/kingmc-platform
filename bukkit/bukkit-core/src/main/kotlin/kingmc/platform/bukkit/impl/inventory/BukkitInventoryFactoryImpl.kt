package kingmc.platform.bukkit.impl.inventory

import kingmc.common.context.annotation.Component
import kingmc.common.text.Text
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.inventory.*
import kingmc.platform.inventory.*

/**
 * Bukkit [BukkitInventoryFactory] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
@BukkitImplementation
@Component
class BukkitInventoryFactoryImpl : BukkitInventoryFactory {
    /**
     * Create a (chest) inventory for bukkit and return
     *
     * @param inventory bukkit side inventory
     * @return the inventory created
     */
    override fun createInventoryForBukkit(inventory: _BukkitInventory): BukkitInventory {
        TODO("Not yet implemented")
    }

    /**
     * Create a player inventory for bukkit and return
     *
     * @param inventory bukkit inventory
     * @return the inventory created
     */
    override fun createPlayerInventoryForBukkit(inventory: _BukkitPlayerInventory): BukkitPlayerInventory {
        TODO("Not yet implemented")
    }

    /**
     * Create a anvil inventory for bukkit and return
     *
     * @param inventory bukkit side inventory
     * @return the inventory created
     */
    override fun createAnvilInventoryForBukkit(inventory: _BukkitAnvilInventory): BukkitAnvilInventory {
        TODO("Not yet implemented")
    }

    /**
     * Create a crafting inventory for bukkit and return
     *
     * @param inventory bukkit side inventory
     * @return the inventory created
     */
    override fun createCraftingInventoryForBukkit(inventory: _BukkitCraftingInventory): BukkitCraftingInventory {
        TODO("Not yet implemented")
    }

    /**
     * Create a inventory view for bukkit inventory view
     *
     * @param inventory bukkit inventory
     * @return the inventory created
     */
    override fun createInventoryViewForBukkit(inventory: _BukkitInventoryView): InventoryView {
        TODO("Not yet implemented")
    }

    /**
     * Create a (chest) inventory and return
     *
     * @param size the size of the inventory
     * @param title the custom title o f the inventory
     * @return the inventory created
     */
    override fun createInventory(size: Int, title: Text?): Inventory {
        TODO("Not yet implemented")
    }

    /**
     * Create a player inventory and return
     *
     * @return the inventory created
     */
    override fun createPlayerInventory(): PlayerInventory {
        TODO("Not yet implemented")
    }

    /**
     * Create an anvil inventory and return
     *
     * @param title the custom title o f the inventory
     * @return the inventory created
     */
    override fun createAnvilInventory(title: Text?): AnvilInventory {
        TODO("Not yet implemented")
    }

    /**
     * Create a crafting inventory and return
     *
     * @param title the custom title o f the inventory
     * @return the inventory created
     */
    override fun createCraftingInventory(title: Text?): CraftingInventory {
        TODO("Not yet implemented")
    }

    /**
     * Create an inventory view
     *
     * @param viewer the viewer that opened the inventory
     * @param inventory the inventory to open for [viewer]
     * @param title the title to the inventory view
     */
    override fun createInventoryView(viewer: InventoryViewer, inventory: Inventory, title: Text?) {
        TODO("Not yet implemented")
    }
}