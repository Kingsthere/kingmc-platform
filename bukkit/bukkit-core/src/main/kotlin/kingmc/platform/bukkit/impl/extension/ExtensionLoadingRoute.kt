package kingmc.platform.bukkit.impl.extension

import kingmc.platform.extension.ExtensionData

typealias ExtensionLoadingRoutes = List<ExtensionLoadingRoute>

/**
 * A route designed to solve the priority to load extensions
 *
 * @since 0.0.8
 * @author kingsthere
 */
class ExtensionLoadingRoute(val extension: ExtensionData, val next: MutableList<ExtensionLoadingRoute>) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ExtensionLoadingRoute) return false

        if (extension != other.extension) return false
        if (next != other.next) return false

        return true
    }

    override fun hashCode(): Int {
        var result = extension.hashCode()
        result = 31 * result + next.hashCode()
        return result
    }

    override fun toString(): String {
        return "ExtensionLoadingRoute(extension=$extension, next=$next)"
    }
}