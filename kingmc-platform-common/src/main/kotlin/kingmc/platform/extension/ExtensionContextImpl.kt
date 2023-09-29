package kingmc.platform.extension

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.Context
import kingmc.platform.context.PlatformContextImpl
import kingmc.platform.context.source.PlatformBeanSource
import java.util.*

/**
 * An implementation of context serves for extensions
 *
 * @author kingsthere
 * @since 0.0.3
 */
open class ExtensionContextImpl(properties: Properties,
                                name: String,
                                beanSource: PlatformBeanSource,
                                parents: Set<Context>,
                                override val extension: ExtensionDefinition
) : PlatformContextImpl(properties, name, beanSource, parents), ExtensionContext, ApplicationExposedContext {
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