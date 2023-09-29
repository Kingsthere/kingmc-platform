package kingmc.platform.context

import kingmc.common.context.annotation.Conditional

/**
 * A condition to check if the minecraft version of the platform below the given version
 *
 * @author kingsthere
 * @since 0.1.2
 */
@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Conditional(ConditionBeforeVersion::class)
annotation class ConditionalBeforeVersion (
    /**
     * The version specifies
     *
     * @since 0.1.2
     */
    val version: String
)
