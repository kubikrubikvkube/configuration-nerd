package com.github.kubikrubikvkube.configurationnerd.reflection

import com.github.kubikrubikvkube.configurationnerd.GradleProject
import com.github.kubikrubikvkube.configurationnerd.generation.Generator
import java.util.stream.Collectors.toUnmodifiableList

object GeneratorInitializer {

    fun initialize(
        generatorClasses: MutableList<Class<out Generator>>,
        gradleProject: GradleProject,
    ): List<Generator> {

        return generatorClasses
            .stream()
            .map { generator ->
                val constructor = generator.getDeclaredConstructor(GradleProject::class.java)
                constructor.newInstance(gradleProject)
            }
            .collect(toUnmodifiableList())
    }
}