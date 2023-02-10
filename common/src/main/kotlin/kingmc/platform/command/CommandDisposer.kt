package kingmc.platform.command

import kingmc.common.application.currentApplication
import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.aware.ContextAware
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Releasable
import kingmc.platform.commands
import kingmc.platform.platform

/**
 * This `CommandDisposer` is responsible for dispose commands when context [release]
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
class CommandDisposer : Releasable, ContextAware {
    override lateinit var context: Context

    /**
     * Release
     */
    override fun release() {
        currentApplication().platform.commands.close()
    }
}