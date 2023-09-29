package kingmc.platform.util

import java.util.*

private const val SNAPSHOT_BIT: Int = 30

/**
 * Enum for each Minecraft protocol version
 *
 * @author kingsthere
 * @since 0.0.8
 */
enum class ProtocolVersion(protocol: Int, snapshotProtocol: Int, vararg names: String) {
    UNKNOWN(-1, "Unknown"),

    LEGACY(-2, "Legacy"),

    MINECRAFT_1_7_2(4, "1.7.2", "1.7.3", "1.7.4", "1.7.5"),

    MINECRAFT_1_7_6(5, "1.7.6", "1.7.7", "1.7.8", "1.7.9", "1.7.10"),

    MINECRAFT_1_8(47, "1.8", "1.8.1", "1.8.2", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9"),

    MINECRAFT_1_9(107, "1.9"),
    MINECRAFT_1_9_1(108, "1.9.1"),
    MINECRAFT_1_9_2(109, "1.9.2"),
    MINECRAFT_1_9_4(110, "1.9.3", "1.9.4"),

    MINECRAFT_1_10(210, "1.10", "1.10.1", "1.10.2"),
    MINECRAFT_1_11(315, "1.11"),
    MINECRAFT_1_11_1(316, "1.11.1", "1.11.2"),

    MINECRAFT_1_12(335, "1.12"),
    MINECRAFT_1_12_1(338, "1.12.1"),
    MINECRAFT_1_12_2(340, "1.12.2"),

    MINECRAFT_1_13(393, "1.13"),
    MINECRAFT_1_13_1(401, "1.13.1"),
    MINECRAFT_1_13_2(404, "1.13.2"),

    MINECRAFT_1_14(477, "1.14"),
    MINECRAFT_1_14_1(480, "1.14.1"),
    MINECRAFT_1_14_2(485, "1.14.2"),
    MINECRAFT_1_14_3(490, "1.14.3"),
    MINECRAFT_1_14_4(498, "1.14.4"),

    MINECRAFT_1_15(573, "1.15"),
    MINECRAFT_1_15_1(575, "1.15.1"),
    MINECRAFT_1_15_2(578, "1.15.2"),

    MINECRAFT_1_16(735, "1.16"),
    MINECRAFT_1_16_1(736, "1.16.1"),

    MINECRAFT_1_16_2(751, "1.16.2"),
    MINECRAFT_1_16_3(753, "1.16.3"),
    MINECRAFT_1_16_4(754, "1.16.4", "1.16.5"),

    MINECRAFT_1_17(755, "1.17"),
    MINECRAFT_1_17_1(756, "1.17.1"),

    MINECRAFT_1_18(757, "1.18", "1.18.1"),
    MINECRAFT_1_18_2(758, "1.18.2"),

    MINECRAFT_1_19(759, "1.19"),
    MINECRAFT_1_19_1(760, "1.19.1", "1.19.2"),
    MINECRAFT_1_19_3(761, "1.19.3"),
    MINECRAFT_1_19_4(762, "1.19.4");

    companion object {
        /**
         * Represents the lowest supported version
         */
        val MINIMUM_VERSION = MINECRAFT_1_7_2

        /**
         * Represents the highest supported version
         */
        val MAXIMUM_VERSION = entries.last()

        /**
         * The user-friendly representation of the lowest and highest supported versions.
         */
        val SUPPORTED_VERSION_STRING = String.format(
            "%s-%s", MINIMUM_VERSION.versionIntroducedIn,
            MAXIMUM_VERSION.mostRecentSupportedVersion
        )

        /**
         * A map linking the protocol version number to its [ProtocolVersion] representation.
         */
        val ID_TO_PROTOCOL_CONSTANT: Map<Int, ProtocolVersion> = buildMap {
            for (version in ProtocolVersion.entries) {
                // For versions where the snapshot is compatible with the prior release version, Mojang will
                // default to that. Follow that behavior since there is precedent (all the Minecraft 1.8
                // minor releases use the same protocol version).
                putIfAbsent(version.protocol, version)
                if (version.snapshotProtocol != -1) {
                    put(version.snapshotProtocol, version)
                }
            }
        }

        /**
         * A set containing all the protocols that the proxy actually supports, excluding special-purpose
         * "versions" like [.LEGACY] and [.UNKNOWN].
         */
        val SUPPORTED_VERSIONS: Set<ProtocolVersion>? = EnumSet.noneOf(ProtocolVersion::class.java).apply {
            for (value in entries) {
                if (!value.isUnknown && !value.isLegacy) {
                    add(value)
                }
            }
        }

        /**
         * Gets the [ProtocolVersion] for the given protocol.
         *
         * @param protocol the protocol as an int
         * @return the protocol version
         */
        fun getProtocolVersion(protocol: Int): ProtocolVersion {
            return ID_TO_PROTOCOL_CONSTANT.getOrDefault(protocol, UNKNOWN)
        }

        /**
         * Returns whether the protocol is supported.
         *
         * @param protocol the protocol as an int
         * @return if the protocol supported
         */
        fun isSupported(protocol: Int): Boolean {
            val version: ProtocolVersion? = ID_TO_PROTOCOL_CONSTANT[protocol]
            return version != null && !version.isUnknown
        }

        /**
         * Returns whether the [ProtocolVersion] is supported.
         *
         * @param version the protocol version
         * @return if the protocol supported
         */
        fun isSupported(version: ProtocolVersion?): Boolean {
            return version != null && !version.isUnknown
        }
    }

    private val protocol: Int
    private var snapshotProtocol = 0
    private val names: Array<out String>

    constructor(protocol: Int, vararg names: String) : this(protocol, -1, *names)

    init {
        if (snapshotProtocol != -1) {
            this.snapshotProtocol = 1 shl SNAPSHOT_BIT or snapshotProtocol
        } else {
            this.snapshotProtocol = -1
        }
        this.protocol = protocol
        this.names = names
    }

    /**
     * Returns the protocol as an int.
     *
     * @return the protocol version
     */
    fun getProtocol(): Int {
        return if (protocol == -1) snapshotProtocol else protocol
    }

    val versionIntroducedIn: String
        /**
         * Returns the user-friendly name of the version
         * this protocol was introduced in.
         *
         * @return the version name
         */
        get() = names.first()
    val mostRecentSupportedVersion: String
        /**
         * Returns the user-friendly name of the last
         * version this protocol is valid for.
         *
         * @return the version name
         */
        get() = names.last()
    val versionsSupportedBy: List<String>
        /**
         * Returns all versions this protocol is valid for.
         *
         * @return the version names
         */
        get() = listOf(*names)
    val isUnknown: Boolean
        /**
         * Returns whether this [ProtocolVersion] is unknown to the proxy.
         *
         * @return if the protocol is unknown
         */
        get() = this == UNKNOWN
    val isLegacy: Boolean
        /**
         * Returns whether this [ProtocolVersion] is a legacy protocol.
         *
         * @return if the protocol is legacy
         */
        get() = this == LEGACY

    override fun toString(): String {
        return versionIntroducedIn
    }
}