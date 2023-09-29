package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.CommandDispatcher
import com.mojang.brigadier.context.CommandContext
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.ConditionalOnBean
import kingmc.platform.command.CommandSender
import kingmc.platform.command.model.Header
import org.bukkit.World

/**
 * A superinterface exposed an interface to interact brigadier on bukkit, in each
 * version of minecraft there should be an implementation to adapt
 *
 * @author kingsthere
 * @since 0.0.5
 */
@Component("brigadierNMS")
@ConditionalOnBean([BrigadierEnvironment::class])
interface BrigadierNMS<TCommandSourceStack : Any> {
    /**
     * Setup vanilla commands into `CommandMap`
     */
    fun setVanillaCommands(first: Boolean)

    /**
     * Sync commands
     */
    fun syncCommands()

    /**
     * Send registered brigadier commands to players
     */
    fun sendCommands()

    /**
     * Gets the vanilla command dispatcher
     */
    fun getVanillaCommandDispatcher(): CommandDispatcher<TCommandSourceStack>

    /**
     * Gets the brigadier command dispatcher
     */
    fun getBrigadierCommandDispatcher(): CommandDispatcher<TCommandSourceStack>

    /**
     * Gets CommandSender from [CommandContext]
     */
    fun getCommandSender(cmdCtx: CommandContext<TCommandSourceStack>): CommandSender

    /**
     * Gets the [World] of [TCommandSourceStack]
     */
    fun getWorldForCSS(css: TCommandSourceStack): World

    /**
     * Deserialize brigadier command from [command header][Header]
     */
    fun deserializeBrigadierCommandFromCommandHeader(commandHeader: Header): HeaderArgumentBuilder<TCommandSourceStack>
}