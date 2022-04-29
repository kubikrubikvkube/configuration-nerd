package com.github.kubikrubikvkube.configurationnerd.reflection

import org.reflections.Configuration
import org.reflections.scanners.Scanners.SubTypes
import org.reflections.util.ClasspathHelper
import org.reflections.util.ConfigurationBuilder
import kotlin.streams.toList

/**
 * Holds [Configuration] to be used in Reflections library
 * Provides all available classpaths for scanning
 */
object ConfigurationHolder {

    val configuration: Configuration by lazy {
        val classLoadersUrls = listOf(
            ClasspathHelper.forClassLoader(),
            ClasspathHelper.forJavaClassPath(),
            ClasspathHelper.forManifest(),
        ).flatMap { it.stream().toList() }

        ConfigurationBuilder()
            .setUrls(classLoadersUrls)
            .setParallel(true)
            .setScanners(SubTypes)
    }
}