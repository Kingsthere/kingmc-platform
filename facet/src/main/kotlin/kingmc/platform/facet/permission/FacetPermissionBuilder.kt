package kingmc.platform.facet.permission

import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionBuilder

/**
 * Facet [PermissionBuilder] implementation
 */
open class FacetPermissionBuilder(val name: String, val defaultState: Boolean, val factory: FacetPermissionDispatcher) : PermissionBuilder {
    val children: MutableSet<String> = mutableSetOf()

    /**
     * Add a child permission to this
     */
    override fun addChild(permission: String): PermissionBuilder {
        children.add(permission)
        return this
    }

    override fun build(): Permission {
        val permission = factory.createPermission(name, defaultState, children)
        factory._permissions.put(name, permission)
        factory.permissionRegistry.registerPermission(permission)
        return permission
    }
}