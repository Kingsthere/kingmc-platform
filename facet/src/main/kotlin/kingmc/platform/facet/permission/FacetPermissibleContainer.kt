package kingmc.platform.facet.permission

import kingmc.platform.facet.Facet
import kingmc.platform.facet.FacetAvailable
import kingmc.platform.facet.FacetImplementation
import kingmc.platform.facet.invoke
import kingmc.platform.permission.PermissibleContainer
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionState

/**
 * A `facet available` implementation of `PermissibleContainer`
 *
 * @since 0.0.9
 * @author kingsthere
 */
@FacetImplementation
open class FacetPermissibleContainer : PermissibleContainer() {
    val getPermission = Facet<Permission, PermissionState> { permission -> super.getPermission(permission) }

    @FacetAvailable
    override fun getPermission(permission: Permission): PermissionState = getPermission.invoke(permission)

    val setPermission = Facet<Permission, Boolean, Unit> { permission, state -> super.setPermission(permission, state) }

    @FacetAvailable
    override fun setPermission(permission: Permission, state: Boolean) = setPermission.invoke(permission, state)

    val unsetPermission = Facet<Permission, Unit> { permission -> super.unsetPermission(permission)}

    @FacetAvailable
    override fun unsetPermission(permission: Permission) = unsetPermission.invoke(permission)
}