package kingmc.platform.bukkit.command

import kingmc.platform.Platform
import kingmc.platform.PlatformImplementation
import kingmc.platform.command.CommandManager
import kingmc.platform.command.model.RegisteredCommand

/**
 * Default command manager implementation of kingmc command api for bukkit
 *
 * @since 0.0.4
 * @author kingsthere
 */
@PlatformImplementation
@Deprecated("Since 0.0.5, kingmc use commandapi to implement kingmc command api")
class BukkitCommandManager : CommandManager {
    /**
     * The default namespace for commands to the commands that
     * registered into this command manager
     */
    override val defaultCommandNamespace: String
        get() = throw UnsupportedOperationException("Removal for compatibles")

    /**
     * Gets the platform of this
     */
    override val platform: Platform
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
    override fun getRegisteredCommands(): List<RegisteredCommand<*>> {
        throw UnsupportedOperationException("Removal for compatibles")
    }

    /**
     * Remove a registered command from this command manager
     */
    override fun minus(command: RegisteredCommand<*>) {
        throw UnsupportedOperationException("Removal for compatibles")
    }

    /**
     * Remove a registered command from this command manager byb name
     */
    override fun minus(name: String) {
        throw UnsupportedOperationException("Removal for compatibles")
    }

    /**
     * Add a registered command to this command manager
     */
    override fun plus(command: RegisteredCommand<*>) {
        throw UnsupportedOperationException("Removal for compatibles")
    }
}