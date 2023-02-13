package kingmc.platform.command.parameter

/**
 * A map stores the parameters that invoking the
 * command
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandParameter
 * @see InvalidParameterException
 */
interface Parameters {
    /**
     * Get a value from this parameters
     *
     * @since 0.0.3
     * @author kingsthere
     */
    operator fun <TValue : Any> get(parameter: CommandParameter<TValue>): TValue?

    companion object {
        /**
         * Returns a [Parameters] reference with empty values
         */
        val EMPTY: Parameters
            get() = EmptyParameters

        /**
         * Returns a [Parameters] reference with no values, but it can
         * return you default values of parameters
         */
        val EMPTY_WITH_DEFAULT: Parameters
            get() = EmptyParameters
    }
}