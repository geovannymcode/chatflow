import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

// Acceso al version catalog
val libs = the<org.gradle.accessors.dm.LibrariesForLibs>()

// Configuración de Java
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(libs.versions.java.get()))
    }
}

// Configuración de Kotlin
kotlin {
    compilerOptions {
        // Target JVM 21
        jvmTarget.set(JvmTarget.JVM_21)

        // Opciones del compilador para mejor código
        freeCompilerArgs.addAll(
            "-Xjsr305=strict",           // Nullability estricta con anotaciones JSR-305
            "-Xjvm-default=all",         // Generar default methods en interfaces
            "-Xemit-jvm-type-annotations" // Emitir type annotations en bytecode
        )
    }
}

// Dependencias comunes para todos los módulos Kotlin
dependencies {
    // Kotlin stdlib
    implementation(libs.kotlin.stdlib)

    // Testing básico
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockk)
}

// Configuración de tests
tasks.withType<Test> {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }
}