package kingmc.platform.context

import kingmc.common.context.annotation.Condition
import kingmc.common.context.annotation.ConditionContext
import kingmc.common.context.beans.LoadingBeanDefinition
import kingmc.common.context.source.ClassGraphConditionContext
import kingmc.platform.context.source.PlatformBeanSource

object ConditionOnPlatform : Condition {
    /**
     * Test if the given bean is loaded on the specified platform
     */
    override fun test(bean: LoadingBeanDefinition, context: ConditionContext): Boolean {
        val beanSource = context.getBeanSource()
        // Validate
        require(context is ClassGraphConditionContext)
        require(beanSource is PlatformBeanSource) { "ConditionOnPlatform only supports for PlatformBeanSource(s)" }

        val annotationInfo = context.annotationInfo
        val currentPlatform = beanSource.platform

        val platformPresent = annotationInfo.getAttribute("platform") as String
        return currentPlatform.id.contains(platformPresent)
    }
}