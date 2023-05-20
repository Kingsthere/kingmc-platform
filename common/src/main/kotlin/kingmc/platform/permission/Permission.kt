package kingmc.platform.permission

/**
 * Represents a permission to a `Permissible`
 *
 * @since 0.0.8
 * @author kingsthere
 */
interface Permission {
    /**
     * The name of this permission
     *
     * for example: `kingmc.command.reload`
     */
    val name: String

    /**
     * The default state of this permission, when the state is equals to [PermissionState.NOT_SET], the
     * actual value get from here
     */
    val defaultState: Boolean

    /**
     * Children permissions of this permission
     */
    val children: Set<Permission>
}