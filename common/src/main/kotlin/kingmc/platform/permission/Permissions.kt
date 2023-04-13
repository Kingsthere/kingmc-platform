package kingmc.platform.permission

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.permissionDispatcher
import kingmc.platform.permissionRegistry

/**
 * Create and register a `Permission` to permission dispatcher
 *
 * @param name the name to the permission
 * @param defaultState the default state to the permission
 * @param children the children to the permission
 */
@WithApplication
fun registerPermission(name: String, defaultState: Boolean = false, children: Set<String> = setOf()) =
    currentApplication().permissionDispatcher.createPermission(name, defaultState, children)

/**
 * Create and register `Permission` to permission dispatcher
 *
 * @param name the name to the permission
 * @param defaultState the default state to the permission
 * @param builderAction action to builder
 */
@WithApplication
fun registerPermission(name: String, defaultState: Boolean = false, builderAction: PermissionBuilder.() -> Unit) =
    currentApplication().permissionDispatcher.createPermissionBuilder(name, defaultState).apply(builderAction).build()

/**
 * Gets a permission from `PermissionRegistry`
 *
 * @param name the name of the permission
 */
@WithApplication
fun Permission(name: String) = currentApplication().permissionRegistry.getPermission(name)