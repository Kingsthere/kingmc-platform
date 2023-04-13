package kingmc.platform.permission

import kingmc.common.application.WithApplication

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
     * @param permission the permission
     */
    fun getPermission(permission: Permission): PermissionState
}

/**
 * Check if this permissible has permission [name]
 *
 * @param name the name of the permission
 */
@WithApplication
fun Permissible.hasPermission(name: String): Boolean {
    val permission = Permission(name)
    val state = this.getPermission(permission)
    return if (state == PermissionState.NOT_SET) {
        permission.defaultState
    } else {
        state == PermissionState.TRUE
    }
}

/**
 * Get a permission from this `Permissible` by the name of
 * the permission
 *
 * @param name the name of the permission
 */
@WithApplication
fun Permissible.getPermission(name: String): PermissionState {
    val permission = Permission(name)
    return this.getPermission(permission)
}