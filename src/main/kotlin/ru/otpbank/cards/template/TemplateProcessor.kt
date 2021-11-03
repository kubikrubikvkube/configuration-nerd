package ru.otpbank.cards.template

import org.slf4j.LoggerFactory
import org.thymeleaf.TemplateEngine
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.FileTemplateResolver
import ru.otpbank.cards.model.Model
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
    fun processToString(templateFileName: String, model: Model): String {
        return templateEngine.process(templateFileName, model.context)
    }

    fun processToFile(templateFileName: String, model: Model, file: File): File {
        if (!file.exists()) {
            logger.info("Creating $file")
            file.createNewFile()
            file.setWritable(true)
            file.setReadable(true)
        } else {
           logger.warn("File ${file.absolutePath} exists. IT'S CONTENT GOING TO BE BE OVERWRITTEN!")
        }
        val textualData = processToString(templateFileName, model)
        file.appendText(textualData)
        return file.normalize()
    }

    fun processToFile(templateFileName: String, model: Model, fileName: String): File {
        if (!fileName.contains(dot) || fileName.count { it == dot } > 1) {
            throw IllegalArgumentException("Filename $fileName is invalid name for a file. Requires format like 'name.txt'")
        }
        val nameAndExtensionList = fileName.split(dot)
        val tempFile = Files.createTempFile(tmpdir, nameAndExtensionList[0], ".${nameAndExtensionList[1]}").toFile()
        return processToFile(templateFileName, model, tempFile)
    }
}