package com.github.kubikrubikvkube.configurationnerd.exception

/**
 * Thrown when trying to add variable key to [com.github.kubikrubikvkube.configurationnerd.model.TemplateModel]
 * when it's already been present in Model
 */
class DuplicateModelKeyException(message: String = "Duplicate key in model") : Throwable(message) {
}