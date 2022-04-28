package com.github.kubikrubikvkube.configurationnerd.exception

import org.gradle.api.GradleException

/**
 * Thrown by [com.github.kubikrubikvkube.configurationnerd.generation.Generator]
 * when generation fails
 */
class GenerationException(message: String = "Generation failed") : GradleException(message)