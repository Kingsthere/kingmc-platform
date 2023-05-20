package kingmc.platform.material

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.platform.materialProvider
import kingmc.util.key.Key

/**
 * A wrapper to wrap Material
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Component
interface MaterialProvider {
    /**
     * Gets a type of `Material` by its name
     *
     * @since 0.0.1
     * @param name the name of the Material
     * @return material with name [name], or `null` if material with name [name] is not exists
     */
    @Deprecated("Name is no longer the identifier for MaterialType(s)")
    fun getTypeByName(name: String): MaterialType<*>?

    /**
     * Gets a type of `Material` by its KEY
     *
     * @since 0.0.1
     * @param key the key of the Material
     * @return material with key [key], or `null` if material with key [key] is not exists
     */
    fun getTypeByKey(key: Key): MaterialType<*>?

    /**
     * Gets all type of `Material`
     *
     * @since 0.0.6
     * @return the materials got
     */
    fun getTypes(): List<MaterialType<*>>
}

/**
 * A shortcut to get a type of `Material` with specifies data from [MaterialProvider]
 *
 * @throws IllegalArgumentException if the providing Material
 *                                  name not valid
 * @since 0.0.6
 * @author kingsthere
 * @see MaterialProvider
 * @see MaterialType
 */
@Suppress("UNCHECKED_CAST")
@Deprecated("Name is no longer the identifier for MaterialType(s)", replaceWith = ReplaceWith("MaterialType(key)"))
@WithApplication
fun <TData : Any> MaterialType(name: String): MaterialType<TData> =
    currentApplication().materialProvider.getTypeByName(name) as MaterialType<TData>

/**
 * A shortcut to get a type of `Material` with specifies data from [MaterialProvider]
 *
 * @throws IllegalArgumentException if the providing Material
 *                                  name not valid
 * @since 0.0.6
 * @author kingsthere
 * @param key the key of the material
 * @see MaterialProvider
 * @see MaterialType
 */
@Suppress("UNCHECKED_CAST")
@WithApplication
fun <TData : Any> MaterialType(key: Key): MaterialType<TData> =
    currentApplication().materialProvider.getTypeByKey(key) as MaterialType<TData>
