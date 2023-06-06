package kingmc.platform.facet.command

import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.command.CommandManager
import kingmc.platform.command.model.Command
import kingmc.platform.command.model.Node
import kingmc.platform.facet.Facet
import kingmc.platform.facet.FacetAvailable
import kingmc.platform.facet.invoke

/**
 * A `facet available` [CommandManager] implementation
 *
 * @since 0.0.9
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
abstract class FacetCommandManager : CommandManager {
    /**
     * Registered commands
     */
    protected val registeredCommands: MutableList<Command<*>> = mutableListOf()

    val register = Facet<Command<*>, Unit> { command ->
        registeredCommands.add(command)
    }

    @FacetAvailable
    override fun register(command: Command<*>) = register.invoke(command)

    val unregister = Facet<Command<*>, Unit> { command ->
        registeredCommands.remove(command)
    }

    @FacetAvailable
    override fun unregister(command: Command<*>) {
        registeredCommands.remove(command)
    }

    override fun unregister(name: String) {
        registeredCommands.removeIf { (it.data as Node).name.startsWith(name) }
    }

    override fun getRegisteredCommands(): List<Command<*>> = registeredCommands

    override fun close() {
        registeredCommands.forEach {
            unregister(it)
        }
        registeredCommands.clear()
    }
}