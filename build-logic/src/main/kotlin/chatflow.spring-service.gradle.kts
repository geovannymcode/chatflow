plugins {
    id("chatflow.kotlin-common")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    id("io.spring.dependency-management")
}

val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

// Configuración de Spring Dependency Management
dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:${libs.versions.spring.boot.get()}")
        mavenBom("org.testcontainers:testcontainers-bom:${libs.versions.testcontainers.get()}")
    }
}

// Hacer clases open para proxies de Spring
allOpen {
    annotation("org.springframework.stereotype.Service")
    annotation("org.springframework.stereotype.Component")
    annotation("org.springframework.stereotype.Repository")
    annotation("org.springframework.stereotype.Controller")
    annotation("org.springframework.web.bind.annotation.RestController")
    annotation("org.springframework.context.annotation.Configuration")
    annotation("org.springframework.transaction.annotation.Transactional")
    annotation("org.springframework.cache.annotation.Cacheable")
}

// Configuración de JPA plugin (genera no-arg constructors)
noArg {
    annotation("jakarta.persistence.Entity")
    annotation("jakarta.persistence.MappedSuperclass")
    annotation("jakarta.persistence.Embeddable")
}

dependencies {
    // Kotlin reflect (necesario para Spring)
    implementation(libs.kotlin.reflect)

    // Jackson para Kotlin
    implementation(libs.bundles.jackson)

    // Spring Boot Configuration Processor
    annotationProcessor(libs.spring.boot.configuration.processor)

    // Testing
    testImplementation(libs.spring.boot.starter.test)
}

// Excluir el logger de testing que viene con Spring Boot Test
configurations.all {
    resolutionStrategy {
        exclude(group = "org.mockito", module = "mockito-core")
        exclude(group = "org.mockito", module = "mockito-junit-jupiter")
    }
}