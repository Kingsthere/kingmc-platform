package kingmc.platform.extension

/**
 * A data class to define the basic information of an extension
 *
 * @since 0.0.3
 * @author kingsthere
 */
data class ExtensionDefinition(
    /**
     * The id of this extension, the id is for server-side
     * it must not conflict with other ids, and must follow the
     * pattern [a-z-0-9], for example: kingmc, kingmc-toolbox
     */
    val id: String,

    /**
     * The name of this extension for human visual
     */
    val name: String,

    /**
     * The tag(or version) of this plugin to
     * identity the version & branches of this
     * plugin
     */
    val tag: String = "0.0.0.1",

    /**
     * The description of this extension
     */
    val description: Description = Description(),

    /**
     * The dependencies of this extension
     */
    val dependencies: Array<out Dependency> = arrayOf(),

    /**
     * The class that defined this extension
     */
    val clazz: Class<*>
) {
    /**
     * Determining the description of an extension, this annotation
     * only used in [Extension] annotation
     *
     * @since 0.0.3
     * @author kingsthere
     */
    data class Description(
        /**
         * The contributors of this extension
         */
        val contributors: Array<out String> = arrayOf(),

        /**
         * The plugin's or author's website. If you have no
         * dedicated website, a link to the page where this
         * plugin is listed is recommended
         */
        val website: String = "https://www.example.com/",

        /**
         * A simple introduction of this extension
         */
        val introduction: String = "No description"
    ) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Description

            if (!contributors.contentEquals(other.contributors)) return false
            if (website != other.website) return false
            if (introduction != other.introduction) return false

            return true
        }

        override fun hashCode(): Int {
            var result = contributors.contentHashCode()
            result = 31 * result + website.hashCode()
            result = 31 * result + introduction.hashCode()
            return result
        }
    }

    /**
     * An annotation declares this extension is needed to depend
     * on
     *
     * @since 0.0.3
     * @author kingsthere
     */
    data class Dependency(
        /**
         * The id of the dependency
         */
        val id: String,

        /**
         * Is this dependency optional?, if the dependency
         * is not optional then when the server trying to load
         * the extension without the specified dependenct will not load
         */
        val optional: Boolean = false
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ExtensionDefinition

        if (id != other.id) return false
        if (name != other.name) return false
        if (tag != other.tag) return false
        if (description != other.description) return false
        if (!dependencies.contentEquals(other.dependencies)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + dependencies.contentHashCode()
        return result
    }
}