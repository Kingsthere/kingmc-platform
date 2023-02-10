package kingmc.platform.bukkit.brigadier

import com.mojang.brigadier.LiteralMessage
import com.mojang.brigadier.StringReader
import com.mojang.brigadier.arguments.ArgumentType
import com.mojang.brigadier.exceptions.CommandExceptionType
import kingmc.platform.command.exceptions.CommandSyntaxException
import kingmc.platform.command.parameter.CommandParameter

class WrappedArgumentType<T : Any>(private val commandParameter: CommandParameter<T>) : ArgumentType<T> {
    override fun parse(reader: StringReader): T {
        return try {
            commandParameter.deserializer.deserialize(WrappedStringReader(reader))
        } catch (e: CommandSyntaxException) {
            throw com.mojang.brigadier.exceptions.CommandSyntaxException(object : CommandExceptionType {  }, LiteralMessage(e.message), e.input, e.cursor)
        }
    }
}