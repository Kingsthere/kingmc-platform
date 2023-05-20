package kingmc.platform.permission

/**
 * A state describe the state of permission, it basically explained
 * three state of permission could be, `true`, `false`, `not set`
 *
 * @since 0.0.2
 * @author kingsthere
 */
enum class PermissionState(val booleanValue: Boolean) {
    TRUE(true),
    FALSE(false),
    NOT_SET(false)
}

/**
 * Convert this boolean to a `PermissionState`
 *
 *  + Returns [PermissionState.TRUE] if boolean value is `true`
 *  + Returns [PermissionState.FALSE] if boolean value is `false`
 *  + Returns [PermissionState.NOT_SET] if boolean value is `null`
 *
 * @receiver Boolean? to convert to
 * @return permission state converted to
 */
fun Boolean?.toPermissionState(): PermissionState {
    return if (this == null) {
        PermissionState.NOT_SET
    } else {
        if (this) {
            PermissionState.TRUE
        } else {
            PermissionState.FALSE
        }
    }
}