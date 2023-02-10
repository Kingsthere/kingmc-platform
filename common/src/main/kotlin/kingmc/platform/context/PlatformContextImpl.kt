package kingmc.platform.context

import kingmc.common.application.ApplicationExposedContext
import kingmc.common.context.GenericApplicationContext
import kingmc.util.format.DefaultFormatArguments
import kingmc.util.format.FormatContext

class PlatformContextImpl(name: String) : PlatformContext, GenericApplicationContext(name = name), ApplicationExposedContext {
    override lateinit var application: PlatformApplication<out PlatformContext>

    override fun getFormatContext(): FormatContext =
        DefaultFormatArguments()
}