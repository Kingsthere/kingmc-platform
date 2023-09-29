package kingmc.platform.bukkit.impl.driver

import kingmc.platform.bukkit.BukkitJavaPlugin
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.driver.PlatformSelector

/**
 * Bukkit [PlatformSelector] implementation
 *
 * @author kingsthere
 * @since 0.0.8
 */
@BukkitImplementation
open class BukkitPlatformSelectorImpl(val _bukkitJavaPlugin: BukkitJavaPlugin) : BukkitPlatformSelector {
    override fun select(): BukkitPlatformDriverImpl {
        return BukkitPlatformDriverImpl(_bukkitJavaPlugin)
    }
}