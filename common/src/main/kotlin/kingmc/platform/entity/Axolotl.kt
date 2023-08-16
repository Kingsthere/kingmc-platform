package kingmc.platform.entity

/**
 * Represents an Axolotl
 */
interface Axolotl : Animals {
    /**
     * Gets if this axolotl is playing dead
     */
    var isPlayingDead: Boolean

    /**
     * The variant of this axolotl
     */
    var variant: Variant

    /**
     * Represents the variant of a axolotl - ie its color
     */
    enum class Variant {
        /**
         * Leucistic (pink) axolotl
         */
        LUCY,

        /**
         * Brown axolotl
         */
        WILD,

        /**
         * Gold axolotl
         */
        GOLD,

        /**
         * Cyan axolotl
         */
        CYAN,

        /**
         * Blue axolotl
         */
        BLUE
    }
}
