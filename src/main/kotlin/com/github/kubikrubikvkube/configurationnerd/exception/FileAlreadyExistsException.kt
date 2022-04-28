package com.github.kubikrubikvkube.configurationnerd.exception

/**
 * Thrown when [com.github.kubikrubikvkube.configurationnerd.generation.Generator]
 * trying to re-write already existing file
 */
class FileAlreadyExistsException(message: String = "File already exists") : Throwable(message)