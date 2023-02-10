package kingmc.platform.permission

/**
 * A superinterface indicate subclasses could have permission set
 *
 * @since 0.0.2
 * @author kingsthere
 */
interface Permissible {
    /**
     * Get a permission from this `Permissible` by the name of
     * the permission
     *
     * @param permission the name of the permission
     */
    operator fun get(permission: String): PermissionState
}