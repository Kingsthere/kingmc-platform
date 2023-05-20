package kingmc.platform.extension

import io.github.classgraph.AnnotationEnumValue
import io.github.classgraph.AnnotationInfo
import io.github.classgraph.ScanResult
import kingmc.common.context.resource.Resource
import kingmc.common.context.resource.ResourceLoadException
import kingmc.common.context.resource.ResourceSource
import kingmc.common.environment.maven.DependencyDispatcher
import kingmc.common.environment.maven.DependencyScope
import kingmc.common.environment.maven.model.*
import kingmc.common.structure.ClassGraphClassSource
import kingmc.common.structure.ExperimentalStructureApi
import kingmc.common.structure.Pluggable
import kingmc.common.structure.classloader.ExtensionClassLoader
import kingmc.util.format.FormatContext
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.yaml.snakeyaml.Yaml

class ExtensionClassSource(val classLoader: ExtensionClassLoader, val formatContext: FormatContext) : ClassGraphClassSource() {
    val extensions = mutableListOf<ExtensionDefinition>()

    init {
        classGraph.overrideClassLoaders(classLoader)
    }

    val scannedResult: ScanResult = classGraph.scan()

    /**
     * Create an [ExtensionDefinition] from annotation info
     */
    @Suppress("UNCHECKED_CAST")
    fun createExtension(annotation: AnnotationInfo): ExtensionDefinition {
        val parameterValues = annotation.parameterValues
        val descriptionValue = (parameterValues.getValue("description") as AnnotationInfo).parameterValues
        val dependenciesValue = parameterValues.getValue("dependencies") as Array<AnnotationInfo>
        return ExtensionDefinition(
            parameterValues.getValue("id") as String,
            parameterValues.getValue("displayName") as String,
            parameterValues.getValue("tag") as String,
            ExtensionDefinition.Description(
                descriptionValue.getValue("author") as Array<out String>,
                descriptionValue.getValue("website") as String,
                descriptionValue.getValue("introduction") as String
            ),
            dependenciesValue.map {
                val dependencyParameterValues = it.parameterValues
                ExtensionDefinition.Dependency(
                    dependencyParameterValues.getValue("id") as String,
                    dependencyParameterValues.getValue("url") as String,
                    dependencyParameterValues.getValue("optional") as Boolean,
                )
            }.toTypedArray())
    }

    /**
     * Create an [ExtensionDefinition] from annotation
     */
    fun createExtension(annotation: Extension): ExtensionDefinition {
        return ExtensionDefinition(
            annotation.id,
            annotation.displayName,
            annotation.tag,
            ExtensionDefinition.Description(
                annotation.description.contributors,
                annotation.description.website,
                annotation.description.introduction
            ),
            annotation.dependencies.map {
                ExtensionDefinition.Dependency(
                    it.id,
                    it.url,
                    it.optional,
                )
            }.toTypedArray())
    }

    fun scanMavenDependencies(): List<Triple<Dependency, Repository, List<JarRelocation>>> = buildList {
        val mavenDependencyContainerClass = "kingmc.common.environment.maven.MavenDependency.Container"
        val relocateContainerClass = "kingmc.common.environment.maven.Relocate.Container"
        scannedResult.getClassesWithAnnotation(mavenDependencyContainerClass).forEach {
            @Suppress("UNCHECKED_CAST")
            val values = it.getAnnotationInfo(mavenDependencyContainerClass).parameterValues.getValue("value") as Array<AnnotationInfo>
            val relocations = it.getAnnotationInfo(relocateContainerClass).parameterValues.getValue("value") as Array<AnnotationInfo>
            values.forEach { mavenDependency ->
                val parameterValues = mavenDependency.parameterValues
                add(Triple(
                    dependency(
                        groupId = parameterValues.getValue("groupId") as String,
                        artifactId = parameterValues.getValue("artifactId") as String,
                        version = parameterValues.getValue("version") as String,
                        scope = DependencyScope.valueOf((parameterValues.getValue("scope") as AnnotationEnumValue).valueName),
                        formatContext = formatContext
                    ), repository(
                        url = parameterValues.getValue("repository") as String,
                        formatContext = formatContext
                    ), relocations.map {
                        val relocationParameterValues = it.parameterValues
                        return@map jarRelocation(
                            pattern = relocationParameterValues.getValue("pattern") as String,
                            relocatedPattern = relocationParameterValues.getValue("relocatedPattern") as String,
                            formatContext = formatContext
                        )
                    }
                ))
            }
        }
    }

    fun scanExtensions(): List<ExtensionDefinition> = buildList {
        val extensionClass = Extension::class.java
        scannedResult.getClassesWithAnnotation(extensionClass).forEach {
            @Suppress("UNCHECKED_CAST")
            val values = it.getAnnotationInfo(extensionClass)
            add(createExtension(values))
        }
    }

    @Suppress("UNCHECKED_CAST")
    suspend fun loadMavenDependencies(dependencyDispatcher: DependencyDispatcher, repositories: Collection<Repository>) = coroutineScope {
        try {
            val config = extension.getInputStream(classLoader)

            // Load maven dependencies from extension.yml
            val yaml: Map<String, Any> = Yaml().load(config)
            val configuredDependencies: List<String> = yaml["maven-dependencies"] as? List<String> ?: emptyList()
            val configuredRepositories: List<String> = yaml["maven-repositories"] as? List<String> ?: emptyList()
            val yamlConfig = ExtensionConfig(configuredDependencies, configuredRepositories)
            val proceedRepositories = repositories + yamlConfig.mavenRepositories.map { Repository(it) }
            yamlConfig.mavenDependencies.forEach { dependency ->
                launch {
                    val splitDependencyNotion = dependency.split(":")
                    dependencyDispatcher.installDependency(
                        Dependency(
                            splitDependencyNotion[0],
                            splitDependencyNotion[1],
                            splitDependencyNotion[2],
                            DependencyScope.RUNTIME
                        ),
                        proceedRepositories,
                        emptySet(),
                        DependencyScope.RUNTIME
                    )
                }
            }

            // Load maven dependencies scanned by classes annotated with @MavenDependency
            scanMavenDependencies().forEach { dependency ->
                launch {
                    dependencyDispatcher.installDependency(
                        dependency.first,
                        listOf(dependency.second),
                        dependency.third,
                        DependencyScope.RUNTIME
                    )
                }
            }
        } catch (e: ResourceLoadException) {
            return@coroutineScope
        }
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

    override fun whenLoadClass(clazz: Class<*>) {
        if (clazz.isAnnotationPresent(Extension::class.java)) {
            val extension = clazz.getAnnotation(Extension::class.java)
            extensions.add(createExtension(extension))
        }
    }

    override fun toString(): String {
        return "ExtensionClassSource(classLoader=$classLoader)"
    }

    /**
     * Data class describe a configuration file for an extension
     */
    data class ExtensionConfig(
        /**
         * Required dependencies to this extension
         */
        val mavenDependencies: List<String>,

        /**
         * Maven repositories to download [mavenDependencies] from
         */
        val mavenRepositories: List<String>
    )

    companion object Resources {
        /**
         * The extension.yml config file
         */
        val extension = Resource(ResourceSource.JAR, "extension.yml")
    }
}