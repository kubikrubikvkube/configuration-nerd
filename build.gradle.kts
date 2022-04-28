import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.21"
    id("org.gradle.kotlin.kotlin-dsl") version "2.1.6"
    `maven-publish`
}

group = "com.github.kubikrubikvkube.configurationnerd"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.yaml:snakeyaml:1.29")
    implementation("org.thymeleaf:thymeleaf:3.0.12.RELEASE")
    implementation("org.reflections:reflections:0.10.2")
    implementation("com.google.guava:guava:31.1-jre")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.4.31")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.test {
    useJUnitPlatform()
}


tasks.withType<KotlinCompile>().all {
    sourceCompatibility = "11"
    targetCompatibility = "11"

    kotlinOptions {
        jvmTarget = "11"
        apiVersion = "11"
        languageVersion = "11"
    }
}

gradlePlugin {
    plugins {
        create("nerd") {
            id = "com.github.kubikrubikvkube.configurationnerd"
            implementationClass = "com.github.kubikrubikvkube.configurationnerd.NerdPlugin"
        }
    }
}

