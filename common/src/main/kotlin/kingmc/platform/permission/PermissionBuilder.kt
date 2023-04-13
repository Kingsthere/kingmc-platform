package kingmc.platform.permission

import kingmc.util.builder.Buildable

/**
 * A builder to built `Permission`
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface PermissionBuilder : Buildable.Builder<Permission> {
    /**
     * Add a child permission to this
     */
    fun addChild(permission: String): PermissionBuilder

    override fun build(): Permission
}