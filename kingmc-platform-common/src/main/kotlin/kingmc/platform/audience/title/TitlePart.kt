package kingmc.platform.audience.title

/**
 * A part of title with the actual value and type
 *
 * @see Title
 * @param T the type of the content of the part
 * @since 0.0.3
 */
interface TitlePart<T : Any> {
    /**
     * The type of this title
     */
    val type: TitlePartType<T>

    /**
     * The value(content) of this part of title
     */
    val value: T
}