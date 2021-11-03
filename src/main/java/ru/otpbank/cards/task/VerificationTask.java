package ru.otpbank.cards.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.provider.Property;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.TaskAction;

public abstract class VerificationTask extends DefaultTask {
    @Input
    Property<String> validationProfile;

    public abstract Property<String> getValidationProfile();

    @TaskAction
    public void action() {
        System.out.println("action:");
        System.out.println(getValidationProfile().get());
    }
}
