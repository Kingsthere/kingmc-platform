package kingmc.platform.entity

import kingmc.common.application.Isolated

/**
 * An interface represent an isolated `Entity` between applications
 *
 * Isolated entities are invisible to other applications, this is used to
 * prevent collisions with other extensions
 *
 * @since 0.1.0
 * @author kingsthere
 */
@Isolated
interface IsolatedEntity : Entity