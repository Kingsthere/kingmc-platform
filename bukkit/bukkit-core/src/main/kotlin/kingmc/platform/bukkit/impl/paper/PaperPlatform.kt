package kingmc.platform.bukkit.impl.paper

import kingmc.platform.bukkit.impl.spigot.SpigotPlatform
import kingmc.platform.driver.PlatformDriver

/**
 * Paper platform compatible
 */
@PaperImplementation
open class PaperPlatform(minecraftVersion: String, driver: PlatformDriver, identifiers: Array<String> = arrayOf())
    : SpigotPlatform(minecraftVersion, driver, arrayOf("paper") + identifiers) {

    override fun toString(): String {
        return "Bukkit(Paper) $minecraftVersion"
    }
}
