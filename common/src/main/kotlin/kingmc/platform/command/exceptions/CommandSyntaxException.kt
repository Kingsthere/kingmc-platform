package kingmc.platform.command.exceptions

class CommandSyntaxException : Exception {
    val msg: String
        get() {
            var message: String = field
            val context = context
            if (context != null) {
                message += " at position $cursor: $context"
            }
            return message
        }
    val input: String?
    val cursor: Int

    constructor(message: String) : super(
        message,
        null,
        ENABLE_COMMAND_STACK_TRACES,
        ENABLE_COMMAND_STACK_TRACES
    ) {
        this.msg = message
        input = null
        cursor = -1
    }

    constructor(message: String, input: String?, cursor: Int) : super(
        message,
        null,
        ENABLE_COMMAND_STACK_TRACES,
        ENABLE_COMMAND_STACK_TRACES
    ) {
        this.msg = message
        this.input = input
        this.cursor = cursor
    }

    val context: String?
        get() {
            if (input == null || cursor < 0) {
                return null
            }
            val builder = StringBuilder()
            val cursor = input.length.coerceAtMost(cursor)
            if (cursor > CONTEXT_AMOUNT) {
                builder.append("...")
            }
            builder.append(input.substring(0.coerceAtLeast(cursor - CONTEXT_AMOUNT), cursor))
            builder.append("<--[HERE]")
            return builder.toString()
        }

    companion object {
        const val CONTEXT_AMOUNT = 10
        var ENABLE_COMMAND_STACK_TRACES = true
    }
}