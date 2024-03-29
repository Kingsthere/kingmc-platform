package kingmc.platform.bukkit.impl.paper

import kingmc.platform.bukkit.impl.spigot.SpigotPlatform
import kingmc.platform.driver.PlatformDriver
import kingmc.util.Version

/**
 * Paper platform compatible
 */
@PaperImplementation
open class PaperPlatform(minecraftVersion: Version, bukkitVersion: String, driver: PlatformDriver, identifiers: Array<String> = arrayOf())
    : SpigotPlatform(minecraftVersion, bukkitVersion, driver, arrayOf("paper") + identifiers) {

    override fun toString(): String {
        return "Bukkit(Paper)-$bukkitVersion (MC: $minecraftVersion)"
    }
}
