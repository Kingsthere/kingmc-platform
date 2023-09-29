package kingmc.platform.velocity.impl.driver

import kingmc.platform.driver.PlatformSelector
import kingmc.platform.velocity.driver.VelocityPlatformDriver

/**
 * Indicates a `PlatformSelector` to select velocity platform drivers
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface VelocityPlatformSelector : PlatformSelector {
    override fun select(): VelocityPlatformDriver
}