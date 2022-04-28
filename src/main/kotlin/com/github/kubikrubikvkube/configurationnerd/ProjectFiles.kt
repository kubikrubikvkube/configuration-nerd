package com.github.kubikrubikvkube.configurationnerd

import com.github.kubikrubikvkube.configurationnerd.exception.FileAlreadyExistsException
import org.gradle.api.InvalidUserDataException
import org.gradle.api.PathValidation
import org.gradle.api.Project
import java.io.File

/**
 * Класс для работы с файлами проекта
 */

class ProjectFiles(private val project: Project) {
    private val separator: String = File.separator

    /**
     * Создаёт файл/директорию внутри проекта.
     * Путь должен быть указан относительно корня проекта:
     * createFile("src\java\Main.java")
     */
    fun createFile(filePath: String): File {
        val fileObject = project.file(filePath, PathValidation.NONE)
        if (fileObject.exists()) {
            throw FileAlreadyExistsException("File $filePath already exists")
        }
        val isNestedFile = filePath.contains(separator)
        if (isNestedFile) {
            val directory = project.file(filePath.substringBeforeLast("\\"))
            directory.mkdirs()

            val fileName = filePath.substringAfterLast("\\")
            val file = File(directory.absolutePath + separator + fileName)
            file.createNewFile()
            file.setReadable(true)
            file.setWritable(true)
        } else {
            File(filePath).createNewFile()
        }

        return project.file(filePath, PathValidation.FILE)
    }

    /**
     * Создаёт файл/директорию внутри проекта, если файл/директория не создана ранее.
     * Путь должен быть указан относительно корня проекта:
     * createFile("src\java\Main.java")
     */
    fun createFileIfNotExists(filePath: String): File {
        val fileObject = project.file(filePath, PathValidation.NONE)
        if (fileObject.exists()) {
            return fileObject
        }
        return createFile(filePath)
    }

    /**
     * Возвращает файл внутри проекта.
     *
     * Путь должен быть указан относительно корня проекта:
     * getFile("src\java")
     *
     */
    fun getFile(filePath: String): File? {

        return try {
            project.file(filePath, PathValidation.FILE)
        } catch (e: InvalidUserDataException) {
            null
        }
    }

    /**
     * Возвращает директорию внутри проекта.
     *
     * Путь должен быть указан относительно корня проекта:
     * getFile("src\java")
     *
     */
    fun getDirectory(filePath: String): File? {

        return try {
            project.file(filePath, PathValidation.DIRECTORY)
        } catch (e: InvalidUserDataException) {
            null
        }
    }

    /**
     * Существует ли файл по данному пути
     *
     * Путь должен быть указан относительно корня проекта:
     * exists("src\java\Main.java")
     */
    fun exists(filePath: String): Boolean {
        return getFile(filePath)?.exists() ?: false
    }
}