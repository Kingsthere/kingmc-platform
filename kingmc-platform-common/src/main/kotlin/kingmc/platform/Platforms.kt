package kingmc.platform

import kingmc.common.context.Context

/**
 * The main class for `kingmc.platform` package
 *
 * @author kingsthere
 * @since 0.0.5
 */
object Platforms {
    /**
     * Registered platforms and contexts
     */
    private val registeredPlatforms: MutableMap<Platform, Context> = HashMap(4)

    /**
     * Register a platform running on current runtime
     *
     * @since 0.0.5
     */
    fun registerPlatform(platform: Platform, context: Context) {
        registeredPlatforms[platform] = context
    }
}