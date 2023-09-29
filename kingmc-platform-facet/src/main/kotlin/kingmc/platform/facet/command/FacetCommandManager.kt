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
 * @author kingsthere
 * @since 0.0.9
 */
@Component
@Scope(BeanScope.SINGLETON)
abstract class FacetCommandManager : CommandManager {
    /**
     * Registered commands
     */
    protected val registeredCommandsList: MutableList<Command<*>> = mutableListOf()

    val register = Facet<Command<*>, Unit> { command ->
        registeredCommandsList.add(command)
    }

    @FacetAvailable
    override fun register(command: Command<*>) = register.invoke(command)

    val unregister = Facet<Command<*>, Unit> { command ->
        registeredCommandsList.remove(command)
    }

    @FacetAvailable
    override fun unregister(command: Command<*>) {
        unregister.invoke(command)
    }

    override fun unregister(name: String) {
        registeredCommandsList.find { (it.data as Node).name == name }?.let {
            unregister(it)
        }
    }

    override fun getRegisteredCommands(): List<Command<*>> = registeredCommandsList

    override fun close() {
        registeredCommandsList.toList().forEach {
            unregister(it)
        }
    }
}