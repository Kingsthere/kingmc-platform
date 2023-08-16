package kingmc.platform.exception

import kingmc.common.text.*
import kingmc.platform.audience.kind.TextCapable
import kingmc.util.exception.ExceptionHandler
import kingmc.util.text.toText
import net.kyori.adventure.text.format.TextDecoration

object TextCapableExceptionHandler : ExceptionHandler<TextCapable> {
    override fun sendException(throwable: Throwable, target: TextCapable) {
        target.sendText(generateExceptionText(throwable))
    }

    fun generateExceptionText(throwable: Throwable): Text =
        Text {
            append(throwable.toText().color(RED))
            append(Text("\n"))
            for (stackTraceElement in throwable.stackTrace) {
                append(Text("  at ").color(DARK_GRAY))
                val stackTraceElementStrings = stackTraceElement.toString().split("(")
                val first = stackTraceElementStrings[0]
                val second = stackTraceElementStrings[1]
                append(Text(first).color(RED))
                append(Text("($second")
                    .style(TextStyle {
                        color(BLUE)
                        decorate(TextDecoration.UNDERLINED)
                    })
                )
                append(Text("\n"))
            }
            throwable.cause?.let { cause ->
                append(Text("Caused by: ").color(RED))
                append(generateExceptionText(cause))
            }
        }
}

/**
 * A shortcut to send a throwable to this `TextCapable`
 */
fun TextCapable.sendException(throwable: Throwable) {
    return TextCapableExceptionHandler.sendException(throwable, this)
}