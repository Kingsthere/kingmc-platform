package kingmc.platform.velocity.impl.driver

import kingmc.platform.VelocityJavaPlugin
import kingmc.platform.driver.PlatformSelector
import kingmc.platform.velocity.VelocityImplementation

/**
 * velocity [PlatformSelector] implementation
 *
 * @since 0.0.8
 * @author kingsthere
 */
@VelocityImplementation
open class VelocityPlatformSelectorImpl(val _velocityJavaPlugin: VelocityJavaPlugin) : VelocityPlatformSelector {
    override fun select(): VelocityPlatformDriverImpl {
        return VelocityPlatformDriverImpl(_velocityJavaPlugin)
    }
}