package kingmc.platform.facet.permission

import kingmc.platform.facet.FacetImplementation
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionBuilder
import kingmc.platform.permission.PermissionDispatcher
import kingmc.platform.permission.PermissionRegistry

/**
 * Facet `PermissionDispatcher` implementation
 */
@FacetImplementation
open class FacetPermissionDispatcher(val permissionRegistry: PermissionRegistry) : PermissionDispatcher {

    /**
     * Permissions created by this factory
     */
    override val permissions: Map<String, Permission>
        get() = _permissions

    /**
     * Internal mutable permissions map
     */
    internal val _permissions: MutableMap<String, Permission> = mutableMapOf()

    /**
     * Create a permission and return
     *
     * @param name the name to the permission
     * @param defaultState the default state to the permission
     * @param children the children to the permission
     */
    override fun createPermission(name: String, defaultState: Boolean, children: Set<Permission>): Permission {
        return FacetPermission(name, defaultState, children)
    }

    /**
     * Create a permission builder and return
     */
    override fun createPermissionBuilder(name: String, defaultState: Boolean): PermissionBuilder {
        return FacetPermissionBuilder(name, defaultState, this)
    }

    override fun registerPermission(permission: Permission) {
        if (!permission.name.endsWith("*")) {
            _permissions.put(permission.name, permission)
            permissionRegistry.registerPermission(permission)
            permission.children.forEach {
                registerPermission(it)
            }
        }
    }

    override fun close() {
        _permissions.clear()
    }
}