@file:JvmName("AudienceFactoryKt")

package kingmc.platform.audience

/**
 * The default [AudienceFactory] to use
 *
 * @since 0.0.3
 * @author kingsthere
 * @see AudienceFactory
 */
@Deprecated(
    message = "Use Platform.audienceFactory instead for using platform api in current application's platform",
    replaceWith = ReplaceWith("Platform.audienceFactory")
)
lateinit var audienceFactory: AudienceFactory
