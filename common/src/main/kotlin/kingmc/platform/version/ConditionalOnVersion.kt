package kingmc.platform.version

import java.lang.annotation.Inherited

/**
 * Indicating a bean that will only load when platform version
 * exactly equals [value]
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Inherited
annotation class ConditionalOnVersion (
    /**
     * The version specifies
     *
     * @since 0.0.5
     */
    val value: String
)
