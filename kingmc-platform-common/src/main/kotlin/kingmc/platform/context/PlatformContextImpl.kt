package kingmc.platform.context

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.BeanSourceApplicationContext
import kingmc.common.context.Context
import kingmc.platform.Platform
import kingmc.platform.context.source.PlatformBeanSource
import kingmc.util.InternalAPI
import java.util.*

open class PlatformContextImpl(
    properties: Properties,
    name: String,
    beanSource: PlatformBeanSource,
    parents: Set<Context>
) : PlatformContext,
    BeanSourceApplicationContext(beanSource, properties, name, parents),
    ApplicationExposedContext {
    override lateinit var application: PlatformApplication
        @InternalAPI set

    /**
     * The platform of this context
     */
    override val platform: Platform
        get() = (beanSource as PlatformBeanSource).platform
}