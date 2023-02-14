package kingmc.platform.audience.text

import kingmc.common.application.Application

/**
 * Get the current text resolver
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Application<*>.textResolver(): TextResolver =
    context.getBean(TextResolver::class)

/**
 * A [Text] instance with no contents
 */
val EMPTY_TEXT: Text = Text {  }