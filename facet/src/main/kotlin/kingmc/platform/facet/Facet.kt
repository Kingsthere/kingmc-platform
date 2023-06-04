package kingmc.platform.facet

/**
 * Indicates a facet for a specific element implementation such as
 *  + Functions
 *  + Properties
 *
 * @param TFunction the kind of function defined the behavior this facet does
 * @since 0.0.9
 * @author kingsthere
 */
sealed interface Facet<TFunction : Function<TReturn>?, TReturn> {
    /**
     * The body of this facet defined the behavior this facet implementation does
     */
    val body: TFunction

    /**
     * The before bodies to execute before the main [body] execute
     */
    val beforeBodies: List<TFunction>

    /**
     * The after bodies to execute after the main [body] execute
     */
    val afterBodies: List<(TReturn) -> TFunction>

    /**
     * Override the `body` of this facet with the given [body]
     *
     * @param body new body to override the old body
     */
    fun override(body: TFunction)

    /**
     * Insert a `TFunction` before this facet execute
     *
     * @param before body to execute before executing main [body]
     */
    fun before(before: TFunction)

    fun after(after: (TReturn) -> TFunction)
}

/**
 * An abstract implementation of [Facet]
 *
 * @since 0.0.9
 * @author kingsthere
 */
abstract class AbstractFacet<TFunction : Function<TReturn>?, TReturn>(
    body: TFunction,
) : Facet<TFunction, TReturn> {
    final override var body: TFunction = body
        private set

    override val beforeBodies: MutableList<TFunction> = ArrayList(4)
    override val afterBodies: MutableList<(TReturn) -> TFunction> = ArrayList(4)

    override fun override(body: TFunction) {
        this.body = body
    }

    override fun before(before: TFunction) {
        beforeBodies.add(before)
    }

    override fun after(after: (TReturn) -> TFunction) {
        afterBodies.add(after)
    }
}

// region Auto-generated FunctionNFacet(s)
/**
 * A `Facet` for `Function0`
 *
 * @param TReturn return type of the function
 */
open class Function0Facet<TElement : Function0<TReturn>, TFunction : Function0<TReturn>, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function1`
 *
 * @param TReturn return type of the function
 */
open class Function1Facet<TElement : Function1<P1, TReturn>, TFunction : Function1<P1, TReturn>, in P1, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function2`
 *
 * @param TReturn return type of the function
 */
open class Function2Facet<TElement : Function2<P1, P2, TReturn>, TFunction : Function2<P1, P2, TReturn>, in P1, in P2, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function3`
 *
 * @param TReturn return type of the function
 */
open class Function3Facet<TElement : Function3<P1, P2, P3, TReturn>, TFunction : Function3<P1, P2, P3, TReturn>, in P1, in P2, in P3, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function4`
 *
 * @param TReturn return type of the function
 */
open class Function4Facet<TElement : Function4<P1, P2, P3, P4, TReturn>, TFunction : Function4<P1, P2, P3, P4, TReturn>, in P1, in P2, in P3, in P4, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function5`
 *
 * @param TReturn return type of the function
 */
open class Function5Facet<TElement : Function5<P1, P2, P3, P4, P5, TReturn>, TFunction : Function5<P1, P2, P3, P4, P5, TReturn>, in P1, in P2, in P3, in P4, in P5, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function6`
 *
 * @param TReturn return type of the function
 */
open class Function6Facet<TElement : Function6<P1, P2, P3, P4, P5, P6, TReturn>, TFunction : Function6<P1, P2, P3, P4, P5, P6, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function7`
 *
 * @param TReturn return type of the function
 */
open class Function7Facet<TElement : Function7<P1, P2, P3, P4, P5, P6, P7, TReturn>, TFunction : Function7<P1, P2, P3, P4, P5, P6, P7, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function8`
 *
 * @param TReturn return type of the function
 */
open class Function8Facet<TElement : Function8<P1, P2, P3, P4, P5, P6, P7, P8, TReturn>, TFunction : Function8<P1, P2, P3, P4, P5, P6, P7, P8, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function9`
 *
 * @param TReturn return type of the function
 */
open class Function9Facet<TElement : Function9<P1, P2, P3, P4, P5, P6, P7, P8, P9, TReturn>, TFunction : Function9<P1, P2, P3, P4, P5, P6, P7, P8, P9, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function10`
 *
 * @param TReturn return type of the function
 */
open class Function10Facet<TElement : Function10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, TReturn>, TFunction : Function10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function11`
 *
 * @param TReturn return type of the function
 */
open class Function11Facet<TElement : Function11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, TReturn>, TFunction : Function11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function12`
 *
 * @param TReturn return type of the function
 */
open class Function12Facet<TElement : Function12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, TReturn>, TFunction : Function12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function13`
 *
 * @param TReturn return type of the function
 */
open class Function13Facet<TElement : Function13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, TReturn>, TFunction : Function13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function14`
 *
 * @param TReturn return type of the function
 */
open class Function14Facet<TElement : Function14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, TReturn>, TFunction : Function14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function15`
 *
 * @param TReturn return type of the function
 */
open class Function15Facet<TElement : Function15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, TReturn>, TFunction : Function15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function16`
 *
 * @param TReturn return type of the function
 */
open class Function16Facet<TElement : Function16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, TReturn>, TFunction : Function16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, in P16, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function17`
 *
 * @param TReturn return type of the function
 */
open class Function17Facet<TElement : Function17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, TReturn>, TFunction : Function17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, in P16, in P17, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function18`
 *
 * @param TReturn return type of the function
 */
open class Function18Facet<TElement : Function18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, TReturn>, TFunction : Function18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, in P16, in P17, in P18, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function19`
 *
 * @param TReturn return type of the function
 */
open class Function19Facet<TElement : Function19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, TReturn>, TFunction : Function19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, in P16, in P17, in P18, in P19, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function20`
 *
 * @param TReturn return type of the function
 */
open class Function20Facet<TElement : Function20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, TReturn>, TFunction : Function20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, in P16, in P17, in P18, in P19, in P20, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function21`
 *
 * @param TReturn return type of the function
 */
open class Function21Facet<TElement : Function21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, TReturn>, TFunction : Function21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, in P16, in P17, in P18, in P19, in P20, in P21, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)

/**
 * A `Facet` for `Function22`
 *
 * @param TReturn return type of the function
 */
open class Function22Facet<TElement : Function22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, TReturn>, TFunction : Function22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, TReturn>, in P1, in P2, in P3, in P4, in P5, in P6, in P7, in P8, in P9, in P10, in P11, in P12, in P13, in P14, in P15, in P16, in P17, in P18, in P19, in P20, in P21, in P22, TReturn>(
    body: TFunction
) : AbstractFacet<TFunction, TReturn>(body)
// endregion

// region Auto-generated shortcuts to create FunctionNFacet(s)
/**
 * A shortcut to create a `Function0Facet`
 */
fun <TReturn> Facet(
    defaultFunction: Function0<TReturn>
): Facet<Function0<TReturn>, TReturn> {
    return Function0Facet(defaultFunction)
}

/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, TReturn> Facet(
    defaultFunction: Function1<P1, TReturn>
): Facet<Function1<P1, TReturn>, TReturn> {
    return Function1Facet(defaultFunction)
}

/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, TReturn> Facet(
    defaultFunction: Function2<P1, P2, TReturn>
): Facet<Function2<P1, P2, TReturn>, TReturn> {
    return Function2Facet(defaultFunction)
}

/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, TReturn> Facet(
    defaultFunction: Function3<P1, P2, P3, TReturn>
): Facet<Function3<P1, P2, P3, TReturn>, TReturn> {
    return Function3Facet(defaultFunction)
}

/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, TReturn> Facet(
    defaultFunction: Function4<P1, P2, P3, P4, TReturn>
): Facet<Function4<P1, P2, P3, P4, TReturn>, TReturn> {
    return Function4Facet(defaultFunction)
}

/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, TReturn> Facet(
    defaultFunction: Function5<P1, P2, P3, P4, P5, TReturn>
): Facet<Function5<P1, P2, P3, P4, P5, TReturn>, TReturn> {
    return Function5Facet(defaultFunction)
}

/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, TReturn> Facet(
    defaultFunction: Function6<P1, P2, P3, P4, P5, P6, TReturn>
): Facet<Function6<P1, P2, P3, P4, P5, P6, TReturn>, TReturn> {
    return Function6Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, TReturn> Facet(
    defaultFunction: Function7<P1, P2, P3, P4, P5, P6, P7, TReturn>
): Facet<Function7<P1, P2, P3, P4, P5, P6, P7, TReturn>, TReturn> {
    return Function7Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, TReturn> Facet(
    defaultFunction: Function8<P1, P2, P3, P4, P5, P6, P7, P8, TReturn>
): Facet<Function8<P1, P2, P3, P4, P5, P6, P7, P8, TReturn>, TReturn> {
    return Function8Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, TReturn> Facet(
    defaultFunction: Function9<P1, P2, P3, P4, P5, P6, P7, P8, P9, TReturn>
): Facet<Function9<P1, P2, P3, P4, P5, P6, P7, P8, P9, TReturn>, TReturn> {
    return Function9Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, TReturn> Facet(
    defaultFunction: Function10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, TReturn>
): Facet<Function10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, TReturn>, TReturn> {
    return Function10Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, TReturn> Facet(
    defaultFunction: Function11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, TReturn>
): Facet<Function11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, TReturn>, TReturn> {
    return Function11Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, TReturn> Facet(
    defaultFunction: Function12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, TReturn>
): Facet<Function12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, TReturn>, TReturn> {
    return Function12Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, TReturn> Facet(
    defaultFunction: Function13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, TReturn>
): Facet<Function13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, TReturn>, TReturn> {
    return Function13Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, TReturn> Facet(
    defaultFunction: Function14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, TReturn>
): Facet<Function14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, TReturn>, TReturn> {
    return Function14Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, TReturn> Facet(
    defaultFunction: Function15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, TReturn>
): Facet<Function15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, TReturn>, TReturn> {
    return Function15Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, TReturn> Facet(
    defaultFunction: Function16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, TReturn>
): Facet<Function16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, TReturn>, TReturn> {
    return Function16Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, TReturn> Facet(
    defaultFunction: Function17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, TReturn>
): Facet<Function17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, TReturn>, TReturn> {
    return Function17Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, TReturn> Facet(
    defaultFunction: Function18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, TReturn>
): Facet<Function18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, TReturn>, TReturn> {
    return Function18Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, TReturn> Facet(
    defaultFunction: Function19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, TReturn>
): Facet<Function19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, TReturn>, TReturn> {
    return Function19Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, TReturn> Facet(
    defaultFunction: Function20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, TReturn>
): Facet<Function20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, TReturn>, TReturn> {
    return Function20Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, TReturn> Facet(
    defaultFunction: Function21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, TReturn>
): Facet<Function21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, TReturn>, TReturn> {
    return Function21Facet(defaultFunction)
}


/**
 * A shortcut to create a `Function0Facet`
 */
fun <P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, TReturn> Facet(
    defaultFunction: Function22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, TReturn>
): Facet<Function22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, TReturn>, TReturn> {
    return Function22Facet(defaultFunction)
}
// endregion

// region Auto-generated Facet<_, FunctionN<*>>.execute() shortcuts

/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function0<TReturn>, TReturn> Facet<TFunction, TReturn>.invoke(): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody()
    }
    var value = body()
    for (afterBody in this.afterBodies) {
        value = afterBody(value)()
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function1<P1, TReturn>, P1, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1)
    }
    var value = body(p1)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function2<P1, P2, TReturn>, P1, P2, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2)
    }
    var value = body(p1, p2)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function3<P1, P2, P3, TReturn>, P1, P2, P3, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3)
    }
    var value = body(p1, p2, p3)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function4<P1, P2, P3, P4, TReturn>, P1, P2, P3, P4, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4)
    }
    var value = body(p1, p2, p3, p4)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function5<P1, P2, P3, P4, P5, TReturn>, P1, P2, P3, P4, P5, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5)
    }
    var value = body(p1, p2, p3, p4, p5)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function6<P1, P2, P3, P4, P5, P6, TReturn>, P1, P2, P3, P4, P5, P6, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6)
    }
    var value = body(p1, p2, p3, p4, p5, p6)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function7<P1, P2, P3, P4, P5, P6, P7, TReturn>, P1, P2, P3, P4, P5, P6, P7, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function8<P1, P2, P3, P4, P5, P6, P7, P8, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function9<P1, P2, P3, P4, P5, P6, P7, P8, P9, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function10<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function11<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function12<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function13<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function14<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function15<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function16<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function17<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function18<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function19<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function20<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function21<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20, p21: P21): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21)
    }
    return value
}


/**
 * Execute this [Facet], this function considered to execute the [Facet.beforeBodies], [Facet.body] and [Facet.afterBodies]
 *
 * @receiver facet to execute
 * @return return value
 */
operator fun <TFunction : Function22<P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, TReturn>, P1, P2, P3, P4, P5, P6, P7, P8, P9, P10, P11, P12, P13, P14, P15, P16, P17, P18, P19, P20, P21, P22, TReturn> Facet<TFunction, TReturn>.invoke(p1: P1, p2: P2, p3: P3, p4: P4, p5: P5, p6: P6, p7: P7, p8: P8, p9: P9, p10: P10, p11: P11, p12: P12, p13: P13, p14: P14, p15: P15, p16: P16, p17: P17, p18: P18, p19: P19, p20: P20, p21: P21, p22: P22): TReturn {
    for (beforeBody in this.beforeBodies) {
        beforeBody(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22)
    }
    var value = body(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22)
    for (afterBody in this.afterBodies) {
        value = afterBody(value)(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22)
    }
    return value
}

// endregion