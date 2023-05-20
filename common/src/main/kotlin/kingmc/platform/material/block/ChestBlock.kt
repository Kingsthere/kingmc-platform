package kingmc.platform.material.block

/**
 * 'type' represents which part of a double chest this block is, or if it is a
 * single chest
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface ChestBlock : Directional, Waterlogged {
    /**
     * Tne type of this chest
     */
    var type: Type

    /**
     * Type of this chest block
     */
    enum class Type {
        /**
         * The chest is not linked to any others and contains only one 27 slot
         * inventory
         */
        SINGLE,

        /**
         * The chest is the left hand side of a double chest and shares a 54
         * block inventory with the chest to its right
         */
        LEFT,

        /**
         * The chest is the right hand side of a double chest and shares a 54
         * block inventory with the chest to its left
         */
        RIGHT
    }
}