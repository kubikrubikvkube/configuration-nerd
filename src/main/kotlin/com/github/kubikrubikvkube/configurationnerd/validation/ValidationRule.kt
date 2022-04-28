package com.github.kubikrubikvkube.configurationnerd.validation

import com.github.kubikrubikvkube.configurationnerd.exception.ValidationException

/**
 * Правило валидации
 */
fun interface ValidationRule {

    /**
     * Валидировать согласно правилу.
     * В случае провала валидации необходимо
     * выбросить исключение-наследник ValidationException
     */
    @Throws(ValidationException::class)
    fun validate()
}