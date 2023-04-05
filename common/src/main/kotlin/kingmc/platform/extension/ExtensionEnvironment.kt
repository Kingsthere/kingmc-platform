package kingmc.platform.extension

import kingmc.common.application.ApplicationEnvironment

/**
 * ApplicationEnvironment describes a extension's environment
 *
 * @since 0.0.3
 * @author kingsthere
 */
class ExtensionEnvironment(
    override val classLoader: ClassLoader,
    ) : ApplicationEnvironment