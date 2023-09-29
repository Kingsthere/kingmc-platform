package kingmc.platform.audience.title

/**
 * The default implement of [Title]
 *
 * @author kingsthere
 * @since 0.0.3
 * @see Title
 */
data class TitlePartImpl<T : Any>(override val type: TitlePartType<T>, override val value: T) : TitlePart<T>