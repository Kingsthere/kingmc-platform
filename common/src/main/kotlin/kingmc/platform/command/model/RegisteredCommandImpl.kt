package kingmc.platform.command.model

/**
 * A default implementation of [RegisteredCommand]
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class RegisteredCommandImpl<TData>(override val data: TData) : RegisteredCommand<TData>