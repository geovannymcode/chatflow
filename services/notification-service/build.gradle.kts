plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    // Módulos internos
    implementation(project(":shared:domain"))
    implementation(project(":shared:events"))

    // Spring
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.mail)
    implementation(libs.spring.boot.starter.thymeleaf)
    implementation(libs.spring.boot.starter.validation)

    // Database
    runtimeOnly(libs.postgresql)

    // Firebase Admin SDK (para push notifications)
    implementation("com.google.firebase:firebase-admin:9.3.0")

    // Testing
    testImplementation(libs.bundles.testing.core)
    testImplementation(libs.bundles.testcontainers)
}