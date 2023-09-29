package kingmc.platform.context

import kingmc.common.context.annotation.Condition
import kingmc.common.context.annotation.ConditionContext
import kingmc.common.context.beans.LoadingBeanDefinition
import kingmc.common.context.source.ClassGraphConditionContext
import kingmc.platform.context.source.PlatformBeanSource
import kingmc.util.Version

object ConditionSinceVersion : Condition {
    override fun test(bean: LoadingBeanDefinition, context: ConditionContext): Boolean {
        val beanSource = context.getBeanSource()
        // Validate
        require(context is ClassGraphConditionContext)
        require(beanSource is PlatformBeanSource) { "ConditionSinceVersion only supports for PlatformBeanSource(s)" }

        val platformVersion = beanSource.platform.minecraftVersion
        val annotationInfo = context.annotationInfo
        val version = annotationInfo.getAttribute("version") as String

        return platformVersion >= Version(version)
    }
}