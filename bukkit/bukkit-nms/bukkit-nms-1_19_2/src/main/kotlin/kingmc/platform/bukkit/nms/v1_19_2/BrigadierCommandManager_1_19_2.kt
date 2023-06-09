package kingmc.platform.bukkit.nms.v1_19_2

import kingmc.common.application.application
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Qualifier
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.common.context.condition.ConditionalOnBean
import kingmc.platform.Releasable
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.bukkit.brigadier.AliasesCommandNode
import kingmc.platform.bukkit.brigadier.BrigadierCommandManager
import kingmc.platform.bukkit.brigadier.BrigadierNMS
import kingmc.platform.bukkit.brigadier.removeCommand
import kingmc.platform.command.NAMESPACE_SEPARATOR
import kingmc.platform.command.model.Header
import kingmc.platform.facet.command.FacetCommandManager
import kingmc.platform.version.ConditionalOnVersion

@Component("brigadierCommandManager_1_19_2")
@ConditionalOnVersion("1.19.2")
@ConditionalOnBean(BrigadierNMS::class)
@Scope(BeanScope.SINGLETON)
@BukkitImplementation
class BrigadierCommandManager_1_19_2 : FacetCommandManager(), BrigadierCommandManager, Releasable {
    @Autowired
    @Qualifier("brigadierNMS_1_19_2")
    lateinit var brigadierNMS: BrigadierNMS_1_19_2

    /**
     * The default namespace for commands to the commands that
     * registered into this command manager
     */
    override val defaultCommandNamespace: String
        get() = application.name

    init {
        register.before { command ->
            val commandNode = command.data
            if (commandNode is Header) {
                try {
                    val commandHeader = brigadierNMS.deserializeBrigadierCommandFromCommandHeader(commandNode)
                    val builtCommandHeader = commandHeader.build()
                    val commandDispatcher = brigadierNMS.getBrigadierDispatcher()
                    // Register command with names without namespace
                    commandDispatcher.root.addChild(builtCommandHeader)
                    // Register command aliases
                    commandHeader.aliases.forEach {
                        commandDispatcher.root.addChild(AliasesCommandNode(it, builtCommandHeader))
                    }

                    // Register command with names with namespace
                    val namespace = commandNode.namespace
                    commandDispatcher.root.addChild(AliasesCommandNode("$namespace$NAMESPACE_SEPARATOR${commandHeader.literal}", builtCommandHeader))
                    // Register command aliases
                    commandHeader.aliases.forEach {
                        commandDispatcher.root.addChild(AliasesCommandNode("$namespace$NAMESPACE_SEPARATOR$it", builtCommandHeader))
                    }
                } catch (e: Exception) {
                    throw RuntimeException("An error occurred while registering command $commandNode", e)
                }
            } else {
                throw IllegalArgumentException("BrigadierCommandManager can only register header command nodes")
            }
            brigadierNMS.syncCommands()
        }

        unregister.before { command ->
            val commandNode = command.data
            if (commandNode is Header) {
                val commandDispatcher = brigadierNMS.getBrigadierDispatcher()

                commandDispatcher.removeCommand(commandNode.name)
                commandNode.aliases.forEach { alias ->
                    commandDispatcher.removeCommand(alias)
                }
                commandDispatcher.removeCommand("${commandNode.namespace}$NAMESPACE_SEPARATOR${commandNode.name}")
                commandNode.aliases.forEach { alias ->
                    commandDispatcher.removeCommand("${commandNode.namespace}$NAMESPACE_SEPARATOR${alias}")
                }
            } else {
                throw IllegalArgumentException("BrigadierCommandManager can only register header command nodes")
            }
            brigadierNMS.syncCommands()
        }
    }

    /**
     * Release
     */
    override fun release() {
        this.close()
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "BrigadierCommandManager_1_19_2(application=$application)"
    }
}