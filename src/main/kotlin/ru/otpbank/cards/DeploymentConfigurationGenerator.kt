package ru.otpbank.cards


import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.get
import ru.otpbank.cards.extension.ProfileExtension
import ru.otpbank.cards.task.VerificationTask

class DeploymentConfigurationGenerator  : Plugin<Project> {
    override fun apply(project: Project) {
        println("works")

        val create = project.extensions.create("profileExtension", ProfileExtension::class.java)
        val taskProvider: TaskProvider<VerificationTask> = project.tasks.register("verificationTask", VerificationTask::class.java)
        val profileExtension: ProfileExtension = project.extensions["profileExtension"] as ProfileExtension

//        val gradleProject = GradleProject(project)
//        ValidationExecutor(gradleProject).executeAll()
    }

}