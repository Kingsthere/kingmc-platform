package kingmc.platform

/**
 * An object declared few priorities for [AwakeProcessor] to sort [Awake] functions
 *
 * @since 0.0.8
 * @author kingsthere
 */
object Priorities {
    const val FIRST: Byte = 2
    const val EARLY: Byte = 1
    const val NORMAL: Byte = 0
    const val LATE: Byte = -1
    const val LAST: Byte = -2
}