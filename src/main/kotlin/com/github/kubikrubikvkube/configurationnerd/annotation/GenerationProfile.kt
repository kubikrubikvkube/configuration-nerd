package com.github.kubikrubikvkube.configurationnerd.annotation

import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.CLASS

/**
 * Mark [com.github.kubikrubikvkube.configurationnerd.generation.Generator]
 * as active generator and provide 'profile' value
 * to define the active profile of Generator
 */
@Target(CLASS)
@Retention(RUNTIME)
annotation class GenerationProfile(val profile: String)
