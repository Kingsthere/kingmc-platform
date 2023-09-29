package kingmc.platform.bukkit.impl.permission

import kingmc.common.application.Application
import kingmc.platform.bukkit.driver.BukkitPlatformDriver
import kingmc.platform.bukkit.permission.BukkitPermissible
import kingmc.platform.bukkit.permission._BukkitPermissible
import kingmc.platform.permission.Permission
import kingmc.platform.permission.PermissionState
import kingmc.platform.platform
import org.bukkit.permissions.PermissionAttachment

open class BukkitPermissibleImpl(val _bukkitPermissible: _BukkitPermissible, val application: Application) : BukkitPermissible {
    val bukkitPlugin = (application.platform.driver as BukkitPlatformDriver).bukkitPluginInstance
    val bukkitAttachments: MutableMap<String, PermissionAttachment> = HashMap()

    override fun toBukkitPermissible(): _BukkitPermissible =
        _bukkitPermissible

    override fun getPermission(permission: Permission): PermissionState {
        return if (_bukkitPermissible.isPermissionSet(permission.name)) {
            if (_bukkitPermissible.hasPermission(permission.name)) {
                PermissionState.TRUE
            } else {
                PermissionState.FALSE
            }
        } else {
            PermissionState.NOT_SET
        }
    }

    override fun setPermission(permission: Permission, state: Boolean) {
        bukkitAttachments[permission.name] = _bukkitPermissible.addAttachment(bukkitPlugin, permission.name, state)
    }

    override fun unsetPermission(permission: Permission) {
        bukkitAttachments[permission.name]?.let { _bukkitPermissible.removeAttachment(it) }
    }
}