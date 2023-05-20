package kingmc.platform.facet.text

import kingmc.common.application.application
import kingmc.common.context.annotation.Bean
import kingmc.common.context.annotation.Configuration
import kingmc.common.text.MiniMessage
import kingmc.platform.facet.FacetImplementation
import kingmc.platform.util.TextSolver

/**
 * Configuration to add facet text solvers to context
 *
 * @since 0.0.7
 * @author kingsthere
 */
@Configuration
@FacetImplementation
object FacetTextSolvers {
    private val _defaultTextSolver by lazy {
        this@FacetTextSolvers.application {
            val miniMessage: MiniMessage = MiniMessage.builder()
                .build()
            val textResolver = MiniMessageTextSolver(miniMessage = miniMessage)
            // Returns
            textResolver
        }
    }

    /**
     * The default text resolver to use (miniMessage is enabled by default)
     */
    @Bean
    fun defaultTextSolver(): TextSolver {
        return _defaultTextSolver
    }
}