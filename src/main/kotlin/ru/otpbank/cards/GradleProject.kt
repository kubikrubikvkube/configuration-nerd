package ru.otpbank.cards

import org.gradle.api.Project

/**
 * Проект Gradle, который находится в процессе сборки.
 * Обёртка над org.gradle.api.Project
 */
class GradleProject(project: Project) {
    val projectDir = project.projectDir
    val properties = ProjectProperties(project)
    val files = ProjectFiles(project)

}