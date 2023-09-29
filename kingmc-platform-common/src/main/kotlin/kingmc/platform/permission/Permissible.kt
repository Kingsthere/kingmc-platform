package kingmc.platform.permission

import kingmc.common.application.WithApplication
import kingmc.platform.permission.Permissible.ALWAYS.getPermission
import kingmc.platform.permission.Permissible.NEVER.getPermission

/**
 * A superinterface indicate subclasses could have permission set
 *
 * @author kingsthere
 * @since 0.0.2
 */
interface Permissible {
    /**
     * Get a permission from this `Permissible` by the name of
     * the permission
     *
     * @param permission permission to get
     */
    fun getPermission(permission: Permission): PermissionState

    /**
     * Set the state of a permission to this `Permissible`
     *
     * @param permission permission to set
     * @param state the permission state to set to
     */
    fun setPermission(permission: Permission, state: Boolean)

    /**
     * Unset a permission to this `Permissible`
     *
     * @param permission permission to unset
     */
    fun unsetPermission(permission: Permission)

    /**
     * A `Permissible` implementation always return [PermissionState.TRUE] as result when [getPermission]
     * being invoked
     */
    object ALWAYS : Permissible {
        override fun getPermission(permission: Permission): PermissionState = PermissionState.TRUE
        override fun setPermission(permission: Permission, state: Boolean) {  }
        override fun unsetPermission(permission: Permission) {  }
    }

    /**
     * A `Permissible` implementation always return [PermissionState.FALSE] as result when [getPermission]
     * being invoked
     */
    object NEVER : Permissible {
        override fun getPermission(permission: Permission): PermissionState = PermissionState.FALSE
        override fun setPermission(permission: Permission, state: Boolean) {  }
        override fun unsetPermission(permission: Permission) {  }
    }
}

/**
 * Set the state of a permission to this `Permissible`
 *
 * @param permission permission to set
 * @param state the permission state to set to
 */
fun Permissible.setPermission(permission: Permission, state: PermissionState) {
    if (state == PermissionState.NOT_SET) {
        unsetPermission(permission)
    } else {
        setPermission(permission, state.booleanValue)
    }
}

/**
 * Set the state of a permission to this `Permissible`
 *
 * @param name the name of the permission
 * @param state the permission state to set to
 */
fun Permissible.setPermission(name: String, state: Boolean) {
    val permission = Permission(name)
    setPermission(permission, state)
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