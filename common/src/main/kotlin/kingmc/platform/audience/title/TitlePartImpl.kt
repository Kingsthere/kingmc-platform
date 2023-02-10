package kingmc.platform.audience.title

/**
 * The default implement of [Title]
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Title
 */
data class TitlePartImpl<T : Any>(override val type: TitlePartType<T>, override val value: T) : TitlePart<T>