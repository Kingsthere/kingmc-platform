package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.util.lifecycle.Lifecycles

/**
 * Annotation to declare an awake function
 *
 * An awake function is similar to a plan scheduled in a `Lifecycle` instance,
 * when the lifecycle reached the given lifecycle this function invoked
 *
 * The application is automatically switch to [kingmc.common.application.application]
 * of the function owner class before invoking the function
 *
 * @author kingsthere
 * @since 0.1.3
 */
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication 
annotation class Awake (
    /**
     * The lifecycle to call the function if this
     *
     * @since 0.0.2
     */
    val lifecycle: Int = Lifecycles.CONST,

    /**
     * The priority to call the function
     *
     * @since 0.0.5
     */
    val priority: Byte = 0
)

/**
 * An extended annotation of [Awake] runs the function at [Lifecycles.INITIALIZE]
 *
 * @author kingsthere
 * @since 0.0.9
 */
@Awake(Lifecycles.INITIALIZE)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication 
annotation class Initialize (
    /**
     * The priority to call the function
     *
     * @since 0.0.9
     */
    val priority: Byte = 0
)

/**
 * An extended annotation of [Awake] runs the function at [Lifecycles.LOAD]
 *
 * @author kingsthere
 * @since 0.0.9
 */
@Awake(Lifecycles.LOAD)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication 
annotation class Load (
    /**
     * The priority to call the function
     *
     * @since 0.0.9
     */
    val priority: Byte = 0
)

/**
 * An extended annotation of [Awake] runs the function at [Lifecycles.ACTIVE]
 *
 * @author kingsthere
 * @since 0.0.9
 */
@Awake(Lifecycles.ACTIVE)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication 
annotation class Active (
    /**
     * The priority to call the function
     *
     * @since 0.0.9
     */
    val priority: Byte = 0
)

/**
 * An extended annotation of [Awake] runs the function at [Lifecycles.SHUTDOWN]
 *
 * @author kingsthere
 * @since 0.0.9
 */
@Awake(Lifecycles.SHUTDOWN)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication 
annotation class Shutdown (
    /**
     * The priority to call the function
     *
     * @since 0.0.9
     */
    val priority: Byte = 0
)