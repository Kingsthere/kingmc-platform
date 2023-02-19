package kingmc.platform.command

import kingmc.common.context.annotation.Component
import kingmc.platform.PlatformExposed
import kingmc.platform.command.model.RegisteredCommand
import java.io.Closeable

const val NAMESPACE_SEPARATOR = ":"

/**
 * A Manager to manage commands, such as registering commands,
 * modify registered commands
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Component
interface CommandManager : PlatformExposed, Closeable {
    /**
     * The default namespace for commands to the commands that
     * registered into this command manager
     */
    val defaultCommandNamespace: String

    /**
     * Add a registered command to this command manager
     */
    operator fun plus(command: RegisteredCommand<*>)

    /**
     * Remove a registered command from this command manager
     */
    operator fun minus(command: RegisteredCommand<*>)

    /**
     * Remove a registered command from this command manager byb name
     */
    operator fun minus(name: String)

    /**
     * Get all registered commands in this command manager
     */
    fun getRegisteredCommands(): List<RegisteredCommand<*>>
}