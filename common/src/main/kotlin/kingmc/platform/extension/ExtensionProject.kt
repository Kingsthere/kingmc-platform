package kingmc.platform.extension

import kingmc.common.OpenAPI
import kingmc.common.structure.*
import kingmc.common.structure.annotation.PackageScan
import java.io.File
import java.io.FileInputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

class ExtensionProject(val file: File, private val classLoader: ClassLoader = OpenAPI.classLoader()!!) : Project {
    /**
     * The extensions that defined in this project
     */
    val extensions: MutableList<ExtensionDefine> = mutableListOf()

    /**
     * The name of all classes in this project
     *
     * @since 0.0.0.1
     */
    private var classNames: MutableList<String>

    /**
     * Package information to this project about
     * the excluded/included packages
     *
     * @since 0.0.0.1
     */
    private lateinit var packageInfo: PackageInfo

    /**
     * The supplier to supply the classes in this project
     *
     * @see getClasses
     */
    var loadedClasses: List<Class<*>>

    init {
        this.classNames = ArrayList()
        val zip = ZipInputStream(FileInputStream(file))
        var entry: ZipEntry? = zip.nextEntry
        while (entry != null) {
            if (!entry.isDirectory && entry.name.endsWith(".class")) {
                // This ZipEntry represents a class. Now, what class does it represent?
                val className = entry.name.replace('/', '.') // including ".class"

                var isExclude = false
                for (excluded in PackageInfo.DEFAULT_EXCLUDED) {
                    if (className.startsWith(excluded)) {
                        isExclude = true
                        break
                    }
                }
                if (!isExclude) {
                    this.classNames.add(className.substring(0, className.length - ".class".length))
                }
            }
            entry = zip.nextEntry
        }
        zip.close()
        val value: MutableList<Class<*>> = ArrayList()
        for (className in classNames) {
            try {
                val clazz: Class<*> = classLoader.loadClass(className) ?: continue
                clazz.sourceFile = this.file

                if (clazz.isAnnotationPresent(PackageScan::class.java)) {
                    val annotation: PackageScan = clazz.getAnnotation(PackageScan::class.java)
                    packageInfo = PackageInfo.create(
                        annotation.excluded.toSet(),
                        annotation.included.toSet()
                    )
                }
                if (clazz.isAnnotationPresent(Extension::class.java)) {
                    val extension = clazz.getAnnotation(Extension::class.java)
                    extensions.add(createExtension(clazz, extension))
                }

                value.add(clazz)
            } catch (e: Exception) {
                throw ProjectInitializeException("Unable to load project", e)
            } catch (e: NoClassDefFoundError) {
                // Send warning log?
            }

        }
        this.loadedClasses = value
    }

    /**
     * Create an [ExtensionDefine] from annotation
     */
    fun createExtension(clazz: Class<*>, annotation: Extension): ExtensionDefine {
        return ExtensionDefine(
            annotation.id,
            annotation.displayName,
            annotation.tag,
            ExtensionDefine.Description(
                annotation.description.contributors,
                annotation.description.website,
                annotation.description.introduction
            ),
            annotation.dependencies.map {
                ExtensionDefine.Dependency(
                    it.id,
                    it.optional
                )
            }.toTypedArray(),
            clazz)
    }

    fun reload() {
        this.extensions.clear()
        this.classNames = ArrayList()
        val zip = ZipInputStream(FileInputStream(file))
        var entry: ZipEntry? = zip.nextEntry
        while (entry != null) {
            if (!entry.isDirectory && entry.name.endsWith(".class")) {
                // This ZipEntry represents a class. Now, what class does it represent?
                val className = entry.name.replace('/', '.') // including ".class"

                var isExclude = false
                for (excluded in PackageInfo.DEFAULT_EXCLUDED) {
                    if (className.startsWith(excluded)) {
                        isExclude = true
                        break
                    }
                }
                if (!isExclude) {
                    this.classNames.add(className.substring(0, className.length - ".class".length))
                }
            }
            entry = zip.nextEntry
        }
        zip.close()
        val value: MutableList<Class<*>> = ArrayList()
        for (className in classNames) {
            try {
                val clazz: Class<*> = classLoader.loadClass(className) ?: continue
                clazz.sourceFile = this.file

                if (clazz.isAnnotationPresent(PackageScan::class.java)) {
                    val annotation: PackageScan = clazz.getAnnotation(PackageScan::class.java)
                    packageInfo = PackageInfo.create(
                        annotation.excluded.toSet(),
                        annotation.included.toSet()
                    )
                }
                if (clazz.isAnnotationPresent(Extension::class.java)) {
                    val extension = clazz.getAnnotation(Extension::class.java)
                    extensions.add(createExtension(clazz, extension))
                }

                value.add(clazz)
            } catch (e: Exception) {
                throw ProjectInitializeException("Unable to load project", e)
            } catch (e: NoClassDefFoundError) {
                // Send warning log?
            }

        }
        this.loadedClasses = value
    }

    /**
     * Filter the classes input by the [PackageInfo]
     *
     * @since 0.0.0.1
     */
    private fun filterClasses(classes: List<Class<*>>) : List<Class<*>> {
        val list : MutableList<Class<*>> = ArrayList()
        for (clazz in classes) {
            a@
            for (include : String in packageInfo.included) {
                try {
                    if (clazz.name.startsWith(include)) {
                        list.add(clazz)
                        break@a
                    }
                } catch (_: IncompatibleClassChangeError) {
                    break@a
                }
            }
        }
        return list
    }

    override fun contains(clazz: Class<*>): Boolean {
        return clazz in getClasses()
    }

    /**
     * Get a class from this project
     *
     * @return the project got, `null` if the
     *         class is not define in this project
     * @since 0.0.2
     * @author kingsthere
     */
    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> getClass(name: String): Class<T> =
        (this.classLoader.loadClass(name) ?: Class.forName(name))!! as Class<T>

    override fun getClasses(): List<Class<*>> {
        // Return the class supplier supplied
        return loadedClasses
    }

    /**
     * Invoke this function to get all pluggable features
     * in this project as a [Set]
     *
     * @see Set
     * @see Pluggable
     * @since 0.0.0.1
     */
    @ExperimentalStructureApi
    override fun getPluggable(): Set<Pluggable> {
        TODO("Not yet implemented")
    }
}