package kingmc.platform.audience.bossbar

import kingmc.common.text.Text
import kingmc.util.builder.Buildable
import net.kyori.adventure.util.Index

/**
 * Represents an in-game bossbar which can be shown to the client
 * and sent to audiences
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface BossBar : Buildable<BossBar, BossBar.Builder> {
    /**
     * The name/title of this bossbar
     * 
     * @since 0.0.3
     * @throws IllegalArgumentException if the setting value
     *                                  is invalid
     * @see text
     */
    var name: Text

    /**
     * The progress of  this bossbar, can
     * only be **0f to 1f**
     * 
     * @since 0.0.3
     * @see Float
     * @throws IllegalArgumentException if the setting value
     *                                  is invalid
     */
    var progress: Float

    /**
     * The color of this bossbar
     *
     * @since 0.0.3
     * @see Color
     */
    var color: Color

    /**
     * The overlay of this bossbar
     *
     * @since 0.0.3
     * @see Overlay
     */
    var overlay: Overlay

    /**
     * The flags to this bossbar
     *
     * @since 0.0.3
     * @see Flag
     */
    val flags: MutableList<Flag>

    /**
     * The builder to build bossbar
     *
     * @since 0.0.3
     * @see Buildable.Builder
     */
    interface Builder : Buildable.Builder<BossBar> {
        /**
         * The name/title of this bossbar
         *
         * @since 0.0.3
         * @throws IllegalArgumentException if the setting value
         *                                  is invalid
         * @see text
         */
        var name: Text

        /**
         * The progress of  this bossbar, can
         * only be **0f to 1f**
         *
         * @since 0.0.3
         * @see Float
         * @throws IllegalArgumentException if the setting value
         *                                  is invalid
         */
        var progress: Float

        /**
         * The color of this bossbar
         *
         * @since 0.0.3
         * @see Color
         */
        var color: Color

        /**
         * The overlay of this bossbar
         *
         * @since 0.0.3
         * @see Overlay
         */
        var overlay: Overlay

        /**
         * The flags to this bossbar
         *
         * @since 0.0.3
         * @see Flag
         */
        val flags: MutableList<Flag>
    }

    /**
     * One of the colors the bar component of a [BossBar].
     * This color does *not* affect the color of the bar's name text.
     * The exact color for each named value may vary slightly based on game version.
     *
     * @since 0.0.3
     * @author kingsthere
     * @see BossBar
     */
    enum class Color(val value: String) {
        /**
         * Pink.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        PINK("pink"),

        /**
         * Blue.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        BLUE("blue"),

        /**
         * Red.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        RED("red"),

        /**
         * Green.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        GREEN("green"),

        /**
         * Yellow.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        YELLOW("yellow"),

        /**
         * Purple.
         *
         * @since 0.0.3
         */
        PURPLE("purple"),

        /**
         * White.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        WHITE("white");

        companion object {
            /**
             * The name map to store all bossbar
             * colors
             *
             * @since 0.0.3
             */
            val NAMES = Index.create(
                Color::class.java
            ) { color: Color -> color.value }
        }
    }


    /**
     * Flags to control toggleable effects of a bossbar.
     *
     * @since 0.0.3
     * @author kingsthere
     * @see BossBar
     */
    enum class Flag(val value: String) {
        /**
         * If the screen should be darkened.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        DARKEN_SCREEN("darken_screen"),

        /**
         * If boss music should be played.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        PLAY_BOSS_MUSIC("play_boss_music"),

        /**
         * If world fog should be created.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        CREATE_WORLD_FOG("create_world_fog");

        companion object {
            /**
             * The name map.
             * 
             * 
             * These names are not "official", but we want to provide them to allow serializers to be consistent.
             *
             * @since 0.0.3
             */
            val NAMES = Index.create(
                Flag::class.java
            ) { flag: Flag -> flag.value }
        }
    }


    /**
     * An overlay on the bar component of a bossbar.
     *
     * @since 0.0.3
     */
    enum class Overlay(val value: String) {
        /**
         * A progress bar.
         *
         * @since 0.0.3
         */
        PROGRESS("progress"),

        /**
         * A bar with `6` notches.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        NOTCHED_6("notched_6"),

        /**
         * A bar with `10` notches.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        NOTCHED_10("notched_10"),

        /**
         * A bar with `12` notches.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        NOTCHED_12("notched_12"),

        /**
         * A bar with `20` notches.
         *
         * @since 0.0.3
         * @sinceMinecraft 1.9
         */
        NOTCHED_20("notched_20");

        companion object {
            /**
             * The name map.
             *
             * @since 0.0.3
             */
            val NAMES = Index.create(
                Overlay::class.java
            ) { overlay: Overlay -> overlay.value }
        }
    }
    
    companion object {

        /**
         * The minimum value the progress can be
         *
         * @since 0.0.3
         */
        var MIN_PROGRESS = 0f

        /**
         * The maximum value the progress can be
         *
         * @since 0.0.3
         */
        var MAX_PROGRESS = 1f

        /**
         * The minimum value the progress can be.
         *
         * @since 0.0.3
         */
        @Deprecated("for removal since 0.0.3, use {@link #MIN_PROGRESS}")
        var MIN_PERCENT = MIN_PROGRESS

        /**
         * The maximum value the progress can be.
         *
         * @since 0.0.3
         */
        @Deprecated("for removal since 0.0.3, use {@link #MAX_PROGRESS}")
        var MAX_PERCENT = MAX_PROGRESS
    }
}