package kingmc.platform.inventory

import kingmc.common.context.annotation.Component
import kingmc.common.text.Text

/**
 * A factory responsible for creating `Inventory`
 *
 * @author kingsthere
 * @since 0.0.6
 */
@Component
interface InventoryFactory {
    /**
     * Create a (chest) inventory and return
     *
     * @param size the size of the inventory
     * @param title the custom title o f the inventory
     * @return the inventory created
     */
    fun createInventory(size: Int = 27, title: Text? = null): Inventory

    /**
     * Create a player inventory and return
     *
     * @return the inventory created
     */
    fun createPlayerInventory(): PlayerInventory

    /**
     * Create an anvil inventory and return
     *
     * @param title the custom title o f the inventory
     * @return the inventory created
     */
    fun createAnvilInventory(title: Text? = null): AnvilInventory

    /**
     * Create a crafting inventory and return
     *
     * @param title the custom title o f the inventory
     * @return the inventory created
     */
    fun createCraftingInventory(title: Text? = null): CraftingInventory

    /**
     * Create an inventory view
     *
     * @param viewer the viewer that opened the inventory
     * @param inventory the inventory to open for [viewer]
     * @param title the title to the inventory view
     */
    fun createInventoryView(viewer: InventoryViewer, inventory: Inventory, title: Text? = null)
}