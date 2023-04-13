package kingmc.platform.util

/**
 * Marks more detail information for an element that only available since specified minecraft version
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY, AnnotationTarget.VALUE_PARAMETER)
annotation class Versioned(
    /**
     * Minecraft version specifies, means that this feature is only available in specifies minecraft version
     *
     * For Examples:
     *
     *  + `1.12`   Means this element available on minecraft 1.12 or higher
     *
     *  + `1.12..1.16` Means this element available between minecraft 1.12 and minecraft 1.16
     *
     *  + `..1.12` Means this element available before minecraft 1.12
     *
     *  + `*` For all versions
     */
    val minecraftVersion: String = "*",

    /**
     * The fallback value of this element to implement downward compatibility for minecraft versions
     */
    val fallback: String = ""
)
