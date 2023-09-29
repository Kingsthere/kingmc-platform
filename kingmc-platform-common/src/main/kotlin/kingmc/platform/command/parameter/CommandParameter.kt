package kingmc.platform.command.parameter

import kingmc.platform.command.StringReader
import kingmc.platform.command.exceptions.CommandSyntaxException
import kingmc.platform.command.suggestion.SuggestionProvider
import kotlin.reflect.KClass

/**
 * ParameterDeserialize define an interface to deserialize from parameter's origin
 * value(String) to the data specified
 *
 * @author kingsthere
 * @since 0.0.3
 */
@FunctionalInterface
interface ParameterDeserializer<V> {
    /**
     * Deserialize a parameter from a [StringReader]
     *
     * @since 0.0.3
     */
    @Throws(CommandSyntaxException::class)
    fun deserialize(reader: StringReader): V
}

/**
 * Determining a command parameter that is registered in
 * a command
 *
 * @author kingsthere
 * @since 0.0.3
 * @param TValue the type of value this parameter specified to
 */
interface CommandParameter<TValue : Any> {
    /**
     * The name of this parameter
     */
    val name: String

    /**
     * The description of this parameter
     */
    val description: String?

    /**
     * Specify is this parameter's value is nullable
     */
    var nullable: Boolean

    /**
     * The index range of this command parameter for executing
     * command
     */
    val index: IntRange

    /**
     * The deserializer of this parameter
     */
    val deserializer: ParameterDeserializer<TValue>

    /**
     * The default value of this command parameter, to
     * use this property, you must set [nullable] value
     * to `true`
     */
    var default: TValue?

    /**
     * The type([Class]) of this parameter's value
     */
    val type: KClass<TValue>

    /**
     * The completion suggester of this parameter to
     * provide completions when trying to type this
     * parameter
     *
     * @see SuggestionProvider
     */
    var suggestion: SuggestionProvider?

}

/**
 * Basic types of command parameter
 */

open class QuotableStringCommandParameter(
    override val name: String,
    override val index: IntRange,
    override val description: String? = null,
    override var suggestion: SuggestionProvider? = null,
) : CommandParameter<String> {
    /**
     * Specify is this parameter's value is nullable
     */
    override var nullable: Boolean = false

    /**
     * The wrapper of this parameter
     */
    override val deserializer: ParameterDeserializer<String> =
        QuotableStringCommandDeserializer

    /**
     * The default value of this command parameter
     */
    override var default: String? = null

    override val type: KClass<String>
        get() = String::class

    object QuotableStringCommandDeserializer : ParameterDeserializer<String> {
        override fun deserialize(reader: StringReader): String =
            reader.readString()
    }

    override fun toString(): String {
        return "quotableString()"
    }
}

open class StringCommandParameter(
    override val name: String,
    override val index: IntRange,
    override val description: String? = null,
    override var suggestion: SuggestionProvider? = null,
) : CommandParameter<String> {
    /**
     * Specify is this parameter's value is nullable
     */
    override var nullable: Boolean = false

    /**
     * The wrapper of this parameter
     */
    override val deserializer: ParameterDeserializer<String> =
        StringCommandDeserializer

    /**
     * The default value of this command parameter
     */
    override var default: String? = null

    override val type: KClass<String>
        get() = String::class

    object StringCommandDeserializer : ParameterDeserializer<String> {
        override fun deserialize(reader: StringReader): String =
            reader.readUnsafeString()
    }

    override fun toString(): String {
        return "string()"
    }
}

open class GreedyStringCommandParameter(
    override val name: String,
    override val index: IntRange,
    override val description: String? = null,
    override var suggestion: SuggestionProvider? = null,
) : CommandParameter<String> {
    /**
     * Specify is this parameter's value is nullable
     */
    override var nullable: Boolean = false

    /**
     * The wrapper of this parameter
     */
    override val deserializer: ParameterDeserializer<String> =
        GreedyStringCommandDeserializer

    /**
     * The default value of this command parameter
     */
    override var default: String? = null

    override val type: KClass<String>
        get() = String::class

    object GreedyStringCommandDeserializer : ParameterDeserializer<String> {
        override fun deserialize(reader: StringReader): String {
            val text: String = reader.remaining
            reader.cursor = reader.totalLength
            return text
        }
    }

    override fun toString(): String {
        return "greedyString()"
    }
}

open class FloatCommandParameter(
    override val name: String,
    override val index: IntRange,
    val range: ClosedFloatingPointRange<Float> = Float.MIN_VALUE..Float.MAX_VALUE,
    override val description: String? = null,
    override var suggestion: SuggestionProvider? = null,
) : CommandParameter<Float> {
    /**
     * Specify is this parameter's value is nullable
     */
    override var nullable: Boolean = false

    /**
     * The wrapper of this parameter
     */
    override val deserializer: ParameterDeserializer<Float> =
        FloatCommandDeserializer(this)

    /**
     * The default value of this command parameter
     */
    override var default: Float? = null

    override val type: KClass<Float>
        get() = Float::class

    class FloatCommandDeserializer(val parameter: FloatCommandParameter) : ParameterDeserializer<Float> {
        override fun deserialize(reader: StringReader): Float {
            val start: Int = reader.cursor
            val result = reader.readFloat()
            val minimum = parameter.range.start
            val maximum = parameter.range.endInclusive
            if (result < minimum) {
                reader.cursor = start
                throw CommandSyntaxException("Float must not be less than $minimum, found $result", reader.string, reader.cursor)
            }
            if (result > maximum) {
                reader.cursor = start
                throw CommandSyntaxException("Float must not be more than $maximum, found $result", reader.string, reader.cursor)
            }
            return result
        }
    }

    override fun toString(): String {
        val minimum = range.start
        val maximum = range.endInclusive
        return if (minimum == Float.MIN_VALUE && maximum == Float.MAX_VALUE) {
            "float()"
        } else if (maximum == Float.MAX_VALUE) {
            "float($minimum)"
        } else {
            "float($minimum..$maximum)"
        }
    }
}

open class DoubleCommandParameter(
    override val name: String,
    override val index: IntRange,
    val range: ClosedFloatingPointRange<Double> = Double.MIN_VALUE..Double.MAX_VALUE,
    override val description: String? = null,
    override var suggestion: SuggestionProvider? = null,
) : CommandParameter<Double> {
    /**
     * Specify is this parameter's value is nullable
     */
    override var nullable: Boolean = false

    /**
     * The wrapper of this parameter
     */
    override val deserializer: ParameterDeserializer<Double> =
        DoubleCommandDeserializer(this)

    /**
     * The default value of this command parameter
     */
    override var default: Double? = null

    override val type: KClass<Double>
        get() = Double::class

    class DoubleCommandDeserializer(val parameter: DoubleCommandParameter) : ParameterDeserializer<Double> {
        override fun deserialize(reader: StringReader): Double {
            val start: Int = reader.cursor
            val result = reader.readDouble()
            val minimum = parameter.range.start
            val maximum = parameter.range.endInclusive
            if (result < minimum) {
                reader.cursor = start
                throw CommandSyntaxException("Double must not be less than $minimum, found $result", reader.string, reader.cursor)
            }
            if (result > maximum) {
                reader.cursor = start
                throw CommandSyntaxException("Double must not be more than $maximum, found $result", reader.string, reader.cursor)
            }
            return result
        }
    }

    override fun toString(): String {
        val minimum = range.start
        val maximum = range.endInclusive
        return if (minimum == Double.MIN_VALUE && maximum == Double.MAX_VALUE) {
            "double()"
        } else if (maximum == Double.MAX_VALUE) {
            "double($minimum)"
        } else {
            "double($minimum..$maximum)"
        }
    }
}

open class IntegerCommandParameter(
    override val name: String,
    override val index: IntRange,
    val range: IntRange = Int.MIN_VALUE..Int.MAX_VALUE,
    override val description: String? = null,
    override var suggestion: SuggestionProvider? = null,
) : CommandParameter<Int> {
    /**
     * Specify is this parameter's value is nullable
     */
    override var nullable: Boolean = false

    /**
     * The wrapper of this parameter
     */
    override val deserializer: ParameterDeserializer<Int> =
        IntegerCommandDeserializer(this)

    /**
     * The default value of this command parameter
     */
    override var default: Int? = null

    override val type: KClass<Int>
        get() = Int::class

    class IntegerCommandDeserializer(val parameter: IntegerCommandParameter) : ParameterDeserializer<Int> {
        override fun deserialize(reader: StringReader): Int {
            val start: Int = reader.cursor
            val result = reader.readInt()
            val minimum = parameter.range.first
            val maximum = parameter.range.last
            if (result < minimum) {
                reader.cursor = start
                throw CommandSyntaxException("Integer must not be less than $minimum, found $result", reader.string, reader.cursor)
            }
            if (result > maximum) {
                reader.cursor = start
                throw CommandSyntaxException("Integer must not be more than $maximum, found $result", reader.string, reader.cursor)
            }
            return result
        }
    }

    override fun toString(): String {
        val minimum = range.first
        val maximum = range.last
        return if (minimum == Int.MIN_VALUE && maximum == Int.MAX_VALUE) {
            "integer()"
        } else if (maximum == Int.MAX_VALUE) {
            "integer($minimum)"
        } else {
            "integer($minimum..$maximum)"
        }
    }
}

open class BooleanCommandParameter(
    override val name: String,
    override val index: IntRange,
    override val description: String? = null,
    override var suggestion: SuggestionProvider? = null,
) : CommandParameter<Boolean> {
    /**
     * Specify is this parameter's value is nullable
     */
    override var nullable: Boolean = false

    /**
     * The wrapper of this parameter
     */
    override val deserializer: ParameterDeserializer<Boolean> =
        BooleanCommandWrapper

    /**
     * The default value of this command parameter
     */
    override var default: Boolean? = false

    override val type: KClass<Boolean>
        get() = Boolean::class

    object BooleanCommandWrapper : ParameterDeserializer<Boolean> {
        override fun deserialize(reader: StringReader): Boolean =
            reader.readBoolean()
    }

    override fun toString(): String {
        return "boolean()"
    }
}
