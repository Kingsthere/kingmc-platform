package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.extension.ExtensionApplication
import kingmc.platform.extension.LoadedExtension
import java.io.File

/**
 * The base directory to store the caches and configurations of
 * kingmc framework
 *
 * @since 0.0.3
 * @author kingsthere
 */
lateinit var baseDirectory: File

/**
 * Create a file based on [baseDirectory]
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun baseFile(pathname: String) =
    File("$baseDirectory/$pathname")

/**
 * Create a file based on [baseDirectory]
 *
 * @since 0.0.3
 * @author kingsthere
 */
fun baseFile(path: String, child: String) =
    File("$baseDirectory/$path", child)

/**
 * Create a file based on the data directory of current
 * extension (extensions/{extensionName}/[pathname])
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun extensionFile(pathname: String): File {
    val application = currentApplication()
    return if (application is ExtensionApplication) {
        baseFile("extensions/${application.context.name}/${pathname}")
    } else {
        baseFile(pathname)
    }
}
/**
 * Create a file based on the data directory of current
 * extension (extensions/{extensionName/file)
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun extensionFile(path: String, child: String): File {
    val application = currentApplication()
    return if (application is ExtensionApplication) {
        baseFile("extensions/${application.context.name}/${path}", child)
    } else {
        baseFile(path, child)
    }
}

/**
 * The data directory of a [LoadedExtension]
 *
 * @since 0.0.3
 * @author kingsthere
 */
val LoadedExtension.extensionDataDirectory: File
    get() = baseFile("extensions/${application.context.name}/")