package kingmc.platform.item

import kingmc.platform.Material
import kingmc.platform.nbt.NBTCompoundHolder
import kingmc.util.builder.Buildable

/**
 * A builder responsible for building [Item] instances
 *
 * @since 0.0.1
 * @author kingsthere
 * @see Buildable.Builder
 * @see Item
 */
interface ItemBuilder : NBTCompoundHolder, Buildable.Builder<Item> {
    /**
     * Set the material of the building item
     */
    fun material(material: Material): ItemBuilder
}