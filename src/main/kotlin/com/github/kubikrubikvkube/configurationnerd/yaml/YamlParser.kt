package com.github.kubikrubikvkube.configurationnerd.yaml

import org.yaml.snakeyaml.Yaml
import java.io.File

/**
 * Парсер YAML файлов
 */
object YamlParser {
    private val parser = Yaml()

    private fun parse(filePath: String): YamlFile {
        File(filePath).inputStream().use {
            return YamlFile(parser.load(it))
        }
    }

    fun parse(file: File): YamlFile {
        return parse(file.absolutePath)
    }
}