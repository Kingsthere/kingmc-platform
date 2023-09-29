package kingmc.platform.bukkit.nbtapi.impl.nbt

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit._BukkitChunk
import kingmc.platform.bukkit.block._BukkitBlock
import kingmc.platform.bukkit.entity._BukkitEntity
import kingmc.platform.bukkit.item._BukkitItemStack
import kingmc.platform.bukkit.nbt.BukkitNBTFactory
import kingmc.platform.bukkit.nbtapi._NBTAPINBTBlock
import kingmc.platform.bukkit.nbtapi._NBTAPINBTChunk
import kingmc.platform.bukkit.nbtapi._NBTAPINBTEntity
import kingmc.platform.bukkit.nbtapi._NBTAPINBTItem
import kingmc.platform.bukkit.nbtapi.nbt.NBTAPINBTCompound
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.NBTType
import kingmc.platform.util.Versioned

/**
 * nbt-api `BukkitNBTFactory` implementation
 *
 * @author kingsthere
 * @since 0.0.8
 */
@Component("nbtapiBukkitNBTFactory")
@BukkitImplementation
class NBTAPIBukkitNBTFactory : BukkitNBTFactory {
    /**
     * Create a nbt compound for specified [itemStack]
     */
    override fun createNBTCompoundForItemStack(itemStack: _BukkitItemStack): NBTCompound {
        return NBTAPINBTCompoundImpl(_NBTAPINBTItem(itemStack))
    }

    /**
     * Create a nbt compound for specified [block]
     */
    @Versioned(minecraftVersion = "1.16.4")
    override fun createNBTCompoundForBlock(block: _BukkitBlock): NBTCompound {
        return NBTAPINBTCompoundImpl(_NBTAPINBTBlock(block).data)
    }

    /**
     * Create a nbt compound for specified [chunk]
     */
    @Versioned(minecraftVersion = "1.16.4")
    override fun createNBTCompoundForChunk(chunk: _BukkitChunk): NBTCompound {
        return NBTAPINBTCompoundImpl(_NBTAPINBTChunk(chunk).persistentDataContainer)
    }

    /**
     * Create a nbt compound for specified [entity]
     */
    override fun createNBTCompoundForEntity(entity: _BukkitEntity): NBTCompound {
        return NBTAPINBTCompoundImpl(_NBTAPINBTEntity(entity))
    }

    /**
     * Create a mutable nbt compound for specified [itemStack]
     */
    override fun createMutableNBTCompoundForItemStack(itemStack: _BukkitItemStack): MutableNBTCompound {
        return NBTAPIMutableNBTCompoundImpl(_NBTAPINBTItem(itemStack, true))
    }

    /**
     * Create a nbt compound for specified [block]
     */
    override fun createMutableNBTCompoundForBlock(block: _BukkitBlock): MutableNBTCompound {
        return NBTAPIMutableNBTCompoundImpl(_NBTAPINBTBlock(block).data)
    }

    /**
     * Create a nbt compound for specified [chunk]
     */
    override fun createMutableNBTCompoundForChunk(chunk: _BukkitChunk): MutableNBTCompound {
        return NBTAPIMutableNBTCompoundImpl(_NBTAPINBTChunk(chunk).persistentDataContainer)
    }

    /**
     * Create a nbt compound for specified [entity]
     */
    override fun createMutableNBTCompoundForEntity(entity: _BukkitEntity): MutableNBTCompound {
        return NBTAPIMutableNBTCompoundImpl(_NBTAPINBTEntity(entity))
    }

    /**
     * Create a new nbt compound
     *
     * @return the `NBTCompound` created
     */
    override fun createNBTCompound(): NBTCompound {
        return NBTAPINBTCompoundImpl()
    }

    /**
     * Create a new **mutable** nbt compound
     *
     * @return the `NBTCompound` created
     */
    override fun createMutableNBTCompound(): MutableNBTCompound {
        return NBTAPIMutableNBTCompoundImpl()
    }

    /**
     * Create a new nbt entry
     *
     * @param TValue the type of the value [NBTCompound.NBTEntry] stores
     * @param key the key of the entry
     * @param type the type of the entry
     * @param value the value of the entry
     * @return the `NBTEntry` created
     */
    @Suppress("UNCHECKED_CAST")
    override fun <TValue : Any> createNBTEntry(
        source: NBTCompound,
        key: String,
        type: NBTType<*>,
        value: TValue
    ): NBTCompound.NBTEntry<TValue> {
        return NBTAPINBTCompoundImpl.createNBTEntry(
            nbtapiSource = (source as NBTAPINBTCompound).toNBTAPINBTCompound(),
            key = key,
            presentNBTType = type,
            value = value,
            source = source
        ) as NBTCompound.NBTEntry<TValue>
    }

    /**
     * Create a new mutable nbt entry
     *
     * @param TValue the type of the value [MutableNBTCompound.MutableNBTEntry] stores
     * @param key the key of the entry
     * @param type the type of the entry
     * @param value the value of the entry
     * @return the `NBTEntry` created
     */
    @Suppress("UNCHECKED_CAST")
    override fun <TValue : Any> createMutableNBTEntry(
        source: NBTCompound,
        key: String,
        type: NBTType<*>,
        value: TValue
    ): MutableNBTCompound.MutableNBTEntry<TValue> {
        return NBTAPIMutableNBTCompoundImpl.createNBTEntry(
            nbtapiSource = (source as NBTAPINBTCompound).toNBTAPINBTCompound(),
            key = key,
            presentNBTType = type,
            value = value,
            source = source
        ) as MutableNBTCompound.MutableNBTEntry<TValue>
    }
}