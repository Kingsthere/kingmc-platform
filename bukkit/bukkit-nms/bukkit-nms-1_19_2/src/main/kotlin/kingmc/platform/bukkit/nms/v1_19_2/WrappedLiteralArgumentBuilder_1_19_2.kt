package kingmc.platform.bukkit.nms.v1_19_2

import com.mojang.brigadier.Command
import com.mojang.brigadier.RedirectModifier
import com.mojang.brigadier.SingleRedirectModifier
import com.mojang.brigadier.builder.ArgumentBuilder
import com.mojang.brigadier.builder.LiteralArgumentBuilder
import com.mojang.brigadier.tree.CommandNode
import com.mojang.brigadier.tree.LiteralCommandNode
import kingmc.platform.audience.CommandSender
import net.minecraft.commands.CommandSourceStack
import java.util.function.Predicate

class WrappedLiteralArgumentBuilder_1_19_2(val from: LiteralArgumentBuilder<CommandSender>) : LiteralArgumentBuilder<CommandSourceStack>(from.literal) {
    override fun getThis(): LiteralArgumentBuilder<CommandSourceStack> {
        return this
    }

    override fun then(argument: ArgumentBuilder<CommandSourceStack, *>?): LiteralArgumentBuilder<CommandSourceStack> {
        return super.then(argument)
    }

    override fun then(argument: CommandNode<CommandSourceStack>?): LiteralArgumentBuilder<CommandSourceStack> {
        return super.then(argument)
    }

    override fun getArguments(): MutableCollection<CommandNode<CommandSourceStack>> {
        return super.getArguments()
    }

    override fun executes(command: Command<CommandSourceStack>?): LiteralArgumentBuilder<CommandSourceStack> {
        return super.executes(command)
    }

    override fun getCommand(): Command<CommandSourceStack> {
        return super.getCommand()
    }

    override fun requires(requirement: Predicate<CommandSourceStack>?): LiteralArgumentBuilder<CommandSourceStack> {
        return super.requires(requirement)
    }

    override fun getRequirement(): Predicate<CommandSourceStack> {
        return super.getRequirement()
    }

    override fun redirect(target: CommandNode<CommandSourceStack>?): LiteralArgumentBuilder<CommandSourceStack> {
        return super.redirect(target)
    }

    override fun redirect(
        target: CommandNode<CommandSourceStack>?,
        modifier: SingleRedirectModifier<CommandSourceStack>?,
    ): LiteralArgumentBuilder<CommandSourceStack> {
        return super.redirect(target, modifier)
    }

    override fun fork(
        target: CommandNode<CommandSourceStack>?,
        modifier: RedirectModifier<CommandSourceStack>?,
    ): LiteralArgumentBuilder<CommandSourceStack> {
        return super.fork(target, modifier)
    }

    override fun forward(
        target: CommandNode<CommandSourceStack>?,
        modifier: RedirectModifier<CommandSourceStack>?,
        fork: Boolean,
    ): LiteralArgumentBuilder<CommandSourceStack> {
        return super.forward(target, modifier, fork)
    }

    override fun getRedirect(): CommandNode<CommandSourceStack> {
        return super.getRedirect()
    }

    override fun getRedirectModifier(): RedirectModifier<CommandSourceStack> {
        return super.getRedirectModifier()
    }

    override fun isFork(): Boolean {
        return super.isFork()
    }

    override fun build(): LiteralCommandNode<CommandSourceStack> {
        return super.build()
    }

    override fun getLiteral(): String {
        return super.getLiteral()
    }
}