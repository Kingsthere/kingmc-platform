package kingmc.platform.common

import kingmc.platform.Platform
import kingmc.platform.event.ListenerManager
import kingmc.platform.event.Publisher

open class CommonListenerManager(override val publisher: Publisher, override val platform: Platform) : ListenerManager