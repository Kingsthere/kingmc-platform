package kingmc.platform.item

import kingmc.common.application.WithApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.material.Material
import kingmc.util.key.Key

/**
 * A factory provide [ItemStack] instances
 *
 * @author kingsthere
 * @since 0.0.5
 */
@Component
interface ItemFactory  {
    /**
     * A constant value for an `Air ItemStack`
     */
    val AIR: ItemStack

    /**
     * Build an item from a [ItemBuilder] and return
     *
     * @param key the key of the item
     * @param builderAction the action to the builder
     * @return the item built
     */
    fun buildItem(key: Key, builderAction: @WithApplication ItemBuilder.() -> Unit): Item

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
     * @return the item stack created
     */
    fun createItemStack(material: Material<*>, amount: Int = 1): ItemStack
}