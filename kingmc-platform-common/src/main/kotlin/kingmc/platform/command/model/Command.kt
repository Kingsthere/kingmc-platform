package kingmc.platform.command.model

/**
 * Represent a command that is used to register to a
 * command manager
 *
 * @author kingsthere
 * @since 0.0.3
 * @param TData the type of data of this registered command
 */
interface Command<TData> {
    /**
     * The data of this command
     */
    val data: TData
}