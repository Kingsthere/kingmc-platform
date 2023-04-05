package kingmc.platform.bukkit.command

import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.command.CommandManager
import kingmc.platform.command.model.Command

/**
 * Default command manager implementation of kingmc command api for bukkit
 *
 * @since 0.0.4
 * @author kingsthere
 */
@BukkitImplementation
@Deprecated("Since 0.0.5, kingmc use commandapi to implement kingmc command api")
class BukkitCommandManager : CommandManager {
    /**
     * The default namespace for commands to the commands that
     * registered into this command manager
     */
    override val defaultCommandNamespace: String
        get() = throw UnsupportedOperationException("Removal for compatibles")

    /**
     * Close this command manager
     */
    override fun close() {
        throw UnsupportedOperationException("Removal for compatibles")
    }

    /**
     * Get all registered commands in this command manager
     */
    override fun getRegisteredCommands(): List<Command<*>> {
        throw UnsupportedOperationException("Removal for compatibles")
    }

    /**
     * Remove a registered command from this command manager
     */
    override fun unregister(command: Command<*>) {
        throw UnsupportedOperationException("Removal for compatibles")
    }

    /**
     * Remove a registered command from this command manager byb name
     */
    override fun unregister(name: String) {
        throw UnsupportedOperationException("Removal for compatibles")
    }

    /**
     * Add a registered command to this command manager
     */
    override fun register(command: Command<*>) {
        throw UnsupportedOperationException("Removal for compatibles")
    }
}