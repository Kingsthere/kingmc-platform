package kingmc.platform.material

/**
 * A package class for a [MaterialType] and it's [data]
 *
 * @param TData the type of the data of the material
 * @param type the material
 * @param data the data of the material
 * @since 0.0.6
 * @author kingsthere
 */
open class Material<TData : Any>(val type: MaterialType<TData>, open val data: Any) {
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