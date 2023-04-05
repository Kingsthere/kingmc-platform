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
 * @since 0.0.3
 * @author kingsthere
 */
@get:WithApplication
val baseDirectory: File
    get() = currentApplication().platform.driver.baseDirectory

/**
 * Create a file based on [baseDirectory]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun baseFile(pathname: String) =
    File("$baseDirectory/$pathname")

/**
 * Create a file based on [baseDirectory]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun baseFile(path: String, child: String) =
    File("$baseDirectory/$path", child)

/**
 * Create a file path based on [baseDirectory]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun baseFilePath(pathname: String) =
    Path("$baseDirectory/$pathname")

/**
 * Create a file path based on [baseDirectory]
 *
 * @since 0.0.3
 * @author kingsthere
 */
@WithApplication
fun baseFilePath(path: String, child: String) =
    Path("$baseDirectory/$path", child)

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
        baseFile("extensions/${application.name}/${pathname}")
    } else {
        baseFile(pathname)
    }
}

/**
 * Create a file path based on the data directory of current
 * extension (extensions/{extensionName}/[pathname])
 *
 * @since 0.0.3
 * @author kingsthere
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
 * extension (extensions/{extensionName/file)
 *
 * @since 0.0.3
 * @author kingsthere
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
 * extension (extensions/{extensionName/file)
 *
 * @since 0.0.3
 * @author kingsthere
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
 * The data directory of current application
 *
 * @since 0.0.3
 * @author kingsthere
 */
@get:WithApplication
val dataDirectory: File
    get() = baseFile("extensions/${currentApplication().name}/")