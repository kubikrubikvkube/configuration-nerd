package com.github.kubikrubikvkube.configurationnerd.exception

/**
 * Thrown when there is a problem processing Thymeleaf template
 */
class TemplateProcessingException(message: String = "Template processing failed") : Throwable(message)