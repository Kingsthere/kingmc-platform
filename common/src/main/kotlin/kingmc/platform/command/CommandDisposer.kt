package kingmc.platform.command

import kingmc.common.application.currentApplication
import kingmc.common.context.annotation.Component
import kingmc.common.context.annotation.Scope
import kingmc.common.context.beans.BeanScope
import kingmc.platform.Releasable
import kingmc.platform.commandFactory

/**
 * This `CommandDisposer` is responsible for dispose commands when context [release]
 *
 * @since 0.0.5
 * @author kingsthere
 */
@Component
@Scope(BeanScope.SINGLETON)
class CommandDisposer : Releasable {
    /**
     * Release
     */
    override fun release() {
        currentApplication().commandFactory.close()
    }
}