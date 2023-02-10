package kingmc.platform

import kingmc.common.structure.annotation.PackageScan

/**
 * Enable component scan for platform api
 *
 * @since 0.0.3
 * @author kingsthere
 */
@PackageScan(
    included = ["kingmc.platform"],
    excluded = []
)
object PlatformScan