package kingmc.platform.audience.text

/**
 * Builds a new [BlockNBTText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [BlockNBTText]
 * @since 4.6.0
 */
fun BlockNBT(builder: BlockNBTTextBuilder.() -> Unit): BlockNBTText = Text.blockNBT(builder)

/**
 * Builds a new [EntityNBTText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [EntityNBTText]
 * @since 4.6.0
 */
fun EntityNBT(builder: EntityNBTTextBuilder.() -> Unit): EntityNBTText = Text.entityNBT(builder)

/**
 * Builds a new [KeybindText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [KeybindText]
 * @since 4.6.0
 */
fun Keybind(builder: KeybindTextBuilder.() -> Unit): KeybindText = Text.keybind(builder)

/**
 * Builds a new [ScoreText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [ScoreText]
 * @since 4.6.0
 */
fun Score(builder: ScoreTextBuilder.() -> Unit): ScoreText = Text.score(builder)

/**
 * Builds a new [SelectorText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [SelectorText]
 * @since 4.6.0
 */
fun Selector(builder: SelectorTextBuilder.() -> Unit): SelectorText = Text.selector(builder)

/**
 * Builds a new [StorageNBTText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [StorageNBTText]
 * @since 4.6.0
 */
fun StorageNBT(builder: StorageNBTTextBuilder.() -> Unit): StorageNBTText = Text.storageNBT(builder)

/**
 * Builds a new [LiteralText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [LiteralText]
 * @since 4.6.0
 */
fun LiteralText(builder: LiteralTextBuilder.() -> Unit = {  }): LiteralText
    = Text.text(builder)

/**
 * Builds a new [LiteralText] with default content set from
 * the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [LiteralText]
 * @since 4.6.0
 */
fun LiteralText(content: String, builder: LiteralTextBuilder.() -> Unit = {  }): LiteralText =
    Text.text {
        it.content(content)
        it.builder()
    }

/**
 * Builds a new [TranslatableText] from the specified [builder].
 *
 * @param builder the builder to apply values from
 * @return a new [TranslatableText]
 * @since 4.6.0
 */
fun Translatable(builder: TranslatableTextBuilder.() -> Unit): TranslatableText = Text.translatable(builder)