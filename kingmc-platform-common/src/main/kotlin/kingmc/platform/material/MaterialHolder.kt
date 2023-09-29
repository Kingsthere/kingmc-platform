package kingmc.platform.material

/**
 * A superinterface indicating the subclasses holds
 * a [MaterialType]
 *
 * @see MaterialType
 * @author kingsthere
 * @since 0.0.4
 */
interface MaterialHolder {
    /**
     * The material of this holder
     */
    val material: Material<*>

    /**
     * Shortcut to the material type to the material this holder holds
     */
    val materialType: MaterialType<*>
        get() = material.type
}