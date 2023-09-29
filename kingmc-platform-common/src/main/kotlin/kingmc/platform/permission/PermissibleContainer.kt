package kingmc.platform.permission

/**
 * A `Permissible` implementation provide a container to set/get
 * permissions
 *
 * @author kingsthere
 * @since 0.0.9
 */
open class PermissibleContainer : Permissible {
    protected val permissionsSet: MutableMap<String, Boolean> = HashMap()

    override fun getPermission(permission: Permission): PermissionState {
        return permissionsSet[permission.name].toPermissionState()
    }

    override fun setPermission(permission: Permission, state: Boolean) {
        permissionsSet[permission.name] = state
    }

    override fun unsetPermission(permission: Permission) {
        permissionsSet.remove(permission.name)
    }
}