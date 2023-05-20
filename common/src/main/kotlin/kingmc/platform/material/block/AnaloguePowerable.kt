package kingmc.platform.material.block

/**
 * 'power' represents the redstone power level currently being emitted or
 * transmitted via this block, such as
 *  + Redstone Wire
 *  + Pressure Plate Weighted
 *
 * May not be over 9000 or [maximumPower] (usually 15)
 *
 * @since 0.0.8
 * @author kingsthere
 * @see Powerable
 */
interface AnaloguePowerable {
    /**
     * Redstone power level
     */
    var power: Int

    /**
     * The maximum allowed value of redstone power level
     */
    val maximumPower: Int
}