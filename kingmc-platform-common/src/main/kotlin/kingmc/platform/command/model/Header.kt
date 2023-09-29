package kingmc.platform.command.model

/**
 * Represent a header of a command tree
 *
 * @author kingsthere
 * @since 0.0.3
 */
interface Header : Node {
    /**
     * The name of this command
     */
    override var name: String

    /**
     * The description of this command
     */
    override var description: String?

    /**
     * The namespace of this command
     */
    var namespace: String
}