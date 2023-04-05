package kingmc.platform.bukkit.nbtapi.nbt

import kingmc.platform.bukkit.nbtapi._NBTAPINBTCompound
import kingmc.platform.nbt.NBTCompound

/**
 * Extended `NBTCompound` capable to convert to a [de.tr7zw.changeme.nbtapi.NBTCompound]
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface NBTAPINBTCompound : NBTCompound {
    /**
     * Convert this `NBTCompound` to a [de.tr7zw.changeme.nbtapi.NBTCompound]
     */
    fun toNBTAPINBTCompound(): _NBTAPINBTCompound
}