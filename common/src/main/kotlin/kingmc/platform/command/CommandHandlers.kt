package kingmc.platform.command

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.command.model.BlockingHandler
import kingmc.platform.command.model.CommandExecutor
import kingmc.platform.command.model.Handler
import kingmc.platform.command.model.Node
import kingmc.platform.command.parameter.Parameters

/**
 * A shortcut to set the name of this handler
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun <THandler : Handler> THandler.name(name: String) =
    this.apply {
        this.name = name
    }

/**
 * A shortcut to set the description of this handler
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun <THandler : Handler> THandler.description(description: String?) =
    this.apply {
        this.description = description
    }

var Node.rootHandler: Handler?
    get() = handlers.find { it.name == ".root" }
    set(value) {
        handlers.find { it.name == ".root" }?.let {
            handlers.remove(it)
        }
        if (value != null) {
            handlers.add(value)
        }
    }

/**
 * Configure & create the root handler of current command
 * node
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun Node.root(configurer: @WithApplication Handler.() -> Unit): Handler {
    val handler: Handler =
        rootHandler ?: run {
            rootHandler = BlockingHandler(".root", application = currentApplication())
            rootHandler!!
        }
    configurer.invoke(handler)
    return handler
}

/**
 * Register and configure a command handler
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun Node.handler(name: String, configurer: @WithApplication Handler.() -> Unit = {  }): Handler {
    val handler: Handler = BlockingHandler(name, application = currentApplication())
    configurer.invoke(handler)
    this.handlers.add(handler)
    return handler
}

/**
 * Set the executor of current handler
 */
@WithApplication
fun <THandler : Handler> THandler.executes(executor: @WithApplication CommandExecutor) =
    this.apply {
        this.executor = executor
    }

/**
 * Set the executor of current handler
 */
@WithApplication
fun <THandler : Handler> THandler.executes(executor: @WithApplication (CommandSender, Parameters) -> CommandResult) =
    this.apply {
        this.executor = CommandExecutor { context ->
            executor(context.invoker, context.parameters)
        }
    }

/**
 * Get parameters of this handler that is required to execute this command
 */
val Handler.requiredParameters: Int
    get() = this.parameters.count { !it.nullable || it.default != null }