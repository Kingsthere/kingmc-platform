package kingmc.platform.audience.text

import kingmc.common.application.Application

/**
 * Get the current text resolver
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Application<*>.textSolver(): TextSolver =
    context.getBean(TextSolver::class)

/**
 * A [Text] instance with no contents
 */
val EMPTY_TEXT: Text = Text {  }