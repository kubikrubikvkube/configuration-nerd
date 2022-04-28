package com.github.kubikrubikvkube.configurationnerd.annotation

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * Mark [com.github.kubikrubikvkube.configurationnerd.validation.Validator]
 * as active validator and provide 'profile' value
 * to define the active profile of Validator
 */
@Target(CLASS)
@Retention(RUNTIME)
annotation class ValidationProfile(val profile: String)
