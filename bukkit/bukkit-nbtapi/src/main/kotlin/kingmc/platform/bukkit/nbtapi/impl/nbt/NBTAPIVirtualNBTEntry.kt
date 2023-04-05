package kingmc.platform.bukkit.nbtapi.impl.nbt

import kingmc.platform.bukkit.nbtapi._NBTAPINBTCompound
import kingmc.platform.bukkit.nbtapi.nbt.NBTAPINBTCompound
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbt.NBTCompound
import kingmc.platform.nbt.NBTType

class NBTAPIVirtualNBTEntry(
    override var source: NBTCompound,
    override val key: String,
    override val type: NBTType<*>,
    override val value: Any,
) : NBTCompound.NBTEntry<Any> {
    val nbtapiSource: _NBTAPINBTCompound
        get() = (source as NBTAPINBTCompound).toNBTAPINBTCompound()

    /**
     * Merge this nbt entry to another nbt compound
     */
    override fun merge(nbtCompound: NBTCompound) {
        NBTAPIMutableNBTCompoundImpl.createNBTEntry(source, nbtapiSource, key, value, type).apply {
            this.merge(nbtCompound)
        }
    }

    override fun clone(): NBTCompound.NBTEntry<Any> {
        return NBTAPIVirtualNBTEntry(source, key, type, value)
    }
}

class NBTAPIVirtualMutableNBTEntry(
    override var source: NBTCompound,
    override val key: String,
    override val type: NBTType<*>,
    override var value: Any,
) : MutableNBTCompound.MutableNBTEntry<Any> {
    val nbtapiSource: _NBTAPINBTCompound
        get() = (source as NBTAPINBTCompound).toNBTAPINBTCompound()

    /**
     * Merge this nbt entry to another nbt compound
     */
    override fun merge(nbtCompound: NBTCompound) {
        NBTAPIMutableNBTCompoundImpl.createNBTEntry(source, nbtapiSource, key, value, type).apply {
            this.merge(nbtCompound)
        }
    }

    override fun clone(): NBTCompound.NBTEntry<Any> {
        return NBTAPIVirtualNBTEntry(source, key, type, value)
    }
}