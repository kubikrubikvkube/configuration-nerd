package com.github.kubikrubikvkube.configurationnerd.yaml


data class SearchResult(var foundValue: Any?)

/**
 * Абстракция над YAML файлом
 */
class YamlFile(val valueMap: Map<String, Any>) {
    /**
     * Найти значение по ключу, вне зависимости от уровня вложенности
     * При совпадении ключей в структуре вида
     * config:
     *  v1:
     *      "param": true
     *  v2:
     *      "param": false
     *
     *  Будет возвращён первый найденный ключ, т.е. true
     */
    fun findValueByKey(key: String): Any? {
        if (valueMap.containsKey(key)) {
            return valueMap.getValue(key)
        }

        val searchResult = SearchResult(null)
        findNestedValueByKey(key, valueMap, searchResult)
        return searchResult.foundValue
    }

    private fun findNestedValueByKey(key: String, map: Map<*, *>, searchResult: SearchResult) {
        if (searchResult.foundValue != null) {
            return
        }

        for (entry in map) {
            if (entry.value is Map<*, *>) {
                val nestedMap = entry.value as Map<*, *>
                findNestedValueByKey(key, nestedMap, searchResult)
            } else {
                if (entry.key is String && entry.key == key) {
                    searchResult.foundValue = entry.value
                }
            }
        }
    }

    override fun toString(): String {
        return valueMap.toString()
    }


}