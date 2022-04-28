package com.github.kubikrubikvkube.configurationnerd.task

import com.github.kubikrubikvkube.configurationnerd.GradleProject
import com.github.kubikrubikvkube.configurationnerd.reflection.GeneratorCollector
import com.github.kubikrubikvkube.configurationnerd.reflection.GeneratorInitializer
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class GenerationTask @Inject constructor(@Internal val gradleProject: GradleProject) : DefaultTask() {

    @Input
    @JvmField
    var generationProfile: Property<String>? = null

    abstract fun getGenerationProfile(): Property<String?>

    @TaskAction
    fun execute() {
        val generationProfile = getGenerationProfile().get()
        val foundGeneratorClasses = GeneratorCollector.findGenerators(generationProfile)
        val initializedGenerators = GeneratorInitializer.initialize(foundGeneratorClasses, gradleProject)

        initializedGenerators
            .stream()
            .parallel()
            .forEach { it.generateByScripts() }
    }
}