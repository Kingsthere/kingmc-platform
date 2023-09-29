package kingmc.platform

/**
 * Interface to the border of a world
 *
 * @author kingsthere
 * @since 0.0.9
 */
interface WorldBorder {
    /**
     * Resets the border to default values
     */
    fun reset()

    /**
     * The size of this border, cannot less than 1.0 or greater than [maxSize]
     */
    var size: Double

    /**
     * Sets the size of this border in specified [timeInSeconds]
     *
     * @param newSize The new side length of the border.
     * @param timeInSeconds The time in seconds in which the border grows or shrinks from the previous size to that being set
     */
    fun setSize(newSize: Double, timeInSeconds: Long)

    /**
     * The center of this world border
     */
    var center: Location3D

    /**
     * Sets the border center to specified location
     *
     * @param x The new center x-coordinate.
     * @param z The new center z-coordinate.
     */
    fun setCenter(x: Double, z: Double)
    /**
     * The amount of blocks a player may safely be outside the border before taking damage
     */
    var damageBuffer: Double

    /**
     * The current border damage amount
     */
    var damageAmount: Double
    /**
     * Sets the warning time that causes the screen to be tinted red when a contracting border will reach the player within the specified time
     */
    var warningTime: Int

    /**
     * Sets the warning distance that causes the screen to be tinted red when the player is within the specified number of blocks from the border
     */
    var warningDistance: Int

    /**
     * Check if the specified location is inside this border
     *
     * @param location the location to check
     * @return if this location is inside the border or not
     */
    operator fun contains(location: Location): Boolean

    /**
     * Gets the maximum possible size of a WorldBorder
     */
    val maxSize: Double

    /**
     * Gets the absolute value of the maximum x/z center coordinate of a
     * WorldBorder
     */
    val maxCenterCoordinate: Double
}
