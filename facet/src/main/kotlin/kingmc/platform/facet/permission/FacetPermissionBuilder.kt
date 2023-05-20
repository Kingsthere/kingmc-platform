package kingmc.platform.facet.permission

import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionBuilder

/**
 * Facet `PermissionBuilder` implementation
 */
open class FacetPermissionBuilder(override var name: String, val defaultState: Boolean, val facetFactory: FacetPermissionDispatcher) : PermissionBuilder {
    val children: MutableSet<Permission> = mutableSetOf()

    override fun permission(permission: Permission): PermissionBuilder {
        children.add(permission)
        return this
    }

    override fun build(): Permission {
        return facetFactory.createPermission(name, defaultState, children)
    }
}