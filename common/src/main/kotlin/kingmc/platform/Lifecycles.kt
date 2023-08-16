package kingmc.platform

/**
 * An object declared lifecycles of the process to a platform to launch
 *
 * @since 0.0.8
 * @author kingsthere
 */
object Lifecycles {
    /**
     * Platform start loading stage
     */
    const val CONST: Int = 0

    /**
     * Platform loaded fully and started to initialize extensions
     */
    const val INITIALIZE: Int = 1

    /**
     * Extension load
     */
    const val LOAD: Int = 2

    /**
     * Platform fully start up
     */
    const val ACTIVE: Int = 3

    /**
     * Platform shutdown
     */
    const val SHUTDOWN: Int = 4
}