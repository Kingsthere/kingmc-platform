package kingmc.platform.block

/**
 * Represents a block (usually a container) that may be locked. When a lock is
 * active an item with a name corresponding to the key will be required to open
 * this block
 *
 * @author kingsthere
 * @since 0.0.8
 */
interface Lockable {
    /**
     * Checks if the container has a valid (non-empty) key
     */
    val isLocked: Boolean

    /**
     * The key required to access this container. Set to null (or empty
     * string) to remove key
     */
    var lock: String
}