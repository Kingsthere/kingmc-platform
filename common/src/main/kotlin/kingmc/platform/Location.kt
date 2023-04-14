package kingmc.platform

/**
 * A superinterface indicating a position for minecraft (x, y, z, world, direction)
 *
 *
 * Some example of location:
 *  + Location{x=0,y=0,z=0,world=world}
 *  + Location{x=10,y=10,z=0,world=world}
 *  + Location{x=20,y=0,z=20,world=nether}
 *
 * @since 0.0.1
 * @author kingsthere
 * @see getWorld
 * @see Location3D
 */
interface Location : Location3D {
    /**
     * The direction this location is on
     *
     * @since 0.0.1
     * @see Direction
     */
    val direction: Direction

    /**
     * The world this location is on
     *
     * @since 0.0.1
     * @see getWorld
     */
    val world: World?

    /**
     * Adds the location by another.
     *
     * @param location The other location
     * @return the same location
     * @throws IllegalArgumentException for differing worlds
     * @see Vector
     */
    override fun plus(location: Location3D): Location

    /**
     * Adds the location by a vector.
     *
     * @param vec Vector to use
     * @return the same location
     * @see Vector
     */
    override fun plus(vec: Vector): Location

    /**
     * Adds the location by another. Not world-aware.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    override fun plus(x: Double, y: Double, z: Double): Location

    /**
     * Subtracts the location by a vector.
     *
     * @param vec The vector to use
     * @return the same location
     * @see Vector
     */
    override fun minus(vec: Vector): Location

    /**
     * Subtracts the location by another location.
     *
     * @param location The vector to use
     * @return the same location
     * @see Location
     */
    override fun minus(location: Location3D): Location

    /**
     * Subtracts the location by another. Not world-aware and
     * orientation independent.
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param z Z coordinate
     * @return the same location
     * @see Vector
     */
    override fun minus(x: Double, y: Double, z: Double): Location

    /**
     * Performs scalar multiplication, multiplying all components with a
     * scalar. Not world-aware.
     *
     * @param m The factor
     * @return the same location
     * @see Vector
     */
    override fun times(m: Double): Location

    /**
     * Convert current location to a [Vector] by current position
     *
     * @since 0.0.1
     * @author kingsthere
     */
    fun toVector(): Vector

    operator fun component4(): Direction?
    operator fun component5(): World?
    override fun clone(): Location
}