package kingmc.platform.bukkit.command

import kingmc.common.context.annotation.Component
import kingmc.platform.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.SimpleCommandMap
import java.lang.reflect.Field

@Component
class BukkitCommandMap : CommandMap, org.bukkit.command.CommandMap {
    private val classCraftServer: Class<*> by lazy {
        Class.forName(
            "org.bukkit.craftbukkit." + Bukkit.getServer().javaClass.getPackage().name.substring(23) + ".CraftServer"
        )
    }
    private val commandMap: SimpleCommandMap = classCraftServer.getDeclaredMethod("getCommandMap")
        .invoke(Bukkit.getServer()) as SimpleCommandMap
    /**
     * Unregister a command by its label name
     *
     * @return `true` if the command unregistered successfully
     */
    @Suppress("UNCHECKED_CAST")
    override fun unregister(label: String): Boolean {
        return try {
            val knownCommandsField: Field = SimpleCommandMap::class.java.getDeclaredField("knownCommands")
            knownCommandsField.isAccessible = true
            val knownCommands: MutableMap<String, Command> = knownCommandsField.get(commandMap) as MutableMap<String, Command>
            val command: Command = commandMap.getCommand(label)!!
            for (alias in command.aliases) knownCommands.remove(alias)
            knownCommands.remove(command.name)
            command.unregister(commandMap)
            knownCommandsField.isAccessible = false
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