package kingmc.platform.bukkit.impl.item

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.bukkit.item.BukkitItemStack
import kingmc.platform.bukkit.item.SimpleBukkitItem
import kingmc.platform.bukkit.item._BukkitItemStack
import kingmc.platform.bukkit.material.bukkitMaterial
import kingmc.platform.bukkit.nbt.BukkitNBTFactory
import kingmc.platform.item.AbstractItemStack
import kingmc.platform.item.Item
import kingmc.platform.material.Material
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbtFactory

class BukkitItemStackImpl @WithApplication constructor(
    override var amount: Int,
    override var material: Material<*>,
    private val _bukkitItemStack: _BukkitItemStack = _BukkitItemStack(material.type.bukkitMaterial),
    override var nbt: MutableNBTCompound = (currentApplication().nbtFactory as BukkitNBTFactory).createMutableNBTCompoundForItemStack(_bukkitItemStack)
) : BukkitItemStack, AbstractItemStack(material, nbt) {
    /**
     * Convert this `ItemStack` to a [org.bukkit.inventory.ItemStack]
     *
     * @see Item
     */
    override fun toBukkitItemStack(): _BukkitItemStack {
        return _bukkitItemStack
    }

    /**
     * Convert this `ItemStack` to a [Item]
     *
     * @see Item
     */
    override fun toItem(): Item {
        return SimpleBukkitItem(
            material = material,
            nbt = (currentApplication().nbtFactory as BukkitNBTFactory).createNBTCompoundForItemStack(_bukkitItemStack),
            key = material.type.key)
    }
}