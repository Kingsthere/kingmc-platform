package kingmc.platform.velocity.impl

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.PluginDescription
import com.velocitypowered.api.plugin.annotation.DataDirectory
import com.velocitypowered.api.proxy.ProxyServer
import kingmc.platform.velocity.driver.VelocityPlatformDriver
import kingmc.platform.velocity.impl.driver.VelocityPlatformSelector
import kingmc.platform.velocity.impl.driver.VelocityPlatformSelectorImpl
import org.slf4j.Logger
import java.io.File
import java.nio.file.Path
import java.util.*

/**
 * The main class for boot up kingmc in velocity server, main-class in Plugin.yml pointed here
 *
 * @author kingsthere
 * @since 0.0.9
 */
@Plugin(
    id = "kingmc",
    name = "KingMC",
    version = "0.0.93",
    authors = ["Kingsthere"],
    description = "A plugin framework for minecraft"
)
class VelocityJavaPlugin @Inject constructor(
    /**
     * The proxy server instance of this plugin
     */
    val server: ProxyServer,
    /**
     * The logger instance of this plugin
     */
    val logger: Logger,
    @DataDirectory dataDirectory: Path,
    description: PluginDescription
) {
    /**
     * Plugin file
     */
    var file: File

    /**
     * Plugin data folder - `server_root/plugins/KingMC/`
     */
    private val dataFolder: File

    /**
     * Server root - `server_root/`
     */
    private val serverRoot: File

    /**
     * KingMC root - server_root/kingmc/
     */
    val kingmcRoot: File

    /**
     * Temp folder - server_root/kingmc/temp
     */
    val tempFolder: File

    /**
     * The class loader that loads this class
     */
    val classLoader: ClassLoader

    /**
     * Platform selector to adapt current velocity server environment
     */
    var platform: VelocityPlatformSelector

    /**
     * Platform driver to launch kingmc framework on current velocity server
     */
    private var driver: VelocityPlatformDriver

    /**
     * Instantiate this kingmc.platform.velocity.VelocityJavaPlugin, called internally by velocity
     */
    init {
        dataFolder = dataDirectory.toFile()
        val source: Optional<Path> = description.source
        if (source.isPresent) {
            file = source.get().toFile()
        } else {
            throw IllegalStateException("Unable to fetch source that boots kingmc framework")
        }
        serverRoot = dataFolder.getParentFile().getParentFile()
        kingmcRoot = File(serverRoot, "kingmc")
        tempFolder = File(kingmcRoot, "temp")
        classLoader = this.javaClass.getClassLoader()
        if (!kingmcRoot.exists()) {
            kingmcRoot.mkdir()
        }
        if (!tempFolder.exists()) {
            tempFolder.mkdir()
        }

        // Load dependencies
        platform = VelocityPlatformSelectorImpl(this)
        driver = platform.select()
        driver.load()
    }

    /**
     * @param event ProxyInitializeEvent
     */
    @Subscribe
    fun onInitialize(event: ProxyInitializeEvent) {
        driver.enable()
    }

    /**
     * @param event ProxyShutdownEvent
     */
    @Subscribe
    fun onShutdown(event: ProxyShutdownEvent) {
        driver.disable()
    }
}
