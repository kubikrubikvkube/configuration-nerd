package com.github.kubikrubikvkube.configurationnerd.generation

import com.github.kubikrubikvkube.configurationnerd.GradleProject
import com.github.kubikrubikvkube.configurationnerd.exception.GenerationException
import com.github.kubikrubikvkube.configurationnerd.exception.ValidationException

abstract class Generator(val gradleProject: GradleProject) {

    /**
     * Возвращает список скриптов,
     * по которым будет производиться генерация
     */
    abstract fun scripts(): List<GenerationScript>

    /**
     * Генерировать файлы по скриптам
     */
    @Throws(GenerationException::class)
    fun generateByScripts() {
        scripts().forEach { it.generate() }
    }
}