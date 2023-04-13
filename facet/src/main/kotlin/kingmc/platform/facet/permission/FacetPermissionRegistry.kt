package kingmc.platform.facet.permission

import kingmc.platform.facet.FacetImplementation
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionRegistry

/**
 * Facet [PermissionRegistry] implementation
 */
@FacetImplementation
open class FacetPermissionRegistry : PermissionRegistry {
    /**
     * Registered permissions in this registry
     */
    override val permissions: MutableMap<String, Permission> = mutableMapOf()

    /**
     * Register a permission to this registry
     *
     * @param permission
     */
    override fun registerPermission(permission: Permission) {
        permissions.put(permission.name, permission)
    }

    /**
     * Gets a permission from this registry by its name, it returns
     * a permission with [Permission.defaultState] is equals to `false` if
     * permission with [name] is not registered in this registry
     */
    override fun getPermission(name: String): Permission {
        return permissions[name] ?: FacetPermission(name, false, setOf())
    }

    /**
     * Unregister a permission from this registry
     *
     * @param name name of the permission to unregister
     */
    override fun unregisterPermission(name: String) {
        permissions.remove(name)
    }
}