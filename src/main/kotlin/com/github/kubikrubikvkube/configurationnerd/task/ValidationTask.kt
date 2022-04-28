package com.github.kubikrubikvkube.configurationnerd.task

import com.github.kubikrubikvkube.configurationnerd.GradleProject
import com.github.kubikrubikvkube.configurationnerd.reflection.ValidatorCollector
import com.github.kubikrubikvkube.configurationnerd.reflection.ValidatorInitializer
import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.TaskAction
import javax.inject.Inject

abstract class ValidationTask @Inject constructor(@Internal val gradleProject: GradleProject) : DefaultTask() {

    @Input
    @JvmField
    var validationProfile: Property<String>? = null

    abstract fun getValidationProfile(): Property<String?>

    @TaskAction
    fun execute() {
        val validationProfile = getValidationProfile().get()
        val foundValidatorClasses = ValidatorCollector.findValidators(validationProfile)
        val initializedValidators = ValidatorInitializer.initialize(foundValidatorClasses, gradleProject)

        initializedValidators
            .stream()
            .parallel()
            .forEach { it.validateByRules() }
    }
}