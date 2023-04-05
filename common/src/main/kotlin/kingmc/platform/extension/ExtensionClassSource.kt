package kingmc.platform.extension

import kingmc.common.context.resource.Resource
import kingmc.common.context.resource.ResourceLoadException
import kingmc.common.context.resource.ResourceSource
import kingmc.common.environment.maven.DependencyDispatcher
import kingmc.common.environment.maven.DependencyScope
import kingmc.common.environment.maven.model.Dependency
import kingmc.common.environment.maven.model.Repository
import kingmc.common.structure.ExperimentalStructureApi
import kingmc.common.structure.JarFileClassSource
import kingmc.common.structure.Pluggable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.yaml.snakeyaml.Yaml
import java.io.File

class ExtensionClassSource(file: File, classLoader: ClassLoader) : JarFileClassSource(file, classLoader) {
    val extensions = mutableListOf<ExtensionDefinition>()

    /**
     * Create an [ExtensionDefinition] from annotation
     */
    fun createExtension(clazz: Class<*>, annotation: Extension): ExtensionDefinition {
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
                    it.optional
                )
            }.toTypedArray(),
            clazz)
    }

    suspend fun loadDependencies(dependencyDispatcher: DependencyDispatcher, repositories: Collection<Repository>) = coroutineScope {
        try {
            val config = extension.getInputStream(classLoader)
            val yamlConfig = Yaml().load<ExtensionConfig>(config)
            val proceedRepositories = repositories + yamlConfig.repositories.map { Repository(it) }
            yamlConfig.dependencies.forEach { dependency ->
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
            extensions.add(createExtension(clazz, extension))
        }
    }

    /**
     * Data class describe a configuration file for an extension
     */
    data class ExtensionConfig(val dependencies: List<String>, val repositories: List<String>)

    companion object Resources {
        val extension = Resource(ResourceSource.JAR, "extension.yml")
    }
}