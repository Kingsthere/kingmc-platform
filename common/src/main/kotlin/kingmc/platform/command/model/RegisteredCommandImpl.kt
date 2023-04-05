package kingmc.platform.command.model

/**
 * A default implementation of [Command]
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class RegisteredCommandImpl<TData>(override val data: TData) : Command<TData>