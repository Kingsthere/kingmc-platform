package kingmc.platform.event

/**
 * To cancel current event
 *
 * @author kingsthere
 * @since 0.0.5
 */
fun Cancellable.cancel() {
    cancelled = true
}

/**
 * Run the given [block] if this cancellable event is cancelled
 *
 * @param block block to run if this cancellable event is cancelled
 */
inline fun <TEvent : Cancellable> TEvent.ifCancelled(block: (event: TEvent) -> Unit) {
    if (cancelled) {
        block(this)
    }
}

/**
 * Run the given [block] if this cancellable event is not cancelled
 *
 * @param block block to run if this cancellable event is not cancelled
 */
inline fun <TEvent : Cancellable> TEvent.ifNotCancelled(block: (event: TEvent) -> Unit) {
    if (!cancelled) {
        block(this)
    }
}