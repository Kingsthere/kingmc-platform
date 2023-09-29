package kingmc.platform

import kingmc.common.application.WithApplication
import kingmc.common.application.currentApplication
import kingmc.platform.extension.ExtensionApplication
import java.io.File
import java.nio.file.Path
import kotlin.io.path.Path

/**
 * The base directory to store the caches and configurations of
 * kingmc framework
 *
 * @author kingsthere
 * @since 0.0.3
 */
@get:WithApplication
val baseDirectory: File
    get() = currentApplication().platform.driver.baseDirectory

/**
 * Create a file based on [baseDirectory]
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun baseFile(pathname: String) =
    File("$baseDirectory/$pathname")

/**
 * Create a file based on [baseDirectory]
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun baseFile(path: String, child: String) =
    File("$baseDirectory/$path", child)

/**
 * Create a file path based on [baseDirectory]
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun baseFilePath(pathname: String) =
    Path("$baseDirectory/$pathname")

/**
 * Create a file path based on [baseDirectory]
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun baseFilePath(path: String, child: String) =
    Path("$baseDirectory/$path", child)

/**
 * Create a file based on the data directory of current
 * extension (extensions/{extensionName}/[pathname])
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun extensionFile(pathname: String): File {
    val application = currentApplication()
    return if (application is ExtensionApplication) {
        baseFile("extensions/${application.name}/${pathname}")
    } else {
        baseFile(pathname)
    }
}

/**
 * Create a file path based on the data directory of current
 * extension (extensions/{extensionName}/[pathname])
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun extensionFilePath(pathname: String): Path {
    val application = currentApplication()
    return if (application is ExtensionApplication) {
        baseFilePath("extensions/${application.name}/${pathname}")
    } else {
        baseFilePath(pathname)
    }
}

/**
 * Create a file based on the data directory of current
 * extension (extensions/{extensionName}/file)
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun extensionFile(path: String, child: String): File {
    val application = currentApplication()
    return if (application is ExtensionApplication) {
        baseFile("extensions/${application.name}/${path}", child)
    } else {
        baseFile(path, child)
    }
}

/**
 * Create a file path based on the data directory of current
 * extension (extensions/{extensionName}/file)
 *
 * @author kingsthere
 * @since 0.0.3
 */
@WithApplication
fun extensionFilePath(path: String, child: String): Path {
    val application = currentApplication()
    return if (application is ExtensionApplication) {
        baseFilePath("extensions/${application.name}/${path}", child)
    } else {
        baseFilePath(path, child)
    }
}

/**
 * Gets the extension data directory of current application
 *
 * @author kingsthere
 * @since 0.1.3
 */
@get:WithApplication
val extensionDataDirectory: File
    get() = baseFile("extensions/${currentApplication().name}/")