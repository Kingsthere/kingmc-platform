package kingmc.platform.extension

import kingmc.common.application.ApplicationEnvironment
import kotlin.coroutines.CoroutineContext

/**
 * ApplicationEnvironment describes a extension's environment
 *
 * @since 0.0.3
 * @author kingsthere
 */
class ExtensionEnvironment(
    override val classLoader: ClassLoader,
    override val coroutineContext: CoroutineContext
    ) : ApplicationEnvironment