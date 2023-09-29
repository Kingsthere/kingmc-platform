package kingmc.platform.velocity.impl.command

import com.mojang.brigadier.builder.LiteralArgumentBuilder
import kingmc.platform.command.model.Handler

open class HandlerArgumentBuilder<S>(
    val handler: Handler
) : LiteralArgumentBuilder<S>(handler.name) {
    val description: String?
        get() = handler.description
    val aliases: Set<String>
        get() = handler.aliases

}