package com.github.kubikrubikvkube.configurationnerd.reflection

import com.github.kubikrubikvkube.configurationnerd.annotation.GenerationProfile
import com.github.kubikrubikvkube.configurationnerd.generation.Generator
import com.github.kubikrubikvkube.configurationnerd.reflection.ConfigurationHolder.configuration
import org.reflections.Reflections
import java.util.stream.Collectors

/**
 * Коллектор для нахождения нужных генераторов
 */
object GeneratorCollector {


    fun findGenerators(generationProfile: String): MutableList<Class<out Generator>> {
        return Reflections(configuration)
            .getSubTypesOf(Generator::class.java)
            .stream()
            .filter { generatorClass ->
                val isAnnotationPresent = generatorClass.isAnnotationPresent(GenerationProfile::class.java)
                if (isAnnotationPresent) {
                    val annotation = generatorClass.getAnnotation(GenerationProfile::class.java)
                    annotation.profile == generationProfile
                } else false
            }
            .collect(Collectors.toUnmodifiableList());
    }


}