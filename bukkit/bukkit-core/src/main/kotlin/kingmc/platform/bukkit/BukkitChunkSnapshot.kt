package kingmc.platform.bukkit

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.ChunkSnapshot
import kingmc.platform.MaterialType
import kingmc.platform.bukkit.material.BukkitMaterialProvider
import kingmc.platform.materialProvider

class BukkitChunkSnapshot(val _BukkitChunkSnapshot: _BukkitChunkSnapshot, override val worldName: String, override val x: Int, override val z: Int) : ChunkSnapshot {
    /**
     * Get block type for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return block material type
     */
    @WithApplication
    override fun getBlockType(x: Int, y: Int, z: Int): MaterialType<*> {
        return (currentApplication().materialProvider as BukkitMaterialProvider).getFromBukkit(_BukkitChunkSnapshot.getBlockType(x, y, z))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BukkitChunkSnapshot

        if (worldName != other.worldName) return false
        if (x != other.x) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = worldName.hashCode()
        result = 31 * result + x
        result = 31 * result + z
        return result
    }

    override fun toString(): String {
        return "BukkitChunkSnapshot(worldName='$worldName', x=$x, z=$z)"
    }
}