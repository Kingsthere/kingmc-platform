package kingmc.platform.command

import kingmc.common.application.Isolated
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.command.model.Command

const val NAMESPACE_SEPARATOR = ":"

/**
 * A Manager to manage commands, such as registering commands,
 * modify registered commands
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@Isolated
interface CommandManager {
    /**
     * The default namespace for commands to the commands that
     * registered into this command manager
     */
    val defaultCommandNamespace: String

    /**
     * Register a command to this command manager
     */
    fun register(command: Command<*>)

    /**
     * Unregister a command from this command manager
     */
    fun unregister(command: Command<*>)

    /**
     * Unregister a command from this command manager by name
     */
    fun unregister(name: String)

    /**
     * Get all registered commands in this command manager
     */
    fun getRegisteredCommands(): List<Command<*>>

    /**
     * Close this command manager and unregister all commands registered by this manager
     */
    fun close()
}