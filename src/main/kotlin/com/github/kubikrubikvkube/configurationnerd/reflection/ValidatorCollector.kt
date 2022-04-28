package com.github.kubikrubikvkube.configurationnerd.reflection

import com.github.kubikrubikvkube.configurationnerd.annotation.ValidationProfile
import com.github.kubikrubikvkube.configurationnerd.validation.Validator
import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import java.util.stream.Collectors
import kotlin.streams.toList

/**
 * Коллектор для нахождения нужных валидаторов
 */
object ValidatorCollector {

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

    fun findValidators(validationProfile: String): MutableList<Class<out Validator>> {
        return Reflections(configuration)
            .getSubTypesOf(Validator::class.java)
            .parallelStream()
            .filter { validatorClass ->
                println(validatorClass.canonicalName)
                val isAnnotationPresent = validatorClass.isAnnotationPresent(ValidationProfile::class.java)
                println(isAnnotationPresent)
                if (isAnnotationPresent) {
                    val annotation = validatorClass.getAnnotation(ValidationProfile::class.java)
                    println("annotation.profile ${annotation.profile} == validationProfile $validationProfile")
                    annotation.profile == validationProfile
                } else {
                    false
                }
            }
            .collect(Collectors.toUnmodifiableList());
    }


}