package kingmc.platform.bukkit.driver

import kingmc.platform.bukkit._BukkitServer
import kingmc.platform.bukkit.impl.BukkitPlatform
import kingmc.platform.driver.PlatformDriver
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * Extended `PlatformDriver` indicates a driver that boot kingmc framework
 * based on a bukkit or bukkit fork
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface BukkitPlatformDriver : PlatformDriver {
    /**
     * The [_BukkitServer] class
     */
    val bukkitServerClass: Class<out _BukkitServer>

    /**
     * The [JavaPlugin] instance to bukkit api
     */
    val bukkitPluginInstance: JavaPlugin

    /**
     * The `Platform` instance indicate which this driver running on
     */
    override val platform: BukkitPlatform

    /**
     * The plugin file
     */
    val pluginFile: File
}