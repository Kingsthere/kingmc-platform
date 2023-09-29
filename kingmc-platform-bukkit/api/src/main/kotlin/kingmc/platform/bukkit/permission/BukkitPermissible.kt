package kingmc.platform.bukkit.permission

import kingmc.platform.permission.Permissible

/**
 * Extended `Permissible` exposed an interface to convert this into a `org.bukkit.Permissible`
 *
 * @author kingsthere
 * @since 0.0.9
 */
interface BukkitPermissible : Permissible {
    /**
     * Convert this bukkit permissible to a [org.bukkit.permissions.Permissible]
     */
    fun toBukkitPermissible(): _BukkitPermissible
}