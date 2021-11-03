package ru.otpbank.cards.extension

import org.gradle.api.provider.Property

abstract class ProfileExtension {
    abstract val validationProfile: Property<String>
}