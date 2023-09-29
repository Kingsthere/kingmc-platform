package kingmc.platform.extension

import kingmc.common.application.ApplicationEnvironment

/**
 * ApplicationEnvironment describes a extension's environment
 *
 * @author kingsthere
 * @since 0.0.3
 */
class ExtensionEnvironment(
    override val classLoader: ClassLoader,
    ) : ApplicationEnvironment