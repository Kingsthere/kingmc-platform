package kingmc.platform.audience

import kingmc.platform.command.CommandSender

/**
 * Represent a console in minecraft server
 *
 * @author kingsthere
 * @since 0.0.3
 * @see CommandSender
 * @see Audience
 */
interface Console : Audience, CommandSender
