package kingmc.platform.bukkit

import kingmc.common.context.Context
import kingmc.platform.PlatformImplementation
import kingmc.platform.common.AbstractPlatform

/**
 * Bukkit platform implementation
 *
 * @since 0.0.3
 * @author kingsthere
 */
@PlatformImplementation
open class BukkitPlatform(override val minecraftVersion: String, identifiers: Array<String> = arrayOf()) : AbstractPlatform(arrayOf("bukkit") + identifiers) {
    override lateinit var context: Context

    override fun toString(): String {
        return "Bukkit $minecraftVersion"
    }
}