package kingmc.platform.permission

import kingmc.common.application.Isolated
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.aware.ContextAware
import kingmc.common.context.beans.BeanScope
import java.io.Closeable

/**
 * A Dispatcher responsible to register and create `Permission` for isolated applications
 *
 * @since 0.0.8
 * @author kingsthere
 * @see Permission
 */
@Component
@Scope(BeanScope.SINGLETON)
@Isolated
interface PermissionDispatcher : ContextAware, Closeable {
    /**
     * Permissions created by this factory
     */
    val permissions: Map<String, Permission>

    /**
     * Create a permission and return
     *
     * @param name the name to the permission
     * @param defaultState the default state to the permission
     * @param children the children to the permission
     */
    fun createPermission(name: String, defaultState: Boolean = false, children: Set<String>): Permission

    /**
     * Create a permission builder and return
     */
    fun createPermissionBuilder(name: String, defaultState: Boolean = false): PermissionBuilder
}