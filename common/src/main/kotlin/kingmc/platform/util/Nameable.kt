package kingmc.platform.util

import kingmc.common.text.Text

/**
 * Superinterface for nameable entities or blocks
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface Nameable {
    /**
     * The custom name on a mob or block
     */
    var customName: Text?
}