package kingmc.platform.material.block

/**
 * 'age' represents the different growth stages that a crop-like block can go
 * through
 *
 * A value of 0 indicates that the crop was freshly planted, whilst a value
 * equal to [maximumAge] indicates that the crop is ripe and ready
 * to be harvested
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface Ageable {
    /**
     * The `age` value
     */
    var age: Int

    /**
     * The maximum allowed value of the 'age' property.
     */
    val maximumAge: Int
}