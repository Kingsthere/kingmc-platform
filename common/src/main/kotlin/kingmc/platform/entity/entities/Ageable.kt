package kingmc.platform.entity.entities

/**
 * Represents an entity that can age
 */
interface Ageable : Creature {
    /**
     * The age of this mob
     */
    var age: Int

    /**
     * Whether this ageable entity is a baby or an adult
     *
     * @return return true if the mob is an adult
     */
    var isAdult: Boolean
}