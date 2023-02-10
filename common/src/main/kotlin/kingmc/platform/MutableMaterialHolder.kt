package kingmc.platform

/**
 * A superinterface indicating the subclasses have
 * a mutable [Material]
 *
 * @see Material
 * @since 0.0.4
 * @author kingsthere
 */
interface MutableMaterialHolder : MaterialHolder {
    /**
     * The material of this holder
     */
    override var material: Material
}