package com.github.kubikrubikvkube.configurationnerd.model

import com.github.kubikrubikvkube.configurationnerd.exception.DuplicateModelKeyException
import com.github.kubikrubikvkube.configurationnerd.exception.ModelInconsistencyException
import org.thymeleaf.context.Context
import java.util.*

/**
 * Model for Thymeleaf template processing
 */
class TemplateModel(val context: Context = Context()) {
    private val variables = mutableMapOf<String, Any>()

    fun add(key: String, value: Any, keyOverride: Boolean = false): TemplateModel {
        if (variables.containsKey(key) && !keyOverride) {
            throw DuplicateModelKeyException("Key $key already present in model $variables")
        }

        variables[key] = value
        context.setVariable(key, value)
        return this
    }

    fun get(key: String): Any? {
        ensureModelConsistency(key)
        return variables[key]
    }

    fun remove(key: String) {
        variables.remove(key)
        context.removeVariable(key)
    }

    fun getVariables(): MutableMap<String, Any> {
        return Collections.unmodifiableMap(variables)
    }

    override fun toString(): String {
        return variables.toString()
    }

    private fun ensureModelConsistency(key: String) {
        val keysArePresent = variables.containsKey(key) && context.containsVariable(key)
        val valuesAreEqual = variables[key] == context.getVariable(key)
        if (!keysArePresent or !valuesAreEqual) {
            throw ModelInconsistencyException("TemplateModel $this and context $context are inconsistent")
        }
    }
}