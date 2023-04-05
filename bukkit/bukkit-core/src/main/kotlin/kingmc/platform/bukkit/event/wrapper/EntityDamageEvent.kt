package kingmc.platform.bukkit.event.wrapper

import kingmc.platform.bukkit.event.BukkitEntityDamageEvent
import kingmc.platform.bukkit.event.BukkitEventWrapper
import kingmc.platform.event.entity.EntityDamageEvent
import kotlin.reflect.KClass

class EntityDamageEvent : BukkitEventWrapper<BukkitEntityDamageEvent, EntityDamageEvent> {
    override val type: KClass<BukkitEntityDamageEvent>
        get() = BukkitEntityDamageEvent::class

    override fun invoke(p1: BukkitEntityDamageEvent): EntityDamageEvent {
        TODO("Not yet implemented")
    }
}