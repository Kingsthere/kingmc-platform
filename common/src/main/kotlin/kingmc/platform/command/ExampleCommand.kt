package kingmc.platform.command

object ExampleCommand {
    fun initializeCommand() {
        registerCommand(Header("example", "kingmc") {
            handler("reload") {
                val scope = this.string("scope", description = "Scope for things to reload")
                executes { _, _ ->
                    success()
                }
            }
            description = ""
        })
    }
}