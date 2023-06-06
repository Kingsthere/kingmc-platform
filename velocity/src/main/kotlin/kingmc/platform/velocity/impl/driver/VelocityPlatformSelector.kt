package kingmc.platform.velocity.impl.driver

import kingmc.platform.driver.PlatformSelector
import kingmc.platform.velocity.driver.VelocityPlatformDriver

/**
 * Indicates a `PlatformSelector` to select velocity platform drivers
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface VelocityPlatformSelector : PlatformSelector {
    override fun select(): VelocityPlatformDriver
}