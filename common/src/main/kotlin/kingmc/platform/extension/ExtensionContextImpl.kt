package kingmc.platform.extension

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.AbstractApplicationContext
import kingmc.platform.context.PlatformApplication
import java.util.*

/**
 * An implementation of context serves for extensions
 *
 * @since 0.0.3
 * @author kingsthere
 */
class ExtensionContextImpl(properties: Properties, name: String, override val extension: ExtensionDefinition
) : AbstractApplicationContext(properties, name), ExtensionContext, ApplicationExposedContext {
    /**
     * The application of this extension context
     */
    override lateinit var application: PlatformApplication

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "ExtensionContextImpl(name=$name,parents=$parents)" +
                " ${super.toString()}"
    }
}