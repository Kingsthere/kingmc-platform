package kingmc.platform.bukkit.nms.v1_19_2

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import kingmc.common.application.Application
import kingmc.common.application.application
import kingmc.common.application.suspendApplication
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.condition.ConditionalOnBean
import kingmc.common.logging.info
import kingmc.platform.audience.AudienceFactory
import kingmc.platform.audience.CommandSender
import kingmc.platform.bukkit.audience.BukkitAudienceFactory
import kingmc.platform.bukkit.audience.OriginalBukkitCommandSender
import kingmc.platform.bukkit.brigadier.*
import kingmc.platform.bukkit.fromBukkit
import kingmc.platform.command.CommandContext
import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Header
import kingmc.platform.command.model.Node
import kingmc.platform.command.parameter.*
import kingmc.platform.command.rootHandler
import kingmc.platform.version.ConditionalOnVersion
import kotlinx.coroutines.*
import net.minecraft.commands.CommandSourceStack
import org.bukkit.Location
import org.bukkit.World
import java.util.*

@Suppress("ClassName")
@Component("brigadierNMS_1_19_2")
@ConditionalOnVersion("1.19.2")
@ConditionalOnBean(BrigadierNMS::class)
class BrigadierNMS_1_19_2 : BrigadierNMS<CommandSourceStack> {
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
        minecraftServer.getMinecraftServer().playerList.players.forEach {
            minecraftServer.getMinecraftServer().resources.managers.commands.sendCommands(it)
        }
    }

    /**
     * Gets the command dispatcher
     */
    override fun getBrigadierDispatcher(): CommandDispatcher<CommandSourceStack> {
        return minecraftServer.getMinecraftServer().resources.managers.commands.dispatcher
    }

    /**
     * Gets the command sender of a command as a [CommandSender]
     */
    override fun getCommandSender(cmdCtx: BrigadierCommandContext<CommandSourceStack>): CommandSender {
        val css = cmdCtx.source as CommandSourceStack
        val sender = css.bukkitSender
        val position = css.position
        val rotation = css.rotation
        val world: World = this.getWorldForCSS(css)
        val location = Location(world, position.x(), position.y(), position.z(), rotation.x, rotation.y)
        val proxyEntity = css.entity
        val proxy: OriginalBukkitCommandSender? = proxyEntity?.bukkitEntity
        return if (proxy == null || proxy == sender) {
            (audienceFactory as BukkitAudienceFactory).commandSender(sender)
        } else {
            ProxiedLocatableCommandSender(
                caller = (audienceFactory as BukkitAudienceFactory).commandSender(sender),
                callee = (audienceFactory as BukkitAudienceFactory).commandSender(proxy),
                proxiedLocation = location.fromBukkit()
            )
        }
    }

    override fun getWorldForCSS(css: CommandSourceStack): World {
        return css.level.world
    }

    override fun deserializeBrigadierCommandFromCommandHeader(commandHeader: Header): HeaderArgumentBuilder<CommandSourceStack> {
        return HeaderArgumentBuilder<CommandSourceStack>(commandHeader).apply {
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
                        try {
                            val parameters = Parameters.EMPTY
                            application(commandHeader.application) {
                                val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                handler.invoke(commandContext).asInt()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            return@executes 0
                        }
                    }
                } else {
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

    private fun deserializeBrigadierCommandFromCommandNode(commandNode: Node): NodeArgumentBuilder<CommandSourceStack> {
        return NodeArgumentBuilder<CommandSourceStack>(commandNode).apply {
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
                        try {
                            val parameters = Parameters.EMPTY
                            application(commandNode.application) {
                                val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                handler.invoke(commandContext).asInt()
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            return@executes 0
                        }
                    }
                } else {
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

    private fun deserializeBrigadierCommandForCommandHandler(commandHandler: Handler, owner: Node): HandlerArgumentBuilder<CommandSourceStack> {
        return HandlerArgumentBuilder<CommandSourceStack>(commandHandler).apply {
            if (handler.parameters.size == 0) {
                // Insert empty root parameters executor
                executes { css ->
                    try {
                        val parameters = Parameters.EMPTY
                        application(owner.application) {
                            val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                            handler.invoke(commandContext).asInt()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        return@executes 0
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
    ): ArgumentBuilder<CommandSourceStack, *> {
        return deserializeCommandParameter(deserializing, this@BrigadierNMS_1_19_2.application).apply {
                    executes { css ->
                        try {
                            val parameters = BrigadierParameters_1_19_2(css, listed.subList(0, index + 1))
                            return@executes runBlocking {
                                this@BrigadierNMS_1_19_2.suspendApplication {
                                    val commandContext = CommandContext(getCommandSender(css), parameters, css.input)
                                    coroutineScope {
                                        handler.invoke(commandContext).asInt()
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                            return@executes 0
                        }
                    }
                    if (index + 1 < listed.size) {
                        val newIndex = index + 1
                        then(deserializeCommandHandlerParameters(handler, listed, listed[newIndex], newIndex))
                    }
            }

    }

    private fun <TValue : Any> deserializeCommandParameter(parameter: CommandParameter<TValue>, application: Application<*>): RequiredArgumentBuilder<CommandSourceStack, TValue> {
        return RequiredArgumentBuilder
            .argument<CommandSourceStack, TValue>(parameter.name, getArgumentType(parameter))
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

    override fun toString(): String {
        return "BrigadierNMS_1_19_2(minecraftServer=$minecraftServer)"
    }

}