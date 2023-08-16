package kingmc.platform.velocity.impl.extension

import kingmc.platform.extension.ExtensionData

/**
 * A route designed to solve the priority to load extensions
 *
 * @since 0.1.0
 * @author kingsthere
 */
sealed interface ExtensionLoadingRoute {
    /**
     * Next extensions to load
     */
    val next: MutableList<ExtensionLoadingNode>
}

/**
 * Defined the root route to a [ExtensionLoadingRoute]
 */
data class RootExtensionLoadingRoute(
    override val next: MutableList<ExtensionLoadingNode>
) : ExtensionLoadingRoute

/**
 * A simple [ExtensionLoadingRoute] implementation
 */
data class ExtensionLoadingNode(
    val extension: ExtensionData,
    override val next: MutableList<ExtensionLoadingNode>
) : ExtensionLoadingRoute

fun ExtensionLoadingRoute.forEach(consumer: (extension: ExtensionData) -> Unit) {
    this.next.forEach { node ->
        consumer(node.extension)
        node.forEach(consumer)
    }
}