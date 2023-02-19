package kingmc.platform.bukkit.item

import de.tr7zw.changeme.nbtapi.NBTCompound
import de.tr7zw.changeme.nbtapi.NBTContainer
import kingmc.platform.Material
import kingmc.platform.item.AbstractItem
import kingmc.platform.item.ItemBuilder

open class BukkitItem(material: Material, nbt: NBTCompound = NBTContainer()) : AbstractItem(material, nbt) {
    /**
     * Create a builder from this thing.
     *
     * @return a builder
     * @since 0.0.1
     */
    override fun toBuilder(): ItemBuilder {
        return BukkitItemBuilder(nbt, material)
    }
}