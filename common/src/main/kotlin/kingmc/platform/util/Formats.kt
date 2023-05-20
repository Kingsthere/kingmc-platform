@file:Utility

package kingmc.platform.util

import kingmc.common.application.FormatCapableApplication
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.text.Text
import kingmc.util.KingMCDsl
import kingmc.util.Utility
import kingmc.util.format.*

/**
 * Format the receiver with [args]
 *
 * @since 0.0.6
 * @author kingsthere
 * @receiver the string to format
 */
@KingMCDsl
@WithApplication
fun String.format(vararg args: Any, formatStyle: FormatStyle = BracketStyle): String {
    val application = currentApplication()
    return if (application is FormatCapableApplication) {
        this.formatWithContext(
            formatStyle,
            application.getFormatContext()
                .with(ListFormatArguments(args.mapIndexed { index, any -> FormatArgument(any, index.toString()) }))
        )
    } else {
        this.formatWithContext(formatStyle, ListFormatArguments(args.mapIndexed { index, any -> FormatArgument(any, index.toString()) }))
    }
}

/**
 * Format the receiver with [args]
 *
 * @since 0.0.6
 * @author kingsthere
 * @receiver the string to format
 */
@KingMCDsl
@WithApplication
fun Text.format(vararg args: Any, formatStyle: FormatStyle = BracketStyle): Text {
    val application = currentApplication()
    return if (application is FormatCapableApplication) {
        this.formatWithContext(
            formatStyle,
            application.getFormatContext()
                .with(ListFormatArguments(args.mapIndexed { index, any -> FormatArgument(any, index.toString()) }))
        )
    } else {
        this.formatWithContext(formatStyle, ListFormatArguments(args.mapIndexed { index, any -> FormatArgument(any, index.toString()) }))
    }
}