package kingmc.platform

import kingmc.common.application.WithApplication

/**
 * A common annotation for the components
 * that need to use in platform sides
 *
 *
 * Annotate to a function then the function will automatically
 * run when reach the specified lifecycle
 *
 * @since 0.0.2
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication // When @Awake function called by processor it auto surrounded with withApplication {  }
annotation class Awake (
    /**
     * The lifecycle to call the function if this
     * `@Awake` is annotated on a **function**
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
 * @since 0.0.9
 * @author kingsthere
 */
@Awake(Lifecycles.INITIALIZE)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication // When @Awake function called by processor it auto surrounded with withApplication {  }
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
 * @since 0.0.9
 * @author kingsthere
 */
@Awake(Lifecycles.LOAD)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication // When @Awake function called by processor it auto surrounded with withApplication {  }
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
 * @since 0.0.9
 * @author kingsthere
 */
@Awake(Lifecycles.ACTIVE)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication // When @Awake function called by processor it auto surrounded with withApplication {  }
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
 * @since 0.0.9
 * @author kingsthere
 */
@Awake(Lifecycles.SHUTDOWN)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication // When @Awake function called by processor it auto surrounded with withApplication {  }
annotation class Shutdown (
    /**
     * The priority to call the function
     *
     * @since 0.0.9
     */
    val priority: Byte = 0
)