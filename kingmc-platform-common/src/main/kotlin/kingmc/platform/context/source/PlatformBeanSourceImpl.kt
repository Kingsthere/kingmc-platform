package kingmc.platform.context.source

import io.github.classgraph.ClassGraph
import kingmc.common.context.BeanSource
import kingmc.common.context.beans.BeanDefinition
import kingmc.common.context.source.ClassGraphBeanSource
import kingmc.platform.Platform
import java.util.*

/**
 * A simple implementation of [PlatformBeanSource]
 *
 * @author kingsthere
 * @since 0.1.2
 */
open class PlatformBeanSourceImpl(
    classGraph: ClassGraph,
    classLoader: ClassLoader,
    properties: Properties,
    override val platform: Platform,
    parent: List<BeanSource> = emptyList(),
    preparedBeanDefinition: List<BeanDefinition> = emptyList(),
) : ClassGraphBeanSource(classGraph, classLoader, properties, parent, preparedBeanDefinition), PlatformBeanSource {

    override fun toString(): String {
        return "PlatformBeanSourceImpl(platform=$platform)"
    }
}