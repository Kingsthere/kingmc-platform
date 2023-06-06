package kingmc.platform.velocity.driver

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.logging.slf4j.Slf4jLogger
import kingmc.platform.platform
import kingmc.platform.velocity.VelocityJavaPlugin
import kingmc.platform.velocity._VelocityProxyServer
import java.io.File

/**
 * [VelocityPlatformDriver] instance that launched kingmc framework on velocity
 */
@get:WithApplication
val velocityPlatformDriver: VelocityPlatformDriver
    get() = currentApplication().platform.driver as VelocityPlatformDriver

/**
 * velocity plugin instance that launched kingmc framework on velocity with [com.velocitypowered.api.plugin.Plugin]
 * annotation
 */
@get:WithApplication
val velocityPlugin: VelocityJavaPlugin
    get() = velocityPlatformDriver.velocityPluginInstance

/**
 * @see [com.velocitypowered.api.proxy.ProxyServer]
 */
@get:WithApplication
val velocityServer: _VelocityProxyServer
    get() = velocityPlugin.server

/**
 * The [org.slf4j.Logger] provided by velocity
 */
@get:WithApplication
val velocityLogger: Slf4jLogger
    get() = velocityPlugin.logger

/**
 * The velocity plugin file to launch kingmc framework on velocity
 */
@get:WithApplication
val pluginFile: File
    get() = velocityPlatformDriver.pluginFile