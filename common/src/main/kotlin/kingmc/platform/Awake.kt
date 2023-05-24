package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.context.annotation.Component
import kingmc.util.annotation.Extendable
import kingmc.util.annotation.Extended

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
@Extendable
@Extended(Component::class)
@Retention
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS, AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS)
@WithApplication // When @Awake function called by processor it auto surrounded with withApplication {  }
annotation class Awake (
    /**
     * The lifecycle to call the function if this
     * `@Awake` is annotated on a **function**
     *
     * @since 0.0.2
     */
    val lifecycle: Int = -1,

    /**
     * The priority to call the function
     *
     * @since 0.0.5
     */
    val priority: Byte = 0,

    /**
     * The bean name of this component to inject to
     * the ioc container, this property can only use
     * when use this annotation to annotate a **class**
     *
     * @since 0.0.2
     */
    val name: String = ""
)