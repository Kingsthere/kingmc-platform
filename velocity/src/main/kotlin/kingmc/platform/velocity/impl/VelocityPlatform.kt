package kingmc.platform.velocity.impl

import kingmc.platform.driver.PlatformDriver
import kingmc.platform.facet.AbstractPlatform
import kingmc.platform.velocity.VelocityImplementation
import kingmc.util.Version

/**
 * `Platform` implementation for velocity
 *
 * @since 0.0.3
 * @author kingsthere
 */
@VelocityImplementation
open class VelocityPlatform(override val minecraftVersion: Version = Version.LATEST,
                            val velocityName: String,
                            val velocityVendor: String,
                            val velocityVersion: String,
                            override val driver: PlatformDriver,
                            identifiers: Array<String> = arrayOf(),
) : AbstractPlatform(arrayOf("velocity") + identifiers) {
    override fun toString(): String {
        return "Velocity-$velocityVersion (Name: $velocityName Vendor: $velocityVendor)"
    }
}