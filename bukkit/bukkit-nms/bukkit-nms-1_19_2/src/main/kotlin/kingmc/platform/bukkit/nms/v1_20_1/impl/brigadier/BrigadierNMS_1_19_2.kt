package kingmc.platform.bukkit.nms.v1_20_1.impl.brigadier

import com.google.common.collect.Maps
import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.arguments.*
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.RequiredArgumentBuilder
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import com.mojang.brigadier.tree.RootCommandNode
import kingmc.common.application.Application
import kingmc.common.application.application
import kingmc.common.application.withApplication
import kingmc.common.context.annotation.Autowired
import kingmc.common.context.annotation.Component
import kingmc.common.context.condition.ConditionalOnBean
import kingmc.common.logging.error
import kingmc.common.logging.info
import kingmc.platform.audience.AudienceFactory
import kingmc.platform.bukkit.audience.BukkitAudienceFactory
import kingmc.platform.bukkit.brigadier.*
import kingmc.platform.bukkit.command.CommandMap
import kingmc.platform.bukkit.entity.player._BukkitCommandSender
import kingmc.platform.bukkit.nms.v1_20_1.NMSEntityPlayer_1_19_2
import kingmc.platform.bukkit.nms.v1_20_1.NMS_1_19_2
import kingmc.platform.command.*
import kingmc.platform.command.exceptions.CommandExecutionException
import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Header
import kingmc.platform.command.model.Node
import kingmc.platform.command.parameter.*
import kingmc.platform.command.suggestion.BlockingSuggestionProvider
import kingmc.platform.command.suggestion.SuspendSuggestionProvider
import kingmc.platform.version.ConditionalOnVersion
import net.minecraft.commands.CommandListenerWrapper
import net.minecraft.commands.ICompletionProvider
import net.minecraft.network.protocol.game.PacketPlayOutCommands
import net.minecraft.server.level.EntityPlayer
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.craftbukkit.v1_19_R1.command.BukkitCommandWrapper
import org.bukkit.craftbukkit.v1_19_R1.command.VanillaCommandWrapper
import org.bukkit.event.player.PlayerCommandSendEvent
import org.spigotmc.SpigotConfig
import java.lang.reflect.Method
import java.util.*

@Suppress("ClassName")
@Component("brigadierNMS_1_19_2")
@ConditionalOnVersion("1.19.2")
@ConditionalOnBean(BrigadierNMS::class)
class BrigadierNMS_1_19_2 : BrigadierNMS<CommandListenerWrapper> {
    @Autowired
    lateinit var audienceFactory: AudienceFactory

    @Autowired
    lateinit var nms: NMS_1_19_2
    
    @Autowired
    lateinit var commandContextFactory: CommandContextFactory

    @Autowired
    lateinit var commandMap: CommandMap

    init {
        withApplication {
            info("Using ${this@BrigadierNMS_1_19_2::class.qualifiedName} as brigadier support...")
        }
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

    private val setVanillaCommands: Method =
        nms.getCraftServer().javaClass.getDeclaredMethod("setVanillaCommands", Boolean::class.java)

    private val sendAsync: Method =
        getCommandDispatcher().javaClass.getDeclaredMethod("sendAsync", NMSEntityPlayer_1_19_2::class.java)

    private val a: Method = getCommandDispatcher().javaClass.getDeclaredMethod("a",
            CommandNode::class.java,
            CommandNode::class.java,
            CommandListenerWrapper::class.java,
            MutableMap::class.java)

    override fun setVanillaCommands(first: Boolean) {
        setVanillaCommands.setAccessible(true)
        setVanillaCommands(nms.getCraftServer(), first)
        setVanillaCommands.setAccessible(false)
    }

    override fun syncCommands() {
        val craftServer = nms.getCraftServer()
        val dispatcher = net.minecraft.commands.CommandDispatcher().also {
            craftServer.server.at.b().d = it
        }
        commandMap.knownCommands.entries.forEach {
            val (label, command) = it
            if (command is VanillaCommandWrapper) {
                var node: LiteralCommandNode<CommandListenerWrapper> = command.vanillaCommand as LiteralCommandNode<CommandListenerWrapper>
                if (node.literal != label) {
                    val clone: LiteralCommandNode<CommandListenerWrapper> = LiteralCommandNode(
                        label,
                        node.command,
                        node.requirement,
                        node.redirect,
                        node.redirectModifier,
                        node.isFork
                    )
                    node.children.forEach { child ->
                        clone.addChild(child)
                    }
                    node = clone
                }
                dispatcher.a().root.addChild(node)
            } else {
                BukkitCommandWrapper(craftServer, command).register(dispatcher.a(), label)
            }

        }
    }

    override fun sendCommands() {
        sendAsync.setAccessible(true)
        nms.getMinecraftServer().ac().k.forEach {
            sendCommandsAsync(it)
        }
        sendAsync.setAccessible(false)
    }

    private fun sendCommandsAsync(entityplayer: EntityPlayer) {
        a.setAccessible(true)
        val commandDispatcher = getCommandDispatcher().a()
        if (SpigotConfig.tabComplete >= 0) {
            val map: MutableMap<CommandNode<CommandListenerWrapper>, CommandNode<ICompletionProvider>> =
                Maps.newIdentityHashMap()
            val vanillaRoot: RootCommandNode<ICompletionProvider> = RootCommandNode()
            val vanilla = entityplayer.c.vanillaCommandDispatcher.a().root
            map[vanilla] = vanillaRoot
            this.a(vanilla, vanillaRoot, entityplayer.cT(), map)
            val rootcommandnode: RootCommandNode<ICompletionProvider> = RootCommandNode()
            map[commandDispatcher.root] = rootcommandnode
            this.a(commandDispatcher.root, rootcommandnode, entityplayer.cT(), map)
            val bukkit: MutableCollection<String> = LinkedHashSet()
            val var8: Iterator<*> = rootcommandnode.children.iterator()
            while (var8.hasNext()) {
                val node = var8.next() as CommandNode<*>
                bukkit.add(node.name)
            }
            val event = PlayerCommandSendEvent(entityplayer.bukkitEntity, LinkedHashSet(bukkit))
            event.player.server.pluginManager.callEvent(event)
            val var9: Iterator<*> = bukkit.iterator()
            while (var9.hasNext()) {
                val orig = var9.next() as String
                if (!event.commands.contains(orig)) {
                    rootcommandnode.removeCommand(orig)
                }
            }
            entityplayer.b.a(PacketPlayOutCommands(rootcommandnode))
        }
        a.setAccessible(false)
    }

    private fun getCommandDispatcher(): net.minecraft.commands.CommandDispatcher = nms.getMinecraftServer().aC()

    override fun getVanillaCommandDispatcher(): CommandDispatcher<CommandListenerWrapper> {
        return this.nms.getMinecraftServer().vanillaCommandDispatcher.a()
    }

    override fun getBrigadierCommandDispatcher(): CommandDispatcher<CommandListenerWrapper> {
        return this.nms.getMinecraftServer().aC().a()
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
                        return@executes withApplication(handler.application) {
                            try {
                                val parameters = Parameters.EMPTY
                                val commandContext = commandContextFactory.create(getCommandSender(css), parameters, css.input)
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
                                    val commandContext = commandContextFactory.create(getCommandSender(css), parameters, css.input)
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
                        return@executes withApplication(handler.application) {
                            try {
                                val parameters = Parameters.EMPTY
                                val commandContext = commandContextFactory.create(getCommandSender(css), parameters, css.input)
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
                                    val commandContext = commandContextFactory.create(getCommandSender(css), parameters, css.input)
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
                    return@executes withApplication(owner.application) {
                        try {
                            val parameters = Parameters.EMPTY
                            val commandContext = commandContextFactory.create(getCommandSender(css), parameters, css.input)
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
                                val commandContext = commandContextFactory.create(getCommandSender(css), parameters, css.input)
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
    }

    private fun deserializeCommandHandlerParameters(
        handler: Handler,
        listed: List<CommandParameter<*>>,
        deserializing: CommandParameter<*>,
        index: Int
    ): ArgumentBuilder<CommandListenerWrapper, *> {
        return deserializeCommandParameter(deserializing, this@BrigadierNMS_1_19_2.application).apply {
                    executes { css ->
                        return@executes withApplication(handler.application) {
                            try {
                                val parameters = BrigadierParameters_1_19_2(css, listed.subList(0, index + 1))
                                val commandContext = commandContextFactory.create(getCommandSender(css), parameters, css.input)
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
        error("An error occurred while executing a command:", CommandExecutionException("An error occurred while executing a command", exception))
    }

    private fun <TValue : Any> deserializeCommandParameter(parameter: CommandParameter<TValue>, application: Application): RequiredArgumentBuilder<CommandListenerWrapper, TValue> {
        return RequiredArgumentBuilder
            .argument<CommandListenerWrapper, TValue>(parameter.name, getArgumentType(parameter))
            .apply {
                val suggestion = parameter.suggestion
                if (suggestion != null) {
                    if (suggestion is BlockingSuggestionProvider) {
                        suggests(BlockingSuggestionProvider_1_19_2(suggestion, this@BrigadierNMS_1_19_2, application = application))
                    }
                    if (suggestion is SuspendSuggestionProvider) {
                        suggests(SuspendSuggestionProvider_1_19_2(suggestion, this@BrigadierNMS_1_19_2, application = application))
                    }
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
            is StringCommandParameter -> {
                StringArgumentType.word()
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