package com.github.kubikrubikvkube.configurationnerd.exception

import org.gradle.api.GradleException

/**
 * Thrown when [com.github.kubikrubikvkube.configurationnerd.model.TemplateModel]
 * and underlying [org.thymeleaf.context.Context] are inconsistent
 */
class ModelInconsistencyException(message: String = "Model is inconsistent") : GradleException(message) {
}