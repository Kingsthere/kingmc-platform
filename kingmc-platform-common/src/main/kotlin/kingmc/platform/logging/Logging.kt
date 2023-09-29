package kingmc.platform.logging

import kingmc.common.application.WithApplication
import kingmc.common.logging.*
import kingmc.platform.util.format
import kingmc.platform.util.solveText
import kingmc.util.KingMCDsl
import kingmc.util.format.Formatted

/**
 * Log a info level [msg] 
 *
 * @author kingsthere
 * @since 0.0.4
 */
@KingMCDsl
@Formatted
@WithApplication
fun infoColored(msg: String, vararg args: Any) {
    info(solveText(msg.format(args)))
}

/**
 * Log a warn level [msg] 
 *
 * @author kingsthere
 * @since 0.0.4
 */
@KingMCDsl
@Formatted
@WithApplication
fun warnColored(msg: String, vararg args: Any) {
    warn(solveText(msg.format(args)))
}

/**
 * Log a error level [msg] 
 *
 * @author kingsthere
 * @since 0.0.4
 */
@KingMCDsl
@Formatted
@WithApplication
fun errorColored(msg: String, vararg args: Any) {
    error(solveText(msg.format(args)))
}

/**
 * Log a debug level [msg] 
 *
 * @author kingsthere
 * @since 0.0.4
 */
@KingMCDsl
@Formatted
@WithApplication
fun debugColored(msg: String, vararg args: Any) {
    debug(solveText(msg.format(args)))
}

/**
 * Log a trace level [msg] 
 *
 * @author kingsthere
 * @since 0.0.4
 */
@KingMCDsl
@Formatted
@WithApplication
fun traceColored(msg: String, vararg args: Any) {
    trace(solveText(msg.format(args)))
}