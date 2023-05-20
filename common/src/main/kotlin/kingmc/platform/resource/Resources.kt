package kingmc.platform.resource

import kingmc.common.application.WithApplication
import kingmc.common.application.resource.getFile
import kingmc.common.context.resource.Resource
import kingmc.common.context.resource.ResourceSource
import kingmc.platform.dataDirectory
import java.io.File
import java.net.URL
import kotlin.io.path.Path
import kotlin.io.path.toPath

/**
 * Gets the file of this resource from current application's class loader, the file should
 * be copied to the extension data folder
 *
 * @return the file got
 */
@WithApplication
fun Resource.getExtensionFile(): File {
    val outPath = when (source) {
        ResourceSource.JAR -> Path(value as String)
        ResourceSource.URL -> (value as URL).toURI().toPath()
    }
    return getFile(Path("$dataDirectory/$outPath"))
}