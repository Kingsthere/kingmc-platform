@file:Utility

package kingmc.platform.util

import kingmc.common.application.FormatCapableApplication
import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.util.KingMCDsl
import kingmc.util.Utility
import kingmc.util.format.*

/**
 * Format the receiver with [args]
 *
 * @author kingsthere
 * @since 0.0.6
 * @receiver the string to format
 */
@KingMCDsl
@WithApplication
fun String.format(vararg args: Any, formatter: Formatter = BracketStyle): String {
    val application = currentApplication()
    return if (application is FormatCapableApplication) {
        this.formatWithContext(
            formatter,
            application.getFormatContext()
                .with(FormatContext(args.mapIndexed { index, any -> FormatArgument(any, index.toString()) }))
        )
    } else {
        this.formatWithContext(formatter, FormatContext(args.mapIndexed { index, any -> FormatArgument(any, index.toString()) }))
    }
}