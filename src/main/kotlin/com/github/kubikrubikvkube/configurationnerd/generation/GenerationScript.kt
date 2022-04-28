package com.github.kubikrubikvkube.configurationnerd.generation

import com.github.kubikrubikvkube.configurationnerd.exception.GenerationException

/**
 * Скрипт генерации
 */
fun interface GenerationScript {

    /**
     * Генерировать согласно скрипту
     * В случае провала валидации необходимо
     * выбросить исключение-наследник ValidationException
     */
    @Throws(GenerationException::class)
    fun generate()
}