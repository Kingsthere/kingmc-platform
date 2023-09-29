package kingmc.platform.extension

import com.electronwill.nightconfig.core.file.FileConfig
import com.electronwill.nightconfig.toml.TomlFormat
import com.electronwill.nightconfig.yaml.YamlFormat
import io.github.classgraph.AnnotationEnumValue
import io.github.classgraph.AnnotationInfo
import io.github.classgraph.ClassGraph
import io.github.classgraph.ClassInfo
import kingmc.common.context.BeanSource
import kingmc.common.context.beans.BeanDefinition
import kingmc.common.environment.DependencyDeclaration
import kingmc.common.environment.maven.DependencyDispatcher
import kingmc.common.environment.maven.DependencyScope
import kingmc.common.environment.maven.model.*
import kingmc.platform.Platform
import kingmc.platform.baseFile
import kingmc.platform.context.source.PlatformBeanSourceImpl
import kingmc.platform.util.loadConfigIntoProperties
import kingmc.util.annotation.hasAnnotationClassname
import kingmc.util.format.FormatContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.util.*
import java.util.function.Consumer

const val ANNOTATION_EXTENSION: String = "kingmc.platform.extension.Extension"
const val MAVEN_DEPENDENCY_CONTAINER = "kingmc.common.environment.maven.MavenDependency.Container"
const val RELOCATE_CONTAINER = "kingmc.common.environment.maven.Relocate.Container"

/**
 * A simple [ExtensionBeanSource] implementation
 */
open class ExtensionBeanSourceImpl(
    classGraph: ClassGraph,
    classLoader: ClassLoader,
    properties: Properties,
    platform: Platform,
    parent: List<BeanSource> = emptyList(),
    preparedBeanDefinition: List<BeanDefinition> = emptyList(),
    val dependencyDispatcher: DependencyDispatcher
) : PlatformBeanSourceImpl(classGraph, classLoader, properties, platform, parent, preparedBeanDefinition),
    ExtensionBeanSource {
    private var _extension: ExtensionDefinition? = null
    override val extension: ExtensionDefinition
        get() = checkNotNull(_extension) { "No extension is defined by this extension" }
    private val scannedMavenDependencies: MutableSet<DependencyDeclaration> = HashSet()
    private val formatContext = FormatContext(properties)
    
    init {
        scanResult.allClasses.forEach { classInfo ->
            scanClassInfo(classInfo)
        }
        checkNotNull(_extension) { "No extension is defined by this extension" }
    }
    
    @Throws(IOException::class)
    protected fun loadProperties(path: String, extension: ExtensionDefinition, consumer: Consumer<InputStream>) {
        // Load properties from jar (builtin)
        val builtinInputStream = this.javaClass.classLoader.getResourceAsStream(path)
        builtinInputStream.use {
            if (builtinInputStream != null) {
                consumer.accept(builtinInputStream)
            }
        }
        // Load properties from external config
        val externalFile = File(baseFile("extensions/${extension.id}/"), path)
        if (externalFile.exists()) {
            val externalInputStream: InputStream = FileInputStream(externalFile)
            externalInputStream.use { consumer.accept(externalInputStream) }
        }
    }

    @Throws(IOException::class)
    protected fun loadPropertiesFile(path: String, extension: ExtensionDefinition, consumer: Consumer<File>) {
        // Jar builtin properties
        val builtinInputStream = this.javaClass.classLoader.getResourceAsStream(path)
        builtinInputStream.use {
            // KingMC will create external config.xxx file instead
            // if (builtinInputStream != null) {
            //     val tempFile = File.createTempFile(path, ".tmp", _bukkitJavaPlugin.tempFolder)
            //     FileUtils.copyInputStreamToFile(builtinInputStream, tempFile)
            //     consumer.accept(tempFile)
            // }

            val externalFile = File(baseFile("extensions/${extension.id}/"), path)
            if (externalFile.exists()) {
                consumer.accept(externalFile)
            } else {
                if (externalFile.createNewFile()) {
                    FileUtils.copyInputStreamToFile(builtinInputStream, externalFile)
                    consumer.accept(externalFile)
                }
            }
        }
    }

    /**
     * Create an [ExtensionDefinition] from annotation info
     */
    @Suppress("UNCHECKED_CAST")
    private fun createExtension(annotation: AnnotationInfo): ExtensionDefinition {
        val parameterValues = annotation.parameterValues
        val descriptionValue = (parameterValues.getValue("description") as? AnnotationInfo)?.parameterValues
        val dependenciesValue = parameterValues.getValue("dependencies") as? Array<Any>
        return ExtensionDefinition(
            id = parameterValues.getValue("id") as String,
            displayName = parameterValues.getValue("displayName") as String? ?: "",
            version = parameterValues.getValue("version") as String? ?: "0.0.1-SNAPSHOT",
            description = ExtensionDefinition.Description(
                contributors = ((descriptionValue?.getValue("contributors") as Array<out Any>?)?.map { it as String }) ?: emptyList(),
                website = descriptionValue?.getValue("website") as String? ?: "https://www.example.com/",
                introduction = descriptionValue?.getValue("introduction") as String? ?: "No description"
            ),
            dependencies = dependenciesValue?.map {
                val dependencyParameterValues = (it as AnnotationInfo).parameterValues
                ExtensionDefinition.Dependency(
                    id = dependencyParameterValues.getValue("id") as String,
                    version = dependencyParameterValues.getValue("version") as String? ?: "",
                    url = dependencyParameterValues.getValue("url") as String? ?: "",
                    optional = dependencyParameterValues.getValue("optional") as Boolean? == true,
                )
            } ?: emptyList()
        )
    }

    /**
     * Create an [ExtensionDefinition] from annotation
     */
    private fun createExtension(annotation: Extension): ExtensionDefinition {
        return ExtensionDefinition(
            annotation.id,
            annotation.displayName,
            annotation.version,
            ExtensionDefinition.Description(
                annotation.description.contributors.toList(),
                annotation.description.website,
                annotation.description.introduction
            ),
            annotation.dependencies.map {
                ExtensionDefinition.Dependency(
                    id = it.id,
                    version = it.version,
                    url = it.url,
                    optional = it.optional,
                )
            }
        )
    }

    /**
     * Scan maven dependencies required by the given class info
     *
     * @return maven dependencies required by the given class info
     */
    @Suppress("UNCHECKED_CAST")
    private fun scanMavenDependencies(classInfo: ClassInfo): List<DependencyDeclaration> =
        buildList {
            val mavenDependencyContainer = classInfo.getAnnotationInfo(MAVEN_DEPENDENCY_CONTAINER) ?: return@buildList
            val relocateContainer = classInfo.getAnnotationInfo(RELOCATE_CONTAINER)
            val containerValues = mavenDependencyContainer.parameterValues
            val relocateValues = relocateContainer?.parameterValues
            val value = containerValues.getValue("value") as Array<Any>
            val relocations = relocateValues?.getValue("value") as Array<Any>? ?: emptyArray()
            value.forEach {
                val mavenDependency = it as AnnotationInfo
                val parameterValues = mavenDependency.parameterValues
                add(DependencyDeclaration(
                    dependency = dependency(
                        groupId = parameterValues.getValue("groupId") as String,
                        artifactId = parameterValues.getValue("artifactId") as String,
                        version = parameterValues.getValue("version") as String,
                        scope = DependencyScope.valueOf((parameterValues.getValue("scope") as AnnotationEnumValue).valueName),
                        formatContext = formatContext
                    ), repository = repository(
                        url = parameterValues.getValue("repository") as String,
                        formatContext = formatContext
                    ), relocations = relocations.map { relocation ->
                        val relocationParameterValues = (relocation as AnnotationInfo).parameterValues
                        return@map jarRelocation(
                            pattern = relocationParameterValues.getValue("pattern") as String,
                            relocatedPattern = relocationParameterValues.getValue("relocatedPattern") as String,
                            formatContext = formatContext
                        )
                    }
                ))
            }
        }

    /**
     * Try to read the given class info if the given class is annotated with [Extension]
     *
     * @return ExtensionDefinition from [Extension] annotation, or `null` if the class is not annotated with [Extension]
     */
    private fun scanExtension(classInfo: ClassInfo): ExtensionDefinition {
        val extensionAnnotationInfo = classInfo.getAnnotationInfo(ANNOTATION_EXTENSION)
        return createExtension(extensionAnnotationInfo)
    }

    /**
     * Load properties for the given extension
     */
    fun loadProperties(extension: ExtensionDefinition) {
        loadProperties("config.properties", extension) {
            properties.load(it)
        }
        loadPropertiesFile("config.toml", extension) {
            FileConfig.of(it, TomlFormat.instance()).use { fileConfig ->
                fileConfig.load()
                loadConfigIntoProperties(
                    fileConfig,
                    properties,
                )
            }
        }
        loadPropertiesFile("config.yml", extension) {
            FileConfig.of(it, YamlFormat.defaultInstance()).use { fileConfig ->
                fileConfig.load()
                loadConfigIntoProperties(
                    fileConfig,
                    properties,
                )
            }
        }
    }

    /**
     * Scan `@Extension` and maven dependencies for the given class info
     */
    fun scanClassInfo(classInfo: ClassInfo) {
        if (classInfo.annotations.hasAnnotationClassname(ANNOTATION_EXTENSION)) { // Check @Extension annotation
            _extension = scanExtension(classInfo)
        }
        scannedMavenDependencies.addAll(scanMavenDependencies(classInfo))
    }

    /**
     * Load maven dependencies declared in this extension bean source
     */
    override suspend fun loadMavenDependencies() = coroutineScope {
        scannedMavenDependencies.forEach { dependency ->

            launch {
                // Install dependency
                dependencyDispatcher.installDependency(dependency)
            }
        }
    }

    override fun toString(): String {
        return "ExtensionBeanSourceImpl(platform=$platform, extension=$_extension)"
    }
}