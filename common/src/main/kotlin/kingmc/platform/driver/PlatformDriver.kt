package kingmc.platform.driver

import kingmc.common.context.Context
import kingmc.common.environment.maven.DependencyDispatcher
import kingmc.common.logging.Logger
import kingmc.platform.ExperimentalPlatformApi
import kingmc.platform.Platform
import kingmc.platform.context.PlatformApplication
import kingmc.platform.extension.ExtensionDispatcher
import kingmc.util.Reloadable
import java.io.File
import java.util.*

/**
 * A superinterface defined the behaviors of `PlatformDriver` to boot kingmc framework on
 * certain platforms, such as
 *  + Bukkit
 *  + BungeeCord
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface PlatformDriver : Reloadable {
    /**
     * The `Platform` instance indicate which this driver running on
     */
    val platform: Platform

    /**
     * The `logger` of this platform driver
     */
    val logger: Logger

    /**
     * The `Properties` of this platform driver
     */
    val properties: Properties

    /**
     * The `DependencyDispatcher` of this platform driver
     */
    val dependencyDispatcher: DependencyDispatcher

    /**
     * The `ExtensionDispatcher` of this platform driver
     */
    val extensionDispatcher: ExtensionDispatcher

    /**
     * The base directory to store the caches and configurations of
     * kingmc framework
     */
    val baseDirectory: File

    /**
     * The `PlatformApplication` instance of this platform
     */
    val application: PlatformApplication

    /**
     * The `Context` instance of this platform
     */
    val context: Context
        get() = application.context

    /**
     * Load this platform driver
     */
    fun load()

    /**
     * Load the platform application instance
     */
    fun loadPlatformApplication(): PlatformApplication

    /**
     * Enable this platform driver
     */
    fun enable()

    /**
     * Disable this platform driver
     */
    fun disable()

    /**
     * Reload this platform driver
     */
    @ExperimentalPlatformApi
    override fun reload()
}