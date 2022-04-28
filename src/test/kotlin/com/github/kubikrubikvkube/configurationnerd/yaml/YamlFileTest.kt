package com.github.kubikrubikvkube.configurationnerd.yaml

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

internal class YamlFileTest {

    private val mapper: ObjectMapper by lazy {
        val module = KotlinModule.Builder()
            .withReflectionCacheSize(512)
            .configure(KotlinFeature.NullToEmptyCollection, false)
            .configure(KotlinFeature.NullToEmptyMap, false)
            .configure(KotlinFeature.NullIsSameAsDefault, false)
            .configure(KotlinFeature.StrictNullChecks, false)
            .build()

        ObjectMapper().registerModule(module)
    }

    @Test
    fun findValueByPath() {

        val json = """{
              "root": {
                "nested": {
                  "empty": "empty",
                  "key0": "value0"
                },
                "other": {
                  "key1": "value1",
                  "some": "other"
                }
              }
            }""".trimIndent()

        val readTree = mapper.readTree(json)
        val result = mapper.convertValue(readTree, object : TypeReference<Map<String, Any>>() {})

        val yamlFile = YamlFile(result)
        val key0 = yamlFile.findValueByPath("root.nested.key0")
        val key1 = yamlFile.findValueByPath("root.other.key1")
        val key2 = yamlFile.findValueByPath("root.nonexistent.key2")

        assertTrue(key0 == "value0")
        assertTrue(key1 == "value1")
        assertTrue(key2 == null)
    }
}