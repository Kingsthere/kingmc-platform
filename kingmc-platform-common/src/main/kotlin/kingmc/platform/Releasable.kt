package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.util.SubclassSingleton

/**
 * A superinterface for the beans to declare a releasable bean
 *
 * Releasable bean provide a interface to release stuff conveniently by implementing
 * [release], this operation should be invoked when the application of the bean disposes
 *
 * The application is automatically switch to [kingmc.common.application.application]
 * when the [release] function invoked
 *
 * @author kingsthere
 * @since 0.1.3
 */
interface Releasable {
    /**
     * Release
     */
    @WithApplication
    fun release()
}