package kingmc.platform.audience.bossbar

import kingmc.common.text.Text

/**
 * Create and return a boss bar easily
 *
 * @author kingsthere
 * @since 0.0.3
 * @see BossBar
 */
fun bossBarOf(
    name: Text,
    progress: Float = 0.0f,
    color: BossBar.Color = BossBar.Color.PURPLE,
    overlay: BossBar.Overlay = BossBar.Overlay.PROGRESS,
    flags: MutableList<BossBar.Flag> = ArrayList()): BossBar =
    BossBarImpl(name, progress, color, overlay, flags)

/**
 * Create and return a new boss bar builder
 * instance to instantiate [BossBar]
 *
 * @author kingsthere
 * @since 0.0.3
 * @see BossBar
 */
fun bossBarBuilder(): BossBar.Builder
    = BossBarImpl.BuilderImpl()