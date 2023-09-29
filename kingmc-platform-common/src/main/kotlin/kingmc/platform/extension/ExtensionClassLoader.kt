package kingmc.platform.extension

import kingmc.util.AppendableURLClassLoader
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path

/**
 * Class loader responsible for loading the extensions from files
 *
 * @since 0.1.2
 * @author kingsthere
 */
class ExtensionClassLoader(urls: Array<out URL>, parent: ClassLoader? = null)
    : URLClassLoader(urls, parent), AppendableURLClassLoader {
    override fun addPath(path: Path) {
        addURL(path.toUri().toURL())
    }
}