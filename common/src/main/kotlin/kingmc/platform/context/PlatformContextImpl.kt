package kingmc.platform.context

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.GenericApplicationContext

class PlatformContextImpl(name: String) : PlatformContext, GenericApplicationContext(name = name), ApplicationExposedContext {
    override lateinit var application: PlatformApplication
}