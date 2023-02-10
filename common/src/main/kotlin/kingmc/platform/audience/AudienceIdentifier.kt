package kingmc.platform.audience

import java.util.*

/**
 * Represent an audience identifier to identity an audience by
 * the different optional ways, such as uuid, name. You can also
 * use options **more than one** to double-check
 *
 * @since 0.0.3
 * @author kingsthere
 * @param T the type of audience this identifier is identity to
 */
interface AudienceIdentifier<T : Audience> {
    /**
     * Identity the audience by the name of that audience
     *
     * @since 0.0.3
     */
    fun withName(name: String): AudienceIdentifier<T>

    /**
     * Identity the audience by the uuid
     *
     * @since 0.0.3
     */
    fun withUUID(uuid: UUID): AudienceIdentifier<T>
}