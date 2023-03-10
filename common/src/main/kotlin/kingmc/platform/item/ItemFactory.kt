package kingmc.platform.item

import kingmc.common.application.WithApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.Material
import kingmc.platform.PlatformExposed

/**
 * A factory provide [ItemStack] instances
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
interface ItemFactory : PlatformExposed {
    /**
     * Build an item from a [ItemBuilder] and return
     *
     * @param builderAction the action to the builder
     * @return the item built
     */
    fun buildItem(builderAction: @WithApplication ItemBuilder.() -> Unit): Item

    /**
     * Build an `ItemStack` for the given [item]
     *
     * @param item the item template to build `ItemStack` from
     * @param amount the amount of the item stack
     * @return the item stack built
     */
    fun buildItemStackForItem(item: Item, amount: Int = 1): ItemStack

    /**
     * Create an `ItemStack` with basic properties
     *
     * @param material the material of the `ItemStack`
     * @param amount the amount of the `ItemStack`
     * @return the item stack create
     */
    fun createItemStack(material: Material, amount: Int = 1): ItemStack
}