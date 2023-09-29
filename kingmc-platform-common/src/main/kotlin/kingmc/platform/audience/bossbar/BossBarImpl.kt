package kingmc.platform.audience.bossbar

import kingmc.common.text.Text

/**
 * The default implement of [BossBar]
 *
 * @author kingsthere
 * @since 0.0.3
 * @see BossBar
 */
class BossBarImpl(
    override var name: Text,
    override var progress: Float = 0.0f,
    override var color: BossBar.Color = BossBar.Color.PURPLE,
    override var overlay: BossBar.Overlay = BossBar.Overlay.PROGRESS,
    override val flags: MutableList<BossBar.Flag> = ArrayList()
) : BossBar {
    /**
     * Create a builder from this thing.
     *
     * @return a builder
     * @since 0.0.3
     */
    override fun toBuilder(): BossBar.Builder {
        return BuilderImpl().apply {
            name = this@BossBarImpl.name
            progress = this@BossBarImpl.progress
            color = this@BossBarImpl.color
            overlay = this@BossBarImpl.overlay
            flags = this@BossBarImpl.flags
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BossBarImpl

        if (name != other.name) return false
        if (progress != other.progress) return false
        if (color != other.color) return false
        if (overlay != other.overlay) return false
        if (flags != other.flags) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + progress.hashCode()
        result = 31 * result + color.hashCode()
        result = 31 * result + overlay.hashCode()
        result = 31 * result + flags.hashCode()
        return result
    }

    override fun toString(): String {
        return "BossBarImpl(name=$name, progress=$progress, color=$color, overlay=$overlay, flags=$flags)"
    }

    class BuilderImpl : BossBar.Builder {
        /**
         * The name/title of this bossbar
         *
         * @since 0.0.3
         * @throws IllegalArgumentException if the setting value
         *                                  is invalid
         * @see text
         */
        override lateinit var name: Text

        /**
         * The progress of  this bossbar, can
         * only be **0f to 1f**
         *
         * @since 0.0.3
         * @see Float
         * @throws IllegalArgumentException if the setting value
         *                                  is invalid
         */
        override var progress: Float = 0f

        /**
         * The color of this bossbar
         *
         * @since 0.0.3
         * @see BossBar.Color
         */
        override var color: BossBar.Color = BossBar.Color.PURPLE

        /**
         * The overlay of this bossbar
         *
         * @since 0.0.3
         * @see BossBar.Overlay
         */
        override var overlay: BossBar.Overlay = BossBar.Overlay.PROGRESS

        /**
         * The flags to this bossbar
         *
         * @since 0.0.3
         * @see BossBar.Flag
         */
        override var flags: MutableList<BossBar.Flag> = ArrayList()

        fun progress(progress: Float): BossBar.Builder {
            this.progress = progress
            return this
        }

        fun color(color: BossBar.Color): BossBar.Builder {
            this.color = color
            return this
        }

        fun overlay(overlay: BossBar.Overlay): BossBar.Builder {
            this.overlay = overlay
            return this
        }

        fun flag(flag: BossBar.Flag) : BossBar.Builder {
            this.flags.add(flag)
            return this
        }


        /**
         * Builds.
         *
         * @return the built thing
         * @since 0.0.3
         */
        override fun build(): BossBar = BossBarImpl(
            name = this.name,
            progress = this.progress,
            color = this.color,
            overlay = this.overlay,
            flags = this.flags
        )

    }
}