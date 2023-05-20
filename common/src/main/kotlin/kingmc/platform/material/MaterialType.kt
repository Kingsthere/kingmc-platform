package kingmc.platform.material

import kingmc.common.text.Text
import kingmc.util.key.Key
import kingmc.util.key.Keyed
import kingmc.util.text.TextDisplayable
import kotlin.reflect.KClass

/**
 * Describe a type of material in minecraft, when an implement of `MaterialType` with [TData] type
 * `Unit` the implement should be a singleton class
 *
 * @since 0.0.7
 * @author kingsthere
 * @param TData the type of data of this material type, use `Unit` if the data of this material
 *              is unconsidered
 */
interface MaterialType<TData : Any> : Keyed, TextDisplayable {
    /**
     * The name of this material corresponds to minecraft material names, combined with capital letters
     * and underlines, for example: `DIAMOND_SWORD`
     *
     * @since 0.0.1
     */
    @Deprecated("Name is no longer the identifier for MaterialType(s)")
    val name: String

    /**
     * The class of the type of data to this `MaterialType`
     */
    val type: KClass<out TData>

    /**
     * The display name of this material
     *
     * @since 0.0.7
     */
    val displayName: Text

    /**
     * The key of this material type
     *
     * @since 0.0.1
     */
    override val key: Key

    @Deprecated("Since 0.0.6, material(s) were provided by MaterialProvider")
    companion object Provider {

        /**
         * A map store the registered Materials
         *
         * @since 0.0.1
         */
        private val materials: MutableMap<Key, MaterialType<*>> = HashMap()

        /**
         * Register a material
         *
         * @throws IllegalArgumentException if the material is already registered
         * @since 0.0.1
         */
        fun register(material: MaterialType<*>) {
            if (materials.containsKey(material.key)) {
                throw IllegalArgumentException("Unable to register material ${material.name}, this material is already registered")
            }
            materials[material.key] = material
        }

        fun values(): Array<MaterialType<*>> {
            return materials.values.toTypedArray()
        }

        fun <T : Any> valueOf(value: Key): MaterialType<*> {
            return (materials[value] ?: throw IllegalArgumentException("Unable to find material $value"))
        }
    }
}

