package kingmc.platform.velocity.impl.command

import kingmc.platform.command.model.Header

open class HeaderArgumentBuilder<S>(
    header: Header,
) : NodeArgumentBuilder<S>(header)