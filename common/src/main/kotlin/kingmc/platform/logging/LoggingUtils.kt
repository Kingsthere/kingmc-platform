package kingmc.platform.logging

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.logging.*
import kingmc.platform.audience.text.LiteralText
import kingmc.platform.audience.text.TextResolver
import kingmc.platform.audience.text.textResolver
import kingmc.util.KingMCDsl
import kingmc.util.format.EnableFormat

/**
 * Log a info level [LiteralText] resolved from [TextResolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun infoColored(msg: String) {
    info(currentApplication().textResolver().resolve(msg))
}

/**
 * Log a warn level [LiteralText] resolved from [TextResolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun warnColored(msg: String) {
    warn(currentApplication().textResolver().resolve(msg))
}

/**
 * Log a error level [LiteralText] resolved from [TextResolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun errorColored(msg: String) {
    error(currentApplication().textResolver().resolve(msg))
}

/**
 * Log a debug level [LiteralText] resolved from [TextResolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun debugColored(msg: String) {
    debug(currentApplication().textResolver().resolve(msg))
}

/**
 * Log a trace level [LiteralText] resolved from [TextResolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun traceColored(msg: String) {
    trace(currentApplication().textResolver().resolve(msg))
}