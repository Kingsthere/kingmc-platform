package kingmc.platform.velocity.impl.command

import com.velocitypowered.api.command.CommandSource
import kingmc.platform.command.parameter.CommandParameter
import kingmc.platform.command.parameter.Parameters

class BrigadierParameters(private val brigadierCommandContext: BrigadierCommandContext<CommandSource>, private val parameters: List<CommandParameter<*>>) : Parameters {
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
        return "BrigadierParameters(value=$value)"
    }
}