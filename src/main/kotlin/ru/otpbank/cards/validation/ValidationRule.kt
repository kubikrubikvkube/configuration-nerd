package ru.otpbank.cards.validation

import ru.otpbank.cards.exception.ValidationException

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