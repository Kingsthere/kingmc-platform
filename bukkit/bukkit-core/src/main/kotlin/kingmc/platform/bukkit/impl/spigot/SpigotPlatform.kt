package kingmc.platform.bukkit.impl.spigot

import kingmc.platform.bukkit.impl.BukkitPlatform
import kingmc.platform.driver.PlatformDriver
import kingmc.util.Version

/**
 * Spigot platform compatible
 */
@SpigotImplementation
open class SpigotPlatform(minecraftVersion: Version, bukkitVersion: String, driver: PlatformDriver, identifiers: Array<String> = arrayOf())
    : BukkitPlatform(minecraftVersion, bukkitVersion, driver, arrayOf("spigot") + identifiers) {

    override fun toString(): String {
        return "Bukkit(spigot)-$bukkitVersion (MC: $minecraftVersion)"
    }
}
