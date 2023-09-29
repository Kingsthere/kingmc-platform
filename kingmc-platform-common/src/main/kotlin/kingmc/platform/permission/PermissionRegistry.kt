package kingmc.platform.permission

import kingmc.common.context.annotation.Component

/**
 * A registry for registering permissions
 *
 * @author kingsthere
 * @since 0.0.8
 */
@Component
interface PermissionRegistry {
    /**
     * Registered permissions in this registry
     */
    val permissions: Map<String, Permission>

    /**
     * Register a permission to this registry
     *
     * @param permission the permission to register
     */
    fun registerPermission(permission: Permission)

    /**
     * Gets a permission from this registry by its name, it returns
     * a permission with [Permission.defaultState] is equals to `false` if
     * permission with [name] is not registered in this registry
     */
    fun getPermission(name: String): Permission

    /**
     * Unregister a permission from this registry
     *
     * @param name name of the permission to unregister
     */
    fun unregisterPermission(name: String)
}