package kingmc.platform.bukkit

import kingmc.platform.bukkit.driver.BukkitPlatformDriver
import kingmc.platform.bukkit.impl.driver.BukkitPlatformSelector
import kingmc.platform.bukkit.impl.driver.BukkitPlatformSelectorImpl
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

/**
 * The main class for boot up kingmc in bukkit server, main-class in Plugin.yml pointed here
 *
 * @author kingsthere
 * @since 0.0.3
 */
class BukkitJavaPlugin : JavaPlugin() {
    /**
     * Plugin file
     */
    val pluginFile = file

    /**
     * Plugin class loader (org.bukkit.plugin.PluginClassLoader)
     */
    val pluginClassLoader = classLoader

    /**
     * Plugin data folder - `server_root/plugins/KingMC/`
     */
    val pluginDataFolder = dataFolder

    /**
     * Server root - `server_root/`
     */
    val pluginServerRoot = pluginDataFolder.getParentFile().getParentFile()

    /**
     * KingMC root - server_root/kingmc/
     */
    val kingmcRoot = File(pluginServerRoot, "kingmc")

    /**
     * Temp folder - server_root/kingmc/temp
     */
    val tempFolder = File(kingmcRoot, "temp")

    /**
     * Platform selector to adapt current bukkit server environment
     */
    lateinit var platform: BukkitPlatformSelector

    /**
     * Platform driver to launch kingmc framework on current bukkit server
     */
    lateinit var driver: BukkitPlatformDriver

    override fun onLoad() {
        if (!kingmcRoot.exists()) {
            kingmcRoot.mkdir()
        }
        if (!tempFolder.exists()) {
            tempFolder.mkdir()
        }

        // Load dependencies
        platform = BukkitPlatformSelectorImpl(this)
        driver = platform.select()
        driver.load()
    }

    override fun onEnable() {
        driver.enable()
    }

    override fun onDisable() {
        driver.disable()
    }
}
