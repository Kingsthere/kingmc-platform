package kingmc.platform.context

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.AbstractApplicationContext
import java.util.*

class PlatformContextImpl(properties: Properties, name: String) : PlatformContext, AbstractApplicationContext(properties, name), ApplicationExposedContext {
    override lateinit var application: PlatformApplication
}