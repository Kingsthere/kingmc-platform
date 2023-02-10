package kingmc.platform.bukkit

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import kingmc.platform.Platform
import kingmc.platform.PlatformImplementation
import kingmc.platform.World
import kingmc.platform.WorldProvider

/**
 * The [WorldProvider] implement use in bukkit platform
 *
 * @since 0.0.3
 */
@PlatformImplementation
object BukkitWorldProvider : WorldProvider {
    /**
     * Gets the platform of this
     */
    override val platform: Platform
        get() = bukkitPlatform

    override fun getWorld(name: String): World =
        cachedWords.get(name) { BukkitWorld(Bukkit.getWorld(name) ?: throw IllegalArgumentException("World not found $name")) }!!

    val cachedWords: Cache<String, World> = Caffeine.newBuilder()
        .build()

    fun getWorldForBukkit(originalBukkitWorld: OriginalBukkitWorld): World =
        cachedWords.get(originalBukkitWorld.name) { BukkitWorld(originalBukkitWorld) }!!
}
