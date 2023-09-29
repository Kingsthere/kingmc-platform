package kingmc.platform.bukkit

import kingmc.platform.driver.PlatformDriver
import kingmc.platform.facet.AbstractPlatform
import kingmc.util.Version

/**
 * `Platform` implementation for bukkit
 *
 * @author kingsthere
 * @since 0.0.3
 */
@BukkitImplementation
open class BukkitPlatform(override val minecraftVersion: Version,
                          val bukkitVersion: String,
                          override val driver: PlatformDriver,
                          identifiers: Array<String> = arrayOf(),
) : AbstractPlatform(arrayOf("bukkit") + identifiers) {
    override fun toString(): String {
        return "Bukkit-$bukkitVersion (MC: $minecraftVersion)"
    }
}