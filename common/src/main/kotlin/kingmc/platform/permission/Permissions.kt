package kingmc.platform.permission

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.permissionDispatcher
import kingmc.platform.permissionRegistry

/**
 * Register a created `Permission` to permisison dispatcher
 *
 * @param permission permission to register
 */
fun registerPermission(permission: Permission) {
    currentApplication().permissionDispatcher.registerPermission(permission)
}

/**
 * Create a `Permission` by permission dispatcher
 *
 * @param name the name to the permission
 * @param defaultState the default state to the permission
 * @param children the children to the permission
 */
@WithApplication
fun createPermission(name: String, defaultState: Boolean = false, children: Set<Permission> = setOf()) =
    currentApplication().permissionDispatcher.createPermission(name, defaultState, children)

/**
 * Create a `Permission` by permission dispatcher
 *
 * @param name the name to the permission
 * @param defaultState the default state to the permission
 * @param builderAction action to builder
 */
@WithApplication
inline fun createPermission(name: String, defaultState: Boolean = false, builderAction: PermissionBuilder.() -> Unit): Permission {
    val permissionDispatcher = currentApplication().permissionDispatcher
    return permissionDispatcher.createPermissionBuilder(name, defaultState).apply(builderAction).build()
}

/**
 * Gets a permission from `PermissionRegistry`
 *
 * @param name the name of the permission
 */
@WithApplication
fun Permission(name: String) = currentApplication().permissionRegistry.getPermission(name)