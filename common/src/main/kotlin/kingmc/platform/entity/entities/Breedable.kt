package kingmc.platform.entity.entities

/**
 * Represents an entity that can age and breed.
 */
interface Breedable : Ageable {
    /**
     * Lock the age of the animal, setting this to `true` will prevent the animal from
     * maturing or getting ready for mating
     */
    var ageLock: Boolean

    /**
     * Whether this breedable entity could breed, `true` if this breedable entity is
     * ready to breed
     */
    var canBreed: Boolean
}