package kingmc.platform.audience.title

import kingmc.platform.audience.text.Text
import kingmc.util.Ticks

/**
 * Create an instance of [Title]
 *
 * @since 0.0.3
 */
fun Title(title: Text, subtitle: Text, times: Title.Times = DEFAULT_TIMES) : Title {
    return TitleImpl(title, subtitle, times)
}

/**
 * Create an instance of [TitlePart]
 *
 * @since 0.0.3
 */
fun <T : Any> TitlePart(type: TitlePartType<T>, value: T): TitlePart<T> {
    return TitlePartImpl(type, value)
}

/**
 * The default times to titles
 *
 * @since 0.0.3
 */
val DEFAULT_TIMES = Title.Times.of(Ticks.duration(10), Ticks.duration(70), Ticks.duration(20))