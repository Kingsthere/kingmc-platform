package kingmc.platform.bukkit.impl

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import kingmc.common.application.Application
import kingmc.platform.Chunk
import kingmc.platform.ChunkSnapshot
import kingmc.platform.World
import kingmc.platform.block.Block
import kingmc.platform.bukkit.BukkitChunkSnapshot
import kingmc.platform.bukkit._BukkitChunk
import kingmc.platform.bukkit.impl.block.BukkitBlockImpl

/**
 * Bukkit [Chunk] implementation
 */
class BukkitChunkImpl(private val _bukkitChunk: _BukkitChunk,
                      override val world: World,
                      override val application: Application) : Chunk {
    val blockCaches: Cache<Triple<Int, Int, Int>, Block> = Caffeine.newBuilder()
        .build()

    private val _blocks: List<Block> by lazy {
        val minX: Int = this.x shl 4
        val minZ: Int = this.z shl 4
        val maxX = minX or 15
        val maxY: Int = world.maxHeight
        val maxZ = minZ or 15

        buildList {
            for (x in minX..maxX) {
                for (y in world.minHeight..maxY) {
                    for (z in minZ..maxZ) {
                        add(world.getBlockAt(x, y, z))
                    }
                }
            }
        }
    }

    /**
     * The x-coordinate of this chunk
     */
    override val x: Int
        get() = _bukkitChunk.x

    /**
     * The y-coordinate of this chunk
     */
    override val z: Int
        get() = _bukkitChunk.z

    /**
     * Gets a block from this chunk
     *
     * @param x 0-15
     * @param y world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return the Block
     */
    override fun get(x: Int, y: Int, z: Int): Block {
        return blockCaches.get(Triple(x, y, z)) { BukkitBlockImpl(_bukkitChunk.getBlock(x, y, z), application) }!!
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<Block> =
        _blocks.iterator()

    /**
     * Capture a [ChunkSnapshot] for accessing this chunk
     *
     * @return the chunk snapshot captured
     */
    override fun getChunkSnapshot(): ChunkSnapshot {
        return BukkitChunkSnapshot(_bukkitChunk.chunkSnapshot, world.name, x, z, world.maxHeight, world.minHeight)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BukkitChunkImpl

        if (world != other.world) return false
        if (x != other.x) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = world.hashCode()
        result = 31 * result + x
        result = 31 * result + z
        return result
    }

    override fun toString(): String {
        return "BukkitChunk(world=$world, x=$x, z=$z)"
    }
}