package kingmc.platform.audience.title

import kingmc.common.text.Text

/**
 * A type of part of a title
 *
 * @see Title
 * @param T the type of the content of the part
 * @since 0.0.3
 */
interface TitlePartType<T : Any> {
    companion object {
        /**
         * The title part of a title.
         *
         * @since 0.0.3
         */
        val TITLE: TitlePartType<Text> = object : TitlePartType<Text> {
            override fun toString(): String {
                return "TitlePart.TITLE"
            }
        }

        /**
         * The subtitle part of a title.
         *
         * @since 0.0.3
         */
        val SUBTITLE: TitlePartType<Text> = object : TitlePartType<Text> {
            override fun toString(): String {
                return "TitlePart.SUBTITLE"
            }
        }

        /**
         * The times part of a title.
         *
         * @since 0.0.3
         */
        val TIMES: TitlePartType<Title.Times> = object : TitlePartType<Title.Times> {
            override fun toString(): String {
                return "TitlePart.TIMES"
            }
        }
    }
}