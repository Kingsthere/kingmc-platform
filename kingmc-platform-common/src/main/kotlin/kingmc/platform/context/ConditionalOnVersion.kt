package kingmc.platform.context

import kingmc.common.context.annotation.Conditional

/**
 * A condition to check if the minecraft version of the platform exactly equals the given version
 *
 * @author kingsthere
 * @since 0.1.2
 */
@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Conditional(ConditionOnVersion::class)
annotation class ConditionalOnVersion (
    /**
     * The version specifies
     *
     * @since 0.1.2
     */
    val version: String
)
