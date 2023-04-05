package kingmc.platform.nbt

/**
 * Annotation to mark a property that represents a value in a `NBTCompound`
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Retention
@Target(AnnotationTarget.PROPERTY)
annotation class NBTProperty(
    /**
     * The path of the nbt that this property representing
     */
    val path: String
)
