package kingmc.platform.driver

import kingmc.util.AppendableURLClassLoader
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path

/**
 * Class loader responsible for loading the external driver from files
 *
 * @since 0.1.2
 * @author kingsthere
 */
class DriverClassLoader(urls: Array<out URL>, parent: ClassLoader? = null)
    : URLClassLoader(urls, parent), AppendableURLClassLoader {
    override fun addPath(path: Path) {
        addURL(path.toUri().toURL())
    }
}