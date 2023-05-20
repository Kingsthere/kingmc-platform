package kingmc.platform.proxy

import kingmc.platform.util.ProtocolVersion
import kingmc.util.errorprone.Immutable
import java.util.*

/**
 * Represents a 1.7 or above server list ping response
 */
@Immutable
data class ServerPing(val version: ProtocolVersion) {
    /**
     * A data class describe the protocol version of a server ping response
     *
     * @property protocol the protocol number
     * @param name the name to the protocol
     */
    data class Version(val protocol: Int, val name: String)

    /**
     * A data class describe players information to a server ping response
     *
     * @property online amount of online players on the server
     * @property max maximum players this server could accommodate up to
     * @property sample a sample of players on the server
     */
    data class Players(val online: Int, val max: Int, val sample: List<SamplePlayer>)

    /**
     * A data class describe a sample player to a server ping response
     */
    data class SamplePlayer(val name: String, val id: UUID)
}