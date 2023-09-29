package kingmc.platform.context

import kingmc.common.context.annotation.Conditional

/**
 * A condition to check if the platform that loads the bean is the given platform
 *
 * @author kingsthere
 * @since 0.1.2
 */
@Retention
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
@Conditional(ConditionOnPlatform::class)
annotation class ConditionalOnPlatform (
    /**
     * The platform to check
     */
    val platform: String
)
