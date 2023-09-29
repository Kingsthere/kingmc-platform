package kingmc.platform.command.parameter
/**
 * An abstract default implementation of [Parameters]
 */
class AbstractParameters(
    private val value: Map<CommandParameter<*>, Any?> = mapOf()
) : Parameters {

    /**
     * Get a value from this parameters
     *
     * @since 0.0.3
     * @author kingsthere
     * @throws InvalidParameterException invalid parameter
     */
    @Suppress("UNCHECKED_CAST")
    override fun <TValue : Any> get(parameter: CommandParameter<TValue>): TValue? {
        try {
            val value = value[parameter] as? TValue
            if (!parameter.nullable && value == null) {
                throw InvalidParameterException("Parameter ${parameter.name} is marked as non-nullable")
            } else {
                if (parameter.default != null && value == null) {
                    // Return the default value if the parameter
                    // has a default value set
                    return parameter.default
                }
                return value
            }
        } catch (e: ClassCastException) {
            throw InvalidParameterException("Parameter ${parameter.name} not found")
        }
    }

    override fun toString(): String {
        return "DefaultParameters(value=$value)"
    }
}