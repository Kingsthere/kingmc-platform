package kingmc.platform.audience

import kingmc.platform.MutablePhysical

/**
 * Represent an audience, an audience is a virtual
 * object, an audience must is a human entity, audience
 * receive the packages from a [Tunnel]
 *
 * @since 0.0.3
 * @see Audience
 * @author kingsthere
 */
interface HumanAudience : MutablePhysical, Audience