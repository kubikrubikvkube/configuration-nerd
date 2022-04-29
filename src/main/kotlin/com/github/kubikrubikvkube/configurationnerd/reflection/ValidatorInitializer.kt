package com.github.kubikrubikvkube.configurationnerd.reflection

import com.github.kubikrubikvkube.configurationnerd.GradleProject
import com.github.kubikrubikvkube.configurationnerd.validation.Validator
import java.util.stream.Collectors.toUnmodifiableList

object ValidatorInitializer {

    fun initialize(
        validatorClasses: MutableList<Class<out Validator>>,
        gradleProject: GradleProject,
    ): List<Validator> {

        return validatorClasses
            .parallelStream()
            .map { validator ->
                val constructor = validator.getDeclaredConstructor(GradleProject::class.java)
                constructor.newInstance(gradleProject)
            }
            .collect(toUnmodifiableList())
    }
}