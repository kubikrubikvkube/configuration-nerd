package ru.otpbank.cards.validation

import ru.otpbank.cards.GradleProject
import ru.otpbank.cards.annotation.ValidationProfile
import ru.otpbank.cards.reflection.ValidatorsCollector


/**
 * Запускатель для валидации. Находит все валидаторы и запускает их.
 */
class ValidationExecutor(private val gradleProject: GradleProject) {

    private val validators by lazy {
        ValidatorsCollector.findValidators()
    }

    /**
     * Найти и запустить все валидаторы.
     */
    fun executeAll() {
        validators.stream()
            .filter {
                val profileAnnotation = it.getDeclaredAnnotation(ValidationProfile::class.java)
                true //фильтрация по профилям
            }
            .map { it.getDeclaredConstructor(GradleProject::class.java) }
            .map { it.newInstance(gradleProject) }
            .forEach { it.validateByRules() }

    }
}