package kingmc.platform.entity

import kingmc.platform.material.DyeColor

/**
 * Represents a cat
 */
interface Cat : Tameable, Sittable {
    /**
     * The current type of this cat
     */
    var catType: Type

    /**
     * The collar color of this cat
     */
    var collarColor: DyeColor

    /**
     * Represents the various different cat types there are
     */
    enum class Type {
        TABBY,
        BLACK,
        RED,
        SIAMESE,
        BRITISH_SHORTHAIR,
        CALICO,
        PERSIAN,
        RAGDOLL,
        WHITE,
        JELLIE,
        ALL_BLACK
    }

}