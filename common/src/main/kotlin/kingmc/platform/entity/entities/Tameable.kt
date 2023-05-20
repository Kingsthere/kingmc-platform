package kingmc.platform.entity.entities

import kingmc.platform.entity.AnimalTamer

/**
 * Represents a tameable animal that could tamed by a [AnimalTamer]
 */
interface Tameable : Animals {
    /**
     * `true` If something is tamed then a player can not tame it through normal
     * methods, even if it does not belong to anyone in particular
     */
    var isTamed: Boolean

    /**
     * The owner that tamed this tameable animal, or `null` if this tameable animal
     * is not tamed by any [AnimalTamer]
     */
    var owner: AnimalTamer?
}