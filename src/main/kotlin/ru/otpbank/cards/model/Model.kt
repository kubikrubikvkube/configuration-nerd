package ru.otpbank.cards.model

import org.thymeleaf.context.Context
import java.util.*

/**
 * Модель для процессинга темплейтов
 */
class Model {
    private val properties = mutableMapOf<String, Any>()
    val context = Context()

    fun add(key: String, value: Any): Model {
        properties[key] = value
        context.setVariable(key, value)
        return this
    }

    fun get(key: String): Any? {
        return properties[key]
    }

    fun remove(key: String) {
        properties.remove(key)
        context.removeVariable(key)
    }

    fun getProperties(): MutableMap<String, Any> {
        return Collections.unmodifiableMap(properties)
    }

    override fun toString(): String {
        return properties.toString()
    }
}