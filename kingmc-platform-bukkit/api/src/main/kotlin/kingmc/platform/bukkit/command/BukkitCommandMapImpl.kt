package kingmc.platform.bukkit.command

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.SimpleCommandMap
import java.lang.reflect.Field

/**
 * Bukkit side [CommandMap] implementation implemented by reflection
 */
@Component
open class BukkitCommandMapImpl : CommandMap, org.bukkit.command.CommandMap {
    private val classCraftServer: Class<*> by lazy {
        Class.forName(
            "org.bukkit.craftbukkit." + Bukkit.getServer().javaClass.getPackage().name.substring(23) + ".CraftServer"
        )
    }
    private val commandMap: SimpleCommandMap = classCraftServer.getDeclaredMethod("getCommandMap")
        .invoke(Bukkit.getServer()) as SimpleCommandMap
    override val bukkit: org.bukkit.command.CommandMap
        get() = commandMap
    private val knownCommandsField: Field = SimpleCommandMap::class.java.getDeclaredField("knownCommands")
    @Suppress("UNCHECKED_CAST")
    override val knownCommands: MutableMap<String, Command>
        get() {
            knownCommandsField.isAccessible = true
            val value = knownCommandsField.get(commandMap) as MutableMap<String, Command>
            knownCommandsField.isAccessible = false
            return value
        }

    /**
     * Unregister a command by its label name
     *
     * @return `true` if the command unregistered successfully
     */
    override fun unregister(label: String): Boolean {
        return try {
            knownCommands.remove(label)
            true
        } catch (exception: Exception) {
            exception.printStackTrace()
            false
        }
    }

    override fun registerAll(fallbackPrefix: String, commands: MutableList<Command>) {
        commandMap.registerAll(fallbackPrefix, commands)
    }

    override fun register(label: String, fallbackPrefix: String, command: Command): Boolean {
        return commandMap.register(label, fallbackPrefix, command)
    }

    override fun register(fallbackPrefix: String, command: Command): Boolean {
        return commandMap.register(fallbackPrefix, command)
    }

    override fun dispatch(sender: CommandSender, cmdLine: String): Boolean {
        return commandMap.dispatch(sender, cmdLine)
    }

    override fun clearCommands() {
        commandMap.clearCommands()
    }

    override fun getCommand(name: String): Command? {
        return commandMap.getCommand(name)
    }

    override fun tabComplete(sender: CommandSender, cmdLine: String): MutableList<String>? {
        return commandMap.tabComplete(sender, cmdLine)
    }

    override fun tabComplete(sender: CommandSender, cmdLine: String, location: Location?): MutableList<String>? {
        return commandMap.tabComplete(sender, cmdLine, location)
    }
}