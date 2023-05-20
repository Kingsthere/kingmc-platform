package kingmc.platform.bukkit.nms.v1_19_2

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import kingmc.common.application.Application
import kingmc.common.application.application
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.condition.ConditionalOnBean
import kingmc.common.logging.error
import kingmc.common.logging.info
import kingmc.platform.audience.AudienceFactory
import kingmc.platform.bukkit.audience.BukkitAudienceFactory
import kingmc.platform.bukkit.brigadier.*
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.command.CommandContext
import kingmc.platform.command.CommandSender
import kingmc.platform.command.exceptions.CommandExecutionException
import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Header
import kingmc.platform.command.model.Node
import kingmc.platform.command.parameter.*
import kingmc.platform.command.requiredParameters
import kingmc.platform.command.rootHandler
import kingmc.platform.version.ConditionalOnVersion
import kotlinx.coroutines.*
import net.minecraft.commands.CommandListenerWrapper
import org.bukkit.Location
import org.bukkit.World
import java.util.*

@Suppress("ClassName")
@Component("brigadierNMS_1_19_2")
@ConditionalOnVersion("1.19.2")
@ConditionalOnBean(BrigadierNMS::class)
class BrigadierNMS_1_19_2 : BrigadierNMS<CommandListenerWrapper> {
    @Autowired
    lateinit var audienceFactory: AudienceFactory

    @Autowired
    lateinit var minecraftServer: MinecraftServerNMS_1_19_2

    init {
        application {
            info("Using ${this@BrigadierNMS_1_19_2::class.qualifiedName} as brigadier support...")
        }
    }

    override fun syncCommands() {
        minecraftServer.getMinecraftServer().ac().k.forEach {
            minecraftServer.getMinecraftServer().at.b().d.a(it)
        }
    }

    /**
     * Gets the command dispatcher
     */
    override fun getBrigadierDispatcher(): CommandDispatcher<CommandListenerWrapper> {
        return minecraftServer.getMinecraftServer().at.b().d.a()
    }

    /**
     * Gets the command sender of a command as a [CommandSender]
     */
    override fun getCommandSender(cmdCtx: BrigadierCommandContext<CommandListenerWrapper>): CommandSender {
        val css = cmdCtx.source as CommandListenerWrapper
        val sender = css.bukkitSender
        val position = css.e()
        val rotation = css.l()
        val world: World = this.getWorldForCSS(css)
        val location = Location(world, position.a(), position.b(), position.c(), rotation.i, rotation.j)
        val proxyEntity = css.g()
        val proxy: _BukkitCommandSender? = proxyEntity?.bukkitEntity
        return if (proxy == null || proxy == sender) {
            (audienceFactory as BukkitAudienceFactory).commandSender(sender)
        } else {
            // TODO
            // ProxiedLocatableCommandSender(
            //     caller = (audienceFactory as BukkitAudienceFactory).commandSender(sender),
            //     callee = (audienceFactory as BukkitAudienceFactory).commandSender(proxy),
            //     proxiedLocation = location.asKingMC()
            // )
            (audienceFactory as BukkitAudienceFactory).commandSender(sender)
        }
    }

    override fun getWorldForCSS(css: CommandListenerWrapper): World {
        return css.f().world
    }

    override fun deserializeBrigadierCommandFromCommandHeader(commandHeader: Header): HeaderArgumentBuilder<CommandListenerWrapper> {
        return HeaderArgumentBuilder<CommandListenerWrapper>(commandHeader).apply {
            node.children.forEach {
                val deserializedCommandNode = deserializeBrigadierCommandFromCommandNode(it).build()
                then(deserializedCommandNode)
                it.aliases.forEach { alias ->
                    then(AliasesCommandNode<CommandListenerWrapper>(alias, deserializedCommandNode))
                }
            }
            node.rootHandler?.let { handler ->
                if (handler.parameters.size == 0) {
                    // Insert empty root parameters executor
                    executes { css ->
                        return@executes application(handler.application) {
                            try {
                                val parameters = Parameters.EMPTY
                                val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                handler.invoke(commandContext).asInt()
                            } catch (e: Exception) {
                                printCommandHandleException(e)
                                0
                            }
                        }
                    }
                } else {
                    // Insert default root parameters executor
                    if (handler.requiredParameters == 0) {
                        executes { css ->
                            return@executes application(handler.application) {
                                try {
                                    val parameters = Parameters.EMPTY_WITH_DEFAULT
                                    val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                    handler.invoke(commandContext).asInt()
                                } catch (e: Exception) {
                                    printCommandHandleException(e)
                                    0
                                }
                            }
                        }
                    }
                    then(deserializeCommandHandlerParameters(handler, handler.parameters, handler.parameters.first(), 0))
                }
            }
            node.handlers.forEach {
                if (it.name != ".root") {
                    val deserializedCommandNode = deserializeBrigadierCommandForCommandHandler(it, node).build()
                    then(deserializedCommandNode)
                    it.aliases.forEach { alias ->
                        then(AliasesCommandNode<CommandListenerWrapper>(alias, deserializedCommandNode))
                    }
                }
            }
        }
    }

    private fun deserializeBrigadierCommandFromCommandNode(commandNode: Node): NodeArgumentBuilder<CommandListenerWrapper> {
        return NodeArgumentBuilder<CommandListenerWrapper>(commandNode).apply {
            node.children.forEach {
                val deserializedCommandNode = deserializeBrigadierCommandFromCommandNode(it).build()
                then(deserializedCommandNode)
                it.aliases.forEach { alias ->
                    then(AliasesCommandNode(alias, deserializedCommandNode))
                }
            }
            node.rootHandler?.let { handler ->
                if (handler.parameters.size == 0) {
                    // Insert empty root parameters executor
                    executes { css ->
                        return@executes application(handler.application) {
                            try {
                                val parameters = Parameters.EMPTY
                                val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                handler.invoke(commandContext).asInt()
                            } catch (e: Exception) {
                                printCommandHandleException(e)
                                0
                            }
                        }
                    }
                } else {
                    // Insert default root parameters executor
                    if (handler.requiredParameters == 0) {
                        executes { css ->
                            return@executes application(handler.application) {
                                try {
                                    val parameters = Parameters.EMPTY_WITH_DEFAULT
                                    val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                    handler.invoke(commandContext).asInt()
                                } catch (e: Exception) {
                                    printCommandHandleException(e)
                                    0
                                }
                            }
                        }
                    }
                    then(deserializeCommandHandlerParameters(handler, handler.parameters, handler.parameters.first(), 0))
                }
            }
            node.handlers.forEach {
                if (it.name != ".root") {
                    val deserializedCommandNode = deserializeBrigadierCommandForCommandHandler(it, node).build()
                    then(deserializedCommandNode)
                    it.aliases.forEach { alias ->
                        then(AliasesCommandNode(alias, deserializedCommandNode))
                    }
                }
            }
        }
    }

    private fun deserializeBrigadierCommandForCommandHandler(commandHandler: Handler, owner: Node): HandlerArgumentBuilder<CommandListenerWrapper> {
        return HandlerArgumentBuilder<CommandListenerWrapper>(commandHandler).apply {
            if (handler.parameters.size == 0) {
                // Insert empty root parameters executor
                executes { css ->
                    return@executes application(owner.application) {
                        try {
                            val parameters = Parameters.EMPTY
                            val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                            handler.invoke(commandContext).asInt()
                        } catch (e: Exception) {
                            printCommandHandleException(e)
                            0
                        }
                    }
                }
            } else {
                then(deserializeCommandHandlerParameters(handler, handler.parameters, handler.parameters.first(), 0))
            }
        }
    }

    private fun deserializeCommandHandlerParameters(
        handler: Handler,
        listed: List<CommandParameter<*>>,
        deserializing: CommandParameter<*>,
        index: Int
    ): ArgumentBuilder<CommandListenerWrapper, *> {
        return deserializeCommandParameter(deserializing, this@BrigadierNMS_1_19_2.application).apply {
                    executes { css ->
                        return@executes application(handler.application) {
                            try {
                                val parameters = BrigadierParameters_1_19_2(css, listed.subList(0, index + 1))
                                val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                handler.invoke(commandContext).asInt()
                            } catch (e: Exception) {
                                printCommandHandleException(e)
                                0
                            }
                        }
                    }

                    if (index + 1 < listed.size) {
                        val newIndex = index + 1
                        then(deserializeCommandHandlerParameters(handler, listed, listed[newIndex], newIndex))
                    }
            }

    }

    fun printCommandHandleException(exception: Exception) {
        error("An error occurred while executing a command:")
        CommandExecutionException("An error occurred while executing a command", exception).printStackTrace()
    }

    private fun <TValue : Any> deserializeCommandParameter(parameter: CommandParameter<TValue>, application: Application): RequiredArgumentBuilder<CommandListenerWrapper, TValue> {
        return RequiredArgumentBuilder
            .argument<CommandListenerWrapper, TValue>(parameter.name, getArgumentType(parameter))
            .apply {
                if (parameter.suggestion != null) {
                    suggests(WrappedSuggestionProvider_1_19_2(parameter.suggestion!!, this@BrigadierNMS_1_19_2, outerApplication = application))
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <TValue : Any> getRecognizedBrigadierArgumentType(parameter: CommandParameter<TValue>): ArgumentType<TValue>? {
        return when (parameter) {
            is IntegerCommandParameter -> {
                IntegerArgumentType.integer(parameter.range.first, parameter.range.last)
            }
            is FloatCommandParameter -> {
                FloatArgumentType.floatArg(parameter.range.start, parameter.range.endInclusive)
            }
            is DoubleCommandParameter -> {
                DoubleArgumentType.doubleArg(parameter.range.start, parameter.range.endInclusive)
            }
            is QuotableStringCommandParameter -> {
                StringArgumentType.string()
            }
            is GreedyStringCommandParameter -> {
                StringArgumentType.greedyString()
            }
            is BooleanCommandParameter -> {
                BoolArgumentType.bool()
            }
            else -> null
        } as? ArgumentType<TValue>
    }

    private fun <TValue : Any> getArgumentType(parameter: CommandParameter<TValue>): ArgumentType<TValue> {
        val wrapped = getRecognizedBrigadierArgumentType(parameter)
        return wrapped
            ?: WrappedArgumentType(parameter)
    }

}