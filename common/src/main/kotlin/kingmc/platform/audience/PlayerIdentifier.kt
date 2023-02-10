package kingmc.platform.audience

import kingmc.util.errorprone.Immutable
import java.util.*

/**
 * A simple implementation of [AudienceIdentifier] specify
 * to a [Player] as the result
 *
 * @since 0.0.3
 * @author kingsthere
 */
@Immutable
open class PlayerIdentifier(
    open val name: String?,
    open val uuid: UUID?,
) : AudienceIdentifier<Player> {
    /**
     * Identity the audience by the name of that audience
     *
     * @since 0.0.3
     */
    override fun withName(name: String): PlayerIdentifier =
        PlayerIdentifier(name, this.uuid)

    /**
     * Identity the audience by the uuid
     *
     * @since 0.0.3
     */
    override fun withUUID(uuid: UUID): PlayerIdentifier =
        PlayerIdentifier(this.name, uuid)

}