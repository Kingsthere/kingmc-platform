package kingmc.platform.bukkit

import org.junit.jupiter.api.Test

class ChunksTest {
    val x = 246
    val z = -3

    @Test
    fun test() {
        val maxHeight = 256
        val minX: Int = this.x shl 4
        val minZ: Int = this.z shl 4
        val maxX = minX or 15
        val maxY: Int = maxHeight
        val maxZ = minZ or 15

        println((3937 - 1) % 16)
        println(246 shl 4)
        println((246 shl 4) shr 4)
        val stopwatch = System.currentTimeMillis()
        for (x in minX..maxX) {
            for (y in 0..maxY) {
                for (z in minZ..maxZ) {
                }
            }
        }
        println("Finished (${System.currentTimeMillis() - stopwatch}ms)")
    }
}