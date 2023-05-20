package kingmc.platform.bukkit.coroutine

import kingmc.common.application.WithApplication
import kingmc.common.application.application
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.common.coroutine.AsyncMinecraftCoroutineDispatcher
import kingmc.platform.bukkit.driver.bukkitPlugin
import kotlinx.coroutines.*
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitTask
import java.io.Closeable
import java.lang.Runnable
import kotlin.coroutines.CoroutineContext

/**
 * Bukkit implementation for [AsyncBukkitCoroutineDispatcher]
 */
@Component
@Scope(BeanScope.SINGLETON)
class AsyncBukkitCoroutineDispatcher : AsyncMinecraftCoroutineDispatcher(), Closeable {
    private val plugin = application { bukkitPlugin }
    private val _runTaskLater: (Plugin, Runnable, Long) -> BukkitTask =
                bukkitScheduler::runTaskLaterAsynchronously
    private val _runTask: (Plugin, Runnable) -> BukkitTask =
                bukkitScheduler::runTaskAsynchronously

    init {
        application.addShutdownHook {
            this.close()
        }
    }

    @ExperimentalCoroutinesApi
    override fun scheduleResumeAfterDelay(timeMillis: Long, continuation: CancellableContinuation<Unit>) {
        val task = _runTaskLater(
                plugin,
                Runnable {
                    this@AsyncBukkitCoroutineDispatcher.application {
                        continuation.apply { resumeUndispatched(Unit) }
                    }
                },
                timeMillis / 50)
        continuation.invokeOnCancellation {
            task.cancel()
        }
    }

    override fun dispatch(context: CoroutineContext, block: @WithApplication Runnable) {
        if (!context.isActive) {
            return
        }

        _runTask(plugin, Runnable {
            this@AsyncBukkitCoroutineDispatcher.application {
                block.run()
            }
        })
    }

    /**
     * Close and stop all tasks scheduled
     */
    override fun close() {
        if (this.isActive) {
            this.cancel(CancellationException("Application disposed"))
        }
    }

}
