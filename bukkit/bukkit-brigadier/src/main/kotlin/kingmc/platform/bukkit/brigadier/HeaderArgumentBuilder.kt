package kingmc.platform.bukkit.brigadier

import kingmc.platform.command.model.Header

open class  HeaderArgumentBuilder<S>(
    header: Header,
) : NodeArgumentBuilder<S>(header)