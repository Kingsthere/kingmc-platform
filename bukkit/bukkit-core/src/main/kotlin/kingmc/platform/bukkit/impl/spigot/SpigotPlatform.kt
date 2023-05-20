package kingmc.platform.bukkit.impl.spigot

import kingmc.platform.bukkit.impl.BukkitPlatform
import kingmc.platform.driver.PlatformDriver
import kingmc.util.Version

/**
 * Spigot platform compatible
 */
@SpigotImplementation
open class SpigotPlatform(minecraftVersion: Version, driver: PlatformDriver, identifiers: Array<String> = arrayOf())
    : BukkitPlatform(minecraftVersion, driver, arrayOf("spigot") + identifiers) {

    override fun toString(): String {
        return "Bukkit(Spigot) $minecraftVersion"
    }
}
