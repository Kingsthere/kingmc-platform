package kingmc.platform.bukkit.command

import kingmc.common.context.annotation.Component
import org.bukkit.command.Command
import org.bukkit.command.CommandMap

/**
 * An Extended CommandMap for registering commands and handle them, this is a
 * solution for bukkit side servers that don't have brigadier supports `(brigadier is added since mc1.13)`
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
interface CommandMap : CommandMap {
    /**
     * Gets the bukkit command map
     */
    val bukkit: CommandMap

    /**
     * Known commands registered in the command map
     */
    val knownCommands: MutableMap<String, Command>

    /**
     * Unregister a command by its label name
     *
     * @return `true` if the command unregistered successfully
     */
    fun unregister(label: String): Boolean
}