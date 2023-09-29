package kingmc.platform.extension

import kingmc.platform.context.source.PlatformBeanSource

/**
 * An interface represents the bean source for an extension
 *
 * @author kingsthere
 * @since 0.1.2
 */
interface ExtensionBeanSource : PlatformBeanSource {
    /**
     * The definition of the extension
     */
    val extension: ExtensionDefinition

    /**
     * Load maven dependencies declared in this extension bean source
     */
    suspend fun loadMavenDependencies()
}