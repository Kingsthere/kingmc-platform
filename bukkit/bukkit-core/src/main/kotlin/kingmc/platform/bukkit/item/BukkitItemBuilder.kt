package kingmc.platform.bukkit.item

import de.tr7zw.changeme.nbtapi.NBTCompound
import de.tr7zw.changeme.nbtapi.NBTContainer
import kingmc.platform.Material
import kingmc.platform.item.Item
import kingmc.platform.item.ItemBuilder

class BukkitItemBuilder(
    override val nbt: NBTCompound = NBTContainer(),
    material: Material?
) : ItemBuilder {
    lateinit var material: Material

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
        return BukkitItem(material, nbt)
    }

    /**
     * Set the material of the building item
     */
    override fun material(material: Material): ItemBuilder {
        this.material = material
        return this
    }
}