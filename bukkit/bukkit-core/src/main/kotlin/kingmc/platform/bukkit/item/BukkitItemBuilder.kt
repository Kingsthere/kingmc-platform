package kingmc.platform.bukkit.item

import kingmc.platform.item.Item
import kingmc.platform.item.ItemBuilder
import kingmc.platform.material.Material
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.util.key.Key

class BukkitItemBuilder(
    override val nbt: MutableNBTCompound,
    material: Material<*>?,
    var key: Key
) : ItemBuilder {
    lateinit var material: Material<*>

    init {
        material?.let {
            this.material = it
        }
    }

    /**
     * Builds.
     *
     * @return the built thing
     * @since 0.0.1
     */
    override fun build(): Item {
        return SimpleBukkitItem(material, nbt, key)
    }

    override fun key(key: Key) {
        this.key = key
    }

    /**
     * Set the material of the building item
     */
    override fun material(material: Material<*>): ItemBuilder {
        this.material = material
        return this
    }
}