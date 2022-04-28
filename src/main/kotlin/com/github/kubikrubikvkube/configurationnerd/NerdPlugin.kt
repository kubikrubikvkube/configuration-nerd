package com.github.kubikrubikvkube.configurationnerd


import com.github.kubikrubikvkube.configurationnerd.task.GenerationTask
import com.github.kubikrubikvkube.configurationnerd.task.ValidationTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class NerdPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        val gradleProject = GradleProject(project)
        project.tasks.register("validationTask", ValidationTask::class.java, gradleProject)
        project.tasks.register("generationTask", GenerationTask::class.java, gradleProject)
    }

}