package kingmc.platform

import kingmc.common.context.Context

/**
 * The main class for `kingmc.platform` package
 *
 * @since 0.0.5
 * @author kingsthere
 */
object Platforms {
    /**
     * Registered platforms and contexts
     */
    val registeredPlatforms: MutableMap<Platform, Context> = HashMap(4)

    /**
     * Register a platform running on current runtime
     *
     * @since 0.0.5
     */
    fun registerPlatform(platform: Platform, context: Context) {
        registeredPlatforms.put(platform, context)
    }
}