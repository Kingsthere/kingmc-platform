package kingmc.platform.permission

/**
 * A state describe the state of permission, it basically explained
 * three state of permission could be, `true`, `false`, `not set`
 *
 * @since 0.0.2
 * @author kingsthere
 */
interface PermissionState : Cloneable {
    /**
     * `true` if this value is a `true` state, or it has
     * default value set to `true` if it is `not set`
     */
    val value: Boolean

    /**
     * `true` if this kind of permission state permission is `not set`
     */
    val set: Boolean

    /**
     * The default value of this permission state, it can return
     * only [value] `true` or `false`
     *
     * @return the default value of this permission state
     */
    fun default(): Boolean
}