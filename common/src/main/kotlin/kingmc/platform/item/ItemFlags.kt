package kingmc.platform.item

import java.util.*

private fun getBitModifier(hideFlag: ItemFlag): Byte {
    return (1 shl hideFlag.ordinal).toByte()
}

fun obtainItemFlagsFromInt(value: Int): Set<ItemFlag> {
    val currentFlags: MutableSet<ItemFlag> = EnumSet.noneOf(ItemFlag::class.java)
    ItemFlag.entries.forEach {
        if (hasItemFlag(value, it)) {
            currentFlags.add(it)
        }
    }
    return currentFlags
}

fun convertItemFlagsToInt(itemFlags: Set<ItemFlag>): Int {
    var value = 0
    itemFlags.forEach {
        value = value or getBitModifier(it).toInt()
    }
    return value
}

fun hasItemFlag(value: Int, flag: ItemFlag): Boolean {
    val bitModifier: Int = getBitModifier(flag).toInt()
    return value and bitModifier == bitModifier
}