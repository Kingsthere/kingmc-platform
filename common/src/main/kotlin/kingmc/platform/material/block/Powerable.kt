package kingmc.platform.material.block

/**
 * 'powered' indicates whether this block is in the powered state or not, i.e.
 * receiving a redstone current of power > 0, such as
 *  + Repeater
 *  + Button
 *  + Door
 *  + Trapdoor
 *
 * @since 0.0.8
 * @author kingsthere
 * @see AnaloguePowerable
 */
interface Powerable {
    /**
     * `true` if this powerable block is powered
     */
    var isPowered: Boolean
}