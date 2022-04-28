package com.github.kubikrubikvkube.configurationnerd.exception

import org.gradle.api.GradleException

/**
 * Thrown by [com.github.kubikrubikvkube.configurationnerd.validation.ValidationRule]
 * when validation fails
 */
class ValidationException(message: String = "Validation failed") : GradleException(message)