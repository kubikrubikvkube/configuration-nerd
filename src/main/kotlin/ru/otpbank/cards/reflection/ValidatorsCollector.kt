package ru.otpbank.cards.reflection

import org.reflections.Reflections
import org.reflections.scanners.Scanners
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import ru.otpbank.cards.validation.Validator
import kotlin.streams.toList

/**
 * Коллектор для нахождения нужных валидаторов
 */
object ValidatorsCollector {

    private val classLoadersUrls = listOf(
        ClasspathHelper.forClassLoader(),
        ClasspathHelper.forJavaClassPath(),
        ClasspathHelper.forManifest()
    ).flatMap { it.stream().toList() }

    private val configuration: ConfigurationBuilder = ConfigurationBuilder()
        .setUrls(classLoadersUrls)
        .setParallel(true)
        .setScanners(Scanners.SubTypes)

    fun findValidators(): MutableSet<Class<out Validator>> {
        return Reflections(configuration).getSubTypesOf(Validator::class.java)
    }

    fun findValidators(packageName: String): MutableSet<Class<out Validator>> {
        return Reflections(packageName).getSubTypesOf(Validator::class.java)
    }

}