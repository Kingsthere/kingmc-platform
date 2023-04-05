package kingmc.platform.bukkit.event

import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.bukkit.BukkitImplementation
import kingmc.platform.event.ListenerManager
import kingmc.platform.facet.FacetListenerManager

/**
 * An implement of  [ListenerManager] for bukkit
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
@BukkitImplementation
open class BukkitListenerManager : FacetListenerManager()