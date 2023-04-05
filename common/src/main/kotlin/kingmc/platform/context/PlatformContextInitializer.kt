package kingmc.platform.context

import kingmc.common.context.beans.beanClass
import kingmc.common.context.initializer.GenericContextInitializer
import kingmc.platform.ConditionalOnPlatform
import kingmc.platform.version.ConditionalBeforeVersion
import kingmc.platform.version.ConditionalOnVersion
import kingmc.platform.version.ConditionalSinceVersion
import kingmc.util.Version
import kingmc.util.annotation.getAnnotation
import kingmc.util.annotation.hasAnnotation
import java.util.function.Predicate

/**
 * Initializer to initialize platform context
 *
 * @since 0.0.3
 * @author kingsthere
 */
open class PlatformContextInitializer(context: PlatformContext) :
    GenericContextInitializer(context) {
        init {
            addBeanFilter(Predicate { bean ->
                if (bean.beanClass.hasAnnotation<ConditionalOnPlatform>()) {
                    val annotation = bean.beanClass.getAnnotation<ConditionalOnPlatform>()!!
                    var contained = false
                    annotation.value.forEach {
                        if (context.application.platform.id.contains(it)) {
                            contained = true
                        }
                    }
                    if (!contained) {
                        return@Predicate false
                    }
                }
                if (bean.beanClass.hasAnnotation<ConditionalOnVersion>()) {
                    val annotation = bean.beanClass.getAnnotation<ConditionalOnVersion>()!!
                    val version = Version(context.application.platform.minecraftVersion)
                    if (version != Version(annotation.value)) {
                        return@Predicate false
                    }
                }
                if (bean.beanClass.hasAnnotation<ConditionalSinceVersion>()) {
                    val annotation = bean.beanClass.getAnnotation<ConditionalSinceVersion>()!!
                    val version = Version(context.application.platform.minecraftVersion)
                    if (version < Version(annotation.value)) {
                        return@Predicate false
                    }
                }
                if (bean.beanClass.hasAnnotation<ConditionalBeforeVersion>()) {
                    val annotation = bean.beanClass.getAnnotation<ConditionalBeforeVersion>()!!
                    val version = Version(context.application.platform.minecraftVersion)
                    if (version >= Version(annotation.value)) {
                        return@Predicate false
                    }
                }
                return@Predicate true
            })
            addElementFilter(Predicate { element ->
                if (element.hasAnnotation<ConditionalOnPlatform>()) {
                    val annotation = element.getAnnotation<ConditionalOnPlatform>()!!
                    var contained = false
                    annotation.value.forEach {
                        if (context.application.platform.id.contains(it)) {
                            contained = true
                        }
                    }
                    if (!contained) {
                        return@Predicate false
                    }
                }
                if (element.hasAnnotation<ConditionalOnVersion>()) {
                    val annotation = element.getAnnotation<ConditionalOnVersion>()!!
                    val version = Version(context.application.platform.minecraftVersion)
                    if (version != Version(annotation.value)) {
                        return@Predicate false
                    }
                }
                if (element.hasAnnotation<ConditionalSinceVersion>()) {
                    val annotation = element.getAnnotation<ConditionalSinceVersion>()!!
                    val version = Version(context.application.platform.minecraftVersion)
                    if (version < Version(annotation.value)) {
                        return@Predicate false
                    }
                }
                if (element.hasAnnotation<ConditionalBeforeVersion>()) {
                    val annotation = element.getAnnotation<ConditionalBeforeVersion>()!!
                    val version = Version(context.application.platform.minecraftVersion)
                    if (version >= Version(annotation.value)) {
                        return@Predicate false
                    }
                }
                return@Predicate true
            })
        }
}