package com.github.kubikrubikvkube.configurationnerd.reflection

import com.github.kubikrubikvkube.configurationnerd.annotation.GenerationProfile
import com.github.kubikrubikvkube.configurationnerd.generation.Generator
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import java.util.stream.Collectors
import kotlin.streams.toList

/**
 * Коллектор для нахождения нужных генераторов
 */
object GeneratorCollector {

    private val configuration by lazy {
        val classLoadersUrls = listOf(
            ClasspathHelper.forClassLoader(),
            ClasspathHelper.forJavaClassPath(),
            ClasspathHelper.forManifest()
        ).flatMap { it.stream().toList() }

        ConfigurationBuilder()
            .setUrls(classLoadersUrls)
            .setParallel(true)
            .setScanners(Scanners.SubTypes)
    }

    fun findGenerators(generationProfile: String): MutableList<Class<out Generator>> {
        return Reflections(configuration)
            .getSubTypesOf(Generator::class.java)
            .parallelStream()
            .filter { generatorClass ->
                val isAnnotationPresent = generatorClass.isAnnotationPresent(GenerationProfile::class.java)
                if (isAnnotationPresent) {
                    val annotation = generatorClass.getAnnotation(GenerationProfile::class.java)
                    annotation.profile == generationProfile
                } else {
                    false
                }
            }
            .collect(Collectors.toUnmodifiableList());
    }


}