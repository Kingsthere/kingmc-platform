package kingmc.platform.bukkit.nms.v1_19_2

import kingmc.platform.Location
import kingmc.platform.audience.CommandSender
import kingmc.platform.audience.LocatableCommandSender
import kingmc.platform.audience.ProxiedCommandSender

/**
 * A CommandSender implemented [ProxiedCommandSender] and [LocatableCommandSender] declares a [location]
 *
 * @since 0.0.5
 * @author kingsthere
 */
class ProxiedLocatableCommandSender(override val caller: CommandSender, override val callee: CommandSender, private val proxiedLocation: Location) : ProxiedCommandSender, LocatableCommandSender, CommandSender by callee {
    /**
     * The proxied location
     *
     * @since 0.0.3
     */
    override val location: Location
        get() = proxiedLocation
}