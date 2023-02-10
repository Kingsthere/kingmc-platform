package kingmc.platform.bukkit

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

typealias Bukkit = Bukkit

/**
 * The class of server is booting this plugin
 *
 * @since 0.0.3
 * @author kingsthere
 */
lateinit var bukkitServerClass: Class<*>

/**
 * The plugin instance to bukkit api
 *
 * @since 0.0.3
 * @author kingsthere
 */
lateinit var bukkitPluginInstance: JavaPlugin

/**
 * The plugin driver to drive kingmc extensions
 *
 * @since 0.0.5
 * @author kingsthere
 */
lateinit var bukkitPluginDriver: BukkitPlugin

/**
 * The file of current plugin jar
 *
 * @since 0.0.3
 * @author kingsthere
 */
lateinit var pluginFile: File

/**
 * The current [BukkitPlatform] instance loaded this application
 *
 * @since 0.0.3
 * @author kingsthere
 */
lateinit var bukkitPlatform: BukkitPlatform
