package ru.otpbank.cards.profiles.default

import ru.otpbank.cards.GradleProject
import ru.otpbank.cards.annotation.ValidationProfile
import ru.otpbank.cards.exception.ValidationException
import ru.otpbank.cards.validation.ValidationRule
import ru.otpbank.cards.validation.Validator

@ValidationProfile("default")
class DockerFileValidator(gradleProject: GradleProject) : Validator(gradleProject) {

    override fun rules(): List<ValidationRule> {
        val dockerValidationRule = ValidationRule {
            val isDockerFileExists = gradleProject.files.exists("Dockerfile")
            if (!isDockerFileExists) {
                throw ValidationException("'Dockerfile' should exist in ${gradleProject.projectDir}")
            }

        }

       return listOf(
           dockerValidationRule
       )
    }
}