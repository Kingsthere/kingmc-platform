package kingmc.platform.context

import kingmc.common.application.ApplicationEnvironment
import kotlin.coroutines.CoroutineContext

data class PlatformApplicationEnvironment(
    override val classLoader: ClassLoader,
    override val coroutineContext: CoroutineContext
) : ApplicationEnvironment
