package kingmc.platform.bukkit.coroutine

import kingmc.common.coroutine.SyncMinecraftCoroutineDispatcher
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.isActive
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import kotlin.coroutines.CoroutineContext

class SyncBukkitCoroutineDispatcher(private val plugin: JavaPlugin) : SyncMinecraftCoroutineDispatcher() {
    private val _runTaskLater: (Plugin, Runnable, Long) -> BukkitTask =
                bukkitScheduler::runTaskLater
    private val _runTask: (Plugin, Runnable) -> BukkitTask =
                bukkitScheduler::runTask

    @ExperimentalCoroutinesApi
    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        val task = _runTaskLater(
                plugin,
                Runnable {
                    continuation.apply { resumeUndispatched(Unit) }
                },
                timeMillis / 50)
        continuation.invokeOnCancellation { task.cancel() }
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if (!context.isActive) {
            return
        }

        _runTask(plugin, block)
    }

}