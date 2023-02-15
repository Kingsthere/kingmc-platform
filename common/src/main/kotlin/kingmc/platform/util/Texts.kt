package kingmc.platform.util

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.audience.kind.TextCapable
import kingmc.platform.audience.text.EMPTY_TEXT
import kingmc.platform.audience.text.Mark
import kingmc.platform.audience.text.Text
import kingmc.platform.audience.text.textSolver
import kingmc.platform.audience.title.DEFAULT_TIMES
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePart
import kingmc.platform.audience.title.TitlePartType

/**
 * A superinterface represent an object that capable to convert
 * into a [Text]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Text
 */
interface TextDisplayable {
    /**
     * Convert this object into a [Text]
     */
    fun asText(): Text
}

/**
 * Convert this object into a [Title]
 *
 * @param times the times of this title
 */
fun TextDisplayable.asTitle(times: Title.Times = DEFAULT_TIMES): Title {
    return Title(asText(), EMPTY_TEXT)
}

/**
 * Convert this object into a [Title], display the text
 * as a `subtitle`
 *
 * @param times the times of this title
 */
fun TextDisplayable.asSubTitle(times: Title.Times = DEFAULT_TIMES): Title {
    return Title(EMPTY_TEXT, asText())
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
    return currentApplication().textSolver().solve(string)
}
/**
 * Send a text from [TextDisplayable.asText] to this audience
 *
 * @since 0.0.2
 * @author kingsthere
 */
fun TextCapable.text(obj: TextDisplayable) {
    this.text(obj.asText())
}

/**
 * Send a text from [TextDisplayable.asText] to this audience with tags
 *
 * @since 0.0.2
 * @author kingsthere
 */
fun TextCapable.text(obj: TextDisplayable, vararg marks: Mark) {
    this.text(obj.asText(), *marks)
}
