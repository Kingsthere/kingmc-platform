package kingmc.platform.logging

import kingmc.common.application.WithApplication
import kingmc.common.logging.*
import kingmc.platform.audience.text.TextSolver
import kingmc.platform.util.solveText
import kingmc.util.KingMCDsl
import kingmc.util.format.EnableFormat

/**
 * Log a info level [text] resolved from [TextSolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun infoColored(msg: String) {
    info(solveText(msg))
}

/**
 * Log a warn level [text] resolved from [TextSolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun warnColored(msg: String) {
    warn(solveText(msg))
}

/**
 * Log a error level [text] resolved from [TextSolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun errorColored(msg: String) {
    error(solveText(msg))
}

/**
 * Log a debug level [text] resolved from [TextSolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun debugColored(msg: String) {
    debug(solveText(msg))
}

/**
 * Log a trace level [text] resolved from [TextSolver]
 *
 * @since 0.0.4
 * @author kingsthere
 */
@KingMCDsl
@EnableFormat
@WithApplication
fun traceColored(msg: String) {
    trace(solveText(msg))
}