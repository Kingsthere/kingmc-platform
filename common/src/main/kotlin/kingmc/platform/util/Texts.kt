@file:Utility

package kingmc.platform.util

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.text.Mark
import kingmc.common.text.TEXT_EMPTY
import kingmc.common.text.Text
import kingmc.platform.audience.kind.TextCapable
import kingmc.platform.audience.title.DEFAULT_TIMES
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePart
import kingmc.platform.audience.title.TitlePartType
import kingmc.util.Utility
import kingmc.util.text.TextDisplayable

/**
 * The text solver of current application
 *
 * @since 0.0.7
 * @author kingsthere
 */
@get:WithApplication
val textSolver: TextSolver
    get() = currentApplication().context.getBean(TextSolver::class)

/**
 * Convert this object into a [Title]
 *
 * @param times the times of this title
 */
fun TextDisplayable.asTitle(times: Title.Times = DEFAULT_TIMES): Title {
    return Title(asText(), TEXT_EMPTY)
}

/**
 * Convert this object into a [Title], display the text
 * as a `subtitle`
 *
 * @param times the times of this title
 */
fun TextDisplayable.asSubTitle(times: Title.Times = DEFAULT_TIMES): Title {
    return Title(TEXT_EMPTY, asText())
}

/**
 * Convert this object into a [TitlePart]
 *
 * @param times the times of this title
 */
fun TextDisplayable.asTitlePart(times: Title.Times = DEFAULT_TIMES): TitlePart<Text> {
    return TitlePart(TitlePartType.TITLE, asText())
}

/**
 * Convert this object into a [Title], display the text
 * as a `subtitle`
 *
 * @param times the times of this title
 */
fun TextDisplayable.asSubTitlePart(times: Title.Times = DEFAULT_TIMES): TitlePart<Text> {
    return TitlePart(TitlePartType.SUBTITLE, asText())
}

/**
 * Resolve a `Text` from [string] from `TextResolver`
 *
 * @since 0.0.5
 * @author kingsthere
 * @param string the string to resolve text from
 * @return the text resolved
 */
@WithApplication
fun solveText(string: String): Text {
    return textSolver.solve(string)
}
/**
 * Send a text from [TextDisplayable.asText] to this audience
 *
 * @since 0.0.2
 * @author kingsthere
 */
fun TextCapable.sendText(obj: TextDisplayable) {
    this.sendText(obj.asText())
}

/**
 * Send a text from [TextDisplayable.asText] to this audience with tags
 *
 * @since 0.0.2
 * @author kingsthere
 */
fun TextCapable.sendText(obj: TextDisplayable, vararg marks: Mark) {
    this.sendText(obj.asText(), *marks)
}
