package kingmc.platform.audience.text

/**
 * A superinterface represents an object that could
 * convert as a [HoverEvent] to apply to a text
 *
 * @since 0.0.3
 * @author kingsthere
 * @see HoverEvent
 * @see text
 */
interface HoverEventDisplayable {
    /**
     * Convert this object as a [HoverEvent]
     */
    fun asHoverEvent(): HoverEvent<*>
}