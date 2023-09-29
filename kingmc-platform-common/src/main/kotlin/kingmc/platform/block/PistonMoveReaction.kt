package kingmc.platform.block

/**
 * Represents how a block or entity will react when interacting with a piston
 * when it is extending or retracting
 */
enum class PistonMoveReaction {
    /**
     * Indicates that the block can be pushed or pulled
     */
    MOVE,

    /**
     * Indicates the block is fragile and will break if pushed on
     */
    BREAK,

    /**
     * Indicates that the block will resist being pushed or pulled
     */
    BLOCK,

    /**
     * Indicates that the entity will ignore any interaction(s) with
     * pistons Blocks should use [PistonMoveReaction.BLOCK]
     */
    IGNORE,

    /**
     * Indicates that the block can only be pushed by pistons, not pulled
     */
    PUSH_ONLY
}