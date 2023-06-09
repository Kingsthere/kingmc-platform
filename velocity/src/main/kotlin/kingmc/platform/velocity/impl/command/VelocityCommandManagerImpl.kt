package kingmc.platform.velocity.impl.command

import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.velocitypowered.api.command.BrigadierCommand
import com.velocitypowered.api.command.CommandSource
import kingmc.common.application.Application
import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.Configuration
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.common.logging.error
import kingmc.platform.Releasable
import kingmc.platform.command.*
import kingmc.platform.command.exceptions.CommandExecutionException
import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Header
import kingmc.platform.command.model.Node
import kingmc.platform.command.parameter.*
import kingmc.platform.facet.command.FacetCommandManager
import kingmc.platform.velocity.VelocityImplementation
import kingmc.platform.velocity.VelocityProxyServer

/**
 * Configuration class responsible for instantiating [VelocityCommandManagerImpl]
 */
@Configuration
@Scope(BeanScope.SINGLETON)
@VelocityImplementation
class VelocityCommandManagerConfiguration {
    /**
     * Instantiate [VelocityCommandManagerImpl] into context
     */
    @Bean
    fun velocityCommandManagerImpl(proxyServer: VelocityProxyServer): VelocityCommandManagerImpl =
        VelocityCommandManagerImpl(application, proxyServer)
}

@VelocityImplementation
class VelocityCommandManagerImpl(val application: Application, val proxyServer: VelocityProxyServer) : FacetCommandManager(), Releasable {
    /**
     * The default namespace for commands to the commands that
     * registered into this command manager
     */
    override val defaultCommandNamespace: String
        get() = application.name

    private val _velocityCommandManager = proxyServer.asVelocityProxyServer().commandManager

    init {
        register.before { command ->
            val commandNode = command.data
            if (commandNode is Header) {
                try {
                    val commandHeader = deserializeBrigadierCommandFromCommandHeader(commandNode)
                    val builtCommandHeader = commandHeader.build()
                    // Register command with names without namespace
                    _velocityCommandManager.register(BrigadierCommand(builtCommandHeader))
                    // Register command aliases
                    commandHeader.aliases.forEach {
                        _velocityCommandManager.register(BrigadierCommand(AliasesCommandNode(it, builtCommandHeader)))
                    }

                    // Register command with names with namespace
                    val namespace = commandNode.namespace
                    _velocityCommandManager.register(BrigadierCommand(AliasesCommandNode("$namespace$NAMESPACE_SEPARATOR${commandHeader.literal}", builtCommandHeader)))
                    // Register command aliases
                    commandHeader.aliases.forEach {
                        _velocityCommandManager.register(BrigadierCommand(AliasesCommandNode("$namespace$NAMESPACE_SEPARATOR$it", builtCommandHeader)))
                    }
                } catch (e: Exception) {
                    throw RuntimeException("An error occurred while registering command $commandNode", e)
                }
            } else {
                throw IllegalArgumentException("BrigadierCommandManager can only register header command nodes")
            }
        }

        unregister.before { command ->
            val commandNode = command.data
            if (commandNode is Header) {
                _velocityCommandManager.unregister(commandNode.name)
                commandNode.aliases.forEach { alias ->
                    _velocityCommandManager.unregister(alias)
                }
                _velocityCommandManager.unregister("${commandNode.namespace}$NAMESPACE_SEPARATOR${commandNode.name}")
                commandNode.aliases.forEach { alias ->
                    _velocityCommandManager.unregister("${commandNode.namespace}$NAMESPACE_SEPARATOR${alias}")
                }
            } else {
                throw IllegalArgumentException("BrigadierCommandManager can only register header command nodes")
            }
        }
    }

    fun deserializeBrigadierCommandFromCommandHeader(commandHeader: Header): HeaderArgumentBuilder<CommandSource> {
        return HeaderArgumentBuilder<CommandSource>(commandHeader).apply {
            node.children.forEach {
                val deserializedCommandNode = deserializeBrigadierCommandFromCommandNode(it).build()
                then(deserializedCommandNode)
                it.aliases.forEach { alias ->
                    then(AliasesCommandNode<CommandSource>(alias, deserializedCommandNode))
                }
            }
            node.rootHandler?.let { handler ->
                if (handler.parameters.size == 0) {
                    // Insert empty root parameters executor
                    executes { css ->
                        return@executes withApplication(handler.application) {
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
                            return@executes withApplication(handler.application) {
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
                        then(AliasesCommandNode<CommandSource>(alias, deserializedCommandNode))
                    }
                }
            }
        }
    }

    private fun deserializeBrigadierCommandFromCommandNode(commandNode: Node): NodeArgumentBuilder<CommandSource> {
        return NodeArgumentBuilder<CommandSource>(commandNode).apply {
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
                        return@executes withApplication(handler.application) {
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
                            return@executes withApplication(handler.application) {
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

    private fun deserializeBrigadierCommandForCommandHandler(commandHandler: Handler, owner: Node): HandlerArgumentBuilder<CommandSource> {
        return HandlerArgumentBuilder<CommandSource>(commandHandler).apply {
            if (handler.parameters.size == 0) {
                // Insert empty root parameters executor
                executes { css ->
                    return@executes withApplication(owner.application) {
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
    ): ArgumentBuilder<CommandSource, *> {
        return deserializeCommandParameter(deserializing, this.application).apply {
            executes { css ->
                return@executes withApplication(handler.application) {
                    try {
                        val parameters = BrigadierParameters(css, listed.subList(0, index + 1))
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
        error(
            "An error occurred while executing a command:",
            CommandExecutionException("An error occurred while executing a command", exception)
        )
    }

    private fun <TValue : Any> deserializeCommandParameter(parameter: CommandParameter<TValue>, application: Application): RequiredArgumentBuilder<CommandSource, TValue> {
        return RequiredArgumentBuilder
            .argument<CommandSource, TValue>(parameter.name, getArgumentType(parameter))
            .apply {
                if (parameter.suggestion != null) {
                    suggests(WrappedSuggestionProvider(parameter.suggestion!!, application = application))
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

    /**
     * Gets the command sender of a brigadier command context as a [CommandSender]
     */
    fun getCommandSender(cmdCtx: BrigadierCommandContext<CommandSource>): CommandSender {
        val commandSource = cmdCtx.source as CommandSource
        return proxyServer.getCommandSenderForVelocity(commandSource)
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