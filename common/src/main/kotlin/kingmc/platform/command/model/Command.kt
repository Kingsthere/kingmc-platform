package kingmc.platform.command.model

/**
 * Represent a command that is used to register to a
 * command manager
 *
 * @since 0.0.3
 * @author kingsthere
 * @param TData the type of data of this registered command
 */
interface Command<TData> {
    /**
     * The data of this command
     */
    val data: TData
}