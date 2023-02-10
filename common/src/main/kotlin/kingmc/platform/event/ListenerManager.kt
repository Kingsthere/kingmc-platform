package kingmc.platform.event

import kingmc.common.context.annotation.Component
import kingmc.platform.PlatformExposed
import java.io.Closeable

/**
 * The manager to manage listeners
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Component
interface ListenerManager : PlatformExposed, Closeable {
    /**
     * The publisher this is managing
     *
     * @since 0.0.3
     */
    val publisher: Publisher

    /**
     * Close this listener manager's [publisher]
     */
    override fun close() {
        publisher.clear()
    }
}