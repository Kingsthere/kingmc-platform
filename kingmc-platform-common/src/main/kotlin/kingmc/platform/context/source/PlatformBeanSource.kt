package kingmc.platform.context.source

import kingmc.common.context.BeanSource
import kingmc.platform.Platform

/**
 * Extended `BeanSource` indicates a bean source depends on kingmc platform api
 *
 * @author kingsthere
 * @since 0.1.2
 */
interface PlatformBeanSource : BeanSource {
    /**
     * The platform of this bean source
     */
    val platform: Platform
}