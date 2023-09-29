package kingmc.platform.inventory

/**
 * This exception thrown when using an `Inventory` but the slot trying to
 * employ is not supported
 *
 * @author kingsthere
 * @since 0.0.6
 */
class UnsupportedInventorySlotException(message: String? = null, cause: Throwable? = null) : Exception(message, cause)