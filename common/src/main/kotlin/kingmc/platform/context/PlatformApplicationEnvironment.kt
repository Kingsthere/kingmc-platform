package kingmc.platform.context

import kingmc.common.application.ApplicationEnvironment

data class PlatformApplicationEnvironment(
    override val classLoader: ClassLoader,
) : ApplicationEnvironment
