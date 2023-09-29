package kingmc.platform.extension

import kingmc.common.context.BeanSource

/**
 * Data class represents a loaded extension instance
 *
 * @author kingsthere
 * @since 0.1.2
 */
data class ExtensionInstance(
    /**
     * The definition of this extension
     */
    val definition: ExtensionDefinition,

    /**
     * The context of this extension
     */
    val context: ExtensionContext,

    /**
     * The application to provide api implementations of this extension
     */
    val application: ExtensionApplication,

    /**
     * The bean source to this extension
     */
    val source: BeanSource
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExtensionInstance

        if (definition != other.definition) return false
        if (context != other.context) return false
        if (application != other.application) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = definition.hashCode()
        result = 31 * result + context.hashCode()
        result = 31 * result + application.hashCode()
        result = 31 * result + source.hashCode()
        return result
    }

    override fun toString(): String {
        return "ExtensionInstance(definition=$definition, context=$context, application=$application, source=$source)"
    }
}
