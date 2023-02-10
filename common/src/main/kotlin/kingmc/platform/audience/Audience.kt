package kingmc.platform.audience

import kingmc.platform.audience.capable.*
import kingmc.platform.audience.text.TextDisplayable
import kingmc.util.InternalAPI
import java.io.Closeable

/**
 * A receiver of Minecraft media
 *
 *
 * Audience is designed to be a universal interface for any player, command sender, console,
 * or otherwise who can receive text, titles, boss bars, and other Minecraft media.
 * It is also designed for a **group** of receivers such as a team, server, world, or permission.
 *
 *
 * @see Closeable
 * @since 0.0.3
 * @author kingsthere
 */
interface Audience : Closeable, TextCapable, MinecraftIdentityCapable, PlayerListCapable, BossBarCapable, TitleCapable,
    ActionBarCapable, TextDisplayable, SoundCapable {

    @InternalAPI
    override fun close()
}