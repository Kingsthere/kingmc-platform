package kingmc.platform.bukkit.coroutine

import kingmc.common.coroutine.AsyncMinecraftCoroutineDispatcher
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.isActive
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitTask
import kotlin.coroutines.CoroutineContext

class AsyncBukkitCoroutineDispatcher(private val plugin: JavaPlugin) : AsyncMinecraftCoroutineDispatcher() {
    private val _runTaskLater: (Plugin, Runnable, Long) -> BukkitTask =
                bukkitScheduler::runTaskLaterAsynchronously
    private val _runTask: (Plugin, Runnable) -> BukkitTask =
                bukkitScheduler::runTaskAsynchronously

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
