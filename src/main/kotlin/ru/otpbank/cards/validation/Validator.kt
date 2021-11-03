package ru.otpbank.cards.validation

import ru.otpbank.cards.GradleProject
import ru.otpbank.cards.exception.ValidationException

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
}