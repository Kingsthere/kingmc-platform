package kingmc.platform.extension

/**
 * A data class to define the basic information of an extension
 *
 * @author kingsthere
 * @since 0.0.3
 */
data class ExtensionDefinition(
    /**
     * The id of this extension, the id is for server-side
     * it must not conflict with other ids, and must follow the
     * pattern [a-z-0-9], for example: kingmc, kingmc-toolbox
     */
    val id: String,

    /**
     * The display name of this extension for human visual
     */
    val displayName: String,

    /**
     * The version(or tag) of this plugin to
     * identity the version & branches of this
     * plugin
     */
    val version: String = "0.0.1",

    /**
     * The description of this extension
     */
    val description: Description = Description(),

    /**
     * The dependencies of this extension
     */
    val dependencies: List<Dependency> = listOf()
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
        val contributors: List<String> = listOf(),

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
    )

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
         * The version of this dependency
         */
        val version: String,

        /**
         * The url of the dependency, this url is used to specify the dependency to
         * download if the dependency is missing. You can leave it to default (`empty string`), but if
         * the dependency is missing then kingmc will download it by its [id] which
         * may not be the correct result you wanted
         */
        val url: String = "",

        /**
         * Whether this dependency optional or not, if the dependency
         * is not optional then when the server trying to load
         * the extension without the specified dependenct will not load
         */
        val optional: Boolean = false
    )
}