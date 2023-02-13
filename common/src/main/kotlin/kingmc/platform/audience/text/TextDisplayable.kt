package kingmc.platform.audience.text

import kingmc.platform.audience.title.DEFAULT_TIMES
import kingmc.platform.audience.title.Title
import kingmc.platform.audience.title.TitlePart
import kingmc.platform.audience.title.TitlePartType

/**
 * A superinterface represent an object that could convert
 * into a [Text] that send to audiences
 *
 * @since 0.0.3
 * @author kingsthere
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