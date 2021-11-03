package ru.otpbank.cards.profiles.default

import ru.otpbank.cards.GradleProject
import ru.otpbank.cards.exception.ValidationException
import ru.otpbank.cards.validation.ValidationRule
import ru.otpbank.cards.validation.Validator
import ru.otpbank.cards.yaml.YamlParser

const val valuesFile = "/deploy/chart/values.yaml"
class ValuesYamlValidator(gradleProject: GradleProject) : Validator(gradleProject) {
    override fun rules(): List<ValidationRule> {
        return listOf(
            existsRule(),
            targetPortIs8080()
        )
    }

    private fun existsRule(): ValidationRule {
        return ValidationRule {
            val exists = gradleProject.files.exists(valuesFile)
            if (!exists) {
                throw ValidationException("$valuesFile does not exists")
            }
        }
    }

    private fun targetPortIs8080(): ValidationRule {
        return ValidationRule {
            val file = gradleProject.files.getFile(valuesFile)
            val parsedFile = file?.let { YamlParser.parse(it) }
            val targetPort = parsedFile?.findValueByKey("targetPort")
            if (targetPort != null && targetPort != 8080) {
                throw ValidationException("targetPort in values.yaml should be 8080 ")
            }
        }
    }
}