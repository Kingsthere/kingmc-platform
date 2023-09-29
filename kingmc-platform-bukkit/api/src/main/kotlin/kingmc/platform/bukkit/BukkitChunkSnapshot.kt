package kingmc.platform.bukkit

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.ChunkSnapshot
import kingmc.platform.bukkit.material.BukkitMaterialProvider
import kingmc.platform.material.Material
import kingmc.platform.material.MaterialType
import kingmc.platform.materialProvider

class BukkitChunkSnapshot(
    val _bukkitChunkSnapshot: _BukkitChunkSnapshot,
    override val worldName: String,
    override val x: Int,
    override val z: Int,
    val worldMaxHeight: Int,
    val worldMinHeight: Int
) : ChunkSnapshot {

    private val _materialTypes: List<MaterialType<*>> by lazy {
        val minX: Int = this.x shl 4
        val minZ: Int = this.z shl 4
        val maxX = minX or 15
        val maxY: Int = worldMaxHeight
        val maxZ = minZ or 15

        buildList {
            for (x in minX..maxX) {
                for (y in worldMinHeight..maxY) {
                    for (z in minZ..maxZ) {
                        add(getBlockType(x, y, z))
                    }
                }
            }
        }
    }

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
        return (currentApplication().materialProvider as BukkitMaterialProvider).getTypeForBukkit(_bukkitChunkSnapshot.getBlockType(x, y, z))
    }

    /**
     * Get material for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return block material type
     */
    override fun getMaterial(x: Int, y: Int, z: Int): Material<*> {
        TODO("Not yet implemented")
    }

    /**
     * Get all block's type in this chunk snapshot
     *
     * @return block material types
     */
    override fun getAllMaterialTypes(): List<MaterialType<*>> {
        return this._materialTypes
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

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<Material<*>> {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "BukkitChunkSnapshot(worldName='$worldName', x=$x, z=$z)"
    }
}