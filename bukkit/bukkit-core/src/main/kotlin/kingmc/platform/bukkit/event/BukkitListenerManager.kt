package kingmc.platform.bukkit.event

import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Platform
import kingmc.platform.PlatformImplementation
import kingmc.platform.bukkit.bukkitPlatform
import kingmc.platform.common.AbstractListenerManager
import kingmc.platform.event.ListenerManager

/**
 * An implement of  [ListenerManager] for bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@PlatformImplementation
class BukkitListenerManager : AbstractListenerManager() {
    /**
     * Gets the platform of this
     */
    override val platform: Platform
        get() = bukkitPlatform
}