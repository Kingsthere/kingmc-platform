package kingmc.platform.block

import kingmc.platform.Locatable
import kingmc.platform.material.MaterialHolder

/**
 * Represents a captured state of a block, which will not updated automatically.
 *
 * @author kingsthere
 * @since 0.0.5
 */
interface BlockState : Locatable, MaterialHolder {
    /**
     * Gets the [Block] this state is representing
     */
    val block: Block

    /**
     * Attempts to update the block represented by this state, setting it to
     * the new values as defined by this state.
     *
     *
     * If this state is not placed, this will have no effect and return true.
     *
     *
     * Unless force is true, this will not modify the state of a block if it
     * is no longer the same type as it was when this state was taken. It will
     * return false in this eventuality.
     *
     *
     * If force is true, it will set the type of the block to match the new
     * state, set the state data and then return true.
     *
     *
     * If applyPhysics is true, it will trigger a physics update on
     * surrounding blocks which could cause them to update or disappear.
     *
     * @param force true to forcefully set the state
     * @param applyPhysics false to cancel updating physics on surrounding
     * blocks
     * @return true if the update was successful, otherwise false
     */
    fun update(force: Boolean = false, applyPhysics: Boolean = true): Boolean


    /**
     * Whether the state is placed in the world or 'virtual' (e.g. on an itemstack)
     */
    val isPlaced: Boolean
}