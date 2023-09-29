package kingmc.platform.facet.text

import kingmc.common.application.withApplication
import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.Configuration
import kingmc.common.text.MiniMessage
import kingmc.platform.facet.FacetImplementation
import kingmc.platform.util.TextSolver

/**
 * Configuration to add text solvers to context
 *
 * @author kingsthere
 * @since 0.0.7
 */
@Configuration
@FacetImplementation
object TextSolverFactory {
    private val _defaultTextSolver by lazy {
        this@TextSolverFactory.withApplication {
            val miniMessage: MiniMessage = MiniMessage.builder()
                .build()
            val textResolver = MiniMessageTextSolver(miniMessage = miniMessage)
            // Returns
            textResolver
        }
    }

    /**
     * Provides a mini message implemented text solver
     */
    @Bean
    fun miniMessageTextSolver(): TextSolver {
        return _defaultTextSolver
    }
}