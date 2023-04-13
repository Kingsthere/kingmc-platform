package kingmc.platform.audience.title

import kingmc.common.text.Text
import kotlin.time.Duration

/**
 * The default implement of [Title]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Title
 */
data class TitleImpl(override val title: Text,
                     override val subtitle: Text,
                     override val times: Title.Times) : Title {

    @Throws(IllegalArgumentException::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> part(part: TitlePartType<T>): TitlePart<T> {
        if (part === TitlePartType.TITLE) {
            return TitlePart(part as TitlePartType<T>, title as T)
        } else if (part === TitlePartType.SUBTITLE) {
            return TitlePart(part as TitlePartType<T>, title as T)
        } else if (part === TitlePartType.TIMES) {
            return TitlePart(part as TitlePartType<T>, title as T)
        }

        throw IllegalArgumentException("Unsupported title part: $part")
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TitleImpl

        if (title != other.title) return false
        if (subtitle != other.subtitle) return false
        if (times != other.times) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title.hashCode()
        result = 31 * result + subtitle.hashCode()
        result = 31 * result + times.hashCode()
        return result
    }

    override fun toString(): String {
        return "TitleImpl(title=$title, subtitle=$subtitle, times=$times)"
    }

    /**
     * The default implement of [Title.Times]
     *
     * @since 0.0.3
     * @author kingsthere
     */
    data class TimesImpl(override val fadeIn: Duration,
                         override val stay: Duration,
                         override val fadeOut: Duration) : Title.Times {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as TimesImpl

            if (fadeIn != other.fadeIn) return false
            if (stay != other.stay) return false
            if (fadeOut != other.fadeOut) return false

            return true
        }

        override fun hashCode(): Int {
            var result = fadeIn.hashCode()
            result = 31 * result + stay.hashCode()
            result = 31 * result + fadeOut.hashCode()
            return result
        }

        override fun toString(): String {
            return "TimesImpl(fadeIn=$fadeIn, stay=$stay, fadeOut=$fadeOut)"
        }
    }

}