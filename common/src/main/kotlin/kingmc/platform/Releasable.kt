package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.util.SubclassSingleton

/**
 * A superinterface for the classes that need to release things
 * when the current context is loading this plugin disposed
 *
 *
 * Use annotation [Awake] to annotated with the classes that
 * is subclasses of this then the releasable will auto called
 * when the context disposed
 *
 * @since 0.0.3
 * @author kingsthere
 * @see Awake
 */
@SubclassSingleton
interface Releasable {
    /**
     * Release
     */
    @WithApplication
    fun release()
}