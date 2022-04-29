package com.github.kubikrubikvkube.configurationnerd.reflection

import com.github.kubikrubikvkube.configurationnerd.annotation.ValidationProfile
import com.github.kubikrubikvkube.configurationnerd.reflection.ConfigurationHolder.configuration
import com.github.kubikrubikvkube.configurationnerd.validation.Validator
import org.reflections.Reflections
import java.util.stream.Collectors

/**
 * Коллектор для нахождения нужных валидаторов
 */
object ValidatorCollector {

    fun findValidators(validationProfile: String): MutableList<Class<out Validator>> {
        return Reflections(configuration)
            .getSubTypesOf(Validator::class.java)
            .stream()
            .filter { validatorClass ->
                val isAnnotationPresent = validatorClass.isAnnotationPresent(ValidationProfile::class.java)
                if (isAnnotationPresent) {
                    val annotation = validatorClass.getAnnotation(ValidationProfile::class.java)
                    annotation.profile == validationProfile
                } else false
            }
            .collect(Collectors.toUnmodifiableList());
    }


}