package com.github.kubikrubikvkube.configurationnerd.file

import java.io.File
import java.util.*
import kotlin.streams.toList

const val LINE_BREAK_CHARACTER = "\\"

class PropertyFile(file: File, entriesSeparator: Char = '=') {
    var properties: Map<String, String>

    init {
        val isFileExists = file.exists()
        if (!isFileExists) {
            throw IllegalArgumentException("Can't create PropertyFile from $file - it doesn't exist")
        }
        val canReadFile = file.canRead()
        if (!canReadFile) {
            throw IllegalArgumentException("Can't create PropertyFile from $file - it's not readable")
        }
        val readFileLines = file.bufferedReader()
            .lines()
            .map { it.trim() }
            .filter { !it.startsWith("#") }
            .filter { !it.startsWith("!") }
            .toList()

        val keyValueMap = mutableMapOf<String,String>()
        checkLinesCorrect(readFileLines, entriesSeparator, file)
        readFileLines.forEach {
            val splitString = it.split(entriesSeparator)
            if (splitString.size > 2) {
                throw IllegalArgumentException("Can't split $splitString using $entriesSeparator into key-value pair")
            }
            val key = splitString[0]
            val value = splitString[1]
            keyValueMap[key] = value
        }
        properties = Collections.unmodifiableMap(keyValueMap)

    }

    fun getProperty(key: String): String? {
        return properties[key]
    }

    fun hasProperty(key: String): Boolean {
        return properties[key] != null
    }

    private fun checkLinesCorrect(
        readFileLines: List<String>,
        entriesSeparator: Char,
        file: File,
    ) {
        readFileLines.forEachIndexed { lineIndex, line ->
            val isLineValid = line.contains(entriesSeparator) or line.contains(LINE_BREAK_CHARACTER)
            if (!isLineValid) {
                throw IllegalArgumentException("In ${file.absolutePath} line $lineIndex: $line is invalid for PropertyFile.")
            }
        }
    }
}