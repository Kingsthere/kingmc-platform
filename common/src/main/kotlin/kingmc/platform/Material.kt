package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.util.TextDisplayable
import kingmc.util.key.Key
import kingmc.util.key.Keyed

/**
 * Field name corresponds to minecraft material names
 *
 * @since 0.0.1
 * @author kingsthere
 */
interface Material : Keyed, TextDisplayable {
    /**
     * The raw name of this material corresponds to minecraft material names, combined with capital letters
     * and underlines, for example: `DIAMOND_SWORD`
     *
     * @since 0.0.1
     */
    val name: String

    /**
     * The key of this material
     *
     * @since 0.0.1
     */
    override val key: Key

    companion object {
        /**
         * A map store the registered Materials
         *
         * @since 0.0.1
         */
        private val materials: MutableMap<Key, Material> = HashMap()

        /**
         * Register a material
         *
         * @throws IllegalArgumentException if the material is already registered
         * @since 0.0.1
         */
        fun register(material: Material) {
            if (materials.containsKey(material.key)) {
                throw IllegalArgumentException("Unable to register material ${material.name}, this material is already registered")
            }
            materials[material.key] = material
        }

        fun values(): Array<Material> {
            return materials.values.toTypedArray()
        }

        fun <T : Any> valueOf(value: Key): Material {
            return (materials[value] ?: throw IllegalArgumentException("Unable to find material $value"))
        }
    }
}

/**
 * A wrapper to wrap Material
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Component
interface MaterialProvider {
    /**
     * Get a Material by the name
     *
     * @since 0.0.1
     * @param name the name of the Material
     */
    fun getByName(name: String): Material
}

/**
 * Get a Material<Unit> identity by the name of
 * that Material<Unit>
 *
 * @throws IllegalArgumentException if the providing Material<Unit>
 *                                  name not valid
 * @since 0.0.1
 * @author kingsthere
 */
@WithApplication
fun Material(name: String) =
    currentApplication().materials.getByName(name)