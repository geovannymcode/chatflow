// services/chat-service/build.gradle.kts

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
    implementation(libs.spring.boot.starter.data.redis)
    implementation(libs.spring.boot.starter.amqp)
    implementation(libs.spring.boot.starter.websocket)
    implementation(libs.spring.boot.starter.security)
    implementation(libs.spring.boot.starter.validation)

    // Database
    runtimeOnly(libs.postgresql)

    // JWT (para validar tokens)
    implementation(libs.bundles.jwt)

    // Testing
    testImplementation(libs.bundles.testing.core)
    testImplementation(libs.bundles.testcontainers)
}