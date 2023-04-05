package kingmc.platform.item

import kingmc.platform.Material
import kingmc.platform.nbt.MutableNBTCompoundHolder
import kingmc.util.builder.Buildable
import kingmc.util.key.Key

/**
 * A builder responsible for building [Item] instances
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Buildable.Builder
 * @see Item
 */
interface ItemBuilder : MutableNBTCompoundHolder, Buildable.Builder<Item> {
    /**
     * Set the key of the building item
     */
    fun key(key: Key)

    /**
     * Set the material of the building item
     */
    fun material(material: Material<*>): ItemBuilder
}