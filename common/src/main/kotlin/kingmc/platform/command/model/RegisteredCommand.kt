package kingmc.platform.command.model

/**
 * Represent a registered command that is registered from
 * command manager
 *
 * @since 0.0.3
 * @author kingsthere
 * @param TData the type of data of this registered command
 */
interface RegisteredCommand<TData> {
    /**
     * The data of this command
     */
    val data: TData
}