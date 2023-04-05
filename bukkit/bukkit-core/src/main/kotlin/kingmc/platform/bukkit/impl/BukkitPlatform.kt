package kingmc.platform.bukkit.impl

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.driver.PlatformDriver
import kingmc.platform.facet.AbstractPlatform

/**
 * `Platform implementation for bukkit
 *
 * @since 0.0.3
 * @author kingsthere
 */
@BukkitImplementation
open class BukkitPlatform(override val minecraftVersion: String,
                          override val driver: PlatformDriver,
                          identifiers: Array<String> = arrayOf(),
) : AbstractPlatform(arrayOf("bukkit") + identifiers) {
    override fun toString(): String {
        return "Bukkit $minecraftVersion"
    }
}