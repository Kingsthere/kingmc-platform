package kingmc.platform

import java.lang.annotation.Inherited

/**
 * Indicating a bean that will only load in specified
 * platform
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.CLASS)
@Inherited
annotation class ConditionalOnPlatform (
    /**
     * The platform to set to enable this bean on
     * the specified platform [Platform.id]
     *
     * @since 0.0.3
     */
    vararg val value: String
)
