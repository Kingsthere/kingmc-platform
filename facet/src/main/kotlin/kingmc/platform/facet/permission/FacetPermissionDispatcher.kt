package kingmc.platform.facet.permission

import kingmc.common.context.Context
import kingmc.common.context.annotation.Autowired
import kingmc.platform.facet.FacetImplementation
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionBuilder
import kingmc.platform.permission.PermissionDispatcher
import kingmc.platform.permission.PermissionRegistry

/**
 * Facet [PermissionDispatcher] implementation
 */
@FacetImplementation
open class FacetPermissionDispatcher : PermissionDispatcher {
    override lateinit var context: Context

    @Autowired
    lateinit var permissionRegistry: PermissionRegistry

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
    override fun createPermission(name: String, defaultState: Boolean, children: Set<String>): Permission {
        val permission = FacetPermission(name, defaultState, children)
        _permissions.put(name, permission)
        permissionRegistry.registerPermission(permission)
        return permission
    }

    /**
     * Create a permission builder and return
     */
    override fun createPermissionBuilder(name: String, defaultState: Boolean): PermissionBuilder {
        return FacetPermissionBuilder(name, defaultState, this)
    }

    override fun close() {
        _permissions.clear()
    }
}