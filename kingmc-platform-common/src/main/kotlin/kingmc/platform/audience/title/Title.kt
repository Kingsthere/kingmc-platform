package kingmc.platform.audience.title

import kingmc.common.text.Text
import kingmc.platform.audience.title.Title.Times
import kingmc.util.Ticks
import com.google.errorprone.annotations.Immutable
import kotlin.time.Duration

/**
 * Represents an in-game title, which can be displayed across the
 * centre of the screen in minecraft client, a title could split
 * onto [TitlePartType]
 *
 * @see Times
 * @see TitlePartType
 * @since 0.0.3
 */
@Immutable
interface Title {
    /**
     * The title
     *
     * @since 0.0.3
     */
    val title: Text

    /**
     * The subtitle
     *
     * @since 0.0.3
     */
    val subtitle: Text

    /**
     * The display times
     *
     * @since 0.0.3
     */
    val times: Times

    /**
     * Split this title to the value that the part specified.
     *
     * @param part the part
     * @param T the type of the part
     * @return the value
     * @since 0.0.3
     */
    fun <T : Any> part(part: TitlePartType<T>): TitlePart<T>

    /**
     * Represent the times to display the
     * title to players, properties like:
     *  + fade in
     *  + fade off
     *  + stay
     *
     * @since 0.0.3
     * @author kingsthere
     * @see Title
     */
    interface Times {
        /**
         * Gets the time the title will fade-in.
         *
         * @since 0.0.3
         */
        val fadeIn: Duration

        /**
         * Gets the time the title will stay.
         *
         * @since 0.0.3
         */
        val stay: Duration

        /**
         * Gets the time the title will fade-out.
         *
         * @since 0.0.3
         */
        val fadeOut: Duration

        companion object {
            /**
             * To create a [Times] fastly
             *
             * @since 0.0.3
             */
            fun of(fadeIn: Duration, stay: Duration, fadeOut: Duration) : Times {
                return TitleImpl.TimesImpl(fadeIn, stay, fadeOut)
            }
            /**
             * To create a [Times] fastly using unit
             * [game tick][Ticks]
             *
             * @since 0.0.3
             */
            fun of(fadeIn: Long, stay: Long, fadeOut: Long) : Times {
                return TitleImpl.TimesImpl(Ticks.duration(fadeIn), Ticks.duration(stay), Ticks.duration(fadeOut))
            }
        }
    }
}