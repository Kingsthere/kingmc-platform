package kingmc.platform.bukkit.nms.v1_20_1.impl.brigadier

import kingmc.platform.bukkit.brigadier.BrigadierCommandContext
import kingmc.platform.command.parameter.CommandParameter
import kingmc.platform.command.parameter.Parameters
import net.minecraft.commands.CommandListenerWrapper

class BrigadierParameters_1_20_1(private val brigadierCommandContext: BrigadierCommandContext<CommandListenerWrapper>, private val parameters: List<CommandParameter<*>>) : Parameters {
    private val value: Map<CommandParameter<*>, Any> = buildMap {
        parameters.forEach { parameter ->
            put(parameter, brigadierCommandContext.getArgument(parameter.name, parameter.type.java))
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <TValue : Any> get(parameter: CommandParameter<TValue>): TValue? {
        val value = value[parameter]
        return if (value == null) {
            if (parameter.nullable) {
                if (parameter.default != null) {
                    parameter.default
                } else {
                    null
                }
            } else {
                throw IllegalStateException()
            }
        } else {
            value as TValue
        }
    }

    override fun toString(): String {
        return "BrigadierParameters_1_20_1(value=$value)"
    }
}