package ru.otpbank.cards.exception

import org.gradle.api.GradleException

class ValidationException(message: String = "Validation failed") : GradleException(message)