package kingmc.platform.event

/**
 * To cancel current event
 *
 * @since 0.0.5
 * @author kingsthere
 */
fun Cancellable.cancel() {
    cancelled = true
}