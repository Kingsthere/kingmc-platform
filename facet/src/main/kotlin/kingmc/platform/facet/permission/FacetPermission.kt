package kingmc.platform.facet.permission

import kingmc.platform.facet.FacetImplementation
import kingmc.platform.permission.Permission

/**
 * Facet [Permission] implementation
 */
@FacetImplementation
data class FacetPermission(
    override val name: String,
    override val defaultState: Boolean,
    override val children: Set<String>
) : Permission