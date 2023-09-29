package kingmc.platform.material

import kingmc.common.application.WithApplication
import kingmc.util.key.Key

/**
 * A shortcut to create an `Material` instance with no data
 */
fun Material(type: MaterialType<Unit>) = Material(type, Unit)

/**
 * A shortcut to create an `Material` instance with no data
 */
@WithApplication
fun Material(type: Key) = Material(MaterialType<Unit>(type), Unit)

/**
 * A shortcut to create an `Material` instance with no data
 */
@WithApplication
@Deprecated("Name is no longer the identifier for MaterialType(s)", replaceWith = ReplaceWith("Material(key, data)"))
fun Material(type: String) = Material(MaterialType<Unit>(type), Unit)

/**
 * A package class for a [MaterialType] and it's [data]
 *
 * @param TData the type of the data of the material
 * @param type the material
 * @param data the data of the material
 * @author kingsthere
 * @since 0.0.6
 */
class Material<TData : Any>(val type: MaterialType<TData>, val data: Any) {
    @WithApplication
    constructor(type: Key, data: Any) : this(MaterialType(type), data)

    @WithApplication
    @Deprecated("Name is no longer the identifier for MaterialType(s)", replaceWith = ReplaceWith("Material(key, data)"))
    constructor(type: String, data: Any) : this(MaterialType(type), data)

    operator fun component1(): MaterialType<TData> = type
    operator fun component2(): Any = data
    fun copy(type: MaterialType<TData> = this.type, data: Any = this.data) = Material(type, data)
    
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Material<*>) return false

        if (type != other.type) return false
        if (data != other.data) return false

        return true
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + data.hashCode()
        return result
    }

    override fun toString(): String {
        return "Material(type=$type, data=$data)"
    }
}