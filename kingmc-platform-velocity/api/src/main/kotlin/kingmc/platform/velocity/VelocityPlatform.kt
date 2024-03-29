package kingmc.platform.velocity

import kingmc.platform.driver.PlatformDriver
import kingmc.platform.facet.AbstractPlatform
import kingmc.util.Version

/**
 * `Platform` implementation for velocity
 *
 * @author kingsthere
 * @since 0.0.3
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