// shared/events/build.gradle.kts

plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get().toInt()))
    }
}

dependencies {
    implementation(libs.bundles.jackson)
    // Si solo son DTOs de eventos, probablemente nada aquí
    testImplementation(libs.bundles.testing.core)
}
