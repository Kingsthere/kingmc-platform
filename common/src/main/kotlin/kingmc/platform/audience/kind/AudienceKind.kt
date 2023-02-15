package kingmc.platform.audience.kind

/**
 * An annotation to indicate a kind of audiences, such as
 *  + [TextCapable]
 *  + [SoundCapable]
 *  + [TitleCapable]
 *  + [ActionBarCapable]
 *  + [MinecraftIdentityCapable]
 *  + [PlayerListCapable]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.CLASS)
annotation class AudienceKind