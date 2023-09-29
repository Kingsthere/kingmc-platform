package kingmc.platform.audience.kind

/**
 * An annotation to indicate a kind of audiences, such as
 *  + [TextCapable]
 *  + [SoundCapable]
 *  + [TitleCapable]
 *  + [ActionBarCapable]
 *  + [MinecraftProfileCapable]
 *  + [PlayerListCapable]
 *
 * @author kingsthere
 * @since 0.0.3
 */
@Retention
@Target(AnnotationTarget.CLASS)
annotation class AudienceKind