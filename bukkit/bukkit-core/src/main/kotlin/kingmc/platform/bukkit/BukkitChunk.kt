package kingmc.platform.bukkit

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import kingmc.platform.Chunk
import kingmc.platform.ChunkSnapshot
import kingmc.platform.World
import kingmc.platform.block.Block
import kingmc.platform.bukkit.block.BukkitBlock

class BukkitChunk(private val originalBukkitChunk: OriginalBukkitChunk, override val world: World) : Chunk {
    val blockCaches: Cache<Triple<Int, Int, Int>, Block> = Caffeine.newBuilder().build()
    private val blocks: List<Block> by lazy {
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
        get() = originalBukkitChunk.x

    /**
     * The y-coordinate of this chunk
     */
    override val z: Int
        get() = originalBukkitChunk.z

    /**
     * Gets a block from this chunk
     *
     * @param x 0-15
     * @param y world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return the Block
     */
    override fun get(x: Int, y: Int, z: Int): Block {
        return blockCaches.get(Triple(x, y, z)) { BukkitBlock(originalBukkitChunk.getBlock(x, y, z)) }!!
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<Block> =
        blocks.iterator()

    /**
     * Capture a [ChunkSnapshot] for accessing this chunk
     *
     * @return the chunk snapshot captured
     */
    override fun getChunkSnapshot(): ChunkSnapshot {
        return BukkitChunkSnapshot(originalBukkitChunk.chunkSnapshot, world.name, x, z)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BukkitChunk

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