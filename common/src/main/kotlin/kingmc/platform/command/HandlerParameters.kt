package kingmc.platform.command

import kingmc.common.application.WithApplication
import kingmc.platform.command.model.Handler
import kingmc.platform.command.parameter.*
import kingmc.platform.command.suggestion.BlockingSuggestionProvider
import kingmc.platform.command.suggestion.SuggestionProvider
import kingmc.platform.command.suggestion.SuggestionsBuilder
import kingmc.platform.command.suggestion.SuspendSuggestionProvider

val Handler.currentIndex: Int
    get() = this.parameters.size

/**
 * Create and return a configurable quotable string parameter within
 * specified index range
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Handler.string(name: String, index: IntRange = currentIndex..currentIndex, description: String? = null): StringCommandParameter {
    val parameter = StringCommandParameter(name, index, description)
    this.parameters.add(parameter)
    return parameter
}

/**
 * Create and return a configurable quotable string parameter within
 * specified index range
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Handler.quotableString(name: String, index: IntRange = currentIndex..currentIndex, description: String? = null): QuotableStringCommandParameter {
    val parameter = QuotableStringCommandParameter(name, index, description)
    this.parameters.add(parameter)
    return parameter
}

/**
 * Create and return a configurable greedy string parameter within
 * specified index range
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Handler.greedyString(name: String, index: IntRange = currentIndex..currentIndex, description: String? = null): GreedyStringCommandParameter {
    val parameter = GreedyStringCommandParameter(name, index, description)
    this.parameters.add(parameter)
    return parameter
}

/**
 * Create and return a configurable integer parameter within
 * specified index range
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Handler.integer(name: String, index: IntRange = currentIndex..currentIndex, range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE, description: String? = null): IntegerCommandParameter {
    val parameter = IntegerCommandParameter(name, index, range, description)
    this.parameters.add(parameter)
    return parameter
}

/**
 * Create and return a configurable float parameter within
 * specified index range
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Handler.float(name: String, index: IntRange = currentIndex..currentIndex, range: ClosedFloatingPointRange<Float> = Float.MIN_VALUE..Float.MAX_VALUE, description: String? = null): FloatCommandParameter {
    val parameter = FloatCommandParameter(name, index, range, description)
    this.parameters.add(parameter)
    return parameter
}

/**
 * Create and return a configurable integer parameter within
 * specified index range
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Handler.double(name: String, index: IntRange = currentIndex..currentIndex, range: ClosedFloatingPointRange<Double> = Double.MIN_VALUE..Double.MAX_VALUE, description: String? = null): DoubleCommandParameter {
    val parameter = DoubleCommandParameter(name, index, range, description)
    this.parameters.add(parameter)
    return parameter
}

/**
 * Create and return a configurable boolean parameter within
 * specified index range
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Handler.boolean(name: String, index: IntRange = currentIndex..currentIndex, description: String? = null): BooleanCommandParameter {
    val parameter = BooleanCommandParameter(name, index, description)
    this.parameters.add(parameter)
    return parameter
}

/**
 * Mark this command parameter as nullable or non-nullable
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun <TValue : Any> CommandParameter<TValue>.nullable(nullable: Boolean) =
    this.apply {
        this.nullable = nullable
    }

/**
 * Set the default value of this parameter
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun <TValue : Any> CommandParameter<TValue>.default(value: TValue?) =
    this.apply {
        this.default = value
    }

/**
 * Set the possible suggestion provider of this parameter
 */
fun <TParameter : CommandParameter<*>> TParameter.suggestion(suggester: SuggestionProvider) =
    this.apply {
        this.suggestion = suggester
    }

/**
 * Set the possible suggestion provider of this parameter
 */
fun <TParameter : CommandParameter<*>> TParameter.blockingSuggestion(suggester: @WithApplication SuggestionsBuilder.(CommandContext) -> Unit) =
    this.apply {
        this.suggestion = BlockingSuggestionProvider(suggester)
    }

/**
 * Set the possible suggestion provider of this parameter
 */
fun <TParameter : CommandParameter<*>> TParameter.suspendSuggestion(suggester: @WithApplication suspend SuggestionsBuilder.(CommandContext) -> Unit) =
    this.apply {
        this.suggestion = SuspendSuggestionProvider(suggester)
    }