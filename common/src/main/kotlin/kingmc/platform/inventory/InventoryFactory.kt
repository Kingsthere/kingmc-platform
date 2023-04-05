package kingmc.platform.inventory

import kingmc.common.context.annotation.Component
import kingmc.common.text.Text

/**
 * A factory responsible for creating `Inventory`
 *
 * @since 0.0.6
 * @author kingsthere
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
}