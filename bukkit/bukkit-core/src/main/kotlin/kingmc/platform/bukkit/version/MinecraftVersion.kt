package kingmc.platform.bukkit.version

/**
 * This enum represent every version (not contains beta, alpha) in
 * minecraft, and the protocol number of them.
 *
 * @author kingsthere
 * @since 0.0.3
 */
enum class MinecraftVersion(private val versionNumber: Int, private vararg val version: Int) {
    UNKNOWN(Int.MAX_VALUE),  // Use the newest known mappings
    MC1_7_R4(174, 1, 7, 4), MC1_8_R3(183, 1, 8, 3), MC1_9_R1(191, 1, 9, 1), MC1_9_R2(192, 1, 9, 2), MC1_10_R1(
        1101,
        1,
        10,
        1
    ),
    MC1_11_R1(1111, 1, 11, 1), MC1_12_R1(1121, 1, 12, 1), MC1_13_R1(1131, 1, 13, 1), MC1_13_R2(
        1132,
        1,
        13,
        2
    ),
    MC1_14_R1(1141, 1, 14, 1), MC1_15_R1(1151, 1, 15, 1), MC1_16_R1(1161, 1, 16, 1), MC1_16_R2(
        1162,
        1,
        16,
        2
    ),
    MC1_16_R3(1163, 1, 16, 3), MC1_17_R1(1171, 1, 17, 1), MC1_18_R1(1181, 1, 18, 1), MC1_18_R2(1182, 1, 18, 2);

    /**
     * Get the version as integers
     *
     *
     * For example:
     * 1.13.2 -> [1, 13, 2]
     *
     * @return the protocol number as
     */
    fun version(): IntArray {
        return version
    }

    /**
     * Get the version as version number
     *
     *
     * So the program can easily compare two version by
     * comparing integers
     *
     * @return the version number as
     */
    fun versionNumber(): Int {
        return versionNumber
    }
}