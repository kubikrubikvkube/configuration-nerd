package com.github.kubikrubikvkube.configurationnerd.validation

import com.github.kubikrubikvkube.configurationnerd.GradleProject
import com.github.kubikrubikvkube.configurationnerd.exception.ValidationException

/**
 * Валидатор, состоящий из правил валидации.
 * Каждый валидатор состоит из группы ValidationRule
 */
abstract class Validator(val gradleProject: GradleProject) {
    /**
     * Возвращает список правил,
     * по которым будет производиться валидация
     */
    abstract fun rules(): List<ValidationRule>

    /**
     * Валидировать по всем правилами валидатора
     */
    @Throws(ValidationException::class)
    fun validateByRules() {
        rules().forEach { it.validate() }
    }

    fun assertTrue(condition: () -> Boolean, errorMessage: () -> String) {
        if (!condition.invoke()) {
            throw ValidationException(errorMessage.invoke())
        }
    }

    fun assertTrue(condition: () -> Boolean, errorMessage: String) {
        if (!condition.invoke()) {
            throw ValidationException(errorMessage)
        }
    }

    fun assertTrue(condition: () -> Boolean) {
        if (!condition.invoke()) {
            throw ValidationException().fillInStackTrace()
        }
    }

    fun assertFalse(condition: () -> Boolean, errorMessage: () -> String) {
        if (condition.invoke()) {
            throw ValidationException(errorMessage.invoke())
        }
    }

    fun assertFalse(condition: () -> Boolean, errorMessage: String) {
        if (condition.invoke()) {
            throw ValidationException(errorMessage)
        }
    }

    fun assertFalse(condition: () -> Boolean) {
        if (condition.invoke()) {
            throw ValidationException()
        }
    }
}