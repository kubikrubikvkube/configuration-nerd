package com.github.kubikrubikvkube.configurationnerd.template

import com.github.kubikrubikvkube.configurationnerd.model.TemplateModel
import org.slf4j.LoggerFactory
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.FileTemplateResolver
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

class TemplateProcessor(private val templateDir: File) {
    private val logger = LoggerFactory.getLogger(TemplateProcessor::class.java)
    private val separator: String = File.separator
    private val tmpdir = Path.of(System.getProperty("java.io.tmpdir"))
    private val dot = '.'

    private val templateEngine by lazy {
        val templateEngine = TemplateEngine()
        val templateResolver = FileTemplateResolver()
        val directoryPath = templateDir.absolutePath + separator

        templateResolver.templateMode = TemplateMode.TEXT
        templateResolver.isCacheable = true
        templateResolver.prefix = directoryPath
        templateEngine.addTemplateResolver(templateResolver)
        templateEngine.clearTemplateCache()
        templateEngine
    }

    /**
     * Имя файла должно быть указано с учётом расширения "template.txt"
     */
    fun processToString(templateFileName: String, templateModel: TemplateModel): String {
        return templateEngine.process(templateFileName, templateModel.context)
    }

    fun processToFile(templateFileName: String, templateModel: TemplateModel, file: File): File {
        if (!file.exists()) {
            logger.info("Creating $file")
            file.createNewFile()
            file.setWritable(true)
            file.setReadable(true)
        } else {
            logger.warn("File ${file.absolutePath} exists. IT'S CONTENT IS GOING TO BE OVERWRITTEN!")
        }
        val textualData = processToString(templateFileName, templateModel)
        file.appendText(textualData)
        return file.normalize()
    }

    fun processToFile(templateFileName: String, templateModel: TemplateModel, fileName: String): File {
        if (!fileName.contains(dot) or (fileName.count { it == dot } > 1)) {
            throw IllegalArgumentException("Filename $fileName is invalid name for a file. Requires format like 'name.txt'")
        }
        val nameAndExtensionList = fileName.split(dot)
        val tempFile = Files.createTempFile(tmpdir, nameAndExtensionList[0], ".${nameAndExtensionList[1]}").toFile()
        return processToFile(templateFileName, templateModel, tempFile)
    }
}