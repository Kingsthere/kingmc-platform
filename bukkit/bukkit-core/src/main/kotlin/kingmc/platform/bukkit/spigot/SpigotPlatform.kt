package kingmc.platform.bukkit.spigot

import kingmc.platform.PlatformImplementation
import kingmc.platform.bukkit.BukkitPlatform

/**
 * Spigot platform compatible
 */
@PlatformImplementation
open class SpigotPlatform(minecraftVersion: String, identifiers: Array<String> = arrayOf()) : BukkitPlatform(minecraftVersion, arrayOf("spigot") + identifiers) {

    override fun toString(): String {
        return "Bukkit(Spigot) $minecraftVersion"
    }
}
