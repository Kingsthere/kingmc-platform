package kingmc.platform.bukkit.command

import kingmc.common.context.annotation.Component
import org.bukkit.command.CommandMap

/**
 * An Extended CommandMap for registering commands and handle them
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
interface CommandMap : CommandMap {
    /**
     * Unregister a command by its label name
     *
     * @return `true` if the command unregistered successfully
     */
    fun unregister(label: String): Boolean
}