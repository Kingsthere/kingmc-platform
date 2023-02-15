/**
 * Utility things for minecraft uses in kingmc
 * audience api about **chat**
 *
 * @since 0.0.3
 * @author kingsthere
 */

package kingmc.platform.audience.text

import kingmc.platform.audience.text.tag.TagResolver
import net.kyori.adventure.nbt.api.BinaryTagHolder
import net.kyori.adventure.text.*
import net.kyori.adventure.text.event.ClickEvent
import net.kyori.adventure.text.event.HoverEvent
import net.kyori.adventure.text.event.HoverEventSource
import net.kyori.adventure.text.minimessage.MiniMessage

/*
Alias types from kyori adventure api
to kingmc to avoid classnames collision
 */
typealias Text = Component
typealias LiteralText = TextComponent
typealias TextApplicable = ComponentApplicable
typealias TextBuilderApplicable = ComponentBuilderApplicable
typealias BuildableText<C, B> = BuildableComponent<C, B>
typealias TextBuilder<C, B> = ComponentBuilder<C, B>
typealias StringText = TextComponent

typealias TranslatableText = TranslatableComponent
typealias SelectorText = SelectorComponent
typealias ScoreText = ScoreComponent
typealias ScopedText<C> = ScopedComponent<C>
typealias EntityNBTText = EntityNBTComponent
typealias BlockNBTText = BlockNBTComponent
typealias StorageNBTText = StorageNBTComponent
typealias KeybindText = KeybindComponent

typealias LiteralTextBuilder = TextComponent.Builder
typealias TranslatableTextBuilder = TranslatableComponent.Builder
typealias SelectorTextBuilder = SelectorComponent.Builder
typealias ScoreTextBuilder = ScoreComponent.Builder
typealias EntityNBTTextBuilder = EntityNBTComponent.Builder
typealias BlockNBTTextBuilder = BlockNBTComponent.Builder
typealias StorageNBTTextBuilder = StorageNBTComponent.Builder
typealias KeybindTextBuilder = KeybindComponent.Builder

typealias ClickEvent = ClickEvent
typealias ClickEventAction = ClickEvent.Action
typealias HoverEvent<V> = HoverEvent<V>
typealias HoverEventAction<V> = HoverEvent.Action<V>
typealias HoverEventShowEntity = HoverEvent.ShowEntity
typealias HoverEventShowItem = HoverEvent.ShowItem
typealias HoverEventSource<V> = HoverEventSource<V>

typealias BinaryTagHolder = BinaryTagHolder

/*
Aliases from minimessage
to kingmc
 */
typealias MiniMessage = MiniMessage
typealias StyleTag = Mark

class MiniMessageTextSolver(val miniMessage: MiniMessage, vararg val tags: TagResolver): TextSolver {
    override fun solve(string: String): Text {
        return miniMessage.deserialize(string, *tags)
    }

    override fun encode(text: Text): String {
        return miniMessage.serialize(text)
    }

    class MiniMessageBuilder: TextSolver.Builder {
        lateinit var miniMessage: MiniMessage
        val tags: MutableList<TagResolver> = mutableListOf()

        /**
         * Set the `MiniMessage` to use
         *
         * @since 0.0.3
         * @see MiniMessageBuilder
         */
        fun miniMessage(miniMessage: MiniMessage): MiniMessageBuilder {
            this.miniMessage = miniMessage
            return this
        }

        /**
         * Add a tag resolver to
         *
         * @since 0.0.3
         * @see MiniMessageBuilder
         */
        fun tags(tagResolver: TagResolver): MiniMessageBuilder {
            this.tags.add(tagResolver)
            return this
        }

        override fun build(): TextSolver {
            return MiniMessageTextSolver(miniMessage = miniMessage, tags = tags.toTypedArray())
        }

    }
}
class SimpleTextSolver: TextSolver {

    override fun solve(string: String): Text {
        return Text.text(string)
    }

    override fun encode(text: Text): String {
        return MiniMessage.miniMessage()
            .serialize(text)
    }

    class SimpleMessageBuilder: TextSolver.Builder {

        override fun build(): TextSolver {
            return SimpleTextSolver()
        }

    }
}
/**
 * Create and return a new mini message resolver builder
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun enableMiniMessage(): MiniMessageTextSolver.MiniMessageBuilder {
    return MiniMessageTextSolver.MiniMessageBuilder()
}

/**
 * Create and return a new simple message resolver
 *
 * @since 0.0.3
 * @author kingsthere
 * @see SimpleTextSolver
 */
fun simple(): SimpleTextSolver.SimpleMessageBuilder {
    return SimpleTextSolver.SimpleMessageBuilder()
}