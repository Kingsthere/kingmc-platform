package kingmc.platform.bukkit.driver

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.platform
import org.bukkit.Server
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.util.logging.Logger

/**
 * [JavaPlugin] instance that launched kingmc framework on bukkit
 */
@get:WithApplication
val bukkitPlatformDriver: BukkitPlatformDriver
    get() = currentApplication().platform.driver as BukkitPlatformDriver

/**
 * [JavaPlugin] instance that launched kingmc framework on bukkit
 */
@get:WithApplication
val bukkitPlugin: JavaPlugin
    get() = (currentApplication().platform.driver as BukkitPlatformDriver).bukkitPluginInstance

/**
 * @see [org.bukkit.Server]
 */
@get:WithApplication
val bukkitServerClass: Class<out Server>
    get() = (currentApplication().platform.driver as BukkitPlatformDriver).bukkitServerClass

/**
 * @see [org.bukkit.Server]
 */
@get:WithApplication
val bukkitServer: Server
    get() = bukkitPlugin.server

/**
 * The [java.util.logging.Logger] provided by bukkit
 */
@get:WithApplication
val bukkitLogger: Logger
    get() = bukkitPlugin.logger

/**
 * The bukkit plugin file to launch kingmc framework on bukkit
 */
@get:WithApplication
val pluginFile: File
    get() = (currentApplication().platform.driver as BukkitPlatformDriver).pluginFile