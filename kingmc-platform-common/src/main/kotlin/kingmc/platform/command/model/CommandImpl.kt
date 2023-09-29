package kingmc.platform.command.model

/**
 * A default implementation of [Command]
 *
 * @author kingsthere
 * @since 0.0.3
 */
open class CommandImpl<TData>(override val data: TData) : Command<TData>