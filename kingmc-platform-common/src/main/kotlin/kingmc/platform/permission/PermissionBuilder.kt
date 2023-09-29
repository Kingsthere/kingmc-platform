package kingmc.platform.permission

import kingmc.util.builder.Buildable

/**
 * A builder to built `Permission`
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface PermissionBuilder : Buildable.Builder<Permission> {
    /**
     * The name to permission
     */
    var name: String

    /**
     * Add a child permission to this
     *
     * @param permission the child permission to add
     * @return this
     */
    fun permission(permission: Permission): PermissionBuilder

    override fun build(): Permission
}

/**
 * Create and add a child permission to this
 *
 * @param name name to the permission
 * @param defaultState default state to the permission
 * @param builderAction action to invoke the builder to child permission
 * @return this
 */
fun PermissionBuilder.permission(name: String, defaultState: Boolean = false, builderAction: PermissionBuilder.() -> Unit): PermissionBuilder {
    permission(createPermission("${this.name}.$name", defaultState, builderAction))
    return this
}

/**
 * Create and add a child permission for `path.*` to this
 *
 * @param defaultState default state to the permission
 * @param builderAction action to invoke the builder to child permission
 * @return this
 */
fun PermissionBuilder.all(defaultState: Boolean = false, builderAction: PermissionBuilder.() -> Unit): PermissionBuilder {
    permission(createPermission("${this.name}.*", defaultState, builderAction))
    return this
}