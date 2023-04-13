package kingmc.platform

/**
 * An object declared lifecycles of the process to a platform to launch
 *
 * @since 0.0.8
 * @author kingsthere
 */
object Lifecycles {
    /**
     * Platform initialize
     */
    const val INITIALIZE: Int = 1

    /**
     * Platform load
     */
    const val LOAD: Int = 2

    /**
     * Platform active
     */
    const val ACTIVE: Int = 3

    /**
     * Platform shutdown
     */
    const val SHUTDOWN: Int = 4
}