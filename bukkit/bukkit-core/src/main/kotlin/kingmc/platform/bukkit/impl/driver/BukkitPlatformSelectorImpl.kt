package kingmc.platform.bukkit.impl.driver

import kingmc.platform.BukkitJavaPlugin
import kingmc.platform.driver.PlatformSelector

/**
 * Bukkit [PlatformSelector] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
open class BukkitPlatformSelectorImpl(val _bukkitJavaPlugin: BukkitJavaPlugin) : BukkitPlatformSelector {
    override fun select(): BukkitPlatformDriverImpl {
        return BukkitPlatformDriverImpl(_bukkitJavaPlugin)
    }
}