package kingmc.platform.bukkit.version

/**
 * Annotation for things that can **only use in target versions**
 *
 * @author kingsthere
 * @version 0.0.3
 * @since 0.0.3
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.CONSTRUCTOR
)
annotation class Versioned(
    /**
     * Get the versions it supports
     *
     *
     * Add '+' sign at the end represent the
     * versions that higher then;
     * Add '-' sign at the end represent the
     *
     *
     * versions that below then;
     *
     * For example:
     * 1_13+
     *
     *
     * @return the versions
     */
    val versions: Array<String>
)