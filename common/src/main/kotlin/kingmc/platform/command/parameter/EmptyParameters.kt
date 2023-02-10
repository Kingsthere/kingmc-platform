package kingmc.platform.command.parameter

/**
 * A [Parameters] object represent an empty set of parameters
 */
object EmptyParameters : Parameters {
    override fun <TValue : Any> get(parameter: CommandParameter<TValue>): TValue? {
        return null
    }
}