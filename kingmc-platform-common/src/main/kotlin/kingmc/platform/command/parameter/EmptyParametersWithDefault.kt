package kingmc.platform.command.parameter

/**
 * A [Parameters] object represent an empty set of parameters, returns default value
 * of parameters if there was a default value of that parameter
 */
object EmptyParametersWithDefault : Parameters {
    override fun <TValue : Any> get(parameter: CommandParameter<TValue>): TValue? {
        if (parameter.default != null) {
            return parameter.default
        }
        if (parameter.nullable) {
            return null
        }
        return null
    }
}