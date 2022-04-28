package com.github.kubikrubikvkube.configurationnerd

import org.gradle.api.Project

/**
 * Проект Gradle, который находится в процессе сборки.
 * Обёртка над org.gradle.api.Project
 */
class GradleProject(val project: Project) {
    val projectDir = project.projectDir
    val properties = ProjectProperties(project)
    val files = ProjectFiles(project)

}