package kingmc.platform

/**
 * A superinterface indicating the subclasses holds
 * a [MaterialType]
 *
 * @see MaterialType
 * @since 0.0.4
 * @author kingsthere
 */
interface MaterialHolder {
    /**
     * The material of this holder
     */
    val material: Material<*>
}