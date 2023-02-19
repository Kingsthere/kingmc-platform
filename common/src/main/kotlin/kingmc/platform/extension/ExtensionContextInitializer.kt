package kingmc.platform.extension

import kingmc.util.annotation.getAnnotation
import kingmc.util.annotation.hasAnnotation
import kingmc.common.context.beans.beanClass
import kingmc.platform.ConditionalOnPlatform
import kingmc.platform.context.PlatformContextInitializer

/**
 * Initializer to initialize extension context
 *
 * @since 0.0.3
 * @author kingsthere
 */
class ExtensionContextInitializer(
    context: ExtensionContext,
) : PlatformContextInitializer(context) {
    init {
        addBeanFilter { bean ->
            if (bean.beanClass.hasAnnotation<ConditionalOnPlatform>()) {
                val annotation = bean.beanClass.getAnnotation<ConditionalOnPlatform>()!!
                annotation.value.forEach {
                    if (context.application.platform.id.contains(it)) {
                        return@addBeanFilter true
                    }
                }
                return@addBeanFilter false
            }
            return@addBeanFilter true
        }
    }
}