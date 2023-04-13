package kingmc.platform.bukkit.nbt

import kingmc.common.context.annotation.Component
import kingmc.platform.util.Versioned
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit._BukkitChunk
import kingmc.platform.bukkit.block._BukkitBlock
import kingmc.platform.bukkit.entity._BukkitEntity
import kingmc.platform.bukkit.item._BukkitItemStack
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.NBTFactory

/**
 * Extended `NBTFactory` capable to get `NBTCompound`(s) for
 *  + [org.bukkit.inventory.ItemStack]
 *  + [org.bukkit.persistence.PersistentDataContainer]
 *  + [org.bukkit.block.Block]
 *  + [org.bukkit.Chunk]
 *  + [org.bukkit.entity.Entity]
 *
 * @since 0.0.8
 * @author kingsthere
 */
@Component
@BukkitImplementation
interface BukkitNBTFactory : NBTFactory {
    /**
     * Create a nbt compound for specified [itemStack]
     */
    fun createNBTCompoundForItemStack(itemStack: _BukkitItemStack): NBTCompound

    /**
     * Create a nbt compound for specified [block]
     */
    @Versioned(minecraftVersion = "1.16.4")
    fun createNBTCompoundForBlock(block: _BukkitBlock): NBTCompound

    /**
     * Create a nbt compound for specified [chunk]
     */
    @Versioned(minecraftVersion = "1.16.4")
    fun createNBTCompoundForChunk(chunk: _BukkitChunk): NBTCompound

    /**
     * Create a nbt compound for specified [entity]
     */
    fun createNBTCompoundForEntity(entity: _BukkitEntity): NBTCompound

    /**
     * Create a mutable nbt compound for specified [itemStack]
     */
    fun createMutableNBTCompoundForItemStack(itemStack: _BukkitItemStack): MutableNBTCompound

    /**
     * Create a nbt compound for specified [block]
     */
    @Versioned(minecraftVersion = "1.16.4")
    fun createMutableNBTCompoundForBlock(block: _BukkitBlock): MutableNBTCompound

    /**
     * Create a nbt compound for specified [chunk]
     */
    @Versioned(minecraftVersion = "1.16.4")
    fun createMutableNBTCompoundForChunk(chunk: _BukkitChunk): MutableNBTCompound

    /**
     * Create a nbt compound for specified [entity]
     */
    fun createMutableNBTCompoundForEntity(entity: _BukkitEntity): MutableNBTCompound
}