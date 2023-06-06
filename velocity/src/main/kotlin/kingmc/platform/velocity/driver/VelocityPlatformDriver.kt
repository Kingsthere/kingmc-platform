package kingmc.platform.velocity.driver

import kingmc.platform.driver.PlatformDriver
import kingmc.platform.velocity.VelocityJavaPlugin
import kingmc.platform.velocity.impl.VelocityPlatform
import java.io.File

/**
 * Extended `PlatformDriver` indicates a driver that boot kingmc framework
 * based on a velocity server
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface VelocityPlatformDriver : PlatformDriver {
    /**
     * The [VelocityJavaPlugin] instance to velocity api
     */
    val velocityPluginInstance: VelocityJavaPlugin

    /**
     * The `Platform` instance indicates which this driver running on
     */
    override val platform: VelocityPlatform

    /**
     * The plugin file
     */
    val pluginFile: File
}