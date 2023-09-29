package kingmc.platform.velocity.driver

import kingmc.platform.driver.PlatformDriver
import kingmc.platform.velocity.VelocityPlatform
import java.io.File

/**
 * Extended `PlatformDriver` indicates a driver that boot kingmc framework
 * based on a velocity server
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface VelocityPlatformDriver : PlatformDriver {
    /**
     * The plugin instance for velocity
     */
    val velocityPluginInstance: Any

    /**
     * The `Platform` instance indicates which this driver running on
     */
    override val platform: VelocityPlatform

    /**
     * The plugin file
     */
    val pluginFile: File
}