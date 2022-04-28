package com.github.kubikrubikvkube.configurationnerd

import org.gradle.api.Project

/**
 * Класс для хранение параметров данного проекта
 */
class ProjectProperties(project: Project) {
    val name = project.name
    val dir = project.projectDir
    val parentProject = project.parent
}