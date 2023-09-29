package kingmc.platform.extension


/**
 * Thrown when an unhandled exception happened while the kingmc
 * application trying to initialize extension
 *
 * @author kingsthere
 * @since 0.0.3
 */
open class ExtensionInitializeException : RuntimeException {
    constructor(cause: String): super(cause)
    constructor(cause: String, throwable: Throwable): super(cause, throwable)
}