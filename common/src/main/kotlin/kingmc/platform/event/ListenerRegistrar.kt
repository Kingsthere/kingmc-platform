package kingmc.platform.event

import com.ktil.annotation.getAnnotation
import com.ktil.annotation.hasAnnotation
import kingmc.common.application.application
import kingmc.common.context.Context
import kingmc.common.context.annotation.Component
import kingmc.common.context.process.BeanProcessor
import kingmc.platform.publisher

/**
 * Process the listeners that annotated
 * with [Listener] to the specified publishers
 *
 * @since 0.0.1
 * @author kingsthere
 */
@Component("listenerRegistrar")
object ListenerRegistrar : BeanProcessor {

    override fun dispose(context: Context, bean: Any) {
        val beanClass = bean::class
        if (beanClass.hasAnnotation<Listener>()) {
            val annotation = beanClass.getAnnotation<Listener>()!!
            try {
                if (annotation.publisher != Publisher::class) {
                    // Use specified publisher
                    val publisher = context.getBean(annotation.publisher)
                    publisher.cancel(bean)
                } else {
                    // Use default publisher defined by "defaultEventPublisher"
                    bean.application.publisher.cancel(bean)
                }
            } catch (e: Exception) {
                throw ListenerInitializeException("Unable to register listener ${beanClass.qualifiedName}", e)
            }
        }
    }

    override fun process(context: Context, bean: Any): Boolean {
        val beanClass = bean::class
        if (beanClass.hasAnnotation<Listener>()) {
            val annotation = beanClass.getAnnotation<Listener>()!!
            try {
                if (annotation.publisher != Publisher::class) {
                    // Use specified publisher
                    val publisher = context.getBean(annotation.publisher)
                    publisher.register(bean)
                } else {
                    // Use default publisher defined by "defaultEventPublisher"
                    bean.application.publisher.register(bean)
                }
            } catch (e: Exception) {
                throw ListenerInitializeException("Unable to register listener ${beanClass.qualifiedName}", e)
            }
            return true
        }
        return false
    }

    override val priority: Byte = 1
    override val lifecycle: Int = 3
}