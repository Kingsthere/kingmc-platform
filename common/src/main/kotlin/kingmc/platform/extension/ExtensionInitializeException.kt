package kingmc.platform.extension


/**
 * Thrown when an unhandled exception happened while the kingmc
 * application trying to initialize extension
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class ExtensionInitializeException : RuntimeException {
    constructor(cause: String): super(cause)
    constructor(cause: String, throwable: Throwable): super(cause, throwable)
}