package kingmc.platform.bukkit.nms.v1_20_1.impl.brigadier

import com.mojang.brigadier.tree.CommandNode
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
import kingmc.platform.bukkit.command.CommandMap
import kingmc.platform.command.NAMESPACE_SEPARATOR
import kingmc.platform.command.model.Header
import kingmc.platform.facet.command.FacetCommandManager
import kingmc.platform.version.ConditionalOnVersion
import net.minecraft.commands.CommandListenerWrapper
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

@Component("brigadierCommandManager_1_19_2")
@ConditionalOnVersion("1.19.2")
@ConditionalOnBean(BrigadierNMS::class)
@Scope(BeanScope.SINGLETON)
@BukkitImplementation
class BrigadierCommandManager_1_19_2 @Autowired constructor(
    @Qualifier("brigadierNMS_1_19_2") private val brigadierNMS: BrigadierNMS_1_19_2,
    private val commandMap: CommandMap
) : FacetCommandManager(), BrigadierCommandManager, Releasable {

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
                commandSendLock.withLock {
                    try {
                        val commandHeader = brigadierNMS.deserializeBrigadierCommandFromCommandHeader(commandNode)
                        val builtCommandHeader = commandHeader.build()
                        val commandDispatcher = brigadierNMS.getVanillaCommandDispatcher()

                        fun registerNode(commandNode: CommandNode<CommandListenerWrapper>) {
                            commandDispatcher.root.addChild(commandNode)
                        }

                        // Register command with names without namespace
                        registerNode(builtCommandHeader)
                        // Register command aliases
                        commandHeader.aliases.forEach {
                            registerNode(AliasesCommandNode(it, builtCommandHeader))
                        }

                        // Register command with names with namespace
                        val namespace = commandNode.namespace
                        registerNode(
                            AliasesCommandNode(
                                "$namespace$NAMESPACE_SEPARATOR${commandHeader.literal}",
                                builtCommandHeader
                            )
                        )
                        brigadierNMS.setVanillaCommands(false)
                        brigadierNMS.syncCommands()
                        brigadierNMS.sendCommands()
                    } catch (e: Exception) {
                        throw RuntimeException("An error occurred while registering command $commandNode", e)
                    }
                }
            } else {
                throw IllegalArgumentException("BrigadierCommandManager can only register header command nodes")
            }
        }

        unregister.before { command ->
            val commandNode = command.data
            if (commandNode is Header) {
                commandSendLock.withLock {
                    try {
                        val vanillaCommandDispatcher = brigadierNMS.getVanillaCommandDispatcher()
                        val brigadierCommandDispatcher = brigadierNMS.getBrigadierCommandDispatcher()

                        fun removeNodeName(commandNode: String) {
                            vanillaCommandDispatcher.removeCommand(commandNode)
                            vanillaCommandDispatcher.removeCommand("minecraft:$commandNode")
                            brigadierCommandDispatcher.removeCommand(commandNode)
                            brigadierCommandDispatcher.removeCommand("minecraft:$commandNode")
                            commandMap.unregister(commandNode)
                            commandMap.unregister("minecraft:$commandNode")
                        }

                        removeNodeName(commandNode.name)
                        commandNode.aliases.forEach { alias ->
                            removeNodeName(alias)
                        }
                        removeNodeName("${commandNode.namespace}$NAMESPACE_SEPARATOR${commandNode.name}")
                        brigadierNMS.setVanillaCommands(false)
                        brigadierNMS.syncCommands()
                        brigadierNMS.sendCommands()
                    } catch (e: Exception) {
                        throw RuntimeException("An error occurred while removing command $commandNode", e)
                    }
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

    companion object {
        val commandSendLock = ReentrantLock()
    }

    /**
     * Returns a string representation of the object.
     */
    override fun toString(): String {
        return "BrigadierCommandManager_1_19_2(application=$application)"
    }
}