package kingmc.platform.audience

import kingmc.platform.command.CommandSender

/**
 * Represent a console in minecraft server
 *
 * @since 0.0.3
 * @author kingsthere
 * @see CommandSender
 * @see Audience
 */
interface Console : Audience, CommandSender
