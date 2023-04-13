package kingmc.platform.context

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.GenericApplicationContext
import java.util.*

class PlatformContextImpl(properties: Properties, name: String) : PlatformContext, GenericApplicationContext(properties, name), ApplicationExposedContext {
    override lateinit var application: PlatformApplication
}