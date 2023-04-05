package kingmc.platform.extension

import kingmc.common.context.initializer.HierarchicalContextInitializer

/**
 * A class expressed data to a extension that is loaded
 *
 * @since 0.0.4
 * @author kingsthere
 */
data class ExtensionData(
    /**
     * The definition of this extension
     */
    val definition: ExtensionDefinition,

    /**
     * The context of this extension
     */
    val context: ExtensionContext,

    /**
     * The context initializer that initializing this extension's context
     */
    val contextInitializer: HierarchicalContextInitializer,

    /**
     * The application to provide api implementations of this extension
     */
    val application: ExtensionApplication,

    /**
     * The project this extension loaded from
     */
    val project: ExtensionClassSource
)
