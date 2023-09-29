package kingmc.platform.bukkit.impl.driver

import kingmc.platform.bukkit.driver.BukkitPlatformDriver
import kingmc.platform.driver.PlatformSelector

/**
 * Indicates a `PlatformSelector` to select bukkit platform drivers
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface BukkitPlatformSelector : PlatformSelector {
    override fun select(): BukkitPlatformDriver
}