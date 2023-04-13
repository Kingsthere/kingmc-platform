package kingmc.platform.bukkit.nbt

import kingmc.common.application.Application
import kingmc.platform.bukkit._BukkitChunk
import kingmc.platform.bukkit.block._BukkitBlock
import kingmc.platform.bukkit.entity._BukkitEntity
import kingmc.platform.bukkit.item._BukkitItemStack
import kingmc.platform.nbt.MutableNBTCompound
import kingmc.platform.nbtFactory

/**
 * Create a [MutableNBTCompound] for this `ItemStack`
 *
 * @receiver the `ItemStack` to convert to
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitItemStack.createMutableNBTCompound(application: Application): MutableNBTCompound {
    return (application.nbtFactory as BukkitNBTFactory).createMutableNBTCompoundForItemStack(this)
}

/**
 * Create a [MutableNBTCompound] for this `Entity`
 *
 * @receiver the `Entity` to convert to
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitEntity.createMutableNBTCompound(application: Application): MutableNBTCompound {
    return (application.nbtFactory as BukkitNBTFactory).createMutableNBTCompoundForEntity(this)
}

/**
 * Create a [MutableNBTCompound] for this `Block`
 *
 * @receiver the `Block` to convert to
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitBlock.createMutableNBTCompound(application: Application): MutableNBTCompound {
    return (application.nbtFactory as BukkitNBTFactory).createMutableNBTCompoundForBlock(this)
}

/**
 * Create a [MutableNBTCompound] for this `Chunk`
 *
 * @receiver the `Chunk` to convert to
 * @since 0.0.8
 * @author kingsthere
 */
// @WithApplication use parameter [application] instead
fun _BukkitChunk.createMutableNBTCompound(application: Application): MutableNBTCompound {
    return (application.nbtFactory as BukkitNBTFactory).createMutableNBTCompoundForChunk(this)
}