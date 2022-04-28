package com.github.kubikrubikvkube.configurationnerd.reflection

import com.github.kubikrubikvkube.configurationnerd.GradleProject
import com.github.kubikrubikvkube.configurationnerd.generation.Generator
import java.util.stream.Collectors

object GeneratorInitializer {

    fun initialize(
        generators: MutableList<Class<out Generator>>,
        gradleProject: GradleProject,
    ): MutableList<Generator> {

        return generators
            .stream()
            .map { generator ->
                val constructor = generator.getDeclaredConstructor(GradleProject::class.java)
                constructor.newInstance(gradleProject)
            }
            .collect(Collectors.toUnmodifiableList())
    }
}