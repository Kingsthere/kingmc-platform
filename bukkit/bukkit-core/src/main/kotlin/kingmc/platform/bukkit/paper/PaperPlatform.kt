package kingmc.platform.bukkit.paper

import kingmc.platform.PlatformImplementation
import kingmc.platform.bukkit.spigot.SpigotPlatform

/**
 * Paper platform compatible
 */
@PlatformImplementation
open class PaperPlatform(minecraftVersion: String, identifiers: Array<String> = arrayOf()) : SpigotPlatform(minecraftVersion, arrayOf("paper") + identifiers) {

    override fun toString(): String {
        return "Bukkit(Paper) $minecraftVersion"
    }
}
