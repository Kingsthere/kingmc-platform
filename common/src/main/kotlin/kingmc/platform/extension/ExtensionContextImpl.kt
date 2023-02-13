package kingmc.platform.extension

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.GenericApplicationContext
import kingmc.platform.context.PlatformApplication
import kingmc.platform.context.PlatformContext
import kingmc.util.format.FormatContext

/**
 * An implementation of context serves for extensions
 *
 * @since 0.0.3
 * @author kingsthere
 */
class ExtensionContextImpl(name: String) : GenericApplicationContext(name = name), ExtensionContext, ApplicationExposedContext {
    override fun getFormatContext(): FormatContext =
        ExtensionFormatContext()

    /**
     * The application of this extension context
     */
    override lateinit var application: PlatformApplication<out PlatformContext>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        if (!super.equals(other)) return false
        return true
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }

    override fun toString(): String {
        return "ExtensionContextImpl(name=$name,parents=$parents)" +
                " ${super.toString()}"
    }
}